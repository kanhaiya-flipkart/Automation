package org.flipkart.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

public class Prince {

    public static void verifyJsonArray(JSONArray actualJsonArray, JSONArray jsonArray,String base_url) {
        for (int i = 0; i < actualJsonArray.length(); i++) {
            verifyJsonObject(actualJsonArray.getJSONObject(i), jsonArray.getJSONObject(i),base_url);
        }
    }

    public static void verifyJsonObject(JSONObject actualJsonObject, JSONObject jsonObject,String base_url) {
        Iterator<String> keys = actualJsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String className = actualJsonObject.get(key).getClass().getName();
            if (className == "org.json.JSONObject") {
                verifyJsonObject(actualJsonObject.getJSONObject(key), jsonObject.getJSONObject(key),base_url);
            } else if (className == "org.json.JSONArray") {
                verifyJsonArray(actualJsonObject.getJSONArray(key), jsonObject.getJSONArray(key),base_url);
            } else {
                Assert.assertEquals(jsonObject.get(key), actualJsonObject.get(key), key + " value is not matched in " + base_url);
            }
        }
    }

    public static boolean verifyJson(String output) {
        if (output.charAt(0) == '{') return true;
        else return false;
    }

    public static void groupTesting() {
        try {
            String url = "jdbc:mysql://localhost:3306/testing";
            String userName = "root";
            String passWord = "root1234";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, passWord);
            Statement stmt = con.createStatement();
            String select = "select * from rishabh";

            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                try {
                    String base_url = rs.getString(1);
                    RestAssured.baseURI = base_url;
                    RequestSpecification request = RestAssured.given();
                    if (rs.getString(2) != null) {
                        JSONObject header = new JSONObject(rs.getString(2));
                        Iterator<String> keys = header.keys();

                        while (keys.hasNext()) {
                            String key = keys.next();
                            if (header.get(key) instanceof String) {
                                request.header(key, header.get(key));
                            }
                        }
                    }
                    if (rs.getString(3) != null) {
                        JSONObject queryParam = new JSONObject(rs.getString(2));
                        Iterator<String> keys = queryParam.keys();

                        while (keys.hasNext()) {
                            String key = keys.next();
                            if (queryParam.get(key) instanceof String) {
                                request.queryParam(key, queryParam.get(key));
                            }
                        }
                    }
                    Response response = request.get();
                    int statusCode = response.getStatusCode();
                    Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
                    String body = response.getBody().asString();
                    JSONObject jsonObject, actualJsonObject;

                    if (rs.getString(4) == null) continue;
                    try {
                        if (verifyJson(body)) {
                            jsonObject = new JSONObject(body);
                            actualJsonObject = new JSONObject(rs.getString(4));
                        } else {
                            JSONArray jsonArray = new JSONArray(body);
                            jsonObject = jsonArray.getJSONObject(0);
                            JSONArray actualJsonArray = new JSONArray(rs.getString(4));
                            actualJsonObject = actualJsonArray.getJSONObject(0);
                        }
                        verifyJsonObject(actualJsonObject, jsonObject,base_url);
                    } catch (Exception e) {
                        System.out.println("e");
                    }
                } catch (Exception e) {
                    System.out.println("e");
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("e");
        }
    }

    public static void main(String[] args) {
        groupTesting();
    }


}

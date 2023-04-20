package com.test.prince;

import com.opencsv.CSVReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;


public class RestApi {

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

//    @Test(description = "",priority = 1, groups = "get")
//    public static void Test1() {
//        RestAssured.baseURI = "http://10.24.1.214/sac/998431";
//
//        RequestSpecification request = RestAssured.given();
//        Response response = request.get();
//        String body = response.getBody().asString();
//        JSONArray jsonArray = new JSONArray(body);
//        JSONObject actualJsonObject = new JSONObject("{\n" +
//                "    \"id\": 149,\n" +
////                "    \"account_code\": null,\n" +
//                "    \"description\": \"On-line text based information such as online books, newpapers, periodicals, directories etc\",\n" +
//                "    \"group\": null,\n" +
//                "    \"service_category\": null,\n" +
//                "    \"service_code\": null,\n" +
//                "    \"bu_id\": \"FKN\",\n" +
//                "    \"sac_code\": \"998431\",\n" +
//                "    \"created_at\": \"2017-09-09T20:49:08+05:30\",\n" +
//                "    \"updated_at\": \"2017-09-09T20:49:08+05:30\"\n" +
//                "  }");
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
//        JSONObject jsonObject = jsonArray.getJSONObject(0);
//        verifyJsonObejct(actualJsonObject,jsonObject);
//    }
//
//    @Test(description = "",priority = 2, groups = "get")
//    public static void Test2() {
//        RestAssured.baseURI = "http://10.24.2.38/party_type/";
//
//        RequestSpecification request = RestAssured.given();
//        Response response = request.get();
//        String body = response.getBody().asString();
//        JSONObject jsonObject = new JSONObject(body);
//        JSONObject actualJsonObject = new JSONObject("{\n" +
//                "  \"types\": [\n" +
//                "    {\n" +
//                "      \"party_type_id\": \"organization\",\n" +
//                "      \"description\": \"Organization\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"party_type_id\": \"person\",\n" +
//                "      \"description\": \"Person\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"party_type_id\": \"testing\",\n" +
//                "      \"description\": \"Testing\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}");
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
//        verifyJsonObejct(actualJsonObject,jsonObject);
//    }
//
//    @Test(description = "",priority = 3, groups = "get")
//    public static void Test3() {
//        RestAssured.baseURI = "http://10.24.2.38/party";
//
//
//        RequestSpecification request = RestAssured.given().queryParam("external_id","ICICI");
//        Response response = request.get();
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
//        String body = response.getBody().asString();
//        JSONObject jsonObject = new JSONObject(body);
//        JSONObject actualJsonObject = new JSONObject("{\n" +
//                "  \"version_id\": 1,\n" +
//                "  \"status\": \"approved\",\n" +
//                "  \"external_id\": \"SBI\",\n" +
//                "  \"bu_id\": \"fki\",\n" +
//                "  \"is_active\": true,\n" +
//                "  \"party_type\": {\n" +
//                "    \"party_type_id\": \"organization\",\n" +
//                "    \"description\": \"Organization\"\n" +
//                "  },\n" +
//                "  \"name\": \"ICICI Bank\",\n" +
//                "  \"alternate_name\": null,\n" +
//                "  \"from_date\": \"28-05-2015 18:08:42\",\n" +
//                "  \"thru_date\": null\n" +
//                "}");
//
//        verifyJsonObejct(actualJsonObject,jsonObject);
//    }
//
//    @Test(description = "",priority = 4, groups = "get")
//    public static void Test4() {
//        RestAssured.baseURI = "http://10.24.2.38/relationship_type/creditor";
//
//        RequestSpecification request = RestAssured.given();
//        Response response = request.get();
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
//        String body = response.getBody().asString();
//        JSONObject jsonObject = new JSONObject(body);
//        JSONObject actualJsonObject = new JSONObject("{\n" +
//                "  \"relationship_type_id\": \"creditor\",\n" +
//                "  \"description\": \"Creditor Relationship\",\n" +
//                "  \"from_role_type\": {\n" +
//                "    \"role_type_id\": \"hero\",\n" +
//                "    \"description\": \"Hero\"\n" +
//                "  },\n" +
//                "  \"to_role_type\": {\n" +
//                "    \"role_type_id\": \"service_facilitator\",\n" +
//                "    \"description\": \"Service facilitator\"\n" +
//                "  }\n" +
//                "}");
//
//        verifyJsonObejct(actualJsonObject,jsonObject);
//    }
//
//    @Test(description = "",priority = 5, groups = "get")
//    public static void Test5() {
//        RestAssured.baseURI = "http://10.24.2.38/role_type";
//
//        RequestSpecification request = RestAssured.given();
//        Response response = request.get();
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
//        String body = response.getBody().asString();
//        JSONObject jsonObject = new JSONObject(body);
//        JSONObject actualJsonObject = new JSONObject("{\n" +
//                "  \"types\": [\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"external_liquidation_customer\",\n" +
//                "      \"description\": \"external_liquidation_customer\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"hero\",\n" +
//                "      \"description\": \"Hero\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"legal_organization\",\n" +
//                "      \"description\": \"legal_organization\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"refurbishment_customer\",\n" +
//                "      \"description\": \"Refurbishment Customer\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"seller\",\n" +
//                "      \"description\": \"seller\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"service_facilitator\",\n" +
//                "      \"description\": \"Service facilitator\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"service_user\",\n" +
//                "      \"description\": \"Service user\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"supplier\",\n" +
//                "      \"description\": \"supplier\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"vendor\",\n" +
//                "      \"description\": \"vendor\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"role_type_id\": \"vendor_site\",\n" +
//                "      \"description\": \"Vendor Site\"\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}");
//
//        verifyJsonObejct(actualJsonObject,jsonObject);
//    }
//    @Test(description = "",priority = 6, groups = "get")
//    public static void Test6() {
//        RestAssured.baseURI = "http://10.24.1.214/hsn/998599/digital_voucher_code/validate";
//
//        RequestSpecification request = RestAssured.given();
//        Response response = request.get();
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
//        String body = response.getBody().asString();
//
//    }

    public static boolean verifyJson(String output){
        if(output.charAt(0) == '{')return  true;
        else return  false;
    }


    @Test(groups = "prince")
    public static void groupTesting() {
        try {
            String url = "jdbc:mysql://localhost:3306/testing";
            String userName = "root";
            String passWord = "root1234";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, userName, passWord);
            Statement stmt = con.createStatement();
            String select = "select * from prince";

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


    public static void main(String []args){
        groupTesting();
    }

}

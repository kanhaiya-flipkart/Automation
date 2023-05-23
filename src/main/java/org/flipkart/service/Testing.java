package org.flipkart.service;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;
import org.flipkart.dao.Store;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.flipkart.domain.RestApiTest;
import org.flipkart.email.EmailSender;
import org.flipkart.factory.TestFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Testing {

    private void verifyJsonArray(JSONArray actualJsonArray, JSONArray jsonArray,String base_url) {
        for (int i = 0; i < actualJsonArray.length(); i++) {
            verifyJsonObject(actualJsonArray.getJSONObject(i), jsonArray.getJSONObject(i),base_url);
        }
    }

    public void verifyJsonObject( JSONObject actualJsonObject,JSONObject jsonObject,String base_url){
        Iterator<String> keys = actualJsonObject.keys();
        while(keys.hasNext()){
            String key = keys.next();
            if(actualJsonObject.get(key) instanceof JSONObject){
                verifyJsonObject(actualJsonObject.getJSONObject(key),jsonObject.getJSONObject(key),base_url);
            }
            else if(actualJsonObject.get(key) instanceof JSONArray){
                verifyJsonArray(actualJsonObject.getJSONArray(key),jsonObject.getJSONArray(key),base_url);
            }
            else{
                Assert.assertEquals(jsonObject.get(key),actualJsonObject.get(key), "[" + key+ "]" + " value is not matched in " + base_url + " --> ");
            }
        }
    }

    private boolean verifyJson(String output) {
        try{
            JSONObject jsonObject = new JSONObject(output);
            return true;
        }catch(Exception e){
            return  false;
        }
    }

    private void connectDB(){
        try{
            Store.getInstance();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    private RequestSpecification setHeaders(String headerString,RequestSpecification request){
        if (headerString != null) {
            JSONObject header = new JSONObject(headerString);
            Iterator<String> keys = header.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (header.get(key) instanceof String) {
                    request.header(key, header.get(key));
                }
            }
        }
        return  request;
    }
    private RequestSpecification setQueryParam(String queryParamString,RequestSpecification request){
        if (queryParamString != null) {
            JSONObject queryParam = new JSONObject(queryParamString);
            Iterator<String> keys = queryParam.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                if (queryParam.get(key) instanceof String) {
                    request.queryParam(key, queryParam.get(key));
                }
            }
        }
        return  request;
    }

    private Response getResponse(RestApiTest row_data){
        String base_url = row_data.getBase_url();
        RestAssured.baseURI = base_url;
        RestAssuredConfig config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 1000));
        RequestSpecification request = RestAssured.given();
        setHeaders(row_data.getHeaders(),request);
        setQueryParam(row_data.getQuery_params(),request);
        request.config(config);
        return request.get();
    }

    private void verifyOutput(String body, String output,String base_url){
        JSONObject jsonObject, actualJsonObject;
        if(!verifyJson(body)){
            jsonObject = new JSONArray(body).getJSONObject(0);
            actualJsonObject = new JSONArray(output).getJSONObject(0);
        }
        else{
            jsonObject = new JSONObject(body);
            actualJsonObject = new JSONObject(output);
        }
        verifyJsonObject(actualJsonObject,jsonObject,base_url);
    }

    private void runApiTest(RestApiTest row_data){

        Response response = getResponse(row_data);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
        String body = response.getBody().asString();

        String output = row_data.getOutput();
        if(output != null)verifyOutput(body,output,row_data.getBase_url());
    }

    @Test(dataProvider = "RestApiData",dataProviderClass = TestFactory.class)
    public void groupTesting(RestApiTest restApiTest) {
        runApiTest(restApiTest);
    }

    public static void main(String[] args) {
        XmlSuite suite = new XmlSuite();
        suite.setName("suite_1");

        List<String> listeners = new ArrayList<String>();
        listeners.add("org.flipkart.testng.CustomReporter");
        suite.setListeners(listeners);

        XmlTest test = new XmlTest(suite);
        test.setName("TmpTest");

        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass("org.flipkart.service.Testing"));
        test.setXmlClasses(classes) ;

        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);

        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();

        EmailSender emailSender = new EmailSender();
        emailSender.sendEmail("singhalkanhaiya4321@gmail.com");

    }

}

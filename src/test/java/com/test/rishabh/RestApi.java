package com.test.rishabh;

import com.mongodb.util.JSON;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.Header;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class RestApi {

    public static void verifyJsonArray(JSONArray actualJsonArray,JSONArray jsonArray){
        for(int i = 0;i < actualJsonArray.length();i++){
            verifyJsonObejct(actualJsonArray.getJSONObject(i),jsonArray.getJSONObject(i));
        }
    }

    public static void verifyJsonObejct( JSONObject actualJsonObject,JSONObject jsonObject){
        Iterator<String> keys = actualJsonObject.keys();
        while(keys.hasNext()){
            String key = keys.next();
            String className = actualJsonObject.get(key).getClass().getName();
            if(className == "org.json.JSONObject"){
                verifyJsonObejct(actualJsonObject.getJSONObject(key),jsonObject.getJSONObject(key));
            }
            else if(className == "org.json.JSONArray"){
                verifyJsonArray(actualJsonObject.getJSONArray(key),jsonObject.getJSONArray(key));
            }
            else{
                Assert.assertEquals( jsonObject.get(key),actualJsonObject.get(key),key + " value is not matched");
            }
        }
    }

    @Test(description = "",priority = 1,groups = "get.api_1")
    public static void getTest1(){
        RestAssured.baseURI = "http://10.24.2.106/financial_account/73988";
        RequestSpecification request = RestAssured.given().header("X_BU_ID","fkmp").queryParam("account_num","true").queryParam("skip_ifsc_validation","false");
        Response response = request.get();
        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);
        JSONObject actualJsonObject = new JSONObject("{\n" +
                "  \"account_number\": \"33022521785\",\n" +
                "  \"gl_account_id\": \"1\",\n" +
                "  \"reference_id\": 65538,\n" +
                "  \"from_date\": \"2014-08-07\",\n" +
                "  \"thru_date\": null,\n" +
                "  \"created_at\": \"2014-08-07T16:23:10\",\n" +
                "  \"type\": \"CustomerAccount\",\n" +
                "  \"available_balance\": \"0.0\",\n" +
//                "  \"version\": \"0\",\n" +
                "  \"actual_balance\": \"0.0\",\n" +
                "  \"beneficiary_name\": \"Abhishek Basu\",\n" +
                "  \"updated_at\": \"2014-08-07T16:23:10\",\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"id\": 73988,\n" +
                "  \"bu_id\": \"fkmp\",\n" +
                "  \"owner_party_id\": \"ACC14060848072247657\"\n" +
                "}");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
        verifyJsonObejct(actualJsonObject,jsonObject);
    }

    @Test(description = "",priority = 2,groups = "get.api_1")
    public static void getTest2(){
        RestAssured.baseURI = "http://10.24.2.106/financial_account/73989";
        RequestSpecification request = RestAssured.given().header("X_BU_ID","fkmp").queryParam("account_num","true").queryParam("skip_ifsc_validation","false");
        Response response = request.get();
        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);
        JSONObject actualJsonObject = new JSONObject("{\n" +
                "  \"type\": \"CustomerAccount\",\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"status\": \"active\",\n" +
                "  \"id\": 73989,\n" +
                "  \"financial_account_id\": 73989,\n" +
                "  \"bu_id\": \"fkmp\",\n" +
                "  \"beneficiary_name\": \"shruti dudeja\",\n" +
                "  \"reference_id\": 37492,\n" +
                "  \"account_number\": \"629701508675\",\n" +
                "  \"gl_account_id\": \"1\",\n" +
                "  \"owner_party_id\": \"ACC14050665607004272\",\n" +
                "  \"from_date\": \"2014-08-07\",\n" +
                "  \"thru_date\": null,\n" +
                "  \"actual_balance\": \"0.0\",\n" +
                "  \"available_balance\": \"0.0\",\n" +
                "  \"created_at\": \"2014-08-07T16:23:23\",\n" +
                "  \"updated_at\": \"2014-08-07T16:23:23\"\n" +
                "}");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
        verifyJsonObejct(actualJsonObject,jsonObject);
    }

    @Test(description = "",priority = 3,groups = "get.api_1")
    public static void getTest3(){
        RestAssured.baseURI = "http://10.24.2.106/financial_account/73990";
        RequestSpecification request = RestAssured.given().header("X_BU_ID","fkmp").queryParam("account_num","true").queryParam("skip_ifsc_validation","false");
        Response response = request.get();
        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);
        JSONObject actualJsonObject = new JSONObject("{\n" +
                "  \"type\": \"CustomerAccount\",\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"status\": \"active\",\n" +
                "  \"id\": 73990,\n" +
                "  \"financial_account_id\": 73990,\n" +
                "  \"bu_id\": \"fkmp\",\n" +
                "  \"beneficiary_name\": \"ashish tolani\",\n" +
                "  \"reference_id\": 35090,\n" +
                "  \"account_number\": \"000301560758\",\n" +
                "  \"gl_account_id\": \"1\",\n" +
                "  \"owner_party_id\": \"ACC14071295792525824\",\n" +
                "  \"from_date\": \"2014-08-07\",\n" +
                "  \"thru_date\": null,\n" +
                "  \"actual_balance\": \"0.0\",\n" +
                "  \"available_balance\": \"0.0\",\n" +
                "  \"created_at\": \"2014-08-07T16:23:42\",\n" +
                "  \"updated_at\": \"2014-08-07T16:23:42\"\n" +
                "}");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
        verifyJsonObejct(actualJsonObject,jsonObject);
    }
    @Test(description = "",priority = 4,groups = "get.api_1")
    public static void getTest4(){
        RestAssured.baseURI = "http://10.24.2.106/financial_account/73991";
        RequestSpecification request = RestAssured.given().header("X_BU_ID","fkmp").queryParam("account_num","true").queryParam("skip_ifsc_validation","false");
        Response response = request.get();
        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);
        JSONObject actualJsonObject = new JSONObject("{\n" +
                "  \"type\": \"CustomerAccount\",\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"status\": \"active\",\n" +
                "  \"id\": 73991,\n" +
                "  \"financial_account_id\": 73991,\n" +
                "  \"bu_id\": \"fkmp\",\n" +
                "  \"beneficiary_name\": \"p v sai kapil\",\n" +
                "  \"reference_id\": 38599,\n" +
                "  \"account_number\": \"0447104000085083\",\n" +
                "  \"gl_account_id\": \"1\",\n" +
                "  \"owner_party_id\": \"ACC14056613110824477\",\n" +
                "  \"from_date\": \"2014-08-07\",\n" +
                "  \"thru_date\": null,\n" +
                "  \"actual_balance\": \"0.0\",\n" +
                "  \"available_balance\": \"0.0\",\n" +
                "  \"created_at\": \"2014-08-07T16:24:00\",\n" +
                "  \"updated_at\": \"2014-08-07T16:24:00\"\n" +
                "}");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
        verifyJsonObejct(actualJsonObject,jsonObject);
    }
    @Test(description = "",priority = 5,groups = "get.api_1")
    public static void getTest5(){
        RestAssured.baseURI = "http://10.24.2.106/financial_account/73992";
        RequestSpecification request = RestAssured.given().header("X_BU_ID","fkmp").queryParam("account_num","true").queryParam("skip_ifsc_validation","false");
        Response response = request.get();
        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);
        JSONObject actualJsonObject = new JSONObject("{\n" +
                "  \"type\": \"CustomerAccount\",\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"status\": \"active\",\n" +
                "  \"id\": 73992,\n" +
                "  \"financial_account_id\": 73992,\n" +
                "  \"bu_id\": \"fkmp\",\n" +
                "  \"beneficiary_name\": \"MANOJ KUMAR\",\n" +
                "  \"reference_id\": 32674,\n" +
                "  \"account_number\": \"50100040059452\",\n" +
                "  \"gl_account_id\": \"1\",\n" +
                "  \"owner_party_id\": \"ACC14026949237121710\",\n" +
                "  \"from_date\": \"2014-08-07\",\n" +
                "  \"thru_date\": null,\n" +
                "  \"actual_balance\": \"0.0\",\n" +
                "  \"available_balance\": \"0.0\",\n" +
                "  \"created_at\": \"2014-08-07T16:24:08\",\n" +
                "  \"updated_at\": \"2014-08-07T16:24:08\"\n" +
                "}");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
        verifyJsonObejct(actualJsonObject,jsonObject);
    }

    @Test(description = "",priority = 6,groups = "get.api_1")
    public static void getTest6 (){

        RestAssured.baseURI = "http://10.24.2.106/financial_account/73993";
        RequestSpecification request = RestAssured.given().header("X_BU_ID","fkmp").queryParam("account_num","true").queryParam("skip_ifsc_validation","false");
        Response response = request.get();
        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);
        JSONObject actualJsonObject = new JSONObject("{\n" +
                "  \"type\": \"CustomerAccount\",\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"status\": \"active\",\n" +
                "  \"id\": 73993,\n" +
                "  \"financial_account_id\": 73993,\n" +
                "  \"bu_id\": \"fkmp\",\n" +
                "  \"beneficiary_name\": \"sagar gurav\",\n" +
                "  \"reference_id\": 16785,\n" +
                "  \"account_number\": \"60114535102\",\n" +
                "  \"gl_account_id\": \"1\",\n" +
                "  \"owner_party_id\": \"ACC14042825786971457\",\n" +
                "  \"from_date\": \"2014-08-07\",\n" +
                "  \"thru_date\": null,\n" +
                "  \"actual_balance\": \"0.0\",\n" +
                "  \"available_balance\": \"0.0\",\n" +
                "  \"created_at\": \"2014-08-07T16:24:38\",\n" +
                "  \"updated_at\": \"2014-08-07T16:24:38\"\n" +
                "}");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
        verifyJsonObejct(actualJsonObject,jsonObject);
    }

    @Test(description = "",priority = 7,groups = "get.api_2")
    public static void getTest7 (){
        RestAssured.baseURI = "http://10.24.2.106/financial_account";
        RequestSpecification request = RestAssured.given().header("X_BU_ID","fkmp").queryParam("type","CustomerAccount").queryParam("beneficiary_name"," Abhishek Basu").queryParam("reference_id","65538").queryParam("account_number","33022521785").queryParam("owner_party_id","ACC14060848072247657");
        Response response = request.get();
        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
        int total = jsonObject.getInt("total");
        int count = jsonObject.getInt("count");
        Assert.assertEquals(total,1);
        Assert.assertEquals(count,1);
    }










//    public static void main(String[] args) {
//        getTest1();
//        getTest2();
//        getTest3();
//        getTest4();
//        getTest5();
//        getTest6();
//    }
}

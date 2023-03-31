package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        int num = 16;
        Scanner sc = new Scanner(System.in);
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> keys = new ArrayList<String>(Arrays.asList("id", "type", "beneficiary_name", "reference_id", "account_number", "gl_account_id", "owner_party_id", "bu_id", "from_date", "thru_date", "currency", "actual_balance", "available_balance", "created_at", "updated_at", "version"));
        //        for (int i = 0; i < num; i++) {
//
//            keys.add(sc.next());
////            System.out.println(keys);
//
//        }
//        for(int i = 0;i < keys.size();i++){
//            System.out.print("\"" + keys.get(i) + "\"" + ",");
//        }

        ArrayList<String> values = new ArrayList<String>();

        for (int i = 0; i < num; i++) {

            values.add(sc.next());
            System.out.println(values);

        }
        for (int i = 0; i < values.size(); i++) {
            jsonObject.put(keys.get(i),values.get(i));
        }
        System.out.println(jsonObject);

//        JSONObject obj = new JSONObject();
//
//
//
//
//
//        System.out.println("enter values");
//
//        for (int i = 0; i < num; i++) {
//            values.add(sc.next());
//        }
////        System.out.println(keys);
//
////        System.out.println(values);
////         Printing stored values
//        for (int i = 0; i < num; i++) {
//            obj.put(keys.get(i), values.get(i));
////            System.out.println(keys.get(i) + " " + values.get(i));
//        }
//        System.out.println("printing stored values");
//        System.out.println(obj);
    }
//73988    CustomerAccount    AbhishekBasu    65538    33022521785    1    ACC14060848072247657    fkmp    2014-08-07    NULL    INR    0.000    0.000    2014-08-0716:23:10    2014-08-0716:23:10    0
//    id    type    beneficiary_name    reference_id    account_number    gl_account_id    owner_party_id    bu_id    from_date    thru_date    currency    actual_balance    available_balance    created_at    updated_at    version
//73988    CustomerAccount    AbhishekBasu    65538    33022521785    1    ACC14060848072247657    fkmp    2014-08-07    NULL    INR    0.000    0.000    2014-08-0716:23:10    2014-08-0716:23:10    0
//73989    CustomerAccount    shrutidudeja    37492    629701508675    1    ACC14050665607004272    fkmp    2014-08-07    NULL    INR    0.000    0.000    2014-08-07T16:23:233z    2014-08-07T16:23:233Z    0


}
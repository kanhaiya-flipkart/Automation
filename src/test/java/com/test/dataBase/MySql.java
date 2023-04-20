package com.test.dataBase;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.*;

public class MySql {
    public static void main(String args[]) {
        try{
            String url = "jdbc:mysql://localhost:3306/testing";
            String userName = "root";
            String passWord = "root1234";
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("yes");
            Connection con = DriverManager.getConnection(url,userName,passWord);
            Statement stmt=con.createStatement();
            String create_table = "create table prince(base_url TINYTEXT,headers TEXT,query_param TEXT,output MEDIUMTEXT)";
//            String insert = "insert into prince(base_url) values('http://10.24.2.38/relationship_type/creditor')";
            String delete = "delete from prince where base_url = 'http://10.24.2.38/party_type/'";

//            ResultSet rs=stmt.executeQuery(create_table);
//            while(rs.next())
//                System.out.println(rs.getString(1));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}



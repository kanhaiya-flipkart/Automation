package org.flipkart.dao;

import groovy.lang.GString;
import org.flipkart.domain.RestApiTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.*;


public class Store {
    private Properties properties = new Properties();

    private static Store store;
    private SessionFactory sessionFactoryObj;

    private Store() {
        String dbUrl;
        String dbUserName;
        String dbPassword;
        try(FileInputStream ip= new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(ip);

//           database credentials
             dbUrl = properties.getProperty("database.url");
             dbUserName = properties.getProperty("database.username");
             dbPassword = properties.getProperty("database.password");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.url", dbUrl);
        properties.setProperty("hibernate.connection.username", dbUserName);
        properties.setProperty("hibernate.connection.password", dbPassword);
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("hibernate.order_updates", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        sessionFactoryObj = buildSessionFactory();
    }

    public static Store getInstance() {
        if (store == null) {
            store = new Store();
        }
        return store;
    }

    private SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(RestApiTest.class);
        configuration.addProperties(properties);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    public void createRecord(RestApiTest restApiTest) {
        Transaction transaction = null;
        try (Session session = sessionFactoryObj.openSession()) {
            transaction = session.beginTransaction();
            session.save(restApiTest);
            System.out.println("\n.......Records Saved Successfully In The Database.......\n");

            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<RestApiTest> findAllStudentsWithJpql() {
        try (Session session = sessionFactoryObj.openSession()) {
            return session.createQuery("from RestApiTest", RestApiTest.class).getResultList();
        }
    }

    public RestApiTest findTestWithId(int testId) {
        try (Session session = sessionFactoryObj.openSession()) {
            Query query = session.createQuery("from RestApiTest where testId=:testId");//here persistent class name is Emp
            query.setParameter("testId", testId);
            List<RestApiTest> restApiTests = query.getResultList();
            return restApiTests.get(0);
        }
    }
}

package org.flipkart.dao;
import org.flipkart.domain.RestApiTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class Store {

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static  SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
//        configuration.addAnnotatedClass(Song.class);

        // Create Session Factory
        SessionFactory sessionFactory
                = configuration.buildSessionFactory();
        return sessionFactory;
    }

    public static void createRecord(RestApiTest restApiTest) {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.save(restApiTest);
            System.out.println("\n.......Records Saved Successfully In The Database.......\n");

            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public List<RestApiTest> findAllStudentsWithJpql() {
        sessionObj = buildSessionFactory().openSession();
        return session.createQuery("SELECT a FROM Student a", Student.class).getResultList();
    }

    public static List<RestApiTest> query(String sql){
        try {
            sessionObj = buildSessionFactory().openSession();
            SQLQuery  sqlQuery1 = sessionObj.createSQLQuery(sql);
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }

    }


    public static void main(String[] args) {
        RestApiTest test = new RestApiTest();
        test.setBase_url("org.hibernate.UnknownEntityTypeException: Unable to locate entity descriptor");
        test.setOutput("yes");
        test.setHeaders("sd");
        test.setQuery_params("sd");
        createRecord(test);
        String sql = "SELECT * FROM EMPLOYEE";
//        query();



    }
}

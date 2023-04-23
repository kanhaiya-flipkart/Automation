package org.flipkart.dao;
import org.flipkart.domain.RestApiTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.List;


public class Store {
    private static Store store;
    private SessionFactory sessionFactoryObj;

    private Store() {
        sessionFactoryObj = buildSessionFactory();
    }

    public static synchronized Store getInstance() {
        if(store == null) {
            store = new Store();
        }
        return store;
    }

    private SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory
                = configuration.buildSessionFactory();
        return sessionFactory;
    }

    public void createRecord(RestApiTest restApiTest) {
        Transaction transaction = null;
        try (Session session = sessionFactoryObj.openSession()){
            transaction = session.beginTransaction();
            session.save(restApiTest);
            System.out.println("\n.......Records Saved Successfully In The Database.......\n");

            session.getTransaction().commit();
        } catch(Exception e) {
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

}

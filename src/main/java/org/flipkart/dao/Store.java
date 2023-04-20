package org.flipkart.dao;

import org.flipkart.domain.RestApiTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Store {
    public static void main(String[] args) {

        //Create typesafe ServiceRegistry object
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        RestApiTest apiTest = new RestApiTest();
        apiTest.setBase_url("http://10.24.2.38/party_type/testing");
        apiTest.setOutput("{\"types\":[{\"party_type_id\":\"organization\",\"description\":\"Organization\"},{\"party_type_id\":\"person\",\"description\":\"Person\"},{\"party_type_id\":\"testing\",\"description\":\"Testing\"}]}");

        session.save(apiTest);
        t.commit();
        System.out.println("successfully saved");
        factory.close();
        session.close();

    }
}

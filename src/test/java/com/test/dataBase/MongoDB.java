package com.test.dataBase;


import com.mongodb.client.*;
import org.bson.Document;

public class MongoDB {

    public static void main( String args[] ) {

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("myMongoDb");
//        database.createCollection("customers");
        MongoCollection<Document> collection = database.getCollection("customers");
        Document document = new Document();
//        document.put("name", "Shubham");
//        document.put("company", "Baeldung");

//        collection.insertOne(document);


//        Document query = new Document();
//        query.put("name", "Shubham");
//
//        Document newDocument = new Document();
//        newDocument.put("name", "John");
//
//        Document updateObject = new Document();
//        updateObject.put("$set", newDocument);
//
//        collection.updateOne(query, updateObject);

        Document searchQuery = new Document();
        searchQuery.put("name", "Shubham");
        FindIterable<Document> cursor = collection.find(searchQuery);

        try (final MongoCursor<Document> cursorIterator = cursor.cursor()) {
            while (cursorIterator.hasNext()) {
                System.out.println(cursorIterator.next());
            }
        }
    }
}

package com.company;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Main {

    public static void main(String[] args) {
        Mongo mongoLab = new Mongo("localhost", 27017, "IgorDzierwa1");

        MongoCollection<Document> collection = mongoLab.getDb().getCollection("business");
        System.out.println("Collection business selected successfully");

        mongoLab.showCollections();

        System.out.println();
        mongoLab.exercise5a();
        System.out.println();
        mongoLab.exercise5b();
        System.out.println();
        mongoLab.exercise5c();
        System.out.println();
        mongoLab.exercise5d();
        System.out.println();
        mongoLab.exercise7();
    }
}

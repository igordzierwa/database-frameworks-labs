package com.company;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class Main {

    public static void main(String[] args) {
        Mongo mongoLab = new Mongo("localhost", 27017, "IgorDzierwa1");

        mongoLab.showCollections();

//        System.out.println();
//        mongoLab.pointA();
//        System.out.println();
//        mongoLab.pointB();
//        System.out.println();
//        mongoLab.pointC();
//        mongoLab.pointD();
//        mongoLab.pointE();
//        mongoLab.pointF();
        mongoLab.pointG();



    }
}

package com.company;

import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.util.*;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Projections.include;


public class Mongo {
    private MongoClient mongoClient;
    private MongoDatabase db;

    public Mongo(String hostname, int port, String databaseName) {
        this.mongoClient = new MongoClient(hostname , port);
        this.db = mongoClient.getDatabase(databaseName);
    }

    public MongoClient getMongoClient() {
        return this.mongoClient;
    }

    public MongoDatabase getDb() {
        return this.db;
    }

    public void showCollections() {
        for (String name : db.listCollectionNames()) {
            System.out.println("colection name: " + name);
        }
    }

    public void pointA() {
        MongoCollection<Document> collection = this.db.getCollection("business");
        List<String> results = new ArrayList<>();
        collection.distinct("city", String.class).into(results);

        Collections.sort(results);

        for (String city : results) {
            System.out.println(city);
        }
    }

    public void pointB() {
        MongoCollection<Document> collection = this.db.getCollection("review");
        List<Document> results = new ArrayList<>();
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("date", new Document("$gte", "2011-01-01"));
        collection.find(whereQuery).into(results);

        System.out.println("All reviews after 2011-01-01: " + results.size());
    }

    public void pointC() {
        MongoCollection<Document> collection = this.db.getCollection("business");
        List<Document> results = new ArrayList<>();
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("open", false);
        collection.find(whereQuery)
                .projection(Projections.include("name", "full_address", "stars"))
                .into(results);

        for (Document document : results) {
            System.out.println(document);
        }
    }

    public void pointD() {
        MongoCollection<Document> collection = this.db.getCollection("user");
        List<Document> results = new ArrayList<>();

        BasicDBObject orQuery = new BasicDBObject();
        List<BasicDBObject> orArguments = new ArrayList<>();
        orArguments.add(new BasicDBObject("votes.funny", 0));
        orArguments.add(new BasicDBObject("votes.useful", 0));
        orQuery.put("$or", orArguments);

        collection.find(orQuery).into(results);

        for (Document document : results) {
            System.out.println(document);
        }
    }

    public void pointE() {
        MongoCollection<Document> collection = this.db.getCollection("tip");

        List<Bson> results = Arrays.asList(
                Aggregates.match(Filters.and(
                        Filters.gte("date", "2012-01-01"),
                        Filters.lte("date", "2012-12-31"))),
                Aggregates.group("$business_id", Accumulators.sum("count", 1))
        );

        AggregateIterable iterable = collection.aggregate(results);

        for (Object i : iterable) {
            System.out.println(i);
        }
    }

    public void pointF() {
        MongoCollection<Document> collection = this.db.getCollection("review");

        List <Bson> results = Arrays.asList(
                Aggregates.group("$business_id", Accumulators.avg("averageStars", "$stars")),
                Aggregates.match(Filters.gte("averageStars", 4.0))
        );

        AggregateIterable iterable = collection.aggregate(results);

        for (Object i : iterable) {
            System.out.println(i);
        }
    }

    public void pointG () {
        MongoCollection<Document> collection = this.db.getCollection("business");
        collection.deleteMany(eq("stars", 2.0));
    }
}

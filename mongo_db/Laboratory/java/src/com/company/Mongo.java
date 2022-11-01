package com.company;

import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.util.*;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;


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

    public void exercise5a() {
        MongoCollection<Document> collection = db.getCollection("business");
        ArrayList<Document> results = new ArrayList<>();
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("stars", new Document("$eq", 5));
        collection.find(whereQuery).into(results);
        System.out.println("Count: " + results.size());
    }

    public void exercise5b() {
        MongoCollection<Document> collection = db.getCollection("business");
        List<Bson> results = Arrays.asList(
                Aggregates.match(Filters.eq("categories", "Restaurants")),
                Aggregates.group("$city", Accumulators.sum("count", 1)),
                Aggregates.sort(Sorts.descending("count"))
        );

        AggregateIterable iterable = collection.aggregate(results);

        for(Object i : iterable) {
            System.out.println(i);
        }
    }

    public void exercise5c() {
        MongoCollection<Document> collection = db.getCollection("business");
        List<Bson> results = Arrays.asList(
                Aggregates.match(Filters.and(
                        Filters.eq("categories", "Hotels"),
                        Filters.eq("attributes.Wi-Fi", "free"),
                        Filters.gte("stars", 4.5))),
                Aggregates.group("$state", Accumulators.sum("count", 1)));

        AggregateIterable iterable = collection.aggregate(results);

        for(Object i : iterable) {
            System.out.println(i);
        }
    }

    public void exercise5d() {
        MongoCollection<Document> collection = db.getCollection("user");
        ArrayList<Document> results = new ArrayList<>();
        Map<String, Integer> counter = new HashMap<>();
        int funnyCount = 0;
        int coolCount = 0;
        int usefulCount = 0;

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("votes", new Document("$exists", true));

        collection.find(whereQuery).into(results);

        for(Document user : results) {
            Document votes = (Document) user.get("votes");
            if(votes.getInteger("funny") > 0)
                funnyCount++;
            if(votes.getInteger("cool") > 0)
                coolCount++;
            if(votes.getInteger("useful") > 0)
                usefulCount++;
        }

        counter.put("Funny", funnyCount);
        counter.put("Cool", coolCount);
        counter.put("Useful", usefulCount);

        System.out.println(counter);
    }

    public void exercise7() {
        MongoCollection<Document> collection = db.getCollection("user");

        List<Bson> results = Arrays.asList(
                Aggregates.match(Filters.gte("average_stars", 4.5)),
                Aggregates.sort(Sorts.descending("review_count")
                ));

        AggregateIterable iterable = collection.aggregate(results);
        Document user = (Document) iterable.first();
        System.out.println("Name: " + user.getString("name") +
                "\n" +
                "Review count: " + user.getInteger("review_count") +
                "\n" +
                "Average rate: " + user.getDouble("average_stars"));
    }
}

package com.api.mongoUtil;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.api.mongoUtil.MongoUtil.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class MongoClientApi {

    private MongoDatabase db = null;

    public MongoClientApi(String ip, int port, String database, String name, String password) {
        db = getDb(ip, port, database, name, password);
    }

    public MongoClientApi(String ip, int port, String database) {
        db = getDb(ip, port, database);
    }

    public void testInsert(String collect, Document values) {
        MongoCollection dbCollection = null;
        try {
            dbCollection = db.getCollection(collect);
            dbCollection.insertOne(values);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * mapReduce
     *
     * @param collect
     */
    public MapReduceIterable mapReduce(String collect, String map, String reduce) {
        MongoCollection dbCollection = null;
        MapReduceIterable reduceIterable = null;
        try {
            dbCollection = db.getCollection(collect);
            reduceIterable = dbCollection.mapReduce(map, reduce);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return reduceIterable;
    }

    /**
     * 查询所有文档
     *
     * @return 所有文档集合
     */
    public List<Document> findAll(String collect) {
        List<Document> results = new ArrayList<Document>();
        MongoCollection dbCollection = null;
        dbCollection = db.getCollection(collect);
        FindIterable<Document> iterables = dbCollection.find();
        MongoCursor<Document> cursor = iterables.iterator();
        while (cursor.hasNext()) {
            results.add(cursor.next());
        }
        return results;
    }

    public List<Document> findPage(String collect, int page, int offerSet) {
        MongoCollection dbCollection = null;
        List<Document> result = new ArrayList<>();
        try {
            dbCollection = db.getCollection(collect);
//            FindIterable<Document> findIterable = dbCollection.find(and(gte("stars", 2),lt("stars", 5),eq("categories","Bakery")))
//                    .projection(fields(include("featureId", "eyeFlag","irisenrollTemplate","personId"),
//                            excludeId())).skip(page).limit(pageCount);
            FindIterable<Document> findIterable = dbCollection.find(and(eq("status", 2)))
                    .projection(fields(include("featureId", "eyeFlag", "irisenrollTemplate", "personId")
                    )).skip(page).limit(offerSet);
            MongoCursor<Document> cursor = findIterable.iterator();
            while (cursor.hasNext()) {
                result.add(cursor.next());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    public List<Document> findPagesTest(String collect, int offerSet, Object id, String appId) {
        MongoCollection dbCollection = null;
        List<Document> result = new ArrayList<>();
        try {
            dbCollection = db.getCollection(collect);
            FindIterable<Document> findIterable = dbCollection.find(and(eq("status", "2"), eq("appId", appId),
                    lte("_id", id)))
                    .projection(fields(include("featureId")
                    )).sort(new BasicDBObject("_id", -1)).limit(offerSet);
            MongoCursor<Document> cursor = findIterable.iterator();
            while (cursor.hasNext()) {
                result.add(cursor.next());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }


    public List<Document> findPages(String collect) {
        MongoCollection dbCollection = null;
        List<Document> result = new ArrayList<>();
        try {
            dbCollection = db.getCollection(collect);
            FindIterable<Document> findIterable = dbCollection.find().limit(10);
            MongoCursor<Document> cursor = findIterable.iterator();
            while (cursor.hasNext()) {
                result.add(cursor.next());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }


    public Object findPageID(String collect, String appId) {
        MongoCollection dbCollection = null;
        List<Document> result = new ArrayList<>();
        try {
            dbCollection = db.getCollection(collect);

            FindIterable<Document> findIt = dbCollection.find(and(eq("status", "2"), eq("appId", appId)))
                    .projection(fields(include("featureId")// TODO: djl 2018.1.8 只查询featureid
                    )).sort(new BasicDBObject("_id", -1)).limit(1);
            MongoCursor<Document> cursor = findIt.iterator();
            while (cursor.hasNext()) {
                result.add(cursor.next());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result.get(0).get("_id");
    }

    public void find(String collect) {

        MongoCollection dbCollection = null;
        try {
            dbCollection = db.getCollection(collect);
            dbCollection.find();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public int findDistinct(String collect){
        MongoCollection dbCollection = null;
        long result = 0;
        try {
            dbCollection = db.getCollection(collect);
            result = dbCollection.count();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return (int) result;
    }
    public long findCount(String collect) {

        MongoCollection dbCollection = null;
        long result = 0;
        try {
            dbCollection = db.getCollection(collect);
            result = dbCollection.count();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据appId 获取Count
     **/
    public long findAppIdCount(String collect, String id) {

        MongoCollection dbCollection = null;
        long result = 0;
        try {
            dbCollection = db.getCollection(collect);
            result = dbCollection.count(and(eq("status", "2"), eq("appId", id)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public UpdateResult updateOne(String collect, Bson filter, Bson update) {
        MongoCollection dbCollection = null;
        dbCollection = db.getCollection(collect);
        UpdateResult result = dbCollection.updateOne(filter, update);
        return result;
    }

    public UpdateResult updateOrInsertOne(String collect, Bson filter, Bson update) {
        MongoCollection dbCollection = null;
        dbCollection = db.getCollection(collect);
        UpdateResult result = dbCollection.updateOne(filter, update, new UpdateOptions().upsert(true));
        return result;
    }

    public UpdateResult updateMany(String collect, Bson filter, Bson update) {
        MongoCollection dbCollection = null;
        dbCollection = db.getCollection(collect);
        UpdateResult result = dbCollection.updateMany(filter, update);
        return result;
    }

    public UpdateResult replaceOne(String collect, Bson filter, Document replace){
        MongoCollection dbCollection = null;
        dbCollection = db.getCollection(collect);
        UpdateResult result = dbCollection.replaceOne(filter, replace);
        return result;
    }
    public List<Document> findPersion(String collect, String persionId, String eyeModel) {
        MongoCollection dbCollection = null;
        List<Document> result = new ArrayList<>();
        try {
            dbCollection = db.getCollection(collect);

            FindIterable<Document> findIterable = dbCollection.find(and(eq("personId", persionId)))
                    .projection(fields(include( "irisenrollTemplate")
                    ));
            MongoCursor<Document> cursor = findIterable.iterator();
            while (cursor.hasNext()) {
                result.add(cursor.next());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
        }
        return result;
    }
}

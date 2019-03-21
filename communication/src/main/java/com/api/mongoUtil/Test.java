package com.api.mongoUtil;

import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.List;

public class Test {



    public static void main(String[] asd) {
        long t1 = System.currentTimeMillis();
        MongoClientApi mongoClientApi1 = new MongoClientApi(
                "127.0.0.1", 27017, "test" );

        for (int i = 0; i < 5000; i++) {

            List<Document> l = mongoClientApi1.findPages("user");
//            System.out.println(l.size());
//            List<Document> l = mongoClientApi1.findPersion("irisFeature",
//                    "SYbhXQBId2XM5SnVAfPfYg9qybfYw8UN","1");
//            Document document = new Document();
//            document.put("gender", "male");
//            document.put("name","hahah");
//            mongoClientApi1.testInsert("test",document);
              System.out.println(l.size());

//            updataOrInsert(mongoClientApi1);
        }

        System.out.println("-----" + (System.currentTimeMillis() - t1));

    }

    private static void updataOrInsert(MongoClientApi mongoClientApi1) {
        Document filter = new Document();
        filter.append("gender", "male");

//            filter.append("gender", "zhang");

        //注意：更新文档时，不需要使用"$set"
        Document update = new Document();
        update.append("$set", new Document("gende5r", "yushuai"));
        UpdateResult result = mongoClientApi1.updateOrInsertOne("test",filter,update);
        System.out.println("matched count = " + result.getMatchedCount());
    }

    private static UpdateResult getUpdateResult(MongoClientApi mongoClientApi1) {
        Document filter = new Document();
        filter.append("gender", "male");

        //注意update文档里要包含"$set"字段
        Document update = new Document();
        update.append("$set", new Document("gender", "yushuai"));
        return mongoClientApi1.updateMany("test",filter,update);
    }

    private static void updataone(MongoClientApi mongoClientApi1) {
        Document filter = new Document();
        filter.append("gender", "male");

        //注意update文档里要包含"$set"字段
        Document update = new Document();
        update.append("$set", new Document("gender", "female"));
        UpdateResult countTest1 = mongoClientApi1.updateOne("test",filter,update);
        System.out.println(countTest1.toString());
    }
}

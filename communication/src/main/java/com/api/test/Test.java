package com.api.test;

import com.alibaba.fastjson.JSON;
import com.api.mongoUtil.MongoClientApi;
import com.irisking.open.common.dto.authenticate.AuthenParam;
import entry.test.People;
import entry.test.User;
import org.bson.Document;
import org.bson.types.Binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static com.api.httpUtil.postMain.UtilTool.ObtainPost;

public class Test {

    public static final int count = 2;

    public static long t1 ;

    public static final ConcurrentLinkedQueue<Long> listLong = new ConcurrentLinkedQueue<>();
    private static CountDownLatch latch = new CountDownLatch(count);
    public static void main(String[] asd) throws BrokenBarrierException, InterruptedException {

        String jk =  "count* = count* + (memory1[myBiOne[s+//]] + memory2[myBiOne[s + //]]);";




        int a = 512;
        for (int y=0;y<19;y++){
            int b = a * y;
            for (int i = 0;i<4; i++){
                int c = i == 0 ? i : i*2;
                int d = i == 0 ? i*2+1 : i*2 + 1;

                String s = "count"+ y + " = count" + y +" + (memory" + (c+1) + "[myBiOne[s + "
                + (b  + c) +   "]+toPlus] + memory" +(d + 1 ) + "[myBiOne[s + " + (d +b) + "]]);";
                System.out.println(s);
             }



            jk = jk.replace("*","y");

        }
//        MongoClientApi mongoClientApi1 = new MongoClientApi("10.2.1.224", 27017, "iop_test");
//
//        t1 = System.currentTimeMillis();
//        System.out.println(t1);
//        for (int k=0;k<count;k++){
//            new Thread() {
//                @Override
//                public void run() {
//                    for (int i=0;i<1;i++){
//                        TestAuth(mongoClientApi1);
//                    }
//                };
//            }.start();
//
//        }
//        latch.await();

//        System.out.println(System.currentTimeMillis()-t1);

//        for (Long l:listLong) {
//            if (l>1000L){
//                System.out.println(l);
//            }
//        }

    }

    private static void TestAuth(MongoClientApi mongoClientApi1) {
        Binary binary= null;
        String url = "http://10.2.1.227:26000/authFeathure";

        AuthenParam authenParam = new AuthenParam();
        List<String> list = new ArrayList();
        list.add("KlFrqpWVhoKFVyp63cVEEFHq6rfE3SunpzAyhJ8IYF1vfZGC6m8/v+1qejeVlYfG63sVPc7Ok90Fpro6OWhczKr798ekMAUBQfeWaT+q6H7nlZWnauqSEQVdmrAoK9eeIN+vGcDO9+e/UXnqxpUpAgB9f6q4/+5ARc/q0hUza+6UURGsfe/u6iO32giFxPtTcTXEijG9+v/v/q4EARGVkRU9KGjq6t2VJ2/6syUFFZAQaCPeQHLO1RF1RQVc5Ob/eXkICO7vr1XXRV07I6fYEAfNemB17zoQgu5vN7XMSnqVlZDjzut7eMy/PYOCMm3MhNd7WKzm2zu01IzoQd+aeFK37+XOAhv9lTcywq63FSABcypd1crus7OVjup6fU1Ik8Jq/7en2FAFBVW6fxU91d/Hpu7uewNvb3nfwmJgYHVVxfa3PGzsTEyVz+9rRHIxdRUF7B43AKObiMR33dGEzNJYGErGjb+3/1m6jMWNAEAAenr6+v/tTU3N1ZaWggBAUFF9/++v7vK7l72VxT/Zs+aiunt7daTo/Pz0YDgYGN2XsruvSkvbQ1HT8/r4UW1NzZTay42PIVP9eXt3he0EhOwyB3B57a4yV1GThMQ/O8TvWFv7rIe32sgtPcCDv21l2KgvcNnuppXO4olVc6rdlQAgO/stqbtZ+niwVBb2hIy8jD0EHlyRkMrs7Ob21e0FBVFR8PjkaPiqqu/9/TmTsva0lXkx4GrslYUAOfvu7hXEXSum51gSh994YPfvOJCCbn2XnMxqepWVocbK+3tMzL8NgnZzrYyUhAU9OwgdeVr67q6V3MxtPzeXlTkTkur/+9D+pp0VhoVRGRLm1VUJe/rq6/cVeXo6l0ZRWGi/v/rKTcyGk7c1FYDIaFl5apWVhqKGN3l435UEABF66vuX1X/vr7o4godCQEVNXVVTKv/dRAARuur7x915L6e2MgKFmkhd7314kMJqf7ev7np7FRWVh8/7euz+ffnrMv/vAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACIBkBDTP8AVA==");
        list.add("KnnqopW1ooIXM2r9lYQEEZnq+heVkU6HtjAghpsoTc19eNLCav9//ulpO5QVFcbLezg9rqb68j/tzNBTa27dqKr/14UkIAFBWZaCeT/qSH+vlZUva9KQBU1XurphCdamaPWFGaL+5+eUUBmqtpUIgDV/4uJ7/8QMRer6lTBr6sYVERB9f+7CAH/+1r8VJHJgiR+czgqX+v/+rqQAQRWUERW5SGpqSpW9b+vyswUVFdhwSVuKKvLFvTGdJTXw7uYxEUhIAG+vh1UXBRxqL7YwMM3feGzf6xKShGp/tQyMODKXFWHPynswBM1/a6feXwFItN9zQiD33yAg/H9WgE33aGqK1xfLF9dMIuozTr0fAAq/5dWVzOa6+/3NxpI3eWjVwmp6v6eOUAAFBbe6l6WTGz/t7+77U6PvedmXImIgZBUVpra3LGzsQEW37u9Aw3Jx9f2tOP+9KAmbSSW9O3uKwjo4EEjlh4/33VhYBI2FQAA6+vr6/+9tTc3cnJrKQEBAUVE9f++moqC/lX09vZUGhOza+up/pKz+XFemYPhY0dWWuz+vSMvLQdH3+/hQsymNj96bDfePYD0V8Zv/B0yE5hB4VnD5ryZ6UZGEhP876MR7MJn+pqf72Cg3tMCTvW1I0K8vUNWml52qgls1aOiVhSBw2+qgn1ZcRIfVLz8rS29HZ2+WFBQQyuzspqaeDSUlEVFY+LToeOimqf/1XDWy8rQUFTn5oOjclYQgW7vq7xUF2EqvsDABz19ozd/6MIKCaj8VyMg5N5YVZ87KezSMz3/vppZDAVjWhT09OQEZegr+7qWV7GwtN7eRlRkz+oi/+9L/plVd6s1VE4LPVQ1pe/rq/xdVeWp/PGRRSK+/+8rI7IyGt7c1AIBIaFlpopeXpqK3OXh7l4UEABHq6r+XlSWnrzo4goZITd3deVc6+t3FBAQR6Oqzh5VID4eyMoCXKCh973nezOpvf3/taDq3FBXVz8t7MOh7O+/+94coAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACJBkBCS/8AUg==");
        list.add("KFnqopWVgoKXM2pdlYQEEZrq+weVUE+nsjCAhxooXe95XcrKav9/7eh4MhQVV8TP+3h5aOa6ujcnjtxRe2rdjOr734cmAAABUfamKX3qSm7vlRWva1iSAUXXmrohRf6CcP/FWcqX568cWUnmt5UAAH1/4vK/r0BNBer6kgBr6sQRES1/f+/OAlv37p11QGJqBy/0WCg9+v//rqwAAVWUkR05aGrqyhW3r2v6swUVldgwAOuCInqH1VGNRVWcrupxWUhIAKan1RkXhHhqr7QwA8//KH3f6hCGpmJ3NQzsOhKVldPOz/syDOV7u8fOW0lExPtXBSDPuxJm9/UIAt2HSED9nbtjhlUkIKATxLd9QkqfncXVHYbi+//t7rIyXUj8l0J6/7evUAAFBf+6vZ/3t7+v7+7+E6Pve1mX4mJgJH2VpzY3LGxtRUCH725EYnJ5Nf3kUPcVIvpR4+qf0TGqwlIQcAnn77f1NTEVjMXFAAB6+vr6/399fV38/JqCQEBAUVl9/+eujuLXtZ3fExDuAqz6+up7FKzuXNS3KHgY2d+Xuj2vyMnbgRG3+/pSMQnNj56bCf+JYH81+1OHT8bc5jISFnD57yZ6URGAhP8/eMRJOFP7pof7+EAvPYCTvy9J0O8vUd2mtdWigl91aOiVhGA5u+p199PyAEQXTZt5KQdvJ2eWlBwQ0Oj8ru7+hQUFEVFQ+PjkaHiirf/9dTHy0vS0VFCpqGjdhYBgO7vqdwWUWGunsDADz3po3f9yFIaqYTUUjMkyF5WVx87LcziM53u7lg5DIAyShQ09OQAVeTi67q6V/GwtPz8VsTkT2oj/u5L/5peVgpUVUUqfVU1pe/vq/xdXeWp/dGRReCy3/9rK7cSGt789AIBoSFFrrpWXoqK3PXh5lYQAEFDq6reVlSevvjgwh4ZITV19UVdqeh2FBBARquqThZVKD6awEoeXKCxte3nczup/f2/vajo3FRXFzf96aOx7e7+//78EAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACIBkBCSv8AVQ==");

        String persionId = "23gs4hMTZ0AZvxgYmGjdlCbTgABpyRDo";
        long t2 = System.currentTimeMillis();
        List<Document> documentList = mongoClientApi1.findPersion("irisFeature",persionId,"1");
//        System.out.println("****************************"+(System.currentTimeMillis()-t2));
        List<byte[]> listByte = new ArrayList();
        for (Document document : documentList) {
            binary = (Binary) document.get("irisenrollTemplate");
            listByte.add(binary.getData());
        }
        authenParam.setList(list);
        authenParam.setListByte(listByte);

        long t3 = System.currentTimeMillis();
//
//        System.out.println("*******************************************************");
//        System.out.println(JSON.toJSONString(authenParam));
//        System.out.println("*******************************************************");

        Map map =  ObtainPost(authenParam,url,Map.class);
        listLong.add(System.currentTimeMillis()-t1);
//        System.out.println("////////////////////////////////////"+(System.currentTimeMillis()-t3));
        latch.countDown();
//        System.out.println(map);
    }
}

package com.api.postgresql.mybitis;

import com.api.mysqlUtil.mybitis.MybitisClientApi;
import com.api.mysqlUtil.util.UtilTool;
import com.api.postgresql.entry.IrisTest;
import com.api.postgresql.entry.Test;
import com.api.postgresql.entry.Testyu;

import java.util.ArrayList;
import java.util.List;


public class PostGresTest {

    public static void main(String[] args) {

        MybitisClientApi session = new MybitisClientApi(
                "10.2.1.246",5432,"micro_cloud","postgres","ok", UtilTool.POSTGRES);

//        List<Test> testUser = session.selectList
//                    ("select * from test",Test.class);
//
//        System.out.println("---"+testUser.get(0).toString());
        List<Testyu> t = null;
        long t1 = System.currentTimeMillis();
        Testyu testyu = new Testyu();
        byte[] bbb = {1,5,3,4,6,7,-4};
        testyu.setFeature(bbb);
        testyu.setName("java");
        session.insert("INSERT into testyu(name, feature) values(#{name},#{feature})",testyu);

        for(int i=0;i<1;i++){
            t=session.selectList("select * from testyu",Testyu.class);
        }
        System.out.println((System.currentTimeMillis()-t1)+"-----"+t.get(8).toString());

        byte[] sd = t.get(8).getFeature();
        System.out.println(sd);


//        String fea = "6ubVlYeHMXh4aAE1lMfPF29oUBESP//fXP6/l3/96+suIlDNx0AgNKzu+1kXJm3d8qJszJcVeTv+zAEB8v7sRZaU6mIRXd+3tzFIz91Qq6+nFFDI+qODJiydvc+mqs6/EbJiSMyFVTF7/k0BArL+7EUVt7cQAEBVlZ2mIuru/z2Vh6b/2UQmv5dV/OqLkRRmbL2pyNPW5rrZWVNNjsKx00HBwMrLPh+isxCpJCHF9/v5TCQVUYAip5fHaDAUpysIXVXV15c3L29s7Pp5Xc3ielHVyMT++3FB/P/+7FjV16NqWV1VVbemqqoAEZe3t/9/ff3//7elBQBIyIKKqzeVXaSsvPjZy29NzMJraYXXW8NrT0wETv/7S0GXpq44fHVlilrzLW1YE6eu1Vxse4KAPX93wICffmEBEIbO92t5SG0IwSrA4OyE/TW01MTc3MzspaWlrT2l7Xl6zs5tbzdTWgW3/M/PMWpoEZWGrv3dBCK/B3X46oMVFi78uahK17amxtVdePqubTv70siE/X81ETKC29Pa+Dg6tpcVETERemp9fRKS9v/dFRGEpqf/elhI6u63l5e3FVhoSEARlIaHt2hIETDUz4c3amgREZY//91c/r9X//7v25NHRzU9qMneAAAAAKr/VWL1+pRodbEVJAiiUhIzGKHxcQAASkf/QFI=";
//        List<Object> list = new ArrayList<>();
//        for (int i=1467418;i<10000000;i++){
//            int flag = (i%2==0)?1:2;
//            IrisTest user = new IrisTest(i,fea,101,flag);
//            list.add(user);
//            if (i%1000==0){
//                session.insertMany("INSERT into iristest values(#{id},#{feature},#{appid},#{flag} )",list);
//                list.clear();
//            }
//
//        }

    }
}

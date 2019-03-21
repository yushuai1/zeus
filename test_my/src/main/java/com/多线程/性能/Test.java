package com.多线程.性能;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] sd){

        String tt="10:37/10:25/9:54/9:10/10:35/10:26/8:10/8:38/10:32/8:36/10:43/10:27/10:32/9:02/8:18/9:46/10:31/10:30/9:35/10:30/8:26";
        String[] hj = tt.split("/");
        int M =0;
        int K = 0;
        for (String fg:hj){
            M = M+Integer.parseInt(fg.split(":")[0]);
            K = K+Integer.parseInt(fg.split(":")[1]);
        }
        System.out.println(M+"--"+K+"--"+hj.length);

//        System.out.println("start");
//        long time1 = System.currentTimeMillis();
//        Thread thread = new Thread(){
//            @Override
//            public void run(){
//                System.out.println("Thread time ="+(System.currentTimeMillis()-time1));
//            }
//        };
//        thread.start();
//        for (int i=0;i<200;i++){
//            int m=1;
//            Thread thread1 = new Thread(){
//                @Override
//                public void run(){
//                    while (true){
//                        if (m%5555 ==0){
//                            System.out.println("--");
//                        }
//
//                    }
//
//                }
//            };
//            thread1.start();
//        }

//        Thread thread2 = new Thread(){
//            @Override
//            public void run(){
//                System.out.println("Thread time ="+(System.currentTimeMillis()-time1));
//            }
//        };
//        thread2.start();


//        System.out.println("Thread time over ="+(System.currentTimeMillis()-time1));
//        List<int[]> kl = new ArrayList<>();
//        for (int i=0;i<1;i++) {
//            int[] b = new int[1024*1024*512];
//            for (int m =0;m<1024*1024*512;m++){
////                b[m]=1;
//            }
//            kl.add(b);
//        }
//
//        for (int[] b:kl){
//            System.out.println(b.length);
//        }

//        int[] b = new int[706740220];
//
//        Test gson = new Test();
//        Test gson1 = new Test();
//        List<Test> gsonList = new ArrayList<>();
//        gsonList.add(gson);
//        gsonList.add(gson1);
//        int[] d = new int[100];
//        System.out.println(gsonList.size());
//        System.out.println(b.length+"---"+d.length);
    }
}

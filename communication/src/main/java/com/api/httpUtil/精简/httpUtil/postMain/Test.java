package com.api.httpUtil.精简.httpUtil.postMain;

import org.springframework.http.HttpMethod;

public class Test {

    public static void main(String[] as) {
//        String numString = "1";
//        System.out.println(Integer.parseInt(numString));
//        postniu();
//        postTest();
        getTest();
//        postUse();
//        postUseUsage();
    }

    private static void getTest() {

        ExcePostMain excePostMain = new ExcePostMainImpl();
        String re1=null;
        while(true){
            PostMainEntry postMainEntry1 = new PostMainEntry
                    (HttpMethod.GET,
                            "http://10.3.1.213:8825/test1");
             re1 = excePostMain.getResult(postMainEntry1, String.class);
        }

//        System.out.println(re1);

    }



    }



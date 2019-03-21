package com.api.httpUtil.精简.httpUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author yushuai
 * http通用接口
 */
public class httpClient {

    /**
     * 说明大于一个参数全用post
     * post通用接口传入对象返回对象
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T ObtainPost(Object object, String url, Class<T> responseType) {

        RestTemplate pp =new RestTemplate();

        ResponseEntity postForEntity = pp.postForEntity(url,object,responseType);

        return (T)postForEntity.getBody();
    }

    /**
     * 说明：单个参数查询删除用
     * get通用接口传入对象返回对象
     * @param <T>
     * @return T
     */
    public static <T> T ObtainGet(String url, Class<T> responseType) {

        RestTemplate pp =new RestTemplate();

        ResponseEntity postForEntity = pp.getForEntity(url,responseType);

        return (T)postForEntity.getBody();
    }


    /**
     * 说明：例子
     * @param args
     */
    public static void main(String[] args){
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
//                new HttpComponentsClientHttpRequestFactory(HttpClientUtils.getTLSHttpClient());
//        RestTemplate pp =new RestTemplate(clientHttpRequestFactory);
//        ResponseEntity postForEntity=null;
//        long t1 = System.currentTimeMillis();
//        for (int i=0;i<10000;i++){
//            postForEntity = pp.getForEntity("https://localhost:1997/test/sada45",String.class);
//        }
//        System.out.println("time = "+(System.currentTimeMillis()-t1)+"   *****"+postForEntity.getBody());



        RestTemplate pp =new RestTemplate();
        ResponseEntity postForEntity=null;
        long t1 = System.currentTimeMillis();
        for (int i=0;i<100000;i++){

            postForEntity = pp.getForEntity("http://127.0.0.1:8010/user/1",String.class);
        }
        System.out.println("time = "+(System.currentTimeMillis()-t1)+"   *****"+postForEntity.getBody());



//        String url = "http://127.0.0.1:1989/testPost";
//        User user = new User("yansuo",25);
//        People people =  ObtainPost(user,url,People.class);
//        System.out.println(people.toString());
//
//        String url1 = "http://127.0.0.1:1989/testGet/555";
//        People people1 =  ObtainGet(url1,People.class);
//        System.out.println(people1.toString());
    }
}

package com.esClient.api;


import java.io.IOException;
import java.util.Collections;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Before;
import org.junit.Test;
import com.esClient.api.entry.TestEs;
/**
 * Elasticserach RestClient示例
 *
 * @author fendo
 */
public class Rest {

    private static RestClient restClient;


    public void getRestClient() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "changeme"));

        restClient = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).build();

    }

    @Before
    public void getRest() {
        restClient = RestClient.builder(new HttpHost("192.168.159.128", 9200, "http")).build();
    }


    /**
     * 查看api信息
     *
     * @throws Exception
     */
    @Test
    public void CatApi() throws Exception {
        String method = "GET";
        String endpoint = "/_cat";
        Response response = restClient.performRequest(method, endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建索引
     *
     * @throws Exception
     */
    @Test
    public void CreateIndex() throws Exception {
        String method = "PUT";
        String endpoint = "/test-index";
        Response response = restClient.performRequest(method, endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建文档
     *
     * @throws Exception
     */
    @Test
    public void CreateDocument() throws Exception {

        for (int c= 0;c<10000;c++){
            String method = "PUT";
            String endpoint = "/test-index/test/"+c;
            HttpEntity entity = new NStringEntity(
                    "{\n" +
                            "    \"user\" : \"kimchy12\",\n" +
                            "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
                            "    \"message\" : \"trying out Elasticsearch\"\n" +
                            "}", ContentType.APPLICATION_JSON);

            Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);
            System.out.println(EntityUtils.toString(response.getEntity()));
        }

    }

    /**
     * 获取文档
     *
     * @throws Exception
     */
    @Test
    public void getDocument() throws Exception {
        String method = "GET";
        String endpoint = "/test-index/test/1";
        Response response = restClient.performRequest(method, endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    /**
     * 查询所有数据
     *
     * @throws Exception
     */
    @Test
    public void QueryAll() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 根据ID获取
     *
     * @throws Exception
     */
    @Test
    public void QueryByField() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"user\": \"kimchy\"\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);

        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 更新数据
     *
     * @throws Exception
     */
    @Test
    public void UpdateByScript() throws Exception {
        String method = "POST";
        String endpoint = "/test-index/test/1/_update";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"doc\": {\n" +
                "    \"user\":\"大美女\"\n" +
                "	}\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    @Test
    public void GeoBoundingBox() throws IOException {
        String method = "POST";
        String endpoint = "/attractions/restaurant/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  },\n" +
                "  \"post_filter\": {\n" +
                "    \"geo_bounding_box\": {\n" +
                "      \"location\": {\n" +
                "        \"top_left\": {\n" +
                "          \"lat\": 39.990481,\n" +
                "          \"lon\": 116.277144\n" +
                "        },\n" +
                "        \"bottom_right\": {\n" +
                "          \"lat\": 39.927323,\n" +
                "          \"lon\": 116.405638\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }



    /**
     * 压力测试创建文档
     *
     * @throws Exception
     */
    @Test
    public void CreateDocumentTest() throws Exception {

        for (int c= 80000;c<100000;c++){
            String method = "PUT";
            String endpoint = "/test-yushaui/static/"+c;
            TestEs test = new TestEs();
            test.setAge(c);
            test.setCount(c*2);
            test.setUrl("http//www.baidu.com/"+getRandomString(5)+"/"+getRandomString(6));
            test.setUserName(String.valueOf(getRandomChar())+String.valueOf(getRandomChar())+String.valueOf(getRandomChar()));

            HttpEntity entity = new NStringEntity(JSON.toJSONString(test), ContentType.APPLICATION_JSON);

            Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);
            System.out.println(EntityUtils.toString(response.getEntity()));
        }

    }

    public static char getRandomChar() {
        return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
    }

    //方法1：length为产生的位数
    public static String getRandomString(int length){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<length; ++i){
            //产生0-61的数字
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

}

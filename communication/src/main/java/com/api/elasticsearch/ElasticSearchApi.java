package com.api.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.api.elasticsearch.entry.EntryTest;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.action.search.SearchRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.api.elasticsearch.UtillTool.openCluster;
import static com.api.elasticsearch.UtillTool.openNoCluster;

public class ElasticSearchApi {

    private RestClient restClient;

    public ElasticSearchApi(String ip) {
        this.restClient = UtillTool.getRestEs(ip);
    }

    /**
     * 查看api信息
     */
    public String getInfoApi() throws Exception {
        String method = "GET";
        String endpoint = "/_cat";
        Response response = restClient.performRequest(method, endpoint);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 创建索引
     */
    public String CreateIndex(String index) throws Exception {
        String method = "PUT";
//        String endpoint = "/test-index";
        Response response = restClient.performRequest(method, index);
        return EntityUtils.toString(response.getEntity());
//        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 创建文档
     */
    public String CreateDocument(Object o, String index, String type, String id) throws Exception {


        String method = "PUT";
//            String endpoint = "/test-index/test/"+c;
        String endpoint = String.format("/%s/%s/%s", index, type, id);
        HttpEntity entity = new NStringEntity(JSON.toJSONString(o), ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest
                (method, endpoint, Collections.<String, String>emptyMap(), entity);
        return EntityUtils.toString(response.getEntity());
//            System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 获取文档
     */
    public <T> T getDocument(String index, String type, String id, Class<T> tClass) throws Exception {
        T t = null;
        String method = "GET";
        String endpoint = String.format("/%s/%s/%s", index, type, id);
        Response response = restClient.performRequest(method, endpoint);
        String result = EntityUtils.toString(response.getEntity());
        t = JSON.parseObject(result, tClass);
        return t;
//        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 查询所有数据
     */
    public <T> List<T> QueryAll(String index, String type, Class<T> tClass) throws Exception {

        List<T> list = null;
        String method = "POST";
        String endpoint = String.format("/%s/%s/_search", index, type);
//        String endpoint = "/test-index/test/_search";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);
        list = JSON.parseArray(EntityUtils.toString(response.getEntity()), tClass);
        return list;
//        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    /**
     * 根据ID获取
     */
    public <T> T QueryByID(String index, String type, String id, Class<T> tClass) throws Exception {

        T t = null;

        String method = "POST";
        String endpoint = String.format("/%s/%s/%s", index, type, id);
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"query\": {\n" +
                "    \"match_all\": {}\n" +
                "  }\n" +
                "}", ContentType.APPLICATION_JSON);

        Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);
        t = JSON.parseObject(EntityUtils.toString(response.getEntity()), tClass);
        return t;
//        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 更新数据
     */
    public String UpdateByScript(String index, String type, String id) throws Exception {
        String method = "POST";
        String endpoint = String.format("/%s/%s%s_update", index, type, id);
//        String endpoint = "/test-index/test/1/_update";
        HttpEntity entity = new NStringEntity("{\n" +
                "  \"doc\": {\n" +
                "    \"user\":\"大美女\"\n" +
                "	}\n" +
                "}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), entity);
        return EntityUtils.toString(response.getEntity());
//        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 根据条件
     */
    public void QueryByField(String index, String type, Map<String, String> map) throws Exception {
        GetResponse response = openCluster().prepareGet("twitter", "tweet", "1").get();
    }


    public static void main(String[] args) throws Exception {
        ElasticSearchApi elasticSearchApi = new ElasticSearchApi("192.168.159.128");

//        EntryTest entryTest = new EntryTest();
//        entryTest.setCountNumber(1000110);
//        entryTest.setIdString("我是牛人wppaisai");
//        entryTest.setName("YUSHUAI");
//        String result =elasticSearchApi.CreateDocument
//                (entryTest,"myindex","mytype","shuai");
//        System.out.println(result);

//        EntryTest entryTest1 = elasticSearchApi.getDocument
//                ("myindex", "mytype", "shuai", EntryTest.class);
//
//        System.out.println(entryTest1.toString());

        Map<String,String> map = new HashMap<>();
//        map.put("idString","我是牛人wppaisai");
        map.put("name","YUSHUAI");
//        elasticSearchApi.QueryByField("myindex", "mytype",map);

        GetResponse response = openCluster().prepareGet("myindex", "mytype", "shuai").get();
        System.out.println(response.getSourceAsString());
    }

}

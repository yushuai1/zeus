package com.transport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.StringUtil;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.transport.UtillTool.getEsClient;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

public class TransPortApi {

    private static TransportClient client = null;

    public TransPortApi(String host, String cuserName) {
        client = getEsClient(host, cuserName);
    }


    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 10:50
     * @描述 :  新加文档
     * @参数 :
     * @返回 :
     */
    public long addDocument(String index, String type, String id, Object... object) {

        // 创建文档,相当于往表里面insert一行数据

        IndexResponse response = client.prepareIndex(index, type, id).setSource(object).get();
        return response.getVersion();
    }

    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 11:31
     * @描述 :  批量添加
     * @参数 :
     * @返回 :
     */
    public boolean addDocuments(List<Map<Object, Object>> list, String index, String type) {
        try {

            BulkRequestBuilder bulkRequest = client.prepareBulk();

            for (Map<Object, Object> map : list) {
                //遍历map所有field,构造插入对象
                XContentBuilder xb = XContentFactory.jsonBuilder().startObject();
                for (Object key : map.keySet()) {
                    xb.field((String) key, map.get(key));
                }
                xb.endObject();
                //id尽量为物理表的主键
                bulkRequest.add(client.prepareIndex(index, type, map.get("id").toString().trim()).setSource(xb));
            }
            BulkResponse bulkResponse = bulkRequest.get();

            if (bulkResponse.hasFailures()) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 11:04
     * @描述 :  删除文档
     * @参数 :
     * @返回 :
     */
    public int delDocument(String index, String type, String id) {

        DeleteResponse response = client.prepareDelete(index, type, id).get();

        return response.status().getStatus();
    }


    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 15:30
     * @描述 :  update更新
     * @参数 :
     * @返回 :
     */
    public int updateDocument(String index, String type, String id, String key, String value) {

        UpdateRequest updateRequest = new UpdateRequest(index, type, id);
        try {
            updateRequest.doc(
                    XContentFactory.jsonBuilder().startObject().field(key, value).endObject());
        } catch (Exception e) {
            e.printStackTrace();
        }

        UpdateResponse updateResponse = client.update(updateRequest).actionGet();

        return updateResponse.status().getStatus();


    }


    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 9:57
     * @描述 :  根据id获取结果返回对象
     * @参数 :
     * @返回 :
     */
    private <T> T getResultByID(String index, String type, String id, Class<T> tClass) {

        T t = null;
        GetResponse response = client.prepareGet("myindex", "mytype", "shuai")
                .execute().actionGet();
        // 输出结果
        String r = response.getSourceAsString();
        t = JSON.parseObject(r, tClass);
        System.out.println(response.getSourceAsString());

        return t;
    }

    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 9:57
     * @描述 :  根据id获取结果返回map
     * @参数 :
     * @返回 :
     */
    public Map getResultByIDMap(String index, String type, String id) {

        GetResponse response = client.prepareGet(index, type, id)
                .execute().actionGet();
        return response.getSourceAsMap();
    }

    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 11:29
     * @描述 : 批量查找
     * @参数 :
     * @返回 :
     */
    public List<Map<String, Object>> getDocuments(String index, String type, String... id1) {
        try {

            MultiGetResponse multiGetItemResponses = client.prepareMultiGet().add(index, type, id1).get();
            List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
            for (MultiGetItemResponse itemResponse : multiGetItemResponses) {
                GetResponse response = itemResponse.getResponse();
                if (response.isExists()) {
                    lists.add(response.getSource());
                }
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 11:57
     * @描述 :  查找  flag 1:精确 2：至少一个   match:true精准匹配  false全文检索
     * @参数 :
     * @返回 :
     */
    public List<Map<String, Object>> getResultByFiled
    (String index, String type, Map<String, Object> map, Map<String, Object> map1) {

        List<Map<String, Object>> list = new ArrayList<>();

        BoolQueryBuilder bb = QueryBuilders.boolQuery();
        if (map != null) {
            for (Object key : map.keySet()) {
                Object o = map.get(key);
                if (o instanceof String) {
                    bb = bb.should(QueryBuilders.matchQuery((String) key, o.toString()));
                }
                if (o instanceof Integer) {
                    bb = bb.should(QueryBuilders.matchQuery((String) key, Integer.parseInt(o.toString())));
                }

            }
        }
        BoolQueryBuilder bb1 = QueryBuilders.boolQuery();
        bb1.must(bb);
        if (map1 != null) {
            for (Object key : map1.keySet()) {
                Object o = map1.get(key);
//                term是代表完全匹配，即不进行分词器分析，文档中必须包含整个搜索的词汇
                if (o instanceof String) {
                    bb1 = bb1.must(QueryBuilders.termQuery((String) key, o.toString()));
                }
                if (o instanceof Integer) {
                    bb1 = bb1.must(QueryBuilders.termQuery((String) key, Integer.parseInt(o.toString())));
                }
            }
        }

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type).
                setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(bb1);
        SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHits hits = response.getHits();

        for (SearchHit hit : hits) {
            list.add(hit.getSourceAsMap());
        }

        return list;
    }


    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 11:38
     * @描述 :  精确查找
     * @参数 :  fields  要高亮的字段  rangeLists  范围 参数比如价格   key为   field,from,to
     * @返回 :
     */
    public static List<Map<String, Object>> queryDocuments
    (String index, String type, int from, int size, List<Map<Object, Object>> rangeLists,
     Map<Object, Object> queryMaps, Map<Object, Object> sortMaps, List<String> fields,
     Map<Object, Object> fullTextQueryMaps) {
        try {

            List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
            /** 下面这一段是构造bool嵌套，就是构造一个在满足精确查找的条件下，
             * 再去进行多字段的或者关系的全文检索
             构造全文或关系的查询

             Bool查询中的关键字有：
             must
             所有的语句都 必须（must） 匹配，与 AND 等价。
             must_not
             所有的语句都 不能（must not） 匹配，与 NOT 等价。
             should
             至少有一个语句要匹配，与 OR 等价。
             **/
            BoolQueryBuilder bb = QueryBuilders.boolQuery();
            if (fullTextQueryMaps != null) {
                for (Object key : fullTextQueryMaps.keySet()) {
                    bb = bb.should(QueryBuilders.matchQuery((String) key, fullTextQueryMaps.get(key)));
                }
            }

            //构造精确的并且查询
            BoolQueryBuilder bb1 = QueryBuilders.boolQuery();
            if (queryMaps != null) {
                bb1 = bb1.must(bb);
                for (Object key : queryMaps.keySet()) {
                    bb1 = bb1.must(QueryBuilders.termQuery((String) key, queryMaps.get(key)));
                }
            }
            /** 上面这一段是构造bool嵌套，就是构造一个在满足精确查找的条件下，
             * 再去进行多字段的或者关系的全文检索 **/
            //match全文检索，但是并且的关系， 或者的关系要用
            /*MatchQueryBuilder tq = null;
            if (queryMaps != null) {
                for (Object key : queryMaps.keySet()) {
                    tq = QueryBuilders.matchQuery((String) key, queryMaps.get(key));
                }
            }*/

            //term是代表完全匹配，即不进行分词器分析，文档中必须包含整个搜索的词汇
            /*  TermQueryBuilder tq = null;
                if (queryMaps != null) {
                    for (Object key : queryMaps.keySet()) {
                        tq = QueryBuilders.termQuery((String) key, queryMaps.get(key));
                    }
                }*/

            //构造范围查询参数
            QueryBuilder qb = null;
            if (rangeLists != null && rangeLists.size() > 0) {

                for (Map<Object, Object> map : rangeLists) {
                    if (map != null && (!map.isEmpty())) {
                        qb = QueryBuilders.rangeQuery(map.get("field").toString().trim()).
                                from(map.get("from").toString().trim()).to(map.get("to").toString().trim());
                    }
                }
            }
            //构造排序参数
            SortBuilder sortBuilder = null;
            if (sortMaps != null) {
                for (Object key : sortMaps.keySet()) {
                    sortBuilder = SortBuilders.fieldSort((String) key).
                            order(sortMaps.get(key).toString().trim().equals("ASC") ?
                                    SortOrder.ASC : SortOrder.DESC);
                }
            }

            //构造查询
            SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type).
                    setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(bb1) // Query
                    .setPostFilter(qb) // Filter
                    .setFrom(from).setSize(size).addSort(sortBuilder).setExplain(true);
            //构造高亮字段
//            if (fields != null && fields.size() > 0) {
//                for (String field : fields) {
//                    searchRequestBuilder.addHighlightedField(field);
//                }
//                searchRequestBuilder.setHighlighterEncoder("UTF-8").
//                        setHighlighterPreTags("<span style=\"color:red\">").
//                        setHighlighterPostTags("</span>");
//            }

            //查询
            SearchResponse response = searchRequestBuilder.execute().actionGet();

            //取值
            SearchHits hits = response.getHits();
            for (SearchHit hit : hits) {

//                Map<String, HighlightField> result = hit.highlightFields();
//
//                //用高亮字段替换搜索字段
//                for (String field : fields) {
//                    HighlightField titleField = result.get(field);
//                    if (titleField == null) {
//                        continue;
//                    }
//                    Text[] titleTexts = titleField.fragments();
//                    String value = "";
//                    for (Text text : titleTexts) {
//
//                        value += text;
//                    }
//                    hit.getSource().put(field, value);
//                }
                lists.add(hit.getSourceAsMap());
                //System.out.println(hit.getSource());
                //System.out.println(hit.getHighlightFields());
                //System.out.println(hit.getSourceAsString());//json格式

            }
            return lists;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }


    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 10:00
     * @描述 :  判断索引是否存在
     * @参数 :
     * @返回 :
     */
    public boolean isIndexExist(String index) {
        return client.admin().indices().prepareExists(index).execute().actionGet().isExists();
    }

    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 10:00
     * @描述 :  删除索引
     * @参数 :
     * @返回 :
     */
    public boolean deleteIndex(String index) {
        return isIndexExist(index)
                ? client.admin().indices().prepareDelete(index).execute().actionGet().isAcknowledged()
                : false;
    }

    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 10:01
     * @描述 :  新增索引
     * @参数 :
     * @返回 :
     */
    public boolean addIndex(String index) {
        return isIndexExist(index)
                ? false
                : client.admin().indices().prepareCreate(index).execute().actionGet().isAcknowledged();
    }

    /**
     * @创建人 : 于帅
     * @创建时间 :  2018/7/18 10:01
     * @描述 :  判断类型是否存在
     * @参数 :
     * @返回 :
     */
    public boolean isTypeExist(String index, String type) {
        return isIndexExist(index)
                ? client.admin().indices().prepareTypesExists(index).setTypes(type).execute().actionGet().isExists()
                : false;
    }


}

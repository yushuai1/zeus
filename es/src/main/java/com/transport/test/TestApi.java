package com.transport.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.transport.TransPortApi;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

import static com.transport.test.utill.getString;

public class TestApi {

    private static final String index = "yuindex";
    private static final String type = "yutype";
    public static void main(String[] args) throws Exception {
        TransPortApi transPortApi = new TransPortApi("es","yushuai");

        //添加对象
//        testAdd(transPortApi);
        //批量添加
//        testAdds(transPortApi);
        //删除数据
//        delete(transPortApi);
        //更新数据
//        update(transPortApi);
        //query for map
//        queryForMap(transPortApi);
        //queryMany
//        queryMany(transPortApi);
        //精准查找
        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        map2.put("name","于");
        map1.put("count",53609620);
        List<Map<String,Object>> list = transPortApi.getResultByFiled(index,type,map1,map2);
        System.out.println(list.size());
    }

    private static void queryMany(TransPortApi transPortApi) {
        List<Map<String, Object>> list = transPortApi.getDocuments(index,type,"0","1","2","3");
        System.out.println(list.size());
    }

    private static void queryForMap(TransPortApi transPortApi) {
        Map map = transPortApi.getResultByIDMap(index,type,"0");
        System.out.println(map.toString());
    }

    private static void update(TransPortApi transPortApi) {
        int r = transPortApi.updateDocument(index,type,"0","name","于帅帅");
        System.out.println(r);
    }

    private static void delete(TransPortApi transPortApi) {
        int r = transPortApi.delDocument(index,type,"my1");
        System.out.println(r);
    }

    private static void testAdds(TransPortApi transPortApi) {
        List<Map<Object, Object>> list = new ArrayList<>();


        for (int i = 0;i<10;i++){
            Map<Object, Object> map = new HashMap<>();
            map.put("id",UUID.randomUUID().toString().replace("-",""));
            map.put("name",getString());
            map.put("count",new Random().nextInt());
            list.add(map);
        }
        boolean f = transPortApi.addDocuments(list,index,type);
        System.out.println(f);
    }

    private static void testAdd(TransPortApi transPortApi) {
        EntryTest entryTest = new EntryTest();

        Object[] asd ={"nihao","buhao","age",12};

        long r = transPortApi.addDocument(index,type,"my1",asd);

        System.out.println(r);
    }


    public static Map convertBean(Object bean) throws Exception {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

}

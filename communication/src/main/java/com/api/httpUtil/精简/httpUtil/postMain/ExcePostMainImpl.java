package com.api.httpUtil.精简.httpUtil.postMain;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ExcePostMainImpl extends ExcePostMain {


    @Override
    HttpHeaders auth(PostMainEntry postMainEntry) {


        ////////////////////////////////////////////////////////////////
        HttpHeaders headers1 = new HttpHeaders();

        List<String> cookieList = new ArrayList<String>();


        cookieList.add("HttpOnly=true");
        cookieList.add("Referer:http://sports.sina.com.cn/slamdunk/live.shtml?id=2018121113");

        headers1.put(HttpHeaders.COOKIE,cookieList); //将cookie放入header
       //    headers.setContentType(MediaType.APPLICATION_JSON); //post表单 ，如果是个json则设置为MediaType.APPLICATION_JSON
        return headers1;


        ////////////////////////////////////////////////////////////////////

//
//        if (StringUtils.isEmpty(postMainEntry.getAuthName())) {
//            return null;
//        }
//        if (StringUtils.isEmpty(postMainEntry.getPassWord())) {
//            return null;
//        }
//        /**
//         * 向header中加入用户名和密码
//         */
//        String authNamePassWord = postMainEntry.getAuthName() + ":" + postMainEntry.getPassWord();
//
//        HttpHeaders headers = new HttpHeaders();
//        try {
//            String encodings = DatatypeConverter.printBase64Binary
//                    (authNamePassWord.getBytes("UTF-8"));
//            headers.add("Authorization", "Basic " + encodings);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return headers;
    }


    @Override
    MultiValueMap addTable(PostMainEntry postMainEntry) {
        if (postMainEntry.getParams()==null||postMainEntry.getParams().size()==0){
            return null;
        }
       return postMainEntry.getParams();
    }

    @Override
    <T> T postJson(PostMainEntry postMainEntry, HttpHeaders httpHeaders, RestTemplate restTemplate, Class<T> responseType) {

        if (httpHeaders==null)
        {
            httpHeaders = new HttpHeaders();
        }
        String requestJson = JSON.toJSONString(postMainEntry.getObject());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
        ResponseEntity resp = restTemplate.postForEntity(postMainEntry.getUrl(),entity,responseType);
        return (T)resp.getBody();
    }

}

package com.api.httpUtil.postMain;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

abstract class ExcePostMain {

    public <T> T getResult(PostMainEntry postMainEntry,Class<T> responseType){


        //加入表单
        MultiValueMap multiValueMap = addTable(postMainEntry);

        //加入判定认证服务
        HttpHeaders headers = auth(postMainEntry);


        RestTemplate restTemplate = new RestTemplate();

        if (postMainEntry.getObject()!=null){
            return postJson(postMainEntry,headers,restTemplate,responseType);
        }

        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(multiValueMap, headers);
        ResponseEntity response = restTemplate.exchange(postMainEntry.getUrl(),
                postMainEntry.getRequestMethod(), requestEntity, responseType);

        return (T) response.getBody();
    }


    abstract HttpHeaders auth(PostMainEntry postMainEntry);

    abstract MultiValueMap addTable(PostMainEntry postMainEntry);

    abstract <T> T postJson(PostMainEntry postMainEntry,HttpHeaders httpHeaders, RestTemplate restTemplate,Class<T> responseType);


}

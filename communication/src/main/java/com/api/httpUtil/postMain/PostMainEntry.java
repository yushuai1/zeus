package com.api.httpUtil.postMain;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class PostMainEntry {

    /**
     * GET POST PUT DELETE
     */
    private HttpMethod requestMethod;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 请求用户名密码
     */
    private String authName;
    private String passWord;

    /**
     * 表单
     */
    private MultiValueMap params ;

    /**
     * Object对象
     */
    private Object object;


    public PostMainEntry(HttpMethod requestMethod, String url) {
        this.requestMethod = requestMethod;
        this.url = url;
    }

    public HttpMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(HttpMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public MultiValueMap getParams() {
        return params;
    }

    public void setParams(MultiValueMap params) {
        this.params = params;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

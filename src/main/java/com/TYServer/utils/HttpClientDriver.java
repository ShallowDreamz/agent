package com.TYServer.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhanghuibin on 2017/8/22.
 *
 */
public class HttpClientDriver {
    private static HttpResponse response;
    private static HttpEntity entity;
    static String result="";
    public static CloseableHttpClient httpClient = HttpClientUtil.createSSLClientDefault();

    //接口以post方式请求，参数：url、body(map)、header
    public static String httpPost(String url, Map<String, Object> httpbody, HashMap<String, String> header) throws UnsupportedEncodingException {
        StringEntity entity;
        HttpPost post = new HttpPost(url);
        if (!header.isEmpty() || null != header) {
            Set<?> entrySet = header.entrySet();
            for (Iterator<?> itor = entrySet.iterator(); itor.hasNext();) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) itor.next();
                post.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        String jsonBody = null;
        if(!httpbody.isEmpty()){
            jsonBody = JsonUtil.MapToJson(httpbody);
            entity = new StringEntity(jsonBody,"utf-8");
        }else{
            entity = new StringEntity("{,}");
        }
        post.setEntity(entity);
        result = executeRequest(post);
        return result;
    }

    //接口以post方式请求，参数：url、body(string)、header
    public static String httpPost(String url, String str, HashMap<String,String> header) throws UnsupportedEncodingException{
        StringEntity entity;
        HttpPost post = new HttpPost(url);
        if (!header.isEmpty() || null != header) {
            Set<?> entrySet = header.entrySet();
            for (Iterator<?> itor = entrySet.iterator(); itor.hasNext();) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) itor.next();
                post.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }
        }

        if(!str.isEmpty()){
            entity = new StringEntity(str,"utf-8");
        }else{
            entity = new StringEntity("{,}");
        }
        post.setEntity(entity);
        result = executeRequest(post);
        return result;
    }

    //接口以get方式请求，参数：url、token（方法内处理了header），已经弃用这个方法
    @Deprecated
    public static String httpGet(String url,String token){
        HttpGet get = new HttpGet(url);
        String result = null;
        get.setHeader("token",token);
        //get.setHeader("Content-Type","application/json");
        get.setHeader("version","1.0");
        get.setHeader("filter-key","filter-header");
        get.setHeader("deviceType","android");
        get.setHeader("deviceModel","le1");
        result = executeRequest(get);
        return result;
    }

    //接口以get方式请求，参数：url、header，没有params
    public static String httpGet(String url,HashMap<String, String> header){
        HttpGet get = new HttpGet(url);
        String result = null;
        if (!header.isEmpty() || null != header) {
            Set<?> entrySet = header.entrySet();
            for (Iterator<?> itor = entrySet.iterator(); itor.hasNext();) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) itor.next();
                get.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        result = executeRequest(get);
        return result;
    }

    //接口以get方式请求，参数：url、header，有params
    public static String httpGetOnParams(String url, String token, Map<String,String> map){
        URI uri = null;
        try {
            uri = getURI(url,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpGet get = new HttpGet(uri);
        HttpResponse httpResponse;
        HttpEntity entity;
        String result = null;
        get.setHeader("token",token);
        //get.setHeader("Content-Type","application/json");
        get.setHeader("version","1.0");
        get.setHeader("filter-key","filter-header");
        get.setHeader("deviceType","android");
        get.setHeader("deviceModel","le1");

        result = executeRequest(get);
        return result;
    }

    //不需要登录的post请求
    public static String httpPostNoLogin(String url, Map<String, Object> httpbody, HashMap<String, String> header) throws UnsupportedEncodingException {
        StringEntity entity;
        HttpPost post = new HttpPost(url);
        post.setHeader("x-fs-ei","54811");
        post.setHeader("x-fs-userInfo","1000");
        //get.setHeader("x-fs-peer-name","OpenAPI");
        post.setHeader("Content-Type","application/json");
        if (!header.isEmpty() || null != header) {
            Set<?> entrySet = header.entrySet();
            for (Iterator<?> itor = entrySet.iterator(); itor.hasNext();) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) itor.next();
                post.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }
        }

        String jsonBody = JsonUtil.MapToJson(httpbody);
        entity = new StringEntity(jsonBody,"utf-8");
        post.setEntity(entity);
        String result = executeRequest(post);
        return result;
    }

    private static URI getURI(String httpUrl, Map<String, String> map) throws Exception {
        String hostPort = httpUrl;
        String schema = "http";
        if (httpUrl.startsWith("http://")) {
            hostPort = httpUrl.substring("http://".length());
        } else if (httpUrl.startsWith("https://")) {
            hostPort = httpUrl.substring("https://".length());
            schema = "https";
        }
        String path = "/";
        int index = hostPort.indexOf("/");
        if (-1 != index) {
            path = hostPort.substring(index);
            hostPort = hostPort.substring(0, index);
        }
        String[] hostPortSplit = hostPort.split(":");
        String host = hostPort;
        int port = 80;
        if (2 == hostPortSplit.length) {
            host = hostPortSplit[0];
            port = Integer.parseInt(hostPortSplit[1]);
        }

        URIBuilder builder = new URIBuilder();
        if(schema.equals("http")){
            builder.setScheme(schema).setHost(host).setPort(port).setPath(path);
        }else if (schema.equals("https")){
            builder.setScheme(schema).setHost(host).setPath(path);
        }else{
            builder.setScheme(schema).setHost(host).setPort(port).setPath(path);
        }
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.addParameter(entry.getKey(), entry.getValue());
            }
        }
        URI uri = builder.build();
        return uri;
    }

    //执行post或get的接口请求，并返回接口请求的结果
    private static String executeRequest(HttpUriRequest req){
        String result = null;
        HttpResponse httpResponse;
        HttpEntity entity;
        try{
            httpResponse = httpClient.execute(req);
            System.out.println("接口请求返回状态码：" +httpResponse.getStatusLine());
            entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}

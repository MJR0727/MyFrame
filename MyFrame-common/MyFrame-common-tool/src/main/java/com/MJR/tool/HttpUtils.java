package com.MJR.tool;


import javafx.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Mjr
 * @version 1.0
 * @description: Http连接工具
 * @date 2023/2/19 22:43
 */
public class HttpUtils {

    static CloseableHttpClient httpClient;

    //进行http连接的配置
    static{
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();


        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(500);
        connectionManager.setDefaultMaxPerRoute(500);
        connectionManager.setDefaultSocketConfig(
            SocketConfig.custom()
                    .setSoTimeout(15, TimeUnit.SECONDS)
                    .setTcpNoDelay(true)
                    .build()
        );
        connectionManager.setValidateAfterInactivity(TimeValue.ofSeconds(10));

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Timeout.ofSeconds(1))
                .setConnectionRequestTimeout(Timeout.ofSeconds(1))
                .setResponseTimeout(Timeout.ofSeconds(1))
                .build();

        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .disableAutomaticRetries()
                .build();
    }

    public static String post(String url, List<Pair<String,String>> requestParam, Map<String,String> requestHeaderList) throws Exception {
        String postUrl = createUrl(url, requestParam);
        final HttpPost httpPost = new HttpPost(postUrl);
        if(Objects.isNull(requestHeaderList)&&requestHeaderList.size()!=0){
            requestHeaderList.forEach((key,value)->httpPost.addHeader(key,value));
        }
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(httpPost);
            String rspMsg = EntityUtils.toString(response.getEntity());
            return rspMsg;
        }finally {
            if(response!=null){
                //消费掉，避免浪费内存。
                EntityUtils.consume(response.getEntity());
            }
        }

    }

    private static String createUrl(String baseUrl,List<Pair<String,String>> requestParam){
        if(StringUtils.isBlank(baseUrl)){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(baseUrl);
        if(CollectionUtils.isEmpty(requestParam)||requestParam==null){
            return stringBuilder.toString();
        }
        stringBuilder.append("?");
        for(Pair pair: requestParam){
            String key = (String) pair.getKey();
            String value = (String) pair.getValue();
            stringBuilder.append(key).append("=").append(value);
        }
        String url = stringBuilder.toString();
        return url;
    }
}

package com.zsyc.framework.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpHeaders;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.nio.charset.StandardCharsets;

/**
 * Created by lcs on 2019-05-10.
 */
@Slf4j
public class HttpUtil {
    /**
     * http get
     *
     * @param url
     * @return
     */
    public static JSONObject httpGet(String url) {
        String bodyAsString = "";

        try {
            bodyAsString = Request.Get(url).execute().returnContent().asString(StandardCharsets.UTF_8);
            JSONObject jsonObject = JSON.parseObject(bodyAsString);
            return jsonObject;
        } catch (Exception e) {
            log.error("httpGet", e);
            throw new RuntimeException(e);
        } finally {
            log.info("http get {},{}", url, bodyAsString);
        }
    }

    /**
     * http post
     * @param url
     * @return
     */
    public static JSONObject httpPost(String url, String data) {
        String bodyAsString = "";
        try {
            bodyAsString = Request.Post(url)
                    .addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
                    .body(new StringEntity(data, Consts.UTF_8))
                    .execute().returnContent()
                    .asString(Consts.UTF_8);
            JSONObject jsonObject = JSON.parseObject(bodyAsString);
            return jsonObject;
        } catch (Exception e) {
            log.error("httpPost", e);
            throw new RuntimeException(e);
        } finally {
            log.info("http post {},\nparam {},\nresponse {}", url, data, bodyAsString);
        }
    }
}

package com.zsyc.zt.b2b.config;

import com.alibaba.fastjson.JSONObject;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.framework.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.zsyc.zt.b2b.config.WechatConfig.WECHAT_API_HOST;

/**
 * Created by lcs on 2019-05-10.
 */
@Component
@Slf4j
public class WechatAccessToken {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 获取分销端的access_token
     *
     * @return
     */
    public String getWechatPartnerAccessToken() {
        final String redisKey = String.format("wechat:access_token:%s", this.wechatConfig.getZhongTian().getAppId());
        String accessToken = this.stringRedisTemplate.opsForValue().get(redisKey);

        if (accessToken != null) {
            log.debug("get access token from cache ");
            return accessToken;
        }

        String url = String.format("%s/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
                WECHAT_API_HOST,
                this.wechatConfig.getZhongTian().getAppId(),
                this.wechatConfig.getZhongTian().getAppSecret());

        JSONObject jsonObject = HttpUtil.httpGet(url);

        AssertExt.notNull(jsonObject.getString("access_token"), jsonObject.toJSONString());

        this.stringRedisTemplate.opsForValue().set(redisKey, jsonObject.getString("access_token"), jsonObject.getLong("expires_in") - 60L, TimeUnit.SECONDS);
        return jsonObject.getString("access_token");
    }
}

package com.zsyc.pay.abc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lcs on 2020/10/15.
 */


@Configuration
@ConfigurationProperties("zsyc.pay.abc")
@Data
public class AbcPayProperties {
    /**
     * 支付回调
     */
    private String payCallback;

    /**
     *  微信小程序 appid
     */
    private String appId;

}

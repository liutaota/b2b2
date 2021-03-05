package com.zsyc.invoice.baiwang.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lcs on 2020/10/9.
 */
@Configuration
@ConfigurationProperties("zsyc.invoice.baiwang")
@Data
public class BaiWangProperties {
    /**
     * api 接口地址
     */
    private String api;

    /**
     * 电子发票接口地址
     */
    private String invoiceApi;

    /**
     * 销售方纳税人识别号
     */
    private String xsfNsrsbh;
    /**
     * 销售方名称
     */
    private String xsfMc;

    /**
     * sn
     */
    private String sn;

}

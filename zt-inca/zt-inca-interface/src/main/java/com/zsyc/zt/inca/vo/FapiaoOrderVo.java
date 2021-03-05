package com.zsyc.zt.inca.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class FapiaoOrderVo implements Serializable {
    /**
     * 取自 bms_sa_doc
     */
    private List<Long> erpOrderIdList;

    private String b2bOrderNo;

    private Long b2bOrderId;

    /**
     * 以下三个字段，新建一个表  表明  b2b_fapiao_config  字段按下面命名
     */
    /**
     * 本公司税号
     */
    private String taxNo;

    /**
     * 连接fapiao项目的用户名
     */
    private String username;
    /**
     * 连接fapiao项目的密码
     */
    private String password;
}

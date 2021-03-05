package com.zsyc.zt.inca.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailVo implements Serializable {
    private Long goodsId;
    private String goodsName;
    private String lotNo;
    private Long factoryId;
    private String factoryName;
    private String goodsUnit;
    private Double price;
    private Double num;
    private Double amount;

}

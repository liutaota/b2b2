package com.zsyc.zt.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date proddate ;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date invaliddate ;
    private String batchno;

}

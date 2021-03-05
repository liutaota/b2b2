package com.zsyc.zt.inca.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsVo implements Serializable {

    private Long goodsId;
    private String goodsName;
    private String goodsUnit;
    private Long factoryId;
    private String currencyName;
    private String goodsType;
    private String factoryName;
    private String medicineTypeName;
    private String prodArea;        // oucs add in 2021-01-17
    private Integer medicinetype;
    private Long zxB2bNumLimit;
    private String classnRow2;
    private Long classnRow3;
    private String className1;
    private String className2;
    private String className3;
    private String opcode;
    private Integer accFlag;

    /**
     * 当前客户使用的价格类型
     */
    private Integer priceId;
    /**
     * 当前客户使用的价格
     */
    private Double price;
    /**
     * 当前客户
     */
    private Long customId;
    /**
     * 当前独立单元
     */
    private Integer entryId;
}

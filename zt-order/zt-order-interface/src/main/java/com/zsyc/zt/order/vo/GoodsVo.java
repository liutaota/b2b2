package com.zsyc.zt.order.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author peiqy
 */
@Data
public class GoodsVo implements Serializable {

    @JsonProperty("goods_id")
    private Long goodsId;
    @JsonProperty("goods_name")
    private String goodsName;
    private String currencyName;
    /**
     * 规格
     */
    private String goodsType;
    private Long factoryId;
    private String factoryName;
    /**
     * 剂型
     */
    private Long medicineType;
    private String medicineTypeName;
    private Double price;
    private String approveDocNo;

    private String goodsUnit;
    private String prodArea;
    private String barcode;
    /**
     * 包装规格
     */
    private String  yjGoodsType;

    /**
     * 上市公司持有人
     */
    private String possessor;

    /**
     * 库存
     */
    private Integer  storageId;

    /**
     * 库存
     */
    private Double  goodsQty;

}

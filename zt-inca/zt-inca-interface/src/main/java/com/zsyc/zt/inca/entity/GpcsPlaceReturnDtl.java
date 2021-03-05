package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/*
* 配送退细单*/
@Data
@TableName("GPCS_PLACERETURNDTL")
@KeySequence("GPCS_PLACERETURNDTL_SEQ")
public class GpcsPlaceReturnDtl extends Model<GpcsPlaceReturnDtl> {
    private static final long serialVersionUID = 1L;
    /*配送细单ID*/
    @TableId("placereturndtlid")
    private Long placereturndtlid;
    @TableField("placereturnid")
    private Long placereturnid;//配送总单ID
    @TableField("goodsid")
    private Long goodsid;//货品ID
    @TableField("goodsdtlid")
    private Long goodsdtlid;//货品明细ID
    @TableField("goodsunit")
    private String goodsunit;//基本单位
    @TableField("goodsqty")
    private Double goodsqty;//基本单位数量
    @TableField("backwhyid")
    private int backwhyid;//退货原因ID
    @TableField("placepriceid")
    private int placepriceid;//配送价格类型ID
    @TableField("placeprice")
    private Double placeprice;//配送价格
    @TableField("placemoney")
    private Double placemoney;//退货金额
    @TableField("resaleprice")
    private Double resaleprice;//零售价
    @TableField("lotid")
    private Long lotid;//批号ID
    @TableField("batchid")
    private Long batchid;//批次ID
    @TableField("posid")
    private Long posid;//货位ID
    @TableField("goodsstatusid")
    private Integer goodsstatusid;//货品状态ID
    @TableField("usestatus")
    private String usestatus;//使用状态
    @TableField("memo")
    private String memo;
    @TableField("approvestatus")
    private Integer approvestatus;//审批状态
    @TableField("cansaleqty")
    private Double cansaleqty;
   /* @TableField("b2b_orderdtl_id")
    private String b2bOrderDtlId;*/
    @TableField("approvedate")
    private LocalDateTime approvedate;
    @TableField("approvemanid")
    private Long approvemanid;
    @TableField("sendwmsflag")
    private Integer sendwmsflag;
    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;
    @TableField("B2B_ORDER_ID")
    private String b2bOrderId;
    @TableField("B2B_SRC_ORDER_ID")
    private String b2bSrcOrderId;
    @TableField("B2B_SRC_ORDER_NO")
    private String b2bSrcOrderNo;
    @TableField("B2B_ORDER_DTL_ID")
    private String b2bOrderDtlId;
    @TableField("B2B_SRC_ORDER_DTL_ID")
    private String b2bSrcOrderDtlId;
    @TableField("B2B_SRC_ERP_ORDER_ID")
    private Long b2bSrcErpOrderId;
    @TableField("B2B_SRC_ERP_ORDER_DTL_ID")
    private Long b2bSrcErpOrderDtlId;
    @TableField("B2B_ORDER_TYPE")
    private Long b2bOrderType;
    /*保管账id*/
    @TableField("storageid")
    private Integer storageid;
    @TableField("TOTAL_LINE")
    private Double totalLine;
    private Long redocid;

    private Date invaliddate;
    private Date proddate;
    private Long redtlid;
    private Long customid;
    private Long retinyid;
    private Long rgid;
}

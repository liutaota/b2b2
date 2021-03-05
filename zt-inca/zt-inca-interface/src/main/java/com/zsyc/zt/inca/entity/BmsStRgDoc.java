package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author liutao
 * 收货单
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_ST_RG_DOC")
@ApiModel(value="BMS_ST_RG_DOC对象", description="")
@KeySequence("BMS_ST_RG_DOC_seq")
public class BmsStRgDoc extends Model<BmsStRgDoc> {
   @TableId("RGID")
   private Long rgid;
   @TableField("RGTYPE")
   private Integer rgtype;
   @TableField("GOODSID")
   private Long goodsid;
   @TableField("QUALIFIEDQTY")
   private Double qualifiedqty;
    @TableField("UNQUALIFIEDQTY")
   private Double unqualifiedqty;
    @TableField("REQUALIFIEDQTY")
   private Double requalifiedqty;
    @TableField("REUNQUALIFIEDQTY")
   private Double reunqualifiedqty;
    @TableField("REGOODSQTY")
   private Double regoodsqty;
    @TableField("SALERID")
   private Long salerid;
    @TableField("UNITPRICE")
   private Double unitprice;
    @TableField("TOTAL_LINE")
   private Double totalline;
    @TableField("USESTATUS")
   private Integer usestatus;
    @TableField("COMPANYID")
   private Long companyid;
    @TableField("STORERID")
   private Long storerid;
    @TableField("REINPUTMANID")
   private Long reinputmanid;
    @TableField("RECREDATE")
   private Date recredate;
    @TableField("REDOCID")
   private Long redocid;
    @TableField("REDTLID")
   private Long redtlid;
    @TableField("STORAGEID")
   private Long storageid;
    @TableField("FETCHID")
   private Long fetchid;
    @TableField("FETCHDTLID")
   private Long fetchdtlid;
    @TableField("ENTRYID")
   private Integer entryid;
    @TableField("REALPRICE")
   private Double realprice;
    @TableField("REALMONEY")
   private Double realmoney;
    @TableField("TWOINVOICEINFLAG")
   private Integer twoinvoiceinflag;


    /*商城单号*/
    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;

    /*商城单号*/
    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;


    /*商城单号*/
    @TableField("b2b_src_order_id")
    private Long b2bSrcOrderId;

    /*商城单号*/
    @TableField("B2B_src_ORDER_NO")
    private String b2bSrcOrderNo;

    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

}

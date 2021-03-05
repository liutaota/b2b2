package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_ST_RE_DOC")
@ApiModel(value="BmsStReDoc对象", description="")
@KeySequence(value = "BMS_ST_RE_DOC_SEQ")
public class BmsStReDoc extends Model<BmsStReDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("REDOCID")
    private Long redocid;

    @TableField("RETYPE")
    private Integer retype;

    @TableField("FREIGHTBILLNO")
    private String freightbillno;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("STORERID")
    private Long storerid;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private Date credate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("TWOINVOICEINFLAG")
    private Integer twoinvoiceinflag;

    @TableField("FILECOUNT")
    private Long filecount;

    @TableField("PLACERETURNID")
    private Long placereturnid;

    @TableField("ZX_MEMO")
    private String zxMemo;

    @TableField("ZX_RECMANID")
    private Long zxRecmanid;

    @TableField("ZX_RECDATE")
    private Date zxRecdate;

    @TableField("JD_ORDER_ID")
    private Double jdOrderId;
//    @TableField("B2B_SHOP_FLAG")
//    private Integer b2bShopFlag;


    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;
/*    @TableField("B2B_ORDER_ID")
    private String b2bOrderId;*/


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
}

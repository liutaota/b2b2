package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-01-11
 */
@TableName("GPCS_PLACESUPPLYDTL_ST")
@Data
@KeySequence("GPCS_PLACESUPPLYDTL_ST_SEQ")
@EqualsAndHashCode(callSuper = false)
public class GpcsPlacesupplydtlSt extends Model<GpcsPlacesupplydtlSt> {

    private static final long serialVersionUID = 1L;

    @TableId("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("PLACESUPPLYDTLID")
    private Long placesupplydtlid;

    @TableField("STORAGEID")
    private Integer storageid;

    @TableField("DESTTABLE")
    private Long desttable;

    @TableField("DESTDTLID")
    private Long destdtlid;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("PLACEPRICEID")
    private Integer placepriceid;

    @TableField("UNITPRICE")
    private Double unitprice;

    @TableField("TOTAL_LINE")
    private Double totalLine;

    @TableField("PRICEMOD")
    private Double pricemod;

    @TableField("PLACEPRICE")
    private Double placeprice;

    @TableField("ENTRYID")
    private Integer entryid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("SETTLETYPEID")
    private Long settletypeid;

    @TableField("PLACETYPE")
    private Integer placetype;

    @TableField("SUPRICE")
    private Double suprice;

    @TableField("SUBATCHID")
    private Long subatchid;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("LASTPOSTENTRYID")
    private Long lastpostentryid;

    @TableField("LASTPOSTSTORAGEID")
    private Long lastpoststorageid;

    @TableField("ECODE")
    private String ecode;

    @TableField("POSTPLACEPRICEID")
    private Long postplacepriceid;

    @TableField("POSTPRICEMOD")
    private Integer postpricemod;

    @TableField("POSTSETTLETYPEID")
    private Long postsettletypeid;

    @TableField("POSTINVTYPE")
    private Integer postinvtype;

    @TableField("POSTPLACEPRICE")
    private Double postplaceprice;

    @TableField("POSTUNITPRICE")
    private Double postunitprice;

    @TableField("POSTENTRYID")
    private Long postentryid;

    @TableField("POSTTOTAL_LINE")
    private Double posttotalLine;

    @TableField("POSTPLACETYPE")
    private Integer postplacetype;

    @TableField("GENBILLFLAG")
    private Integer genbillflag;

    @TableField("POSTSALESDTLID")
    private Long postsalesdtlid;

    @TableField("POSTSUDOCDTLID")
    private Long postsudocdtlid;

    @TableField("POSTSUDTLMEMO")
    private String postsudtlmemo;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;

    private Double jdSourceNum;
    private Long jdOrderId;
    private Long jdOrderDtlId;

    //京东 总金额
    private Double jdTotalMoney;

    //京东 优惠
    private Double jdDiscount;

    //京东 已付金额
    private Double jdPayMoney;


    private Integer jdOrderType;

    private Integer jdPayType;



    private Double jdMoney;
    private Double jdUnitPrice;


    /*@TableField(exist = false)
    private List<GpcsPlacesupplydtlStdtl> detailList;*/


}

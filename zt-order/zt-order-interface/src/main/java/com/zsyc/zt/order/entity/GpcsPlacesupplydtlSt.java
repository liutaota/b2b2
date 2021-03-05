package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("GPCS_PLACESUPPLYDTL_ST")
public class GpcsPlacesupplydtlSt extends Model<GpcsPlacesupplydtlSt> {

    private static final long serialVersionUID = 1L;

    @TableId("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("PLACESUPPLYDTLID")
    private Long placesupplydtlid;

    @TableField("STORAGEID")
    private Long storageid;

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
    private Long entryid;

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

    @TableField("JD_SOURCE_NUM")
    private Double jdSourceNum;

    @TableField("JD_ORDER_ID")
    private Long jdOrderId;

    @TableField("JD_ORDER_DTL_ID")
    private Long jdOrderDtlId;

    @TableField("JD_TOTAL_MONEY")
    private Double jdTotalMoney;

    @TableField("JD_DISCOUNT")
    private Double jdDiscount;

    @TableField("JD_PAY_MONEY")
    private Double jdPayMoney;

    /**
     * ??????
     */
    @TableField("JD_UNIT_PRICE")
    private Double jdUnitPrice;

    /**
     * ??????
     */
    @TableField("JD_MONEY")
    private Double jdMoney;

    /**
     * ??????  1????  2  ???????
     */
    @TableField("JD_ORDER_TYPE")
    private Integer jdOrderType;

    @TableField("JD_PAY_TYPE")
    private Integer jdPayType;


    @Override
    protected Serializable pkVal() {
        return this.placesupplydtlstid;
    }

}

package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("BMS_SU_SUPPLYGOODS_DTL")
public class BmsSuSupplygoodsDtl extends Model<BmsSuSupplygoodsDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("SUPPLYGOODSDTLID")
    private Long supplygoodsdtlid;

    @TableField("SUPPLYGOODSDOCID")
    private Long supplygoodsdocid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("PRIORITY")
    private Integer priority;

    @TableField("AGREEDOCFLAG")
    private Integer agreedocflag;

    @TableField("LASTSUDATE")
    private LocalDateTime lastsudate;

    @TableField("LASTPRICE")
    private BigDecimal lastprice;

    @TableField("HIGHESTPRICE")
    private BigDecimal highestprice;

    @TableField("LOWESTPRICE")
    private BigDecimal lowestprice;

    @TableField("AVGPRICE")
    private BigDecimal avgprice;

    @TableField("HIGHESTPRICEDATE")
    private LocalDateTime highestpricedate;

    @TableField("LOWESTPRICEDATE")
    private LocalDateTime lowestpricedate;

    @TableField("MAXPERIOD")
    private BigDecimal maxperiod;

    @TableField("MINPERIOD")
    private BigDecimal minperiod;

    @TableField("AVGPERIOD")
    private BigDecimal avgperiod;

    @TableField("SUTIMES")
    private Long sutimes;

    @TableField("SUGOODSQTYS")
    private BigDecimal sugoodsqtys;

    @TableField("SUMONEYS")
    private BigDecimal sumoneys;

    @TableField("SUBACKTIMES")
    private Long subacktimes;

    @TableField("SUBACKGOODSQTYS")
    private BigDecimal subackgoodsqtys;

    @TableField("SUBACKMONEYS")
    private BigDecimal subackmoneys;

    @TableField("PAYMETHOD")
    private Integer paymethod;

    @TableField("PAYLIMIT")
    private String paylimit;

    @TableField("SUDISCOUNT")
    private Integer sudiscount;

    @TableField("ISTICKETFLAG")
    private Integer isticketflag;

    @TableField("CREDITMONEY")
    private BigDecimal creditmoney;

    @TableField("CREDITDAY")
    private Integer creditday;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("STATUSDESC")
    private String statusdesc;

    @TableField("REBATEPOLICY")
    private String rebatepolicy;

    @TableField("RATING")
    private String rating;

    @TableField("LASTSUPPLYID")
    private Long lastsupplyid;

    @TableField("LASTAGENTID")
    private Long lastagentid;

    @TableField("LASTCONTACTID")
    private Long lastcontactid;

    @TableField("LASTSUDOCID")
    private Long lastsudocid;

    @TableField("LASTSUQTY")
    private BigDecimal lastsuqty;


    @Override
    protected Serializable pkVal() {
        return this.supplygoodsdtlid;
    }

}

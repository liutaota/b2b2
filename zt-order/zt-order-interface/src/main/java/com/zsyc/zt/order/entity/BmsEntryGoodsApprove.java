package com.zsyc.zt.order.entity;

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
@TableName("BMS_ENTRY_GOODS_APPROVE")
public class BmsEntryGoodsApprove extends Model<BmsEntryGoodsApprove> {

    private static final long serialVersionUID = 1L;

    @TableId("APPROVEID")
    private Long approveid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("FACTORYID")
    private Long factoryid;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("MEDICINETYPE")
    private Integer medicinetype;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("STANDARDTYPE")
    private String standardtype;

    @TableField("STORAGECONDITIONFLAG")
    private Integer storageconditionflag;

    @TableField("IMPORTFLAG")
    private Integer importflag;

    @TableField("BIOMEDFLAG")
    private Integer biomedflag;

    @TableField("ISEGGPEPTIDEFLAG")
    private Integer iseggpeptideflag;

    @TableField("DRUGFLAG")
    private Integer drugflag;

    @TableField("EPHEDRINEFLAG")
    private Integer ephedrineflag;

    @TableField("APPROVESTATUS")
    private Integer approvestatus;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("FUNCTION")
    private String function;

    @TableField("QFUSESTATUS")
    private Integer qfusestatus;

    @TableField("QFMEMO")
    private String qfmemo;

    @TableField("QFLEVELID")
    private Integer qflevelid;

    @TableField("QFAPPMANID")
    private Long qfappmanid;

    @TableField("QFAPPDATE")
    private LocalDateTime qfappdate;

    @TableField("APPROVEMANID")
    private Long approvemanid;

    @TableField("APPROVEMEMO")
    private String approvememo;

    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;


    @Override
    protected Serializable pkVal() {
        return this.approveid;
    }

}

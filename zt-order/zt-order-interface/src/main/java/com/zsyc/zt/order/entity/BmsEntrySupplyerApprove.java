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
@TableName("BMS_ENTRY_SUPPLYER_APPROVE")
public class BmsEntrySupplyerApprove extends Model<BmsEntrySupplyerApprove> {

    private static final long serialVersionUID = 1L;

    @TableId("APPROVEID")
    private Long approveid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("SUPPLYTYPE")
    private Integer supplytype;

    @TableField("SUPPLYNO")
    private String supplyno;

    @TableField("TAXNUMBER")
    private String taxnumber;

    @TableField("ZONE")
    private String zone;

    @TableField("LEGALPERSON")
    private String legalperson;

    @TableField("SUPPLYGOODS")
    private String supplygoods;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("QFLEVELID")
    private Integer qflevelid;

    @TableField("QFMEMO")
    private String qfmemo;

    @TableField("QFUSESTATUS")
    private Integer qfusestatus;

    @TableField("QFAPPMANID")
    private Long qfappmanid;

    @TableField("QFAPPDATE")
    private LocalDateTime qfappdate;

    @TableField("APPROVESTATUS")
    private Integer approvestatus;

    @TableField("APPROVEMANID")
    private Long approvemanid;

    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;

    @TableField("APPROVEMEMO")
    private String approvememo;


    @Override
    protected Serializable pkVal() {
        return this.approveid;
    }

}

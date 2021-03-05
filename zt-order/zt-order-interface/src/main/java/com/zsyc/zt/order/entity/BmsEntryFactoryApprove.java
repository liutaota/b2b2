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
@TableName("BMS_ENTRY_FACTORY_APPROVE")
public class BmsEntryFactoryApprove extends Model<BmsEntryFactoryApprove> {

    private static final long serialVersionUID = 1L;

    @TableId("APPROVEID")
    private Long approveid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FACTORYID")
    private Long factoryid;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("FACTORYTYPE")
    private Integer factorytype;

    @TableField("FACTORYNO")
    private String factoryno;

    @TableField("TAXNUMBER")
    private String taxnumber;

    @TableField("ZONE")
    private String zone;

    @TableField("LEGALPERSON")
    private String legalperson;

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

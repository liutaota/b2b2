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
@TableName("PUB_SUPPLYER_ALTER_APPROVE")
public class PubSupplyerAlterApprove extends Model<PubSupplyerAlterApprove> {

    private static final long serialVersionUID = 1L;

    @TableId("APPROVEID")
    private Long approveid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("CONTENT")
    private String content;

    @TableField("APPROVESTATUS")
    private Integer approvestatus;

    @TableField("APPROVEMEMO")
    private String approvememo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("APPROVEMANID")
    private Long approvemanid;

    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;


    @Override
    protected Serializable pkVal() {
        return this.approveid;
    }

}

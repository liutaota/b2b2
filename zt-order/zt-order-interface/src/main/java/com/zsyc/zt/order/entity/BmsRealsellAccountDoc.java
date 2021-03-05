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
@TableName("BMS_REALSELL_ACCOUNT_DOC")
public class BmsRealsellAccountDoc extends Model<BmsRealsellAccountDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("REALSELLID")
    private Long realsellid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("MONTH")
    private Integer month;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("PLANPAYDATE")
    private LocalDateTime planpaydate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("EXEMANID")
    private Long exemanid;

    @TableField("EXEDATE")
    private LocalDateTime exedate;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.realsellid;
    }

}

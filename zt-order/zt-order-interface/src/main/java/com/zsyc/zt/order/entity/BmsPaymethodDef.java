package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("BMS_PAYMETHOD_DEF")
public class BmsPaymethodDef extends Model<BmsPaymethodDef> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("PAYMETHOD")
    private Integer paymethod;

    @TableField("PAYENABLE")
    private Integer payenable;

    @TableField("PAYMEMO")
    private String paymemo;

    @TableField("PAYMETHODNAME")
    private String paymethodname;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

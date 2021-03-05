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
@TableName("PUB_BANK")
public class PubBank extends Model<PubBank> {

    private static final long serialVersionUID = 1L;

    @TableId("BANKID")
    private Long bankid;

    @TableField("BANKOPCODE")
    private String bankopcode;

    @TableField("BANKNO")
    private String bankno;

    @TableField("BANKPINYIN")
    private String bankpinyin;

    @TableField("CORPCODE")
    private String corpcode;

    @TableField("BANKNAME")
    private String bankname;

    @TableField("BANKMEMO")
    private String bankmemo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ACCNO")
    private String accno;

    @TableField("B2BBANKTYPE")
    private Integer b2bbanktype;

    @TableField("MERCHANTS")
    private String merchants;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;


    @Override
    protected Serializable pkVal() {
        return this.bankid;
    }

}

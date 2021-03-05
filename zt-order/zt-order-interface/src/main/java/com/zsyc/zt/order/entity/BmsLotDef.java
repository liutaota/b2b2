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
@TableName("BMS_LOT_DEF")
public class BmsLotDef extends Model<BmsLotDef> {

    private static final long serialVersionUID = 1L;

    @TableId("LOTID")
    private Long lotid;

    @TableField("LOTNO")
    private String lotno;

    @TableField("KILLLOTNO")
    private String killlotno;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PRODDATE")
    private LocalDateTime proddate;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("LOTSORTNO")
    private String lotsortno;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("REGISTDOCNO")
    private String registdocno;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CONSERVEDATE")
    private LocalDateTime conservedate;

    @TableField("CHECKNO")
    private String checkno;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("KILLDATE")
    private LocalDateTime killdate;

    @TableField("CHECKNOMEMO")
    private String checknomemo;

    @TableField("PRINTSET")
    private Integer printset;


    @Override
    protected Serializable pkVal() {
        return this.lotid;
    }

}

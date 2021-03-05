package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_LOT_DEF")
@ApiModel(value="BmsLotDef对象", description="")
@KeySequence(value = "BMS_LOT_DEF_SEQ")
public class BmsLotDef extends BaseBean {

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


}

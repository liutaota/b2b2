package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("GPCS_PLACESUPPLY_TINY")
@ApiModel(value="GpcsPlacesupplyTiny对象", description="")
@KeySequence(value = "GPCS_PLACESUPPLY_TINY_SEQ")
public class GpcsPlacesupplyTiny extends Model<GpcsPlacesupplyTiny> {

    private static final long serialVersionUID = 1L;

    @TableId("PLACESUPPLYTINYID")
    private Long placesupplytinyid;

    @TableField("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("PRINTNO")
    private String printno;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("RECIEVEFLAG")
    private Integer recieveflag;

    @TableField("RECIEVEQTY")
    private Double recieveqty;

    @TableField("POSTBATCHID")
    private Long postbatchid;

    @TableField("ACCEPTMANID")
    private Long acceptmanid;

    @TableField("ACCEPTDATE")
    private LocalDateTime acceptdate;

    @TableField("CHECKSTATUS")
    private Integer checkstatus;

    @TableField("RECHECKSTATUS")
    private Integer recheckstatus;

    @TableField("RECHECKMANID")
    private Long recheckmanid;

    @TableField("RECHECKDATE")
    private LocalDateTime recheckdate;

    @TableField("RECHECKMEMO")
    private String recheckmemo;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("CHECKMANID2")
    private Long checkmanid2;

    @TableField("QUALITYQTY")
    private Double qualityqty;

    @TableField("REJECTQTY")
    private Double rejectqty;

    @TableField("UNQUALIFIEDQTY")
    private Double unqualifiedqty;


}

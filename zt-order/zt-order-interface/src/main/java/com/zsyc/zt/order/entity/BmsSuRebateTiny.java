package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_SU_REBATE_TINY")
public class BmsSuRebateTiny extends Model<BmsSuRebateTiny> {

    private static final long serialVersionUID = 1L;

    @TableId("REBATETINYID")
    private Long rebatetinyid;

    @TableField("REBATEDTLID")
    private Long rebatedtlid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("LADDERSTART")
    private BigDecimal ladderstart;

    @TableField("LADDERHANDLE1")
    private String ladderhandle1;

    @TableField("LADDEREND")
    private BigDecimal ladderend;

    @TableField("LADDERHANDLE2")
    private String ladderhandle2;

    @TableField("LADDERVALUE")
    private BigDecimal laddervalue;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CLASSID")
    private Long classid;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("SETID")
    private Long setid;

    @TableField("AREAID")
    private Long areaid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("POINTCLASSID")
    private Long pointclassid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("FACTORYID")
    private Long factoryid;

    @TableField("GOODSCLASSID")
    private Long goodsclassid;

    @TableField("GOODSSETID")
    private Long goodssetid;

    @TableField("GOODSBRANDID")
    private Long goodsbrandid;

    @TableField("SETTLETYPEID")
    private Long settletypeid;

    @TableField("CALCTYPE")
    private Integer calctype;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.rebatetinyid;
    }

}

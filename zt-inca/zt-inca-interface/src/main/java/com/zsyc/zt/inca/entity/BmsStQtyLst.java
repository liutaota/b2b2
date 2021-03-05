package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-25
 */
@Data

@Accessors(chain = true)
@TableName("BMS_ST_QTY_LST")
@ApiModel(value="BmsStQtyLst对象", description="")
//@KeySequence(value = "BMS_ST_QTY_LST_SEQ")
public class BmsStQtyLst extends Model<BmsStQtyLst> {

    private static final long serialVersionUID = 1L;

    @TableId("GOODSID")
    private Long goodsid;

    @TableField("GOODSDETAILID")
    private Long goodsdetailid;

    @TableField("STORAGEID")
    private Integer storageid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Integer goodsstatusid;

    @TableField("GOODSQTY")
    private Double goodsqty;


    //临时库存占用数量
    @TableField(exist = false)
    private Double qtyTmp;

    //实际库存
    @TableField(exist = false)
    private Double qtyFact;

    @TableField(exist = false)
    private Date proddate;

    @TableField(exist = false)
    private Date invaliddate;


}

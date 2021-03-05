package com.zsyc.zt.inca.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * 保存销退单和销售单的对应关系
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BMS_SA_TO_SA")
public class BmsSaToSa extends Model<BmsSaToSa> {

    private static final long serialVersionUID = 1L;

    //销售细单id
    @TableId("SADTLID1")
    private Long sadtlid1;

    //销售退货细单id
    @TableField("SADTLID2")
    private Long sadtlid2;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("IODTLID")
    private Long iodtlid;


    @Override
    protected Serializable pkVal() {
        return this.sadtlid1;
    }

}

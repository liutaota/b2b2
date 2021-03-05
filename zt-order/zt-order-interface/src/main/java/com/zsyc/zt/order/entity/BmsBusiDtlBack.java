package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_BUSI_DTL_BACK")
public class BmsBusiDtlBack extends Model<BmsBusiDtlBack> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("UPQTY")
    private BigDecimal upqty;

    @TableField("DOWNQTY")
    private BigDecimal downqty;

    @TableField("SUPLANQTY")
    private BigDecimal suplanqty;

    @TableField("STQTY")
    private BigDecimal stqty;

    @TableField("NOSTINQTY")
    private BigDecimal nostinqty;

    @TableField("NOSTOUTQTY")
    private BigDecimal nostoutqty;

    @TableField("SUPLANFLAG")
    private Integer suplanflag;

    @TableField("SUCONFLAG")
    private Integer suconflag;

    @TableField("SUFLAG")
    private Integer suflag;

    @TableField("SUPLANUSEQTY")
    private BigDecimal suplanuseqty;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("BUSIDTLID")
    private Long busidtlid;

    @TableField("SETUPQTY")
    private BigDecimal setupqty;

    @TableField("SETDOWNQTY")
    private BigDecimal setdownqty;

    @TableField("MODIFYTYPE")
    private Integer modifytype;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

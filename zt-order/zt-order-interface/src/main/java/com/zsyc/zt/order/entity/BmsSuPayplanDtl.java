package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_SU_PAYPLAN_DTL")
public class BmsSuPayplanDtl extends Model<BmsSuPayplanDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("PAYPLANID")
    private Long payplanid;

    @TableId("PAYPLANDTLID")
    private Long payplandtlid;

    @TableField("PAYMETHOD")
    private Long paymethod;

    @TableField("PAYLIMIT")
    private String paylimit;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PLANGOODSQTY")
    private BigDecimal plangoodsqty;

    @TableField("PLANPAYAMT")
    private BigDecimal planpayamt;

    @TableField("SUSETDTLID")
    private Long susetdtlid;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("THISEXECAMT")
    private BigDecimal thisexecamt;

    @TableField("PLNPAYDATE")
    private LocalDateTime plnpaydate;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;


    @Override
    protected Serializable pkVal() {
        return this.payplandtlid;
    }

}

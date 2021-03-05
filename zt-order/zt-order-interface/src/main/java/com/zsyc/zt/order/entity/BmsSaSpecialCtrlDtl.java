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
@TableName("BMS_SA_SPECIAL_CTRL_DTL")
public class BmsSaSpecialCtrlDtl extends Model<BmsSaSpecialCtrlDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("CTRLDTLID")
    private Long ctrldtlid;

    @TableField("CTRLDOCID")
    private Long ctrldocid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("SINGLENUM")
    private BigDecimal singlenum;

    @TableField("CTRLDAYS")
    private Long ctrldays;

    @TableField("CTRLNUM")
    private BigDecimal ctrlnum;


    @Override
    protected Serializable pkVal() {
        return this.ctrldtlid;
    }

}

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
@TableName("BMS_SU_PROT_GOODS_DTL")
public class BmsSuProtGoodsDtl extends Model<BmsSuProtGoodsDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("PROTDTLID")
    private Long protdtlid;

    @TableField("PROTDOCID")
    private Long protdocid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("REBATERATE")
    private BigDecimal rebaterate;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.protdtlid;
    }

}

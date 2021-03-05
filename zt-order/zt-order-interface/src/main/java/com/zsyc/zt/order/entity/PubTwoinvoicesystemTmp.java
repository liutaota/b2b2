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
@TableName("PUB_TWOINVOICESYSTEM_TMP")
public class PubTwoinvoicesystemTmp extends Model<PubTwoinvoicesystemTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private BigDecimal id;

    @TableField("GOODSID")
    private String goodsid;

    @TableField("SUPPLYID")
    private String supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("WTR")
    private String wtr;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

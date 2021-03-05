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
@TableName("BMS_FETCH_TO_SAIO")
public class BmsFetchToSaio extends Model<BmsFetchToSaio> {

    private static final long serialVersionUID = 1L;

    @TableId("FETCHDTLID")
    private Long fetchdtlid;

    @TableField("IODTLID")
    private Long iodtlid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("SALESDTLID")
    private Long salesdtlid;


    @Override
    protected Serializable pkVal() {
        return this.fetchdtlid;
    }

}

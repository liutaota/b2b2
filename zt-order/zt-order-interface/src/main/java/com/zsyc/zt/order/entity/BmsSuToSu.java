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
@TableName("BMS_SU_TO_SU")
public class BmsSuToSu extends Model<BmsSuToSu> {

    private static final long serialVersionUID = 1L;

    @TableId("SUDTLID1")
    private Long sudtlid1;

    @TableField("SUDTLID2")
    private Long sudtlid2;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;


    @Override
    protected Serializable pkVal() {
        return this.sudtlid1;
    }

}

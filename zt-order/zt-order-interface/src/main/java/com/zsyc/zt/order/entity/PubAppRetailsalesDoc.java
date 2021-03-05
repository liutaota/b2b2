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
@TableName("PUB_APP_RETAILSALES_DOC")
public class PubAppRetailsalesDoc extends Model<PubAppRetailsalesDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SALESID")
    private Long salesid;

    @TableField("SALES")
    private BigDecimal sales;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("COUNTNUM")
    private Long countnum;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("GROSS")
    private BigDecimal gross;

    @TableField("GROSSRATE")
    private BigDecimal grossrate;


    @Override
    protected Serializable pkVal() {
        return this.salesid;
    }

}

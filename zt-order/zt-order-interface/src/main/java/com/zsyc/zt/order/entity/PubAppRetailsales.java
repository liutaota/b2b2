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
@TableName("PUB_APP_RETAILSALES")
public class PubAppRetailsales extends Model<PubAppRetailsales> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("MONEY")
    private BigDecimal money;

    @TableField("INSIDERMONEY")
    private BigDecimal insidermoney;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("CUSTOMERQTY")
    private Long customerqty;

    @TableField("INSIDERQTY")
    private Long insiderqty;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("GROSS")
    private BigDecimal gross;

    @TableField("INSIDERGROSS")
    private BigDecimal insidergross;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

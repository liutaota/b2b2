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
@TableName("PUB_FORMONEY")
public class PubFormoney extends Model<PubFormoney> {

    private static final long serialVersionUID = 1L;

    @TableId("FMID")
    private Long fmid;

    @TableField("FMOPCODE")
    private String fmopcode;

    @TableField("FMNAME")
    private String fmname;

    @TableField("FMSIGN")
    private String fmsign;

    @TableField("FMUNIT")
    private String fmunit;

    @TableField("FMRATE")
    private BigDecimal fmrate;

    @TableField("USESTATUS")
    private Integer usestatus;


    @Override
    protected Serializable pkVal() {
        return this.fmid;
    }

}

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
@TableName("PUB_FORMONEY_RATE")
public class PubFormoneyRate extends Model<PubFormoneyRate> {

    private static final long serialVersionUID = 1L;

    @TableId("RATEID")
    private Long rateid;

    @TableField("FMID")
    private Long fmid;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("RATE")
    private BigDecimal rate;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.rateid;
    }

}

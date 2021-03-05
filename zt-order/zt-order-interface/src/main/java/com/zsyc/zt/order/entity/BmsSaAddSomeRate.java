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
@TableName("BMS_SA_ADD_SOME_RATE")
public class BmsSaAddSomeRate extends Model<BmsSaAddSomeRate> {

    private static final long serialVersionUID = 1L;

    @TableId("RATEID")
    private Long rateid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("RATE")
    private BigDecimal rate;

    @TableField("PRICETYPEID")
    private Long pricetypeid;

    @TableField("RATETYPE")
    private Integer ratetype;

    @TableField("RATEEXPLAIN")
    private String rateexplain;


    @Override
    protected Serializable pkVal() {
        return this.rateid;
    }

}

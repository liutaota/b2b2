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
@TableName("BMS_GOODS_SORT_RULE_DEF")
public class BmsGoodsSortRuleDef extends Model<BmsGoodsSortRuleDef> {

    private static final long serialVersionUID = 1L;

    @TableId("RULEDEFID")
    private Long ruledefid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GRADE")
    private Integer grade;

    @TableField("BUSITYPE")
    private Integer busitype;

    @TableField("STANDARD")
    private Integer standard;

    @TableField("SCOPESTART")
    private BigDecimal scopestart;

    @TableField("SCOPEEND")
    private BigDecimal scopeend;

    @TableField("PLACEPOINTID")
    private Long placepointid;


    @Override
    protected Serializable pkVal() {
        return this.ruledefid;
    }

}

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
@TableName("BMS_ASSESS_SA")
public class BmsAssessSa extends Model<BmsAssessSa> {

    private static final long serialVersionUID = 1L;

    @TableId("DTLID")
    private Long dtlid;

    @TableField("DOCID")
    private Long docid;

    @TableField("MONEY1")
    private BigDecimal money1;

    @TableField("MONEY2")
    private BigDecimal money2;

    @TableField("ASSRATE")
    private BigDecimal assrate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}

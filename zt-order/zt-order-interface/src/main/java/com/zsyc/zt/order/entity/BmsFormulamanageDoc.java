package com.zsyc.zt.order.entity;

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
@TableName("BMS_FORMULAMANAGE_DOC")
public class BmsFormulamanageDoc extends Model<BmsFormulamanageDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("FORMULADOCID")
    private Long formuladocid;

    @TableField("FORMULANAME")
    private String formulaname;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("COMFIRMMANID")
    private Long comfirmmanid;

    @TableField("COMFIRMDATE")
    private LocalDateTime comfirmdate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("FORMULACODE")
    private String formulacode;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.formuladocid;
    }

}

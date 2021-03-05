package com.zsyc.zt.order.entity;

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
@TableName("BMS_SA_SPECIAL_CTRL_DOC")
public class BmsSaSpecialCtrlDoc extends Model<BmsSaSpecialCtrlDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("CTRLDOCID")
    private Long ctrldocid;

    @TableField("CUSTOMCLASSID")
    private Long customclassid;

    @TableField("CONTROLTYPE")
    private Integer controltype;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.ctrldocid;
    }

}

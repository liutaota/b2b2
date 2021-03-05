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
@TableName("BMS_ASSESS_DEF")
public class BmsAssessDef extends Model<BmsAssessDef> {

    private static final long serialVersionUID = 1L;

    @TableId("ASSESSEID")
    private Long assesseid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("EMPFLAG")
    private Integer empflag;

    @TableField("USEMMFLAG")
    private Integer usemmflag;

    @TableField("CUSTOMFLAG")
    private Integer customflag;

    @TableField("GOODSFLAG")
    private Integer goodsflag;


    @Override
    protected Serializable pkVal() {
        return this.assesseid;
    }

}

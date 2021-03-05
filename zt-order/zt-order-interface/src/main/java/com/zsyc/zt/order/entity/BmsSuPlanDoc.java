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
@TableName("BMS_SU_PLAN_DOC")
public class BmsSuPlanDoc extends Model<BmsSuPlanDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("PLANDOCID")
    private Long plandocid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("DTLLINES")
    private Long dtllines;

    @TableField("CONFIRMGOODSLINES")
    private Long confirmgoodslines;

    @TableField("UNCONFIRMGOODSLINES")
    private Long unconfirmgoodslines;

    @TableField("WFUSESTATUS")
    private Integer wfusestatus;

    @TableField("WFPROCESS")
    private Integer wfprocess;

    @TableField("WFMEMO")
    private String wfmemo;

    @TableField("COMEFROM")
    private Integer comefrom;


    @Override
    protected Serializable pkVal() {
        return this.plandocid;
    }

}

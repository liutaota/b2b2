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
@TableName("BMS_BUSI_DEF")
public class BmsBusiDef extends Model<BmsBusiDef> {

    private static final long serialVersionUID = 1L;

    @TableId("BUSIACCID")
    private Long busiaccid;

    @TableField("OPCODE")
    private String opcode;

    @TableField("BUSINAME")
    private String businame;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("AUTOUPDATE")
    private Integer autoupdate;

    @TableField("CLACDATEN")
    private Long clacdaten;

    @TableField("CALCMETHOD")
    private Integer calcmethod;


    @Override
    protected Serializable pkVal() {
        return this.busiaccid;
    }

}

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
@TableName("BMS_CHANGEPLANPAYDATE_DOC")
public class BmsChangeplanpaydateDoc extends Model<BmsChangeplanpaydateDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("CHANGEID")
    private Long changeid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("PLANPAYDATE")
    private LocalDateTime planpaydate;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("EXEMANID")
    private Long exemanid;

    @TableField("EXEDATE")
    private LocalDateTime exedate;

    @TableField("USESTATUS")
    private Integer usestatus;


    @Override
    protected Serializable pkVal() {
        return this.changeid;
    }

}

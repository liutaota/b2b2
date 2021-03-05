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
@TableName("BMS_ST_ERA_DOC")
public class BmsStEraDoc extends Model<BmsStEraDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("ERAID")
    private Long eraid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("INOUTFLAG")
    private Integer inoutflag;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("GOTO")
    private Integer _goto;

    @TableField("MEMO")
    private String memo;

    @TableField("REASON")
    private Integer reason;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("RGDTLID")
    private Long rgdtlid;


    @Override
    protected Serializable pkVal() {
        return this.eraid;
    }

}

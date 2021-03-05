package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
@TableName("BMS_BOXUP_DOC")
public class BmsBoxupDoc extends Model<BmsBoxupDoc> {

    private static final long serialVersionUID = 1L;

    @TableField("BOXNO")
    private String boxno;

    @TableField("BOXMANID")
    private Long boxmanid;

    @TableField("BOXDATE")
    private LocalDateTime boxdate;

    @TableId("BOXDOCID")
    private Long boxdocid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("OPERATIONTYPE")
    private Integer operationtype;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ZX_DESKNO")
    private String zxDeskno;

    @TableField("ZX_LOADCARFLAG")
    private Integer zxLoadcarflag;

    @TableField("VEHICLEID")
    private Long vehicleid;

    @TableField("ZX_LOADMANID")
    private Long zxLoadmanid;

    @TableField("ZX_LOADDATE")
    private LocalDateTime zxLoaddate;


    @Override
    protected Serializable pkVal() {
        return this.boxdocid;
    }

}

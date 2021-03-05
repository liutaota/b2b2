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
@TableName("BMS_ECODE_RECORD")
public class BmsEcodeRecord extends Model<BmsEcodeRecord> {

    private static final long serialVersionUID = 1L;

    @TableId("RECORDID")
    private Long recordid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("ECODE")
    private String ecode;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("EXPFLAG")
    private Integer expflag;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("EXPDATE")
    private LocalDateTime expdate;

    @TableField("EXPMANID")
    private Long expmanid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.recordid;
    }

}

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
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("GSP_LICENSE_TYPE")
public class GspLicenseType extends Model<GspLicenseType> {

    private static final long serialVersionUID = 1L;

    @TableId("LICENSETYPEID")
    private Long licensetypeid;

    @TableField("LICENSENAME")
    private String licensename;

    @TableField("CUSTOMFLAG")
    private Integer customflag;

    @TableField("SUPPLYFLAG")
    private Integer supplyflag;

    @TableField("GOODSFLAG")
    private Integer goodsflag;

    @TableField("RANGEFLAG")
    private Integer rangeflag;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("MEMO")
    private String memo;

    @TableField("EARLYWARNDAYS")
    private Integer earlywarndays;

    @TableField("SUPERDAYS")
    private Integer superdays;

    @TableField("VALIDCTRL")
    private Integer validctrl;

    @TableField("CONTROLPRODDATE")
    private Integer controlproddate;

    /**
     * 是否先决条件  1 表示 一定需要并且不过期  2 表示有的话 一定不能过期 3 就是不管控
     */
    @TableField("IS_PREMISE")
    private Integer isPremise;


    @Override
    protected Serializable pkVal() {
        return this.licensetypeid;
    }

}

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
@TableName("PUB_DATA_EXPORT")
public class PubDataExport extends Model<PubDataExport> {

    private static final long serialVersionUID = 1L;

    @TableField("OPID")
    private Long opid;

    @TableField("SHOWHEAD")
    private String showhead;

    @TableField("EXPORTNUM")
    private Long exportnum;

    @TableField("SPLITCHAR")
    private String splitchar;

    @TableField("PLAN")
    private String plan;

    @TableField("EXPORTCLASSNAME")
    private String exportclassname;

    @TableField("EXPORTTYPE")
    private String exporttype;

    @TableField("CONFIGFILES")
    private String configfiles;

    @TableId("EXPORTID")
    private Long exportid;

    @TableField("ISEXPORTALL")
    private String isexportall;

    @TableField("ISAUTOFILENAME")
    private String isautofilename;

    @TableField("MDEEXPORTTYPE")
    private String mdeexporttype;

    @TableField("EXPORTMODE")
    private String exportmode;

    @TableField("ISAPPEND")
    private String isappend;

    @TableField("DEFSHOWHEAD")
    private String defshowhead;

    @TableField("TITLE")
    private String title;

    @TableField("FILESUFFIX")
    private String filesuffix;


    @Override
    protected Serializable pkVal() {
        return this.exportid;
    }

}

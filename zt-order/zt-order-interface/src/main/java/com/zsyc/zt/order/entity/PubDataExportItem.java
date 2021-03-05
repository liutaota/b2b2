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
@TableName("PUB_DATA_EXPORT_ITEM")
public class PubDataExportItem extends Model<PubDataExportItem> {

    private static final long serialVersionUID = 1L;

    @TableId("EXPORTID")
    private Long exportid;

    @TableField("FIELDCODE")
    private String fieldcode;

    @TableField("FIELDCODECH")
    private String fieldcodech;

    @TableField("DEFFIELDCODE")
    private String deffieldcode;

    @TableField("DEFFIELDCODECH")
    private String deffieldcodech;

    @TableField("ISEXPORT")
    private String isexport;

    @TableField("ISMODELCOL")
    private String ismodelcol;

    @TableField("NUMFORMATER")
    private String numformater;

    @TableField("DATEFORMATER")
    private String dateformater;

    @TableField("MAXLENGTH")
    private Long maxlength;

    @TableField("ORDERNO")
    private Long orderno;

    @TableField("COLTYPE")
    private String coltype;

    @TableField("ISEXPORTFLAG")
    private String isexportflag;

    @TableField("EXPORTFLAGVALUE")
    private String exportflagvalue;

    @TableField("ISMASTERCOL")
    private String ismastercol;

    @TableField("MATCHMAP")
    private String matchmap;


    @Override
    protected Serializable pkVal() {
        return this.exportid;
    }

}

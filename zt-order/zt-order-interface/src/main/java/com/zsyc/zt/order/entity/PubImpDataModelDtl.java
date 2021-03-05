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
@TableName("PUB_IMP_DATA_MODEL_DTL")
public class PubImpDataModelDtl extends Model<PubImpDataModelDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("MODELID")
    private Long modelid;

    @TableId("MODELDTLID")
    private Long modeldtlid;

    @TableField("ORDERNO")
    private Long orderno;

    @TableField("EXCELCOLNAME")
    private String excelcolname;

    @TableField("REALCOLNAME")
    private String realcolname;

    @TableField("NOTNULL")
    private Integer notnull;

    @TableField("EXPLAIN")
    private String explain;

    @TableField("MEMO")
    private String memo;

    @TableField("DATATYPE")
    private Integer datatype;

    @TableField("LENGTH")
    private Long length;

    @TableField("SCALE")
    private Long scale;

    @TableField("CHECKTYPE")
    private Integer checktype;

    @TableField("CHECKSQL")
    private String checksql;

    @TableField("TABSFLAG")
    private Integer tabsflag;

    @TableField("NEWLINEFLAG")
    private Integer newlineflag;

    @TableField("ENTERFLAG")
    private Integer enterflag;

    @TableField("BEFORESPACE")
    private Integer beforespace;

    @TableField("INSPACE")
    private Integer inspace;

    @TableField("BEHINDSPACE")
    private Integer behindspace;


    @Override
    protected Serializable pkVal() {
        return this.modeldtlid;
    }

}

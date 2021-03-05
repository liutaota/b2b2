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
@TableName("PUB_PRINTPOINT_DTL")
public class PubPrintpointDtl extends Model<PubPrintpointDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("FILENAME")
    private String filename;

    @TableField("SQLRULES")
    private String sqlrules;

    @TableField("PRINTPOINTID")
    private Long printpointid;

    @TableField("INCOLNAME")
    private String incolname;

    @TableField("TABLENAME")
    private String tablename;

    @TableField("DEFAULTPRINT")
    private Long defaultprint;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

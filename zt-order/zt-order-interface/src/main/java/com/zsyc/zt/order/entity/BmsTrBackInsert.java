package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_TR_BACK_INSERT")
public class BmsTrBackInsert extends Model<BmsTrBackInsert> {

    private static final long serialVersionUID = 1L;

    @TableField("SEQID")
    private Long seqid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("TYPE")
    private Integer type;

    @TableField("TRDTLID")
    private Long trdtlid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCEID")
    private Long sourceid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

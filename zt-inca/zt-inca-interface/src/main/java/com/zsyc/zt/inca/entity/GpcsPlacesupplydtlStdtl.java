package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author peiqy
 * @since 2020-01-13
 */
@TableName("GPCS_PLACESUPPLYDTL_STDTL")
@Data
@KeySequence("GPCS_PLACESUPPLYDTL_STDTL_SEQ")
@EqualsAndHashCode(callSuper = false)
public class GpcsPlacesupplydtlStdtl extends Model<GpcsPlacesupplydtlStdtl>{

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Integer goodsstatusid;

}

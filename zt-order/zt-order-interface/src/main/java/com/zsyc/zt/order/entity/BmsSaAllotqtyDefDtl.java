package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_SA_ALLOTQTY_DEF_DTL")
public class BmsSaAllotqtyDefDtl extends Model<BmsSaAllotqtyDefDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("DEFDTLID")
    private Long defdtlid;

    @TableField("DEFDOCID")
    private Long defdocid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("ALLOTQTY")
    private BigDecimal allotqty;

    @TableField("ACCSAQTY")
    private BigDecimal accsaqty;

    @TableField("MODIFYMANID")
    private Long modifymanid;

    @TableField("MODIFYDATE")
    private LocalDateTime modifydate;


    @Override
    protected Serializable pkVal() {
        return this.defdtlid;
    }

}

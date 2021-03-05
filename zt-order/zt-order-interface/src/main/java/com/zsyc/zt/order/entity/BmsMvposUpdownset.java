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
@TableName("BMS_MVPOS_UPDOWNSET")
public class BmsMvposUpdownset extends Model<BmsMvposUpdownset> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("DOWNQTY")
    private BigDecimal downqty;

    @TableField("DEFMVPACKQTY")
    private Integer defmvpackqty;

    @TableField("MVPOSDAYOUT")
    private BigDecimal mvposdayout;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

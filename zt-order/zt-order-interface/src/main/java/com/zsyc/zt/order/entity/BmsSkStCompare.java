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
@TableName("BMS_SK_ST_COMPARE")
public class BmsSkStCompare extends Model<BmsSkStCompare> {

    private static final long serialVersionUID = 1L;

    @TableId("DOCID")
    private Long docid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("STQTY")
    private BigDecimal stqty;

    @TableField("SKQTY")
    private BigDecimal skqty;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("BALANCE")
    private BigDecimal balance;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("USEMM")
    private Long usemm;

    @TableField("TMPID")
    private Long tmpid;


    @Override
    protected Serializable pkVal() {
        return this.docid;
    }

}

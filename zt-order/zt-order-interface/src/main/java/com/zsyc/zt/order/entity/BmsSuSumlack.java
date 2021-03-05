package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_SU_SUMLACK")
public class BmsSuSumlack extends Model<BmsSuSumlack> {

    private static final long serialVersionUID = 1L;

    @TableField("LACKID")
    private Long lackid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CONFIRMANID")
    private Long confirmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("MEMO1")
    private String memo1;

    @TableField("MEMO2")
    private String memo2;

    @TableField("MEMO3")
    private String memo3;

    @TableField("MEMO4")
    private String memo4;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

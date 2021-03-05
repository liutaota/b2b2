package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("BMS_SA_CONTODOC")
public class BmsSaContodoc extends Model<BmsSaContodoc> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CONDTLID",type = IdType.INPUT)
    private Long condtlid;

    @TableField("SALESDTLID")
    private Long salesdtlid;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("TOTAL_LINE")
    private Double totalLine;


    @Override
    protected Serializable pkVal() {
        return this.condtlid;
    }

}

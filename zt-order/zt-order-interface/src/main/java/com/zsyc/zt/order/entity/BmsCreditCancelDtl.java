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
@TableName("BMS_CREDIT_CANCEL_DTL")
public class BmsCreditCancelDtl extends Model<BmsCreditCancelDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("CREDITCANCELDTLID")
    private Long creditcanceldtlid;

    @TableField("CREDITCANCELDOCID")
    private Long creditcanceldocid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CREDIT_LEVEL")
    private Integer creditLevel;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ADJUSTITEM")
    private Integer adjustitem;

    @TableField("WFUSESTATUS")
    private Integer wfusestatus;

    @TableField("WFPROCESS")
    private Integer wfprocess;

    @TableField("WFMEMO")
    private String wfmemo;

    @TableField("DTLMEMO")
    private String dtlmemo;


    @Override
    protected Serializable pkVal() {
        return this.creditcanceldtlid;
    }

}

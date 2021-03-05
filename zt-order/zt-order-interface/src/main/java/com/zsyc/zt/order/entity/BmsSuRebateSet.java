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
@TableName("BMS_SU_REBATE_SET")
public class BmsSuRebateSet extends Model<BmsSuRebateSet> {

    private static final long serialVersionUID = 1L;

    @TableId("SETID")
    private Long setid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ACCREC")
    private BigDecimal accrec;

    @TableField("TYPE")
    private Integer type;

    @TableField("REBATEDOCID")
    private Long rebatedocid;

    @TableField("INSTANCEID")
    private Long instanceid;

    @TableField("CREATETYPE")
    private Integer createtype;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("CERTID")
    private Long certid;

    @TableField("ACCUSESTATUS")
    private Integer accusestatus;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.setid;
    }

}

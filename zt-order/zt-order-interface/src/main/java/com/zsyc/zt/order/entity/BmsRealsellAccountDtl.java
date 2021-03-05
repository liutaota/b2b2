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
@TableName("BMS_REALSELL_ACCOUNT_DTL")
public class BmsRealsellAccountDtl extends Model<BmsRealsellAccountDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("REALSELLDTLID")
    private Long realselldtlid;

    @TableField("REALSELLID")
    private Long realsellid;

    @TableField("BUSIDATE")
    private LocalDateTime busidate;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("DOCUMENTTYPE")
    private Integer documenttype;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("THISQTY")
    private BigDecimal thisqty;

    @TableField("THISMONEY")
    private BigDecimal thismoney;

    @TableField("NOSETTLEQTY")
    private BigDecimal nosettleqty;

    @TableField("NOSETTLEMONEY")
    private BigDecimal nosettlemoney;

    @TableField("SUQTY")
    private BigDecimal suqty;

    @TableField("SUPRICE")
    private BigDecimal suprice;

    @TableField("SUMONEY")
    private BigDecimal sumoney;

    @TableField("PAYMOTHOD")
    private Integer paymothod;

    @TableField("PAYPARAM")
    private String payparam;

    @TableField("SUDOCID")
    private Long sudocid;

    @TableField("SUDTLID")
    private Long sudtlid;

    @TableField("SETTLEDTLIDS")
    private String settledtlids;


    @Override
    protected Serializable pkVal() {
        return this.realselldtlid;
    }

}

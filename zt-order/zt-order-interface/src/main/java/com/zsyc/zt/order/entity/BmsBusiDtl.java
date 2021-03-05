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
@TableName("BMS_BUSI_DTL")
public class BmsBusiDtl extends Model<BmsBusiDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("BUSIACCID")
    private Long busiaccid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("REFUPQTY")
    private BigDecimal refupqty;

    @TableField("REFDOWNQTY")
    private BigDecimal refdownqty;

    @TableField("UPQTY")
    private BigDecimal upqty;

    @TableField("DOWNQTY")
    private BigDecimal downqty;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("D1AVGSALEQTY")
    private BigDecimal d1avgsaleqty;

    @TableField("D1PROPORTION")
    private BigDecimal d1proportion;

    @TableField("D2AVGSALEQTY")
    private BigDecimal d2avgsaleqty;

    @TableField("D2PROPORTION")
    private BigDecimal d2proportion;

    @TableField("D3AVGSALEQTY")
    private BigDecimal d3avgsaleqty;

    @TableField("D3PROPORTION")
    private BigDecimal d3proportion;

    @TableField("D4AVGSALEQTY")
    private BigDecimal d4avgsaleqty;

    @TableField("D4PROPORTION")
    private BigDecimal d4proportion;

    @TableField("COLLIGATEQTY")
    private BigDecimal colligateqty;

    @TableField("ISUNLOCK")
    private Integer isunlock;

    @TableField("LASTCALCMANID")
    private Long lastcalcmanid;

    @TableField("LASTCALCDATE")
    private LocalDateTime lastcalcdate;


    @Override
    protected Serializable pkVal() {
        return this.busiaccid;
    }

}

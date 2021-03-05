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
@TableName("BMS_BUSI_PARAM_DEF")
public class BmsBusiParamDef extends Model<BmsBusiParamDef> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("BUSIACCID")
    private Long busiaccid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("SETLEVEL")
    private Integer setlevel;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("AVGCALCMETHOD")
    private Integer avgcalcmethod;

    @TableField("ISRETURNQTY")
    private Integer isreturnqty;

    @TableField("ROUNDMETHOD")
    private Integer roundmethod;

    @TableField("ROUNDPRECISION")
    private Integer roundprecision;

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

    @TableField("NDAYSNOCALC")
    private Long ndaysnocalc;

    @TableField("DEFAFULTUPDAYS")
    private Long defafultupdays;

    @TableField("DEFAFULTDOWNDAYS")
    private Long defafultdowndays;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("COLLIGATEAVGSALERATIO")
    private BigDecimal colligateavgsaleratio;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

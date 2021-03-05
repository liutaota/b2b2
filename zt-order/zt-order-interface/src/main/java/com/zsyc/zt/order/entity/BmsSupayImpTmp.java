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
@TableName("BMS_SUPAY_IMP_TMP")
public class BmsSupayImpTmp extends Model<BmsSupayImpTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("TMPID")
    private Long tmpid;

    @TableField("DOCID")
    private Long docid;

    @TableField("IMPFLAG")
    private Integer impflag;

    @TableField("ERRORMESSAGE")
    private String errormessage;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("BANKDOCNO")
    private String bankdocno;

    @TableField("SETTLETYPEID")
    private Long settletypeid;

    @TableField("SETTLETYPE")
    private String settletype;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("TOAL_OUT")
    private BigDecimal toalOut;

    @TableField("BANKID")
    private Long bankid;

    @TableField("BANKNAME")
    private String bankname;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("AGENTNAME")
    private String agentname;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("EMPLOYEENAME")
    private String employeename;

    @TableField("ZXCOLNUM1")
    private String zxcolnum1;

    @TableField("ZXCOLNUM2")
    private String zxcolnum2;

    @TableField("ZXCOLNUM3")
    private String zxcolnum3;

    @TableField("ZXCOLNUM4")
    private String zxcolnum4;

    @TableField("ZXCOLNUM5")
    private String zxcolnum5;

    @TableField("ZXCOLNUM6")
    private String zxcolnum6;

    @TableField("ZXCOLNUM7")
    private String zxcolnum7;

    @TableField("ZXCOLNUM8")
    private String zxcolnum8;

    @TableField("ZXCOLNUM9")
    private String zxcolnum9;

    @TableField("ZXCOLNUM10")
    private String zxcolnum10;

    @TableField("ACCNO")
    private String accno;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}

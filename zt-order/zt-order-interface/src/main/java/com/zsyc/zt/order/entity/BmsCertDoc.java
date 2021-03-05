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
@TableName("BMS_CERT_DOC")
public class BmsCertDoc extends Model<BmsCertDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("CERTID")
    private Long certid;

    @TableField("CERTTYPEID")
    private Long certtypeid;

    @TableField("CERTNO")
    private String certno;

    @TableField("CERTDATE")
    private LocalDateTime certdate;

    @TableField("DEBITMONEY")
    private BigDecimal debitmoney;

    @TableField("CREDITMONEY")
    private BigDecimal creditmoney;

    @TableField("CERTMANID")
    private Long certmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("USEMM")
    private Long usemm;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("TOZWFLAG")
    private Long tozwflag;

    @TableField("SKCOSTFLAG")
    private Integer skcostflag;

    @TableField("VOUCHERNO")
    private String voucherno;

    @TableField("VOUCHERID")
    private Long voucherid;

    @TableField("CERTCLASS")
    private String certclass;


    @Override
    protected Serializable pkVal() {
        return this.certid;
    }

}

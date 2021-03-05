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
@TableName("BMS_SU_REMITTANCE_DOC")
public class BmsSuRemittanceDoc extends Model<BmsSuRemittanceDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("REMITTANCEID")
    private Long remittanceid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("PAYPLANDATE")
    private LocalDateTime payplandate;

    @TableField("PAYTYPE")
    private Long paytype;

    @TableField("APPLYMONEY")
    private BigDecimal applymoney;

    @TableField("APPROVALMONEY")
    private BigDecimal approvalmoney;

    @TableField("PAYEDMONEY")
    private BigDecimal payedmoney;

    @TableField("REASON")
    private String reason;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("APPLYID")
    private Long applyid;

    @TableField("APPLYTYPE")
    private Integer applytype;

    @TableField("APPLYDATE")
    private LocalDateTime applydate;

    @TableField("PAYABLEMONEY")
    private BigDecimal payablemoney;

    @TableField("BANKNAME")
    private String bankname;

    @TableField("BANKNO")
    private String bankno;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.remittanceid;
    }

}

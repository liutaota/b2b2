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
@TableName("BMS_CREDIT_TMP")
public class BmsCreditTmp extends Model<BmsCreditTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALERDEPTID")
    private Long salerdeptid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CREDIT")
    private BigDecimal credit;

    @TableField("BEGINDATE")
    private LocalDateTime begindate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("OWEMONEY")
    private BigDecimal owemoney;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

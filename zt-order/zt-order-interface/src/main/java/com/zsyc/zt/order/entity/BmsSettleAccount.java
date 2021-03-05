package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
@TableName("BMS_SETTLE_ACCOUNT")
public class BmsSettleAccount extends Model<BmsSettleAccount> {

    private static final long serialVersionUID = 1L;

    @TableId("USEDAY")
    private LocalDateTime useday;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("CHECKMEMO")
    private String checkmemo;

    @TableField("SETTLECERTID")
    private Long settlecertid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("HANDINCHECKFLAG")
    private Integer handincheckflag;

    @TableField("DAYCUTDATE")
    private LocalDateTime daycutdate;

    @TableField("SASETCERTFLAG")
    private Integer sasetcertflag;

    @TableField("DATACUTMANID")
    private Long datacutmanid;

    @TableField("DIAGNOSISCERTFLAG")
    private Integer diagnosiscertflag;


    @Override
    protected Serializable pkVal() {
        return this.useday;
    }

}

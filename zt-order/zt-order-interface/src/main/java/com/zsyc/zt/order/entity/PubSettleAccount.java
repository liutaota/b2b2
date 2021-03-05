package com.zsyc.zt.order.entity;

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
@TableName("PUB_SETTLE_ACCOUNT")
public class PubSettleAccount extends Model<PubSettleAccount> {

    private static final long serialVersionUID = 1L;

    @TableId("USEMM")
    private Long usemm;

    @TableField("USEYEAR")
    private Integer useyear;

    @TableField("USEMONTH")
    private Integer usemonth;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("ALLOWINIT")
    private Integer allowinit;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("LOGICMM")
    private Long logicmm;


    @Override
    protected Serializable pkVal() {
        return this.usemm;
    }

}

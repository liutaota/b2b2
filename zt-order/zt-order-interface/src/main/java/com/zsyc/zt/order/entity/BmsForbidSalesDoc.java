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
@TableName("BMS_FORBID_SALES_DOC")
public class BmsForbidSalesDoc extends Model<BmsForbidSalesDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("FORBIDID")
    private Long forbidid;

    @TableField("FORBIDFLAG")
    private Integer forbidflag;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.forbidid;
    }

}

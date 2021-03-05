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
@TableName("BMS_CHECK_DESK_MGR")
public class BmsCheckDeskMgr extends Model<BmsCheckDeskMgr> {

    private static final long serialVersionUID = 1L;

    @TableId("DESKID")
    private Long deskid;

    @TableField("DESKNO")
    private String deskno;

    @TableField("ORDERSEQ")
    private String orderseq;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("LASTCHECKDATE")
    private LocalDateTime lastcheckdate;

    @TableField("CHECKAREASID")
    private Long checkareasid;


    @Override
    protected Serializable pkVal() {
        return this.deskid;
    }

}

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
@TableName("BMS_GOODS_STATUS")
public class BmsGoodsStatus extends Model<BmsGoodsStatus> {

    private static final long serialVersionUID = 1L;

    @TableId("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("GOODSSTATUS")
    private String goodsstatus;

    @TableField("USEFLAG")
    private Integer useflag;

    @TableField("QUALITYSTATUS")
    private Integer qualitystatus;

    @TableField("PRIORITY")
    private Integer priority;

    @TableField("CANEDITFLAG")
    private Integer caneditflag;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;


    @Override
    protected Serializable pkVal() {
        return this.goodsstatusid;
    }

}

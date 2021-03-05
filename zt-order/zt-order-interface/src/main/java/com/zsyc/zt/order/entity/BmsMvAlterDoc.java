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
@TableName("BMS_MV_ALTER_DOC")
public class BmsMvAlterDoc extends Model<BmsMvAlterDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("ALTERID")
    private Long alterid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("KEEPDATE")
    private LocalDateTime keepdate;

    @TableField("KEEPMANID")
    private Long keepmanid;

    @TableField("REALFLAG")
    private Integer realflag;

    @TableField("ORGINVQTY")
    private BigDecimal orginvqty;

    @TableField("USESTATUS")
    private Integer usestatus;


    @Override
    protected Serializable pkVal() {
        return this.alterid;
    }

}

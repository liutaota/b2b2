package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_ST_RECEIVE_GOODS_DTL_TMP")
public class BmsStReceiveGoodsDtlTmp extends Model<BmsStReceiveGoodsDtlTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("STHOUSEID")
    private Long sthouseid;

    @TableField("RGGOODSDTLID")
    private Long rggoodsdtlid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableId("SEQID")
    private Long seqid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

package com.zsyc.zt.order.entity;

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
@TableName("BMS_ST_GOODS_LST")
public class BmsStGoodsLst extends Model<BmsStGoodsLst> {

    private static final long serialVersionUID = 1L;

    @TableId("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("ZEROFLAG")
    private Integer zeroflag;

    @TableField("DEFPOSID")
    private Long defposid;

    @TableField("MVMUSTUSEDEF")
    private Integer mvmustusedef;

    @TableField("QUONDAMID")
    private Long quondamid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("QUBUFFID")
    private Long qubuffid;


    @Override
    protected Serializable pkVal() {
        return this.storageid;
    }

}

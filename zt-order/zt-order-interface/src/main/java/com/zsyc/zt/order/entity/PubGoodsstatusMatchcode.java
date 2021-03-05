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
@TableName("PUB_GOODSSTATUS_MATCHCODE")
public class PubGoodsstatusMatchcode extends Model<PubGoodsstatusMatchcode> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("WMSGSTATUSCODE")
    private String wmsgstatuscode;

    @TableField("WMSGSTATUS")
    private String wmsgstatus;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

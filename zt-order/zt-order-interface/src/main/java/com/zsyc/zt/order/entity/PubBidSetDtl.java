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
@TableName("PUB_BID_SET_DTL")
public class PubBidSetDtl extends Model<PubBidSetDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("SETID")
    private Long setid;

    @TableId("SETDTLID")
    private Long setdtlid;

    @TableField("CUSTOMID")
    private Long customid;


    @Override
    protected Serializable pkVal() {
        return this.setdtlid;
    }

}

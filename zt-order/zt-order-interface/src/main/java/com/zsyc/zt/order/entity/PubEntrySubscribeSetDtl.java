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
@TableName("PUB_ENTRY_SUBSCRIBE_SET_DTL")
public class PubEntrySubscribeSetDtl extends Model<PubEntrySubscribeSetDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("SUBSCRIBESETDTLID")
    private Long subscribesetdtlid;

    @TableField("SUBSCRIBESETID")
    private Long subscribesetid;

    @TableField("SUBSQL")
    private String subsql;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.subscribesetdtlid;
    }

}

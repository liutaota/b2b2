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
@TableName("PUB_ENTRY_SUBSCRIBE_SET")
public class PubEntrySubscribeSet extends Model<PubEntrySubscribeSet> {

    private static final long serialVersionUID = 1L;

    @TableId("SUBSCRIBESETID")
    private Long subscribesetid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("DATATYPE")
    private Integer datatype;

    @TableField("SUBSCRIBETYPE")
    private Integer subscribetype;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;


    @Override
    protected Serializable pkVal() {
        return this.subscribesetid;
    }

}

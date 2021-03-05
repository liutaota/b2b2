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
@TableName("PUB_CUSTOM_TO_SALER")
public class PubCustomToSaler extends Model<PubCustomToSaler> {

    private static final long serialVersionUID = 1L;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALERDEPTID")
    private Long salerdeptid;

    @TableId("SEQID")
    private Long seqid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("ENTRYCUSTOMERID")
    private Long entrycustomerid;

    @TableField("ZX_FLAG")
    private Integer zxFlag;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

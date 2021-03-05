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
@TableName("PUB_TWOINVOICESYSTEM_DTL")
public class PubTwoinvoicesystemDtl extends Model<PubTwoinvoicesystemDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("SYSTEMDTLID")
    private Long systemdtlid;

    @TableField("SYSTEMID")
    private Long systemid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("FILECOUNT")
    private Long filecount;


    @Override
    protected Serializable pkVal() {
        return this.systemdtlid;
    }

}

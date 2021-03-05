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
@TableName("PUB_TWOINVOICESYSTEM_DOC")
public class PubTwoinvoicesystemDoc extends Model<PubTwoinvoicesystemDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SYSTEMID")
    private Long systemid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("GOODSLIST")
    private Integer goodslist;

    @TableField("MEMO")
    private String memo;

    @TableField("FILECOUNT")
    private Long filecount;


    @Override
    protected Serializable pkVal() {
        return this.systemid;
    }

}

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
@TableName("PUB_ENTRY_BANK")
public class PubEntryBank extends Model<PubEntryBank> {

    private static final long serialVersionUID = 1L;

    @TableId("ENTRYBANKID")
    private Long entrybankid;

    @TableField("BANKID")
    private Long bankid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SUBSCRIBESETDTLID")
    private Long subscribesetdtlid;

    @TableField("ENTRYBANKMEMO")
    private String entrybankmemo;

    @TableField("FINANCIALCODE")
    private String financialcode;


    @Override
    protected Serializable pkVal() {
        return this.entrybankid;
    }

}

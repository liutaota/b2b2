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
@TableName("BMS_CNS_ST")
public class BmsCnsSt extends Model<BmsCnsSt> {

    private static final long serialVersionUID = 1L;

    @TableId("STSEQID")
    private Long stseqid;

    @TableField("ENTRYSEQID")
    private Long entryseqid;

    @TableField("STORAGEID")
    private Long storageid;


    @Override
    protected Serializable pkVal() {
        return this.stseqid;
    }

}

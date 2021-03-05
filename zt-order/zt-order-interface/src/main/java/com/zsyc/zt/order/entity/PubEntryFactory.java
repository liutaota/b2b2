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
@TableName("PUB_ENTRY_FACTORY")
public class PubEntryFactory extends Model<PubEntryFactory> {

    private static final long serialVersionUID = 1L;

    @TableId("ENTRYFACTORYID")
    private Long entryfactoryid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FACTORYID")
    private Long factoryid;

    @TableField("GSPUSESTATUS")
    private Integer gspusestatus;

    @TableField("BMSUSESTATUS")
    private Integer bmsusestatus;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("FIRSTAPPROVEDATE")
    private LocalDateTime firstapprovedate;


    @Override
    protected Serializable pkVal() {
        return this.entryfactoryid;
    }

}

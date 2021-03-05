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
@TableName("PUB_SYSTEM_CONFIG")
public class PubSystemConfig extends Model<PubSystemConfig> {

    private static final long serialVersionUID = 1L;

    @TableId("SYSCONFIGID")
    private Long sysconfigid;

    @TableField("INTERTYPE")
    private Integer intertype;

    @TableField("INTERNAME")
    private String intername;

    @TableField("INTERVERSION")
    private String interversion;

    @TableField("RELEASEDATE")
    private LocalDateTime releasedate;

    @TableField("ISUSED")
    private Integer isused;

    @TableField("INSTALLSTATUS")
    private Integer installstatus;

    @TableField("INSTALLDATE")
    private LocalDateTime installdate;

    @TableField("INSTALLMANID")
    private Long installmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("USEFORPROVERSION")
    private String useforproversion;

    @TableField("ID")
    private String id;

    @TableField("NDBINAME")
    private String ndbiname;

    @TableField("UNZIPPATH")
    private String unzippath;

    @TableField("UPDATEDATE")
    private LocalDateTime updatedate;


    @Override
    protected Serializable pkVal() {
        return this.sysconfigid;
    }

}

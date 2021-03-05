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
@TableName("PUB_WMS_ERRORLOG")
public class PubWmsErrorlog extends Model<PubWmsErrorlog> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("MEMO")
    private String memo;

    @TableField("ERRORSTACK")
    private String errorstack;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ERRORTIME")
    private Integer errortime;

    @TableField("WMSID")
    private String wmsid;

    @TableField("TABLENAME")
    private String tablename;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

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
@TableName("PUB_ENTRY")
public class PubEntry extends Model<PubEntry> {

    private static final long serialVersionUID = 1L;

    @TableId("ENTRYID")
    private Long entryid;

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("ENTRYCOMPANYID")
    private Long entrycompanyid;

    @TableField("LEGALENTRYID")
    private Long legalentryid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("GOODSOWNERID")
    private String goodsownerid;

    @TableField("MEDICODE")
    private String medicode;

    @TableField("CATEGORYID")
    private Long categoryid;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("ZX_IPADDRESS")
    private String zxIpaddress;


    @Override
    protected Serializable pkVal() {
        return this.entryid;
    }

}

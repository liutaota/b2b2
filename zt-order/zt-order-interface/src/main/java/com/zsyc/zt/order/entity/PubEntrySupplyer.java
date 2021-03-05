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
@TableName("PUB_ENTRY_SUPPLYER")
public class PubEntrySupplyer extends Model<PubEntrySupplyer> {

    private static final long serialVersionUID = 1L;

    @TableId("ENTRYSUPPLYERID")
    private Long entrysupplyerid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GSPUSESTATUS")
    private Integer gspusestatus;

    @TableField("FIRSTAPPROVEDATE")
    private LocalDateTime firstapprovedate;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SUBSCRIBESETDTLID")
    private Long subscribesetdtlid;

    @TableField("ENTRYMEMO")
    private String entrymemo;

    @TableField("SUUESSTATUS")
    private Integer suuesstatus;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("ISMUSTAGENT")
    private Integer ismustagent;

    @TableField("ISMUSTCONTACT")
    private Integer ismustcontact;


    @Override
    protected Serializable pkVal() {
        return this.entrysupplyerid;
    }

}

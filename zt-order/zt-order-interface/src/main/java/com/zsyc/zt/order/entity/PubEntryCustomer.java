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
@TableName("PUB_ENTRY_CUSTOMER")
public class PubEntryCustomer extends Model<PubEntryCustomer> {

    private static final long serialVersionUID = 1L;

    @TableId("ENTRYCUSTOMERID")
    private Long entrycustomerid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GSPUSESTATUS")
    private Integer gspusestatus;

    @TableField("FIRSTAPPROVEDATE")
    private LocalDateTime firstapprovedate;

    @TableField("LOWPRICEFLAG")
    private Integer lowpriceflag;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("TRANPRIORITY")
    private String tranpriority;

    @TableField("TRANMETHODID")
    private Integer tranmethodid;

    @TableField("DELIVERMETHOD")
    private Integer delivermethod;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("REQPRINTQUFLAG")
    private Integer reqprintquflag;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("SUBSCRIBESETDTLID")
    private Long subscribesetdtlid;

    @TableField("SAUSESTATUS")
    private Integer sausestatus;

    @TableField("ENTRYMEMO")
    private String entrymemo;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("ISMUSTAGENT")
    private Integer ismustagent;

    @TableField("ISMUSTCONTACT")
    private Integer ismustcontact;

    @TableField("TWOINVFLAG")
    private Integer twoinvflag;

    @TableField("TWOINVMETHOD")
    private Integer twoinvmethod;

    @TableField("ZX_ISFIX")
    private Integer zxIsfix;

    @TableField("ZX_ISPRINT")
    private Integer zxIsprint;

    @TableField("ZX_DZ_DATE")
    private Long zxDzDate;

    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

    /**
     * ??????? 0 ? 1?
     */
    @TableField("ZX_PL_PRICE_FLAG")
    private Integer zxPlPriceFlag;


    @Override
    protected Serializable pkVal() {
        return this.entrycustomerid;
    }

}

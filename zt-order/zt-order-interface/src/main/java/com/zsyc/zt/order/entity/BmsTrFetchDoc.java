package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_TR_FETCH_DOC")
public class BmsTrFetchDoc extends Model<BmsTrFetchDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("FETCHID")
    private Long fetchid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("FETCHTYPE")
    private Integer fetchtype;

    @TableField("FETCHNO")
    private String fetchno;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("COMPANYNAME")
    private String companyname;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("TRANSFLAG")
    private Integer transflag;

    @TableField("TRPOSID")
    private Long trposid;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("MEMO")
    private String memo;

    @TableField("WFUSESTATUS")
    private Integer wfusestatus;

    @TableField("WFPROCESS")
    private Integer wfprocess;

    @TableField("WFMEMO")
    private String wfmemo;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("DELIVERMETHOD")
    private Integer delivermethod;

    @TableField("MIDPOSID")
    private Long midposid;

    @TableField("STORERID")
    private Long storerid;

    @TableField("NOWMSFLAG")
    private Integer nowmsflag;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("INVDEMAND")
    private Integer invdemand;

    @TableField("PLANINVDATE")
    private LocalDateTime planinvdate;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("EXPECTGETDATE")
    private LocalDateTime expectgetdate;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ZX_BH_FLAG")
    private Long zxBhFlag;

    @TableField("ZX_ORDERNUMBER")
    private String zxOrdernumber;

    @TableField("ZX_TOTAL")
    private BigDecimal zxTotal;

    @TableField("B2B_NOT_WRITE_BACK")
    private BigDecimal b2bNotWriteBack;


    @Override
    protected Serializable pkVal() {
        return this.fetchid;
    }

}

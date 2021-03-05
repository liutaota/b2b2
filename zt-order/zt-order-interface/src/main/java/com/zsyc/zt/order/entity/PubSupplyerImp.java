package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("PUB_SUPPLYER_IMP")
public class PubSupplyerImp extends Model<PubSupplyerImp> {

    private static final long serialVersionUID = 1L;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYOPCODE")
    private String supplyopcode;

    @TableField("SUPPLYNO")
    private String supplyno;

    @TableField("SUPPLYPINYIN")
    private String supplypinyin;

    @TableField("CORPCODE")
    private String corpcode;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("SUPPLYMEMO")
    private String supplymemo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("IMPORTFLAG")
    private Integer importflag;

    @TableField("CITYID")
    private Long cityid;

    @TableField("COUNTRYID")
    private Long countryid;

    @TableField("REGISTADD")
    private String registadd;

    @TableField("ADDRESS")
    private String address;

    @TableField("ISFARMERFLAG")
    private Integer isfarmerflag;

    @TableField("GSPFLAG")
    private Integer gspflag;

    @TableField("TAXNUMBER")
    private String taxnumber;

    @TableField("ZONE")
    private String zone;

    @TableField("LEGALPERSON")
    private String legalperson;

    @TableField("GSPCATEGORYID")
    private Long gspcategoryid;

    @TableField("MEDICODE")
    private String medicode;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("FMID")
    private Long fmid;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("MEMO")
    private String memo;

    @TableField("MEMO1")
    private String memo1;

    @TableField("ZX_TEL")
    private String zxTel;

    @TableField("ZX_TELNAME")
    private String zxTelname;

    @TableField("ZX_TYPE")
    private Integer zxType;

    @TableField("ZX_INVONFLAG")
    private Integer zxInvonflag;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

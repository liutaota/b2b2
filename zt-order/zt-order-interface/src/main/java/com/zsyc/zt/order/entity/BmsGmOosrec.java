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
@TableName("BMS_GM_OOSREC")
public class BmsGmOosrec extends Model<BmsGmOosrec> {

    private static final long serialVersionUID = 1L;

    @TableId("OOSRECID")
    private Long oosrecid;

    @TableField("OOSRECNO")
    private String oosrecno;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("REGISTERMANID")
    private Long registermanid;

    @TableField("OOSGOODSQTY")
    private BigDecimal oosgoodsqty;

    @TableField("OOSREASON")
    private String oosreason;

    @TableField("MEMO")
    private String memo;

    @TableField("PROCFLAG")
    private Integer procflag;

    @TableField("REACHDATE")
    private LocalDateTime reachdate;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("PROCMANID")
    private Long procmanid;

    @TableField("PROCDATE")
    private LocalDateTime procdate;

    @TableField("STOREDEPTID")
    private Long storedeptid;

    @TableField("USETYPE")
    private Integer usetype;

    @TableField("APPLYFLAG")
    private Integer applyflag;

    @TableField("APPLYTYPE")
    private Integer applytype;

    @TableField("QUALITY")
    private String quality;

    @TableField("ACCFLAG")
    private String accflag;

    @TableField("ZX_ENDDATE")
    private LocalDateTime zxEnddate;

    @TableField("ZX_REDATE")
    private LocalDateTime zxRedate;


    @Override
    protected Serializable pkVal() {
        return this.oosrecid;
    }

}

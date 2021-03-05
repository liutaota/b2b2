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
@TableName("PUB_STQTY_IMP_TMP")
public class PubStqtyImpTmp extends Model<PubStqtyImpTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("TMPID")
    private Long tmpid;

    @TableField("DOCID")
    private Long docid;

    @TableField("IMPFLAG")
    private Long impflag;

    @TableField("ERRORMESSAGE")
    private String errormessage;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("STORAGENAME")
    private String storagename;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("BATCHCREDATE")
    private LocalDateTime batchcredate;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("LOTNO")
    private String lotno;

    @TableField("PRODATE")
    private LocalDateTime prodate;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("POSID")
    private Long posid;

    @TableField("POSNO")
    private String posno;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("DEPUTYID")
    private Long deputyid;

    @TableField("DEPUTYNAME")
    private String deputyname;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("GOODSSTATUS")
    private String goodsstatus;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("FACTORYID")
    private Long factoryid;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("PACKNAME")
    private String packname;

    @TableField("PACKSIZE")
    private BigDecimal packsize;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}

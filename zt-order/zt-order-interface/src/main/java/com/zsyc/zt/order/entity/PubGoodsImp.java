package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("PUB_GOODS_IMP")
public class PubGoodsImp extends Model<PubGoodsImp> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("SUPPLYTAXRATE")
    private BigDecimal supplytaxrate;

    @TableField("SALESTAXRATE")
    private BigDecimal salestaxrate;

    @TableField("OPCODE")
    private String opcode;

    @TableField("GOODSPINYIN")
    private String goodspinyin;

    @TableField("ACCFLAG")
    private Integer accflag;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("GSPFLAG")
    private Integer gspflag;

    @TableField("ALONEPACKFLAG")
    private Integer alonepackflag;

    @TableField("SPECIALDRUG")
    private Integer specialdrug;

    @TableField("SPECIALCTRL")
    private Integer specialctrl;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("LOTFLAG")
    private Integer lotflag;

    @TableField("ZX_SPLIT_TYPE")
    private Integer zxSplitType;

    @TableField("ZX_TYPECODE")
    private String zxTypecode;

    @TableField("ZX_KPOPCODE")
    private String zxKpopcode;

    @TableField("ZX_OPCODE")
    private String zxOpcode;

    @TableField("ZX_GOODS_ID")
    private Long zxGoodsId;

    @TableField("BARCODE")
    private Long barcode;

    @TableField("GOODSNO")
    private String goodsno;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

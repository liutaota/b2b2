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
@TableName("PUB_ENTRY_GOODS")
public class PubEntryGoods extends Model<PubEntryGoods> {

    private static final long serialVersionUID = 1L;

    @TableId("ENTRYGOODSID")
    private Long entrygoodsid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GSPUSESTATUS")
    private Integer gspusestatus;

    @TableField("FIRSTSUDATE")
    private LocalDateTime firstsudate;

    @TableField("FIRSTAPPROVEDATE")
    private LocalDateTime firstapprovedate;

    @TableField("SUUESSTATUS")
    private Integer suuesstatus;

    @TableField("SAUSESTATUS")
    private Integer sausestatus;

    @TableField("LEASTSALEQTY")
    private BigDecimal leastsaleqty;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("GOODSINVNAME")
    private String goodsinvname;

    @TableField("SALESTAXRATE")
    private BigDecimal salestaxrate;

    @TableField("SUPPLYTAXRATE")
    private BigDecimal supplytaxrate;

    @TableField("CUSTOMSTAX")
    private BigDecimal customstax;

    @TableField("BMSUSESTATUS")
    private Integer bmsusestatus;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("SUBSCRIBESETDTLID")
    private Long subscribesetdtlid;

    @TableField("USEFLAG")
    private Integer useflag;

    @TableField("PURBEFOR")
    private Long purbefor;

    @TableField("PROBEFOR")
    private Long probefor;

    @TableField("SUMBEFOR")
    private Long sumbefor;

    @TableField("LEVELNUM")
    private String levelnum;

    @TableField("PLANSTRA")
    private Integer planstra;

    @TableField("AVERRAGETYPE")
    private Integer averragetype;

    @TableField("AVERRAGEINT")
    private Integer averrageint;

    @TableField("PURCHASENUM")
    private BigDecimal purchasenum;

    @TableField("PRODUCENUM")
    private BigDecimal producenum;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("DOUBLERG")
    private Integer doublerg;

    @TableField("DOUBLECHECK")
    private Integer doublecheck;

    @TableField("DOUBLEPICK")
    private Integer doublepick;

    @TableField("BANCASH")
    private Integer bancash;

    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

    @TableField("OLD_SUPPLYERID")
    private Long oldSupplyerid;

    /**
     * 0??? 1??
     */
    @TableField("ZX_KP_FLAG")
    private Integer zxKpFlag;

    @TableField("ZX_FLAG_LIN")
    private Integer zxFlagLin;

    /**
     * ????????
     */
    @TableField("ZX_JD_NUM_LIMIT")
    private BigDecimal zxJdNumLimit;

    /**
     * B2B??????
     */
    @TableField("ZX_B2B_NUM_LIMIT")
    private BigDecimal zxB2bNumLimit;

    @TableField("ENTRY_MEMO")
    private String entryMemo;


    @Override
    protected Serializable pkVal() {
        return this.entrygoodsid;
    }

}

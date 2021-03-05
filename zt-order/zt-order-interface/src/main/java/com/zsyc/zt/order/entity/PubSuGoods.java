package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("PUB_SU_GOODS")
public class PubSuGoods extends Model<PubSuGoods> {

    private static final long serialVersionUID = 1L;

    @TableField("ENTRYID")
    private BigDecimal entryid;

    @TableField("GOODSID")
    private BigDecimal goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("WBZDW")
    private String wbzdw;

    @TableField("BASEUNITQTY")
    private BigDecimal baseunitqty;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("BARCODE")
    private String barcode;

    @TableField("PFRJ")
    private BigDecimal pfrj;

    @TableField("PFJJ")
    private BigDecimal pfjj;

    @TableField("MESSAGE")
    private String message;

    @TableField("EMPLOYEENAME")
    private String employeename;

    @TableField("EMPLOYEEID")
    private BigDecimal employeeid;

    @TableField("SUPPLYID")
    private BigDecimal supplyid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("MEMO")
    private String memo;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("LASTPRICE")
    private BigDecimal lastprice;

    @TableField("FACTORYID")
    private BigDecimal factoryid;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("SU_GOODSQTY")
    private BigDecimal suGoodsqty;

    @TableField("LAST_FACTORYNAME")
    private String lastFactoryname;

    @TableField("JH_GOODSQTY")
    private BigDecimal jhGoodsqty;

    @TableField("LAST_PRICE")
    private BigDecimal lastPrice;

    @TableField("LAST_FACTORYID")
    private BigDecimal lastFactoryid;

    @TableField("DAY")
    private BigDecimal day;

    @TableId("PU_SU_ID")
    private BigDecimal puSuId;

    @TableField("GOODSID_GPS")
    private String goodsidGps;


    @Override
    protected Serializable pkVal() {
        return this.puSuId;
    }

}

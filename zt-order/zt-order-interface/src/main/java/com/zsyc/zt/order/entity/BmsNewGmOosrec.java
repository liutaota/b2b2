package com.zsyc.zt.order.entity;

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
@TableName("BMS_NEW_GM_OOSREC")
public class BmsNewGmOosrec extends Model<BmsNewGmOosrec> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("PRODAREA")
    private String prodarea;

    @TableField("MEDICINETYPENAME")
    private String medicinetypename;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("REGISTDOCNO")
    private String registdocno;

    @TableField("GOODSMEMO")
    private String goodsmemo;

    @TableField("SOURCEOFGOODS")
    private String sourceofgoods;

    @TableField("BUYQTY")
    private String buyqty;

    @TableField("PRICEINSTRUCTION")
    private String priceinstruction;

    @TableField("BUYER")
    private String buyer;

    @TableField("BUYERTEL")
    private String buyertel;

    @TableField("BUYERMEMO")
    private String buyermemo;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("INPUTDATE")
    private LocalDateTime inputdate;

    @TableField("DEALSTATUS")
    private Integer dealstatus;

    @TableField("DEALMANID")
    private Long dealmanid;

    @TableField("DEALDEPTID")
    private Long dealdeptid;

    @TableField("DEALDATE")
    private LocalDateTime dealdate;

    @TableField("DEALMEMO")
    private String dealmemo;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FILECOUNT")
    private Integer filecount;

    @TableId("OOSRECID")
    private Long oosrecid;

    @TableField("ISONSALE")
    private Integer isonsale;

    @TableField("GOODSOPCODE")
    private String goodsopcode;

    @TableField("FEEDBACKOBJ")
    private Integer feedbackobj;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("TRADEMARK")
    private String trademark;

    @TableField("GOODSEFFECT")
    private String goodseffect;

    @TableField("FEEDBACKREASON")
    private String feedbackreason;

    @TableField("PLACEPOINTNAME")
    private String placepointname;

    @TableField("BUYERID")
    private Long buyerid;


    @Override
    protected Serializable pkVal() {
        return this.oosrecid;
    }

}

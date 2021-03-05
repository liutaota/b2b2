package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("GPCS_PLACE_ACCEPT_DTL")
@ApiModel(value="GpcsPlaceAcceptDtl对象", description="")
@KeySequence(value = "GPCS_PLACE_ACCEPT_DTL_SEQ")
public class GpcsPlaceAcceptDtl extends Model<GpcsPlaceAcceptDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("ACCEPTDTLID")
    private Long acceptdtlid;

    @TableField("ACCEPTDOCID")
    private Long acceptdocid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDETAILID")
    private Long goodsdetailid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("PSGOODSQTY")
    private Double psgoodsqty;

    @TableField("ACCEPTQTY")
    private Double acceptqty;

    @TableField("OLDSUPPLYDTLID")
    private Long oldsupplydtlid;

    @TableField("OLDSUPPLYTINYID")
    private Long oldsupplytinyid;

    @TableField("PLACEPRICE")
    private Double placeprice;

    @TableField("ACCMONEY")
    private Double accmoney;

    @TableField("RESALEPRICE")
    private Double resaleprice;

    @TableField("RESALEMONEY")
    private Double resalemoney;

    @TableField("MEMO")
    private String memo;

    @TableField("ZXCOLUMN1")
    private String zxcolumn1;

    @TableField("ZXCOLUMN2")
    private String zxcolumn2;

    @TableField("ZXCOLUMN3")
    private String zxcolumn3;

    @TableField("ZXCOLUMN4")
    private String zxcolumn4;

    @TableField("ZXCOLUMN5")
    private String zxcolumn5;

    @TableField("ZXCOLUMN6")
    private Long zxcolumn6;

    @TableField("ZXCOLUMN7")
    private Long zxcolumn7;

    @TableField("ZXCOLUMN8")
    private Long zxcolumn8;

    @TableField("ZXCOLUMN9")
    private Double zxcolumn9;

    @TableField("ZXCOLUMN10")
    private Double zxcolumn10;

    @TableField("DESTTABLE")
    private Long desttable;

    @TableField("DESTDTLID")
    private Long destdtlid;

    @TableField("DIRECTSUDTLID")
    private Long directsudtlid;

    @TableField("DIRECTTABLE")
    private Long directtable;

    @TableField("DIRECTDTLID")
    private Long directdtlid;

    @TableField("BUNKERTOTAL")
    private Double bunkertotal;

    @TableField("BUNKERFLAG")
    private Integer bunkerflag;

    @TableField("UNQUALIFIEDQTY")
    private Double unqualifiedqty;

    @TableField("TRANSPORTID")
    private Long transportid;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("COLDEQUIP")
    private Integer coldequip;

    @TableField("STARTPLACE")
    private String startplace;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("REACHDATE")
    private LocalDateTime reachdate;

    @TableField("STARTTEMPERATURE")
    private Double starttemperature;

    @TableField("REACHTEMPERATURE")
    private Double reachtemperature;

    @TableField("ISREACH")
    private Integer isreach;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("CHECKMANID2")
    private Long checkmanid2;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("PENDING")
    private String pending;


}

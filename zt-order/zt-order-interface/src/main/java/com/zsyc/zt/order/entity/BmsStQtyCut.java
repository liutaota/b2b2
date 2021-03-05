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
@TableName("BMS_ST_QTY_CUT")
public class BmsStQtyCut extends Model<BmsStQtyCut> {

    private static final long serialVersionUID = 1L;

    @TableId("CUTID")
    private Long cutid;

    @TableField("CUTNO")
    private String cutno;

    @TableField("STORAGEIDS")
    private String storageids;

    @TableField("CUTDATE")
    private LocalDateTime cutdate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("STHOUSEIDS")
    private String sthouseids;

    @TableField("MEMO")
    private String memo;

    @TableField("JUDGEFLAG")
    private Integer judgeflag;

    @TableField("OFLSFLAG")
    private Integer oflsflag;

    @TableField("INPUTMETHOD")
    private Integer inputmethod;

    @TableField("GENFLAG")
    private Integer genflag;

    @TableField("GOODSDTLFLAG")
    private Integer goodsdtlflag;

    @TableField("BATCHFLAG")
    private Integer batchflag;

    @TableField("LOTFLAG")
    private Integer lotflag;

    @TableField("POSFLAG")
    private Integer posflag;

    @TableField("GOODSSTATUSFLAG")
    private Integer goodsstatusflag;

    @TableField("COUNTERID")
    private Long counterid;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("GOODSIDS")
    private String goodsids;

    @TableField("POSID")
    private String posid;

    @TableField("POSNOS")
    private String posnos;

    @TableField("STORAGENAMES")
    private String storagenames;

    @TableField("STHOUSENAMES")
    private String sthousenames;

    @TableField("GOODSNAMES")
    private String goodsnames;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("PDTYPE")
    private Integer pdtype;


    @Override
    protected Serializable pkVal() {
        return this.cutid;
    }

}

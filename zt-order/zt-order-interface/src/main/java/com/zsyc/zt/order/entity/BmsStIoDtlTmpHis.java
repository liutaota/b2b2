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
@TableName("BMS_ST_IO_DTL_TMP_HIS")
public class BmsStIoDtlTmpHis extends Model<BmsStIoDtlTmpHis> {

    private static final long serialVersionUID = 1L;

    @TableField("IODTLID")
    private Long iodtlid;

    @TableField("INOUTID")
    private Long inoutid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("DTLGOODSQTY")
    private BigDecimal dtlgoodsqty;

    @TableField("PICKDOCID")
    private Long pickdocid;

    @TableField("DTLPRINTNO")
    private String dtlprintno;

    @TableField("DTLPRINTLINE")
    private Integer dtlprintline;

    @TableField("GSPLSFLAG")
    private Integer gsplsflag;

    @TableField("GSPCALLBKFLAG")
    private Integer gspcallbkflag;

    @TableField("GSPFLAG")
    private Integer gspflag;

    @TableField("BOXSCOPE")
    private String boxscope;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("CHECKSTATUS")
    private Integer checkstatus;

    @TableField("BOXUPFLAG")
    private Integer boxupflag;

    @TableField("CHKQTY")
    private BigDecimal chkqty;

    @TableField("CHECKMANID2")
    private Long checkmanid2;

    @TableField("WMSPROFLAG")
    private Integer wmsproflag;

    @TableField("PUTAWAYFLAG")
    private Integer putawayflag;

    @TableField("GOODSUNITFLAG")
    private Integer goodsunitflag;

    @TableField("PICKFLAG")
    private Integer pickflag;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

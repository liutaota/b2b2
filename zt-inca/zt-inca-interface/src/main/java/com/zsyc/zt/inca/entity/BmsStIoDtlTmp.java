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
 * @since 2020-07-25
 */
@Data

@Accessors(chain = true)
@TableName("BMS_ST_IO_DTL_TMP")
@ApiModel(value="BmsStIoDtlTmp对象", description="")
@KeySequence(value = "BMS_ST_IO_DTL_SEQ")
public class BmsStIoDtlTmp extends Model<BmsStIoDtlTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("IODTLID")
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
    private Integer goodsstatusid;

    @TableField("DTLGOODSQTY")
    private Double dtlgoodsqty;

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
    private Double chkqty;

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

    @TableField("TASKID")
    private Long taskid;

    @TableField("ERPMERGENUMBER")
    private Long erpmergenumber;

    @TableField("ZX_FINISHMANID")
    private Long zxFinishmanid;

    @TableField("ZX_FINISHDATE")
    private LocalDateTime zxFinishdate;

    @TableField("ZX_CONTAINERID")
    private Long zxContainerid;

    @TableField("ZX_PICKQTY")
    private Double zxPickqty;

    @TableField("ZX_FINISH2MANID")
    private Long zxFinish2manid;

    @TableField("ZX_FINISH2DATE")
    private LocalDateTime zxFinish2date;

    @TableField("ZX_CONTAINERNUM")
    private String zxContainernum;

    @TableField("ZX_RELETEID")
    private Long zxReleteid;

    @TableField("ZX_BHRELETEID")
    private Long zxBhreleteid;

    @TableField("ZX_BOXNO")
    private String zxBoxno;


}

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
@TableName("BMS_ST_IO_DOC_INIT")
public class BmsStIoDocInit extends Model<BmsStIoDocInit> {

    private static final long serialVersionUID = 1L;

    @TableId("INOUTID")
    private Long inoutid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("KEEPDATE")
    private LocalDateTime keepdate;

    @TableField("KEEPMANID")
    private Long keepmanid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("INOUTFLAG")
    private Integer inoutflag;

    @TableField("INQTY")
    private BigDecimal inqty;

    @TableField("OLDQTY")
    private BigDecimal oldqty;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("QUONDAMID")
    private Long quondamid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("INPUTDATE")
    private LocalDateTime inputdate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("PASSFLAG")
    private Integer passflag;

    @TableField("UNITCOST")
    private BigDecimal unitcost;


    @Override
    protected Serializable pkVal() {
        return this.inoutid;
    }

}

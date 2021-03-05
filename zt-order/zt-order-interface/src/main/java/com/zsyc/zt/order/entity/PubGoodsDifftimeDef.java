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
@TableName("PUB_GOODS_DIFFTIME_DEF")
public class PubGoodsDifftimeDef extends Model<PubGoodsDifftimeDef> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("DEFREASON")
    private Integer defreason;

    @TableField("PERMITFLAG")
    private Integer permitflag;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("FORCEFLAG")
    private Integer forceflag;

    @TableField("STARTYEAR")
    private Integer startyear;

    @TableField("STARTMONTH")
    private Integer startmonth;

    @TableField("STARTDAY")
    private Integer startday;

    @TableField("ENDYEAR")
    private Integer endyear;

    @TableField("ENDMONTH")
    private Integer endmonth;

    @TableField("ENDDAY")
    private Integer endday;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

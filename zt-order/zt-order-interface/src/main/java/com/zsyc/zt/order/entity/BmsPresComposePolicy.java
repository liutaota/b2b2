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
@TableName("BMS_PRES_COMPOSE_POLICY")
public class BmsPresComposePolicy extends Model<BmsPresComposePolicy> {

    private static final long serialVersionUID = 1L;

    @TableId("POLICYID")
    private Long policyid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("TYPEID")
    private Long typeid;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("MODIFYMANID")
    private Long modifymanid;

    @TableField("MODIFYDATE")
    private LocalDateTime modifydate;

    @TableField("TOTAL")
    private BigDecimal total;

    @TableField("PRESGOODSID")
    private Long presgoodsid;

    @TableField("PRESGOODSQTY")
    private BigDecimal presgoodsqty;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("COMFRIMDATE")
    private LocalDateTime comfrimdate;

    @TableField("COMFRIMMANID")
    private Long comfrimmanid;


    @Override
    protected Serializable pkVal() {
        return this.policyid;
    }

}

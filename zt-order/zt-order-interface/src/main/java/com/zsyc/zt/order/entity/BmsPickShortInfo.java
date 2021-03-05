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
@TableName("BMS_PICK_SHORT_INFO")
public class BmsPickShortInfo extends Model<BmsPickShortInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("INFOID")
    private Long infoid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("SOURCEDTLID")
    private Long sourcedtlid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("COMPANYNAME")
    private String companyname;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("CHECKMANID2")
    private Long checkmanid2;

    @TableField("DESCKID")
    private Long desckid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("OLDGOODSQTY")
    private BigDecimal oldgoodsqty;

    @TableField("TARGETGOODSQTY")
    private BigDecimal targetgoodsqty;

    @TableField("MEMO")
    private String memo;

    @TableField("SHORTREASON")
    private Long shortreason;


    @Override
    protected Serializable pkVal() {
        return this.infoid;
    }

}

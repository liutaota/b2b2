package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_ST_STOREHOUSE")
public class BmsStStorehouse extends Model<BmsStStorehouse> {

    private static final long serialVersionUID = 1L;

    @TableId("STHOUSEID")
    private Long sthouseid;

    @TableField("STHOUSENO")
    private String sthouseno;

    @TableField("STHOUSENAME")
    private String sthousename;

    @TableField("DEFAULTMASTERID")
    private Long defaultmasterid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("STORERID")
    private Long storerid;

    @TableField("GOODSUNITFLAG")
    private Integer goodsunitflag;

    @TableField("POSTCOUNT")
    private Long postcount;

    @TableField("POSTUSEDCOUNT")
    private Long postusedcount;

    @TableField("RFPUTAWAYFLAG")
    private Integer rfputawayflag;

    @TableField("WORKMODEL")
    private Integer workmodel;

    @TableField("UNCHANGEPOS")
    private Integer unchangepos;


    @Override
    protected Serializable pkVal() {
        return this.sthouseid;
    }

}

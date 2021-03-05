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
@TableName("BMS_ST_RG_DOC_EMF")
public class BmsStRgDocEmf extends Model<BmsStRgDocEmf> {

    private static final long serialVersionUID = 1L;

    @TableId("RGID")
    private Long rgid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("STORERID")
    private Long storerid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("RGTYPE")
    private Integer rgtype;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FREIGHTBILLNO")
    private String freightbillno;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("ISOFCHECKED")
    private Integer isofchecked;

    @TableField("UPSTATUS")
    private Integer upstatus;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("LORDERID")
    private Long lorderid;


    @Override
    protected Serializable pkVal() {
        return this.rgid;
    }

}

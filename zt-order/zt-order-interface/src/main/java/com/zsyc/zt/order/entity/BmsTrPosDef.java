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
@TableName("BMS_TR_POS_DEF")
public class BmsTrPosDef extends Model<BmsTrPosDef> {

    private static final long serialVersionUID = 1L;

    @TableId("TRANPOSID")
    private Long tranposid;

    @TableField("OPCODE")
    private String opcode;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("TRANPOSNAME")
    private String tranposname;

    @TableField("POSORDER")
    private Integer posorder;

    @TableField("TRANPOSTYPE")
    private Integer tranpostype;

    @TableField("DISTANCE")
    private Integer distance;

    @TableField("ADDRESS")
    private String address;

    @TableField("TELEPHONE")
    private String telephone;

    @TableField("FAX")
    private String fax;

    @TableField("POSTCODE")
    private String postcode;

    @TableField("CONTRACTMAN")
    private String contractman;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("TRANSLINEID")
    private Long translineid;

    @TableField("TRANSORTNO")
    private Long transortno;

    @TableField("DEFAULTFLAG")
    private Integer defaultflag;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;
    @TableField("b2b_store_id")
    private Long b2bStoreId;

    @Override
    protected Serializable pkVal() {
        return this.tranposid;
    }

}

package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-24
 */
@Data

@Accessors(chain = true)
@TableName("BMS_TR_POS_DEF")
@ApiModel(value="BmsTrPosDef对象", description="")
@KeySequence(value = "BMS_TR_POS_DEF_SEQ")
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
    private Date sysModifydate;

    @TableField("TOWMSDATE")
    private Date towmsdate;

    @TableField("b2b_store_id")
    private Long b2bStoreId;

    @TableField("b2b_sub_custom_id")
    private Long b2bSubCustomId;


}

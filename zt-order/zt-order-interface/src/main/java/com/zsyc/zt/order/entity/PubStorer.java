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
@TableName("PUB_STORER")
public class PubStorer extends Model<PubStorer> {

    private static final long serialVersionUID = 1L;

    @TableId("STORERID")
    private Long storerid;

    @TableField("STOREROPCODE")
    private String storeropcode;

    @TableField("STORERNO")
    private String storerno;

    @TableField("MASTERID")
    private Long masterid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CHECKSEQUNCE")
    private Integer checksequnce;

    @TableField("PICKFINISHFLAG")
    private Integer pickfinishflag;

    @TableField("REAUTOCHECHFLAG")
    private Integer reautochechflag;

    @TableField("AUTOPREPAREINFLAG")
    private Integer autoprepareinflag;

    @TableField("AUTOPUTAWAYFLAG")
    private Integer autoputawayflag;

    @TableField("CHANGEQTYFLAG")
    private Integer changeqtyflag;

    @TableField("ISWMSMODE")
    private Integer iswmsmode;

    @TableField("UNITALLOCWITHNOUNITFLAG")
    private Integer unitallocwithnounitflag;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("CHECKMANID2")
    private Long checkmanid2;

    @TableField("USEWMS")
    private Integer usewms;

    @TableField("WMSCENTERUCODE")
    private String wmscenterucode;

    @TableField("FENQUTYPE")
    private Integer fenqutype;

    @TableField("BUSINESSTYPE")
    private Integer businesstype;


    @Override
    protected Serializable pkVal() {
        return this.storerid;
    }

}

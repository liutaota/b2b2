package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
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
@TableName("BMS_TR_DOC")
@KeySequence("BMS_TR_DOC_SEQ")
public class BmsTrDoc extends Model<BmsTrDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("TRID")
    private Long trid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("TARGETDATE")
    private LocalDateTime targetdate;

    @TableField("TRANFLAG")
    private Integer tranflag;

    @TableField("TRNO")
    private String trno;

    @TableField("FROMCOMPANYID")
    private Long fromcompanyid;

    @TableField("TOCOMPANYID")
    private Long customid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("TRANPRIORITY")
    private Integer tranpriority;

    @TableField("URGENTFLAG")
    private Integer urgentflag;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("TARGETPOSID")
    private Long targetposid;

    @TableField("MIDPOSID")
    private Long midposid;

    @TableField("SELFTRANTYPE")
    private Integer selftrantype;

    @TableField("TRANSLINEID")
    private Long translineid;

    @TableField("COLDSTORAGEFLAG")
    private Integer coldstorageflag;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("ADDRESS")
    private String address;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("ENTRYID")
    private Integer entryid;

    @TableField("COLLECTIONFLAG")
    private Integer collectionflag;

    @TableField("COLLECTMANID")
    private Long collectmanid;

    @TableField("COLLECTDATE")
    private LocalDateTime collectdate;

    @TableField("PRINTCOUNT")
    private Integer printcount;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("CHECKSTATUS")
    private Integer checkstatus;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.trid;
    }

}

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
@TableName("PUB_FACTORY")
public class PubFactory extends Model<PubFactory> {

    private static final long serialVersionUID = 1L;

    @TableId("FACTORYID")
    private Long factoryid;

    @TableField("FACTORYOPCODE")
    private String factoryopcode;

    @TableField("FACTORYNO")
    private String factoryno;

    @TableField("FACTORYPINYIN")
    private String factorypinyin;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("CORPCODE")
    private String corpcode;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("GSPFLAG")
    private Integer gspflag;

    @TableField("TAXNUMBER")
    private String taxnumber;

    @TableField("ZONE")
    private String zone;

    @TableField("LEGALPERSON")
    private String legalperson;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("POSSESSOR")
    private String possessor;


    @Override
    protected Serializable pkVal() {
        return this.factoryid;
    }

}

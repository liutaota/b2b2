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
@TableName("PUB_FACTORY_IMP_TMP")
public class PubFactoryImpTmp extends Model<PubFactoryImpTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("FACTORYID")
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

    @TableField("MEMO")
    private String memo;

    @TableField("IMPFLAG")
    private Integer impflag;

    @TableField("ERRORMESSAGE")
    private String errormessage;

    @TableField("DOCID")
    private Long docid;

    @TableId("TMPID")
    private Long tmpid;

    @TableField("ZXCOLNUM1")
    private String zxcolnum1;

    @TableField("ZXCOLNUM2")
    private String zxcolnum2;

    @TableField("ZXCOLNUM3")
    private String zxcolnum3;

    @TableField("ZXCOLNUM4")
    private String zxcolnum4;

    @TableField("ZXCOLNUM5")
    private String zxcolnum5;

    @TableField("ZXCOLNUM6")
    private String zxcolnum6;

    @TableField("ZXCOLNUM7")
    private String zxcolnum7;

    @TableField("ZXCOLNUM8")
    private String zxcolnum8;

    @TableField("ZXCOLNUM9")
    private String zxcolnum9;

    @TableField("ZXCOLNUM10")
    private String zxcolnum10;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}

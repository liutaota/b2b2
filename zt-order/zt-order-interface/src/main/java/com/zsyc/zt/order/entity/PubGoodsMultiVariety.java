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
@TableName("PUB_GOODS_MULTI_VARIETY")
public class PubGoodsMultiVariety extends Model<PubGoodsMultiVariety> {

    private static final long serialVersionUID = 1L;

    @TableId("VARIETYID")
    private Long varietyid;

    @TableField("PARENTVARIETYID")
    private Long parentvarietyid;

    @TableField("VARIETYNAME")
    private String varietyname;

    @TableField("VARIETYNO")
    private String varietyno;

    @TableField("VARIETYOPCODE")
    private String varietyopcode;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("MEMO")
    private String memo;

    @TableField("CLASSNO")
    private String classno;

    @TableField("VARLEVEL")
    private Integer varlevel;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CATEGORY")
    private Integer category;

    @TableField("ISLEAF")
    private Integer isleaf;

    @TableField("ENTRYFLAG")
    private Integer entryflag;

    @TableField("CLASSID")
    private Long classid;

    @TableField("CLASSDESC")
    private String classdesc;

    @TableField("EDITFLAG")
    private Integer editflag;


    @Override
    protected Serializable pkVal() {
        return this.varietyid;
    }

}

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
@TableName("PUB_GOODS_MULVARIT_DTL")
public class PubGoodsMulvaritDtl extends Model<PubGoodsMulvaritDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("VARIETYID")
    private Long varietyid;

    @TableId("VARIETYDTLID")
    private Long varietydtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("SQLRULE")
    private String sqlrule;

    @TableField("CLASSDESC")
    private String classdesc;

    @TableField("EDITFLAG")
    private Integer editflag;


    @Override
    protected Serializable pkVal() {
        return this.varietydtlid;
    }

}

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
@TableName("PUB_COMPANY_GOODS_LST")
public class PubCompanyGoodsLst extends Model<PubCompanyGoodsLst> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNO")
    private String goodsno;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("SUPAGENTID")
    private String supagentid;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}

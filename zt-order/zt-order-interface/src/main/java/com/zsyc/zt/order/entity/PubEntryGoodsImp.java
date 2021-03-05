package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("PUB_ENTRY_GOODS_IMP")
public class PubEntryGoodsImp extends Model<PubEntryGoodsImp> {

    private static final long serialVersionUID = 1L;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GSPUSESTATUS")
    private Integer gspusestatus;

    @TableField("SUUESSTATUS")
    private Integer suuesstatus;

    @TableField("SAUSESTATUS")
    private Integer sausestatus;

    @TableField("ZX_FLAG_LIN")
    private Integer zxFlagLin;

    @TableField("ENTRYGOODSID")
    private Long entrygoodsid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}

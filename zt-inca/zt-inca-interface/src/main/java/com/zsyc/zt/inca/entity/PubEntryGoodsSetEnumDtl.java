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
@TableName("PUB_ENTRY_GOODS_SET_ENUM_DTL")
@ApiModel(value="PubEntryGoodsSetEnumDtl对象", description="")
@KeySequence(value = "PUB_ENTRY_GOODS_SET_ENUM_DTL_SEQ")
public class PubEntryGoodsSetEnumDtl extends Model<PubEntryGoodsSetEnumDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("SETENUMDTLID")
    private Long setenumdtlid;

    @TableField("SETID")
    private Long setid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("IMPGOODSID")
    private Long impgoodsid;

    @TableField("IMPGOODSNAME")
    private String impgoodsname;

    @TableField("MEMO")
    private String memo;


}

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
@TableName("BMS_GOODS_FEATURE")
public class BmsGoodsFeature extends Model<BmsGoodsFeature> {

    private static final long serialVersionUID = 1L;

    @TableId("FEATUREID")
    private Long featureid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSCLASS")
    private Long goodsclass;

    @TableField("SCATTERPICKRATE")
    private Integer scatterpickrate;

    @TableField("WHOLEPICKRATE")
    private Integer wholepickrate;

    @TableField("GOODSCLASSMEMO")
    private String goodsclassmemo;

    @TableField("SCATTERPICKRATEMEMO")
    private String scatterpickratememo;

    @TableField("WHOLEPICKRATEMEMO")
    private String wholepickratememo;

    @TableField("MEMO")
    private String memo;

    @TableField("STORERID")
    private Long storerid;


    @Override
    protected Serializable pkVal() {
        return this.featureid;
    }

}

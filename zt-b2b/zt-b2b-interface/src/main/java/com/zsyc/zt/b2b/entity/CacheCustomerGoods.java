package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 客户可购货品缓存表
 * </p>
 *
 * @author MP
 * @since 2020-08-15
 */
@Data
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("CACHE_CUSTOMER_GOODS")
@ApiModel(value="CacheCustomerGoods对象", description="客户可购货品缓存表")
public class CacheCustomerGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    @TableField("CUSTOMER_ID")
    private Long customerId;

    /**
     * 货品ID
     */
    @ApiModelProperty(value = "货品ID")
    @TableField("GOODS_ID")
    private Long goodsId;


}

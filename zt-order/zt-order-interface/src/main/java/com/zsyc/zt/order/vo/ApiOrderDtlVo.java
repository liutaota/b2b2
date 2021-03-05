package com.zsyc.zt.order.vo;

import com.zsyc.zt.order.entity.ApiOrderDtl;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 对接订单数据细单
 * </p>
 *
 * @author MP
 * @since 2021-02-22
 */
@Data
@ApiModel(value="ApiOrderDtlVo对象", description="对接订单数据细单")
public class ApiOrderDtlVo extends ApiOrderDtl {

    private static final long serialVersionUID = 1L;

}

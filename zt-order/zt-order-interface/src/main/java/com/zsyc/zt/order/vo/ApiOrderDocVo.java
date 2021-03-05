package com.zsyc.zt.order.vo;

import com.zsyc.zt.order.entity.ApiOrderDoc;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 对接订单数据总单
 * </p>
 *
 * @author MP
 * @since 2021-02-22
 */
@Data
@ApiModel(value="ApiOrderDocVo对象", description="对接订单数据总单")
public class ApiOrderDocVo extends ApiOrderDoc {
    private static final long serialVersionUID = 1L;

    /**
     * 细单集合
     */
    List<ApiOrderDtlVo> orderInfoDtlList;

}

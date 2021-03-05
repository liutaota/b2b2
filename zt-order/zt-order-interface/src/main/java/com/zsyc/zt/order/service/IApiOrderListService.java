package com.zsyc.zt.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.order.entity.ApiOrderList;
import com.zsyc.zt.order.vo.ApiOrderListVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP
 * @since 2021-02-03
 */
public interface IApiOrderListService extends IService<ApiOrderList> {

    /**
     * 分页查询对接订单
     * @param page
     * @param apiOrderListVo
     * @return
     */
    IPage<ApiOrderListVo> selectListPage(Page<ApiOrderListVo> page, ApiOrderListVo apiOrderListVo);

    /**
     * ID查询对接订单信息
     * @param id
     * @return
     */
    ApiOrderListVo getApiOrder(Long id);

    /**
     * 批量删除对接订单
     * @param asList
     * @return
     */
    Integer deleteApiOrder(List<Long> asList);
}

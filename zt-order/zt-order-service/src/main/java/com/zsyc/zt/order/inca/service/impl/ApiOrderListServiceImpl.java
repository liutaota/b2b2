package com.zsyc.zt.order.inca.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.order.entity.ApiOrderList;
import com.zsyc.zt.order.inca.mapper.ApiOrderListMapper;
import com.zsyc.zt.order.service.IApiOrderListService;
import com.zsyc.zt.order.util.AssertExt;
import com.zsyc.zt.order.vo.ApiOrderListVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP
 * @since 2021-02-03
 */
@Service
public class ApiOrderListServiceImpl extends ServiceImpl<ApiOrderListMapper, ApiOrderList> implements IApiOrderListService {

    @Autowired
    private ApiOrderListMapper apiOrderListMapper;

    @Override
    public IPage<ApiOrderListVo> selectListPage(Page<ApiOrderListVo> page, ApiOrderListVo apiOrderListVo) {
        AssertExt.notNull(page,"分页参数为空");
        return apiOrderListMapper.selectListPage(page,apiOrderListVo);
    }

    @Override
    public ApiOrderListVo getApiOrder(Long id) {
        AssertExt.hasId(id,"ID为空");
        ApiOrderListVo vo = new ApiOrderListVo();
        ApiOrderList apiOrder = apiOrderListMapper.selectById(id);
        BeanUtils.copyProperties(apiOrder,vo);
        return vo;
    }

    @Override
    public Integer deleteApiOrder(List<Long> asList) {
        AssertExt.notEmpty(asList,"参数为空");
        return apiOrderListMapper.deleteBatchIds(asList);
    }
}

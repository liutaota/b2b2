package com.zsyc.zt.order.inca.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.order.entity.ApiOrderDtl;
import com.zsyc.zt.order.inca.mapper.ApiOrderDtlMapper;
import com.zsyc.zt.order.service.IApiOrderDtlService;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 * 对接订单数据细单 服务实现类
 * </p>
 *
 * @author MP
 * @since 2021-02-22
 */
@Service
public class ApiOrderDtlServiceImpl extends ServiceImpl<ApiOrderDtlMapper, ApiOrderDtl> implements IApiOrderDtlService {

}

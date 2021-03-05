package com.zsyc.zt.order.inca.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.order.entity.ApiOrderDoc;
import com.zsyc.zt.order.inca.mapper.ApiOrderDocMapper;
import com.zsyc.zt.order.service.IApiOrderDocService;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 * 对接订单数据总单 服务实现类
 * </p>
 *
 * @author MP
 * @since 2021-02-22
 */
@Service
public class ApiOrderDocServiceImpl extends ServiceImpl<ApiOrderDocMapper, ApiOrderDoc> implements IApiOrderDocService {

}

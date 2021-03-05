package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zsyc.zt.inca.entity.B2bOrderList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-17
 */
public interface B2bOrderListMapper extends BaseMapper<B2bOrderList> {

    void add1HintCount(@Param(Constants.WRAPPER)QueryWrapper<B2bOrderList> queryWrapper);
}

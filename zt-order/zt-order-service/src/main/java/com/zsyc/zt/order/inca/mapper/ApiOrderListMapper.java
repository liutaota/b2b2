package com.zsyc.zt.order.inca.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.order.entity.ApiOrderList;
import com.zsyc.zt.order.vo.ApiOrderListVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-19
 */
public interface ApiOrderListMapper extends BaseMapper<ApiOrderList> {

    void add1HintCount(@Param(Constants.WRAPPER) QueryWrapper<ApiOrderList> queryWrapper,@Param("message")String 下发成功);


    void updateSuccess(@Param(Constants.WRAPPER)QueryWrapper<ApiOrderList> queryWrapper, @Param("message")String 下发成功);

    void updateFailed(@Param(Constants.WRAPPER)QueryWrapper<ApiOrderList> queryWrapper, @Param("message")String message);

    /**
     * 分页查询对接订单
     * @param page
     * @param apiOrderListVo
     * @return
     */
    IPage<ApiOrderListVo> selectListPage(Page<ApiOrderListVo> page, @Param("apiOrderListVo") ApiOrderListVo apiOrderListVo);
}

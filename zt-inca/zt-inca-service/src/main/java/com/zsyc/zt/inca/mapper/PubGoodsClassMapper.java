package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.PubGoodsClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.vo.BusinessScopeVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-24
 */
public interface PubGoodsClassMapper extends BaseMapper<PubGoodsClass> {

    List<BusinessScopeVo> selectTopBusinessScope();
}

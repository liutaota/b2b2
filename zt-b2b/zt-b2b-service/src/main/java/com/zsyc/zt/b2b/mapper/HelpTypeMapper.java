package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.HelpType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.HelpTypeVo;

import java.util.List;

/**
 * <p>
 * 帮助类型 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
public interface HelpTypeMapper extends BaseMapper<HelpType> {
    /**
     * 帮助类型列表
     * @return
     */
    List<HelpType> getHelpTypeList();

    /**
     * 帮助类型列表
     * @return
     */
    List<HelpTypeVo> getHelpTypeVoList();
}

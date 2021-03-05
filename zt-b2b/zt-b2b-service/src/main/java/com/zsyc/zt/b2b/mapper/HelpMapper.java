package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.Help;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 帮助指南 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
public interface HelpMapper extends BaseMapper<Help> {
    /**
     * 帮助指南列表
     * @return
     */
    List<Help> getHelpList();
}

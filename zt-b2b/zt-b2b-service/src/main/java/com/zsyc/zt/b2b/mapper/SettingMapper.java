package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Setting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统设置 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
public interface SettingMapper extends BaseMapper<Setting> {
    /**
     * 参数设置列表
     * @param page
     * @param setting
     * @return
     */
    IPage<Setting> getSettingList(Page page, Setting setting);
}

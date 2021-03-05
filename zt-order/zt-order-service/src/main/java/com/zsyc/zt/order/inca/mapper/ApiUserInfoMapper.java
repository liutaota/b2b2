package com.zsyc.zt.order.inca.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.order.entity.ApiUserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.order.vo.ApiUserInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-20
 */
public interface ApiUserInfoMapper extends BaseMapper<ApiUserInfo> {

    /**
     * 分页查询对接账号信息
     * @param page
     * @param apiUserInfoVo
     * @return
     */
    IPage<ApiUserInfoVo> selectListPage(Page<ApiUserInfoVo> page, @Param("apiUserInfoVo") ApiUserInfoVo apiUserInfoVo);

    /**
     * 逻辑删除
     * @param ids
     * @return
     */
    Integer deleteUserInfos(@Param("ids") List<Long> ids);
}

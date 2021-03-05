package com.zsyc.zt.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.order.entity.ApiUserInfo;
import com.zsyc.zt.order.vo.ApiUserInfoVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP
 * @since 2021-02-03
 */
public interface IApiUserInfoService extends IService<ApiUserInfo> {

    /**
     * 分页查询对接账号信息
     * @param page
     * @param apiUserInfoVo
     * @return
     */
    IPage<ApiUserInfoVo> selectListPage(Page<ApiUserInfoVo> page, ApiUserInfoVo apiUserInfoVo);

    /**
     * 删除对接账号信息
     * @param ids
     * @return
     */
    Integer deleteUserInfos(List<Long> ids);

    /**
     * 修改对接账号信息
     * @param apiUserInfoVo
     * @return
     */
    Integer updateUserInfo(ApiUserInfoVo apiUserInfoVo);

    /**
     * 新增对接账户信息
     * @param apiUserInfoVo
     * @return
     */
    Integer saveUserInfo(ApiUserInfoVo apiUserInfoVo);

    /**
     * ID查询对接账号信息
     * @param id
     * @return
     */
    ApiUserInfoVo getApiUserInfo(Long id);

}

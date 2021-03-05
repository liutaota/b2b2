package com.zsyc.zt.order.inca.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.order.entity.ApiOrderList;
import com.zsyc.zt.order.entity.ApiUserInfo;
import com.zsyc.zt.order.inca.mapper.ApiUserInfoMapper;
import com.zsyc.zt.order.service.IApiUserInfoService;
import com.zsyc.zt.order.util.AssertExt;
import com.zsyc.zt.order.vo.ApiOrderListVo;
import com.zsyc.zt.order.vo.ApiUserInfoVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP
 * @since 2021-02-03
 */
@Service
public class ApiUserInfoServiceImpl extends ServiceImpl<ApiUserInfoMapper, ApiUserInfo> implements IApiUserInfoService {

    @Autowired
    private ApiUserInfoMapper apiUserInfoMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public IPage<ApiUserInfoVo> selectListPage(Page<ApiUserInfoVo> page, ApiUserInfoVo apiUserInfoVo) {
        AssertExt.notNull(page,"分页参数为空");
        return apiUserInfoMapper.selectListPage(page,apiUserInfoVo);
    }

    @Override
    public Integer deleteUserInfos(List<Long> ids) {
        AssertExt.notEmpty(ids,"数据ID为空");
        return apiUserInfoMapper.deleteUserInfos(ids);
    }

    @Override
    public Integer updateUserInfo(ApiUserInfoVo apiUserInfoVo) {
        AssertExt.isTrue(apiUserInfoVo != null && apiUserInfoVo.getId() != null,"账户信息未空");
        ApiUserInfo apiUserInfo = new ApiUserInfo();
        BeanUtils.copyProperties(apiUserInfoVo,apiUserInfo);
        if (apiUserInfo.getPassword() != null && !"".equals(apiUserInfo.getPassword())){    // 存在密码，进行加密
            apiUserInfo.setPassword(bCryptPasswordEncoder.encode(apiUserInfo.getPassword()));
        }
        return apiUserInfoMapper.updateById(apiUserInfo);
    }

    @Override
    public Integer saveUserInfo(ApiUserInfoVo apiUserInfoVo) {
        AssertExt.notNull(apiUserInfoVo,"数据为空");
        ApiUserInfo apiUserInfo = new ApiUserInfo();
        BeanUtils.copyProperties(apiUserInfoVo,apiUserInfo);
        apiUserInfo.setPassword(bCryptPasswordEncoder.encode(apiUserInfo.getPassword()));
        apiUserInfo.setCreateTime(LocalDateTime.now());
        apiUserInfo.setIsDel(2);
        return apiUserInfoMapper.insert(apiUserInfo);
    }

    @Override
    public ApiUserInfoVo getApiUserInfo(Long id) {
        AssertExt.hasId(id,"ID为空");
        ApiUserInfoVo vo = new ApiUserInfoVo();
        ApiUserInfo apiUserInfo = apiUserInfoMapper.selectById(id);
        BeanUtils.copyProperties(apiUserInfo,vo);
        return vo;
    }


}

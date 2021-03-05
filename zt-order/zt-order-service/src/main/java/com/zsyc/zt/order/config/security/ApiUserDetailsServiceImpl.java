package com.zsyc.zt.order.config.security;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.zt.order.entity.ApiUserInfo;
import com.zsyc.zt.order.inca.mapper.ApiUserInfoMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 报货平台 获取用户信息
 * @author peiqy
 */
@Service
public class ApiUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    ApiUserInfoMapper apiUserInfoMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<ApiUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ApiUserInfo::getUserName,username);
        ApiUserInfo one = apiUserInfoMapper.selectOne(queryWrapper);

        if(ObjectUtil.isNotNull(one)){
            return new ApiUserDetails(one.getUserName(),one.getPassword(),true,true,true,true,one.getCustomId(),one.getB2bCustomId());
        }

        return null;
    }
}

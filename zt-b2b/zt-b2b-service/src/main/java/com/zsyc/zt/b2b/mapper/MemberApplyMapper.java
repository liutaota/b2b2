package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.MemberApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.MemberApplyVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户注册申请 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-09-07
 */
public interface MemberApplyMapper extends BaseMapper<MemberApply> {
    /**
     * 企业认证列表
     *
     * @param page
     * @param memberApplyVo
     * @return
     */
    IPage<MemberApplyVo> getAuthenticationList(@Param("page") Page page, @Param("memberApplyVo") MemberApplyVo memberApplyVo);

    /**
     * 根据手机号查认证信息
     * @param telephone
     * @return
     */
    MemberApplyVo getTelephoneMemberApplyVo(@Param("telephone") String telephone);
}
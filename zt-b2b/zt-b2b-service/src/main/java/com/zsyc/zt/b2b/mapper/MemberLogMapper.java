package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.MemberLog;
import com.zsyc.zt.b2b.vo.MemberLogVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户日志 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-24
 */
public interface MemberLogMapper extends BaseMapper<MemberLog> {

    /**
     * 客户操作日志
     * @param page
     * @param memberLogVo
     * @return
     */
    IPage<MemberLogVo> getMemberLogList(@Param("page") Page page, @Param("memberLogVo") MemberLogVo memberLogVo);
}

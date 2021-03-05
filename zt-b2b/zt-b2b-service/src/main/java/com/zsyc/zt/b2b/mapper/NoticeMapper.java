package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 公告 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-09-01
 */
public interface NoticeMapper extends BaseMapper<Notice> {
    /**
     * 后台公告列表
     * @param page
     * @param content
     * @return
     */
    IPage<Notice> getAdminNoticeList(@Param("page") Page page,@Param("content") String content);

    /**
     * 前台公告列表
     * @param page
     * @return
     */
    IPage<Notice> getPcNoticeList(@Param("page")Page page);
}

package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Notice;
import com.zsyc.zt.b2b.vo.NoticeVo;

import java.util.List;

public interface NoticeService {

    /**
     * 新增公告
     * @param notice
     */
    void addNotice(Notice notice);

    /**
     * 后台公告列表
     * @param page
     * @param content
     * @return
     */
    IPage<Notice> getAdminNoticeList(Page page, String content);

    /**
     * 修改公告
     * @param notice
     */
    void updateNotice(Notice notice);

    /**
     * 是否启用
     * @param id
     * @param isUse
     */
    void updateNoticeIsUse(Long id, String isUse);

    /**
     * 是否删除
     * @param id
     */
    void noticeIsDel(Long id);

    /**
     * 公告置顶/置底排序
     * @param noticeVo
     */
    void updateNoticeSort(NoticeVo noticeVo);

    /**
     * 公告上下排序
     * @param noticeIdPrev
     * @param noticeIdNext
     */
    void noticeSort(Long noticeIdPrev, Long noticeIdNext);

    /**
     * 前台公告列表
     * @param page
     * @return
     */
    IPage<Notice> getPcNoticeList(Page page);

    /**
     * 不分页公告列表
     * @return
     */
    List<Notice> getPcNoticeListIsNotPage();
}

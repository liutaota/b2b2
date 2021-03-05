package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Notice;
import com.zsyc.zt.b2b.mapper.NoticeMapper;
import com.zsyc.zt.b2b.vo.FloorVo;
import com.zsyc.zt.b2b.vo.NoticeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void addNotice(Notice notice) {
        AssertExt.notBlank(notice.getContent(), "公告内容为空");
        AssertExt.notBlank(notice.getNoticeColor(), "颜色无效");
        AssertExt.notBlank(notice.getNoticeType(), "公告类型为空");
        AssertExt.notNull(notice.getStartTime(), "开始时间无效");
        AssertExt.notNull(notice.getEndTime(), "结束时间无效");
        List<Notice> noticeList = this.noticeMapper.selectList(new QueryWrapper<Notice>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<Integer> sortList = noticeList.stream().map(Notice::getSort).collect(Collectors.toList());
        if (sortList.size() < 1) {
            notice.setSort(1);
        } else {
            notice.setSort(Collections.max(sortList) + 1);
        }
        notice.setAddTime(LocalDateTime.now());
        notice.setCreateTime(LocalDateTime.now());
        this.noticeMapper.insert(notice);
    }

    @Override
    public IPage<Notice> getAdminNoticeList(Page page, String content) {
        return this.noticeMapper.getAdminNoticeList(page, content);
    }

    @Override
    public void updateNotice(Notice notice) {
        AssertExt.hasId(notice.getId(), "公告ID无效");
        AssertExt.notBlank(notice.getContent(), "公告内容为空");
        AssertExt.notBlank(notice.getNoticeColor(), "颜色无效");
        AssertExt.notBlank(notice.getNoticeType(), "公告类型为空");
        AssertExt.notNull(notice.getStartTime(), "开始时间无效");
        AssertExt.notNull(notice.getEndTime(), "结束时间无效");
        Notice noticeDB = this.noticeMapper.selectById(notice.getId());
        BeanUtils.copyProperties(notice, noticeDB);
        this.noticeMapper.updateById(noticeDB);
    }

    @Override
    public void updateNoticeIsUse(Long id, String isUse) {
        AssertExt.hasId(id, "公告ID无效");
        AssertExt.notBlank(isUse, "启用状态为空");
        AssertExt.checkEnum(Notice.EIsUse.class, isUse, "状态不匹配");
        Notice notice = this.noticeMapper.selectById(id);
        notice.setIsUse(isUse);
        this.noticeMapper.updateById(notice);
    }

    @Override
    public void noticeIsDel(Long id) {
        AssertExt.hasId(id, "公告ID无效");
        Notice notice = this.noticeMapper.selectById(id);
        AssertExt.notNull(notice, "公告不存在");
        AssertExt.isFalse(notice.getIsUse().equals(Notice.EIsUse.ON.val()), "公告正在启用，暂时无法删除，请先关闭再操作");
        notice.setIsDel(Constant.IS_DEL.YES);
        this.noticeMapper.updateById(notice);
    }

    @Override
    public void updateNoticeSort(NoticeVo noticeVo) {
        AssertExt.hasId(noticeVo.getId(), "公告id无效");
        AssertExt.notBlank(noticeVo.getSortType(), "排序类型为空");
        AssertExt.checkEnum(FloorVo.ESortType.class, noticeVo.getSortType(), "排序类型不匹配");
        Notice notice = this.noticeMapper.selectOne(new QueryWrapper<Notice>().eq("id", noticeVo.getId()));
        List<Notice> noticeList = this.noticeMapper.selectList(new QueryWrapper<>());
        List<Integer> sortList = noticeList.stream().map(Notice::getSort).collect(Collectors.toList());
        if (NoticeVo.ESortType.TOP.val().equals(noticeVo.getSortType())) {
            AssertExt.isFalse(notice.getSort() >= Collections.max(sortList), "此信息已在顶部");
            notice.setSort(Collections.max(sortList) + 1);
            this.noticeMapper.updateById(notice);
        } else {
            AssertExt.isFalse(notice.getSort() <= Collections.min(sortList), "此信息已在底部");
            notice.setSort(Collections.min(sortList) - 1);
            this.noticeMapper.updateById(notice);
        }
    }

    @Override
    public void noticeSort(Long noticeIdPrev, Long noticeIdNext) {
        AssertExt.hasId(noticeIdPrev, "noticeIdPrev无效");
        AssertExt.hasId(noticeIdNext, "noticeIdNext无效");
        Notice notice = this.noticeMapper.selectById(noticeIdPrev);
        Notice notice1 = this.noticeMapper.selectById(noticeIdNext);
        Integer sort = 0;
        sort = notice.getSort();
        notice.setSort(notice1.getSort());
        notice1.setSort(sort);
        this.noticeMapper.updateById(notice);
        this.noticeMapper.updateById(notice1);
    }

    @Override
    public IPage<Notice> getPcNoticeList(Page page) {
        return this.noticeMapper.getPcNoticeList(page);
    }

    @Override
    public List<Notice> getPcNoticeListIsNotPage() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return this.noticeMapper.selectList(new QueryWrapper<Notice>()
                .eq("IS_DEL", Constant.IS_DEL.NO)
                .eq("IS_USE", Notice.EIsUse.ON.val())
                .lt("START_TIME", localDateTime)
                .gt("END_TIME", localDateTime));
    }
}

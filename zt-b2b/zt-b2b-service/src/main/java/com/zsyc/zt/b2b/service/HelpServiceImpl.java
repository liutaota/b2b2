package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Floor;
import com.zsyc.zt.b2b.entity.Help;
import com.zsyc.zt.b2b.mapper.HelpMapper;
import com.zsyc.zt.b2b.vo.FloorVo;
import com.zsyc.zt.b2b.vo.HelpVo;
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
public class HelpServiceImpl implements HelpService {
    @Autowired
    private HelpMapper helpMapper;

    @Override
    public List<Help> getHelpList() {
        return this.helpMapper.getHelpList();
    }

    @Override
    public void addHelp(Help help, Long userId) {
        AssertExt.notNull(help.getHelpTitle(), "指南标题为空");
        AssertExt.notNull(help.getHelpInfo(), "指南内容为空");
        AssertExt.notNull(help.getHelpUrl(), "跳转链接为空");
        AssertExt.hasId(help.getTypeId(), "帮助类型无效");
        List<Help> helpList = this.helpMapper.selectList(new QueryWrapper<Help>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<String> titleName = helpList.stream().filter(help1 -> !help1.getId().equals(help.getId())).map(Help::getHelpTitle).collect(Collectors.toList());
        titleName.forEach(s -> AssertExt.isFalse(help.getHelpTitle().equals(s),"指南标题已被使用，请重新输入"));

        List<Integer> sortList = helpList.stream().map(Help::getHelpSort).collect(Collectors.toList());
        if (sortList.size() < 1) {
            help.setHelpSort(1);
        } else {
            help.setHelpSort(Collections.max(sortList) + 1);
        }
        help.setCreateUserId(userId);
        help.setCreateTime(LocalDateTime.now());
        this.helpMapper.insert(help);
    }

    @Override
    public void updateHelp(Help help, Long userId) {
        AssertExt.hasId(help.getId(), "ID无效");
        Help helpDB = this.helpMapper.selectById(help.getId());
        AssertExt.notNull(helpDB, "帮助指南不存在");
        List<Help> helpList = this.helpMapper.selectList(new QueryWrapper<Help>().eq("IS_DEL",Constant.IS_DEL.NO));
        List<String> titleName = helpList.stream().filter(help1 -> !help1.getId().equals(help.getId())).map(Help::getHelpTitle).collect(Collectors.toList());
        titleName.forEach(s -> AssertExt.isFalse(help.getHelpTitle().equals(s),"指南标题已被使用，请重新输入"));
        help.setUpdateUserId(userId);
        help.setUpdateTime(LocalDateTime.now());
        BeanUtils.copyProperties(help, helpDB);
        this.helpMapper.updateById(helpDB);
    }

    @Override
    public void updateHelpShow(Long id, String helpShow, Long userId) {
        AssertExt.hasId(id, "无效ID");
        Help help = this.helpMapper.selectById(id);
        AssertExt.notNull(help, "帮助指南不存在");
        AssertExt.notNull(helpShow, "显示状态为空");
        AssertExt.checkEnum(Help.EHelpShow.class, help.getHelpShow(), "显示状态不匹配");
        help.setHelpShow(helpShow);
        help.setUpdateUserId(userId);
        help.setUpdateTime(LocalDateTime.now());
        this.helpMapper.updateById(help);
    }

    @Override
    public void updateHelpIsDel(Long id, Long userId) {
        AssertExt.hasId(id, "无效ID");
        Help help = this.helpMapper.selectById(id);
        AssertExt.notNull(help, "帮助指南不存在");
        AssertExt.isFalse(help.getHelpShow().equals(Help.EHelpShow.ON.val()), "帮助指南正在展示，暂时无法删除，请先关闭再操作！");
        help.setIsDel(Constant.IS_DEL.YES);
        help.setUpdateUserId(userId);
        help.setUpdateTime(LocalDateTime.now());
        this.helpMapper.updateById(help);
    }

    @Override
    public void updateHelpSort(HelpVo helpVo, Long userId) {
        AssertExt.hasId(helpVo.getId(), "帮助指南id无效");
        AssertExt.notBlank(helpVo.getSortType(), "排序类型为空");
        AssertExt.checkEnum(FloorVo.ESortType.class, helpVo.getSortType(), "排序类型不匹配");
        Help help = this.helpMapper.selectOne(new QueryWrapper<Help>().eq("id", helpVo.getId()));
        List<Help> helpList = this.helpMapper.selectList(new QueryWrapper<Help>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<Integer> sortList = helpList.stream().map(Help::getHelpSort).collect(Collectors.toList());
        if (FloorVo.ESortType.TOP.val().equals(helpVo.getSortType())) {
            AssertExt.isFalse(help.getHelpSort() >= Collections.max(sortList), "此信息已在顶部");
            help.setHelpSort(Collections.max(sortList) + 1);
            this.helpMapper.updateById(help);
        } else {
            AssertExt.isFalse(help.getHelpSort() <= Collections.min(sortList), "此信息已在底部");
            help.setHelpSort(Collections.min(sortList) - 1);
            this.helpMapper.updateById(help);
        }
    }

    @Override
    public void helpSort(Long helpIdPrev, Long helpIdNext) {
        AssertExt.hasId(helpIdPrev, "helpIdPrev无效");
        AssertExt.hasId(helpIdNext, "helpIdNext无效");
        Help help = this.helpMapper.selectById(helpIdPrev);
        Help help1 = this.helpMapper.selectById(helpIdNext);
        Integer sort = 0;
        sort = help.getHelpSort();
        help.setHelpSort(help1.getHelpSort());
        help1.setHelpSort(sort);
        this.helpMapper.updateById(help);
        this.helpMapper.updateById(help1);
    }

    @Override
    public Help getHelpByIdInfo(Long id) {
        AssertExt.hasId(id, "id为空");
        Help helpDB = this.helpMapper.selectById(id);
        return helpDB;
    }

}

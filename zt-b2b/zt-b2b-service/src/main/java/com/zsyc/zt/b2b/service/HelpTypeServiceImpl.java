package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Help;
import com.zsyc.zt.b2b.entity.HelpType;
import com.zsyc.zt.b2b.mapper.HelpMapper;
import com.zsyc.zt.b2b.mapper.HelpTypeMapper;
import com.zsyc.zt.b2b.vo.FloorVo;
import com.zsyc.zt.b2b.vo.HelpTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class HelpTypeServiceImpl implements HelpTypeService {
    @Autowired
    private HelpTypeMapper helpTypeMapper;

    @Autowired
    private HelpMapper helpMapper;

    @Override
    public List<HelpType> getHelpTypeList() {
        return this.helpTypeMapper.getHelpTypeList();
    }

    @Override
    public void addHelpType(HelpType helpType, Long userId) {
        AssertExt.notBlank(helpType.getTypeName(), "帮助类型名称不为空");
        List<HelpType> helpTypeList = this.helpTypeMapper.selectList(new QueryWrapper<HelpType>().eq("IS_DEL",Constant.IS_DEL.NO));
        List<String> nameList = helpTypeList.stream().filter(helpType1 -> !helpType1.getId().equals(helpType.getId())).map(HelpType::getTypeName).collect(Collectors.toList());
        nameList.forEach(s -> AssertExt.isFalse(helpType.getTypeName().equals(s),"帮助类型名称已存在，请重新输入"));
        List<Integer> sortList = helpTypeList.stream().map(HelpType::getTypeSort).collect(Collectors.toList());
        if (sortList.size() < 1) {
            helpType.setTypeSort(1);
        } else {
            helpType.setTypeSort(Collections.max(sortList) + 1);
        }
        helpType.setCreateUserId(userId);
        helpType.setCreateTime(LocalDateTime.now());
        this.helpTypeMapper.insert(helpType);
    }

    @Override
    public void updateHelpType(HelpType helpType, Long userId) {
        AssertExt.hasId(helpType.getId(), "无效ID");
        HelpType helpTypeDB = this.helpTypeMapper.selectById(helpType.getId());
        AssertExt.notNull(helpTypeDB, "帮助类型不存在");
        List<HelpType> helpTypeList = this.helpTypeMapper.selectList(new QueryWrapper<HelpType>().eq("IS_DEL",Constant.IS_DEL.NO));
        List<String> nameList = helpTypeList.stream().filter(helpType1 -> !helpType1.getId().equals(helpType.getId())).map(HelpType::getTypeName).collect(Collectors.toList());
        nameList.forEach(s -> AssertExt.isFalse(helpType.getTypeName().equals(s),"帮助类型名称已存在，请重新输入"));
        helpType.setUpdateUserId(userId);
        helpType.setUpdateTime(LocalDateTime.now());
        BeanUtils.copyProperties(helpType, helpTypeDB);
        this.helpTypeMapper.updateById(helpType);
    }

    @Override
    public void updateHelpTypeShow(Long id, String helpShow, Long userId) {
        AssertExt.hasId(id, "ID无效");
        HelpType helpType = this.helpTypeMapper.selectById(id);
        AssertExt.notNull(helpType, "类型不存在");
        AssertExt.notNull(helpShow, "显示状态为空");
        AssertExt.checkEnum(HelpType.EHelpShow.class, helpType.getHelpShow(), "显示状态不匹配");
        helpType.setHelpShow(helpShow);
        helpType.setUpdateUserId(userId);
        helpType.setUpdateTime(LocalDateTime.now());
        this.helpTypeMapper.updateById(helpType);
    }

    @Override
    public void updateHelpTypeIsDel(Long id, Long userId) {
        AssertExt.hasId(id, "无效ID");
        HelpType helpType = this.helpTypeMapper.selectById(id);
        AssertExt.notNull(helpType, "类型为空");
        AssertExt.isFalse(helpType.getHelpShow().equals(HelpType.EHelpShow.ON.val()),"帮助类型正在展示，暂时无法删除，请先关闭再操作！");
        helpType.setIsDel(Constant.IS_DEL.YES);
        helpType.setUpdateUserId(userId);
        helpType.setUpdateTime(LocalDateTime.now());
        this.helpTypeMapper.updateById(helpType);
    }

    @Override
    public List<HelpTypeVo> getHelpTypeVoList() {
        List<HelpTypeVo> helpTypeVoList = this.helpTypeMapper.getHelpTypeVoList();

        helpTypeVoList.forEach(helpTypeVo -> {
            helpTypeVo.setHelpList(this.helpMapper.selectList(new QueryWrapper<Help>().eq("type_id", helpTypeVo.getId()).eq("IS_DEL", Constant.IS_DEL.NO).eq("HELP_SHOW", Help.EHelpShow.ON.val()).orderByDesc("HELP_SORT")));
        });
        return helpTypeVoList;
    }

    @Override
    public void updateHelpTypeSort(HelpTypeVo helpTypeVo, Long userId) {
        AssertExt.hasId(helpTypeVo.getId(), "帮助类型id无效");
        AssertExt.notBlank(helpTypeVo.getSortType(), "排序类型为空");
        AssertExt.checkEnum(FloorVo.ESortType.class, helpTypeVo.getSortType(), "排序类型不匹配");
        HelpType helpType = this.helpTypeMapper.selectOne(new QueryWrapper<HelpType>().eq("id", helpTypeVo.getId()));
        List<HelpType> helpTypeList = this.helpTypeMapper.selectList(new QueryWrapper<HelpType>().eq("IS_DEL",Constant.IS_DEL.NO));
        List<Integer> sortList = helpTypeList.stream().map(HelpType::getTypeSort).collect(Collectors.toList());
        if (FloorVo.ESortType.TOP.val().equals(helpTypeVo.getSortType())) {
            AssertExt.isFalse(helpType.getTypeSort() >= Collections.max(sortList),"此信息已在顶部");
            helpType.setTypeSort(Collections.max(sortList) + 1);
            this.helpTypeMapper.updateById(helpType);
        } else {
            AssertExt.isFalse(helpType.getTypeSort() <= Collections.min(sortList),"此信息已在顶部");
            helpType.setTypeSort(Collections.min(sortList) - 1);
            this.helpTypeMapper.updateById(helpType);
        }
    }

    @Override
    public void helpTypeSort(Long helpTypeIdPrev, Long helpTypeIdNext) {
        AssertExt.hasId(helpTypeIdPrev, "helpTypeIdPrev无效");
        AssertExt.hasId(helpTypeIdNext, "helpTypeIdNext无效");
        HelpType helpType = this.helpTypeMapper.selectById(helpTypeIdPrev);
        HelpType helpType1 = this.helpTypeMapper.selectById(helpTypeIdNext);
        Integer sort = 0;
        sort = helpType.getTypeSort();
        helpType.setTypeSort(helpType1.getTypeSort());
        helpType1.setTypeSort(sort);
        this.helpTypeMapper.updateById(helpType);
        this.helpTypeMapper.updateById(helpType1);
    }
}

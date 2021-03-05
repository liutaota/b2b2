package com.zsyc.zt.b2b.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Class;
import com.zsyc.zt.b2b.mapper.ClassMapper;
import com.zsyc.zt.b2b.vo.ClassImagesVo;
import com.zsyc.zt.fs.service.B2BFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
@Slf4j
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private B2BFileService fileService;

    @Override
    public void editClass(Class goodsClass) {
        AssertExt.notNull(goodsClass.getErpClassId(),"erp分类id为空");
        Class goodsClassDB = this.classMapper.selectOne(new QueryWrapper<Class>().eq("ERP_CLASS_ID",goodsClass.getErpClassId()));
        if (null != goodsClass.getClassImg()){
            String[] classImageList = goodsClass.getClassImg().split(",");
            String classImage = this.fileService.coverImageById(goodsClass.getErpClassId(),Constant.IMAGE_PREFIX.CLASS,classImageList);
            goodsClass.setClassImg(classImage);
            if (null != goodsClassDB){
                goodsClass.setId(goodsClassDB.getId());
                BeanUtils.copyProperties(goodsClass,goodsClassDB);
                this.classMapper.updateById(goodsClassDB);
            }else {
                goodsClass.setCreateTime(LocalDateTime.now());
                this.classMapper.insert(goodsClass);
            }
        }else {
            goodsClass.setClassImg("");
            if (null != goodsClassDB){
                goodsClass.setId(goodsClassDB.getId());
                BeanUtils.copyProperties(goodsClass,goodsClassDB);
                this.classMapper.updateById(goodsClassDB);
            }else {
                goodsClass.setCreateTime(LocalDateTime.now());
                this.classMapper.insert(goodsClass);
            }
        }
    }
}

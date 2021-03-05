package com.zsyc.zt.b2b.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.mapper.MemberMapper;
import com.zsyc.zt.b2b.mapper.NewProductMapper;
import com.zsyc.zt.b2b.vo.GoodsImagesVo;
import com.zsyc.zt.b2b.vo.NewProductImageVo;
import com.zsyc.zt.b2b.vo.NewProductVo;
import com.zsyc.zt.fs.service.B2BFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class NewProductServiceImpl implements NewProductService {
    @Autowired
    private NewProductMapper newProductMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private B2BFileService fileService;

    @Override
    public void newProductRegistration(NewProductVo newProduct) {
        AssertExt.notNull(newProduct.getGoodsName(), "请输入药品名称");
        AssertExt.notNull(newProduct.getSpec(), "请输入药品规格");
        AssertExt.notNull(newProduct.getNum(), "请输入需求数量");
        if (newProduct.getPhotos() != null) {
            List<NewProductImageVo> newProductImageVoList = JSONArray.parseArray(newProduct.getPhotos(), NewProductImageVo.class);
            AssertExt.isFalse(newProductImageVoList.size() > 3, "图片不可超过3张");
            String newProductImg = this.fileService.coverNewProductImage(newProduct.getApprovedocno(), newProductImageVoList, Constant.IMAGE_PREFIX.NEW_PRODUCT);
            newProduct.setPhotos(newProductImg);
        }
        newProduct.setCreateTime(LocalDateTime.now());
        this.newProductMapper.insert(newProduct);
    }

    @Override
    public IPage<NewProductVo> getNewProductList(Page page, NewProductVo newProductVo) {
        AssertExt.notNull(page, "页码为空");
        return this.newProductMapper.getNewProductList(page, newProductVo);
    }

    @Override
    public IPage<NewProductVo> getNewProducts(Page page, NewProductVo newProductVo) {
        AssertExt.notNull(page, "页码为空");
        return this.newProductMapper.getNewProducts(page, newProductVo);
    }
}

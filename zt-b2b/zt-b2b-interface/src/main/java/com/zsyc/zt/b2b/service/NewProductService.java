package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.NewProduct;
import com.zsyc.zt.b2b.vo.NewProductVo;

public interface NewProductService {
    /**
     * 新品登记
     * @param newProduct
     */
    void newProductRegistration(NewProductVo newProduct);

    /**
     * 后台新品登记列表
     * @param newProductVo
     * @param page
     * @return
     */
    IPage<NewProductVo> getNewProductList(Page page, NewProductVo newProductVo);

    /**
     * 前台新品登记列表
     * @param page
     * @param newProductVo
     * @return
     */
    IPage<NewProductVo> getNewProducts(Page page, NewProductVo newProductVo);
}

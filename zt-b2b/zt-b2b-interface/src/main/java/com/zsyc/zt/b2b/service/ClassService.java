package com.zsyc.zt.b2b.service;

import com.zsyc.zt.b2b.entity.Class;
import com.zsyc.zt.b2b.vo.GoodsClassTypeVo;

public interface ClassService {
    /**
     * 编辑商品分类
     * @param goodsClass
     */
    void editClass(Class goodsClass);
}

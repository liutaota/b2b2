package com.zsyc.zt.fapiao.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.fapiao.entity.BwkpGoods;

/**
 * <p>
 * ERP开票商品 服务类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
public interface BwkpGoodsService extends IService<BwkpGoods> {

     IPage  syncGoodsInfo();
}

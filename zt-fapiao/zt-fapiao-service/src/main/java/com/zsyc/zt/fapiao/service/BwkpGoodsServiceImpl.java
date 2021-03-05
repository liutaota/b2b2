package com.zsyc.zt.fapiao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.fapiao.entity.BwkpGoods;
import com.zsyc.zt.fapiao.mapper.BwkpGoodsMapper;
import com.zsyc.zt.inca.entity.PubGoods;
import com.zsyc.zt.inca.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 * ERP开票商品 服务实现类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Service
@Slf4j
public class BwkpGoodsServiceImpl extends ServiceImpl<BwkpGoodsMapper, BwkpGoods> implements BwkpGoodsService {

    @Reference
    GoodsService goodsService;

    @Reference
    SyncConfService syncConfService;

    @Override
    public IPage syncGoodsInfo(){
        IPage page = new Page<>();
        QueryWrapper<PubGoods> queryWrapper = new QueryWrapper();
        IPage<PubGoods> iPage = goodsService.selectGtTimePage(page, syncConfService.getGoodsSyncBeginTime());
        if(iPage.getCurrent()>0){
            iPage.getRecords().forEach(item->{
                BwkpGoods bwkpGoods = new BwkpGoods();
                bwkpGoods.setGoodsCode(String.valueOf(item.getGoodsid()));
                bwkpGoods.setGoodsName(String.valueOf(item.getGoodsname()));
                bwkpGoods.setPyChar(String.valueOf(item.getOpcode()));
                save(bwkpGoods);
            });
        }

        //syncConfService.updateGoodsSyncBeginTime(LocalDateTime.now());

        return  iPage;
    }

}

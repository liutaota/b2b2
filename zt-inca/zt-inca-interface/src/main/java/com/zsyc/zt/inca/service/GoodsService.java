package com.zsyc.zt.inca.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.inca.entity.BmsStQtyLst;
import com.zsyc.zt.inca.entity.GpcsPlaceReturnDtl;
import com.zsyc.zt.inca.entity.PubGoods;
import com.zsyc.zt.inca.vo.GoodsVo;
import com.zsyc.zt.inca.vo.ResultVo;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @date
 * @author peiqy
 */
public interface GoodsService {

    ResultVo valid(Long customId,Integer entryId, List<Long> goodsIds);

    /**
     * 校验货品资料
     * @param goodsIds
     * @return
     */
    ResultVo validGoodsData(List<Long> goodsIds);


    /**
     * 校验货品经营范围
     * @param customId
     * @param  goodsIds
     * @return
     */
    ResultVo validBusinessScope(Long customId,Integer entryId, List<Long> goodsIds);



    /**
     * 校验货品禁销，限销
     * @param customId
     * @param  goodsIds
     * @return
     */
    ResultVo validRestrictForbidSale(Long customId,Integer entryId,List<Long> goodsIds);


    /**
     * 校验货品 OCT标志
     * @param customId
     * @param entryId
     * @param  goodsIds
     * @return
     */
    ResultVo validOtc(Long customId,Integer entryId,List<Long> goodsIds);



/*    List<BmsStQtyLst> selectStockBy(Long customId, Long goodsId, String lotNo,Boolean isGift, List<Integer> storageList);*/

    List<BmsStQtyLst> selectStockBy(Long customId, Long goodsId, String lotNo,Integer goodsType, Integer storageId);

    Integer  getStockBy(Long customId, Long goodsId, String lotNo,Integer goodsType, Integer storageId);

    PubGoods getById(Long goodsId);


    List<GpcsPlaceReturnDtl> selectCanBackQtyBy(Long goodsId, Long lotId,Integer storageId);

    Double selectPriceBy(Long batchId);


    Double selectResalePriceBy(Long goodsId, Long customId);


    Double selectPriceBy(Long goodsId, Integer priceId, Integer entryId);

    /**
     * 获取商品信息
     * @param goodsId
     * @param entryId   独立单元  现在传1
     * @return
     */

    @Cached(name="GoodsService-getDetail-", key="#goodsId+''+#entryId", expire = 300)
    GoodsVo getDetail(Long goodsId,  Integer entryId);

    IPage<PubGoods> selectGtTimePage(IPage page, LocalDateTime goodsSyncBeginTime);

    /**
     * 查询商品(report模块)
     * @param page
     * @param goodsVo
     * @return
     */
    IPage<GoodsVo> selectGoodsList(Page<GoodsVo> page, GoodsVo goodsVo);

    /**
     * 过滤非中药药品IDs
     * @param goodsIds
     * @return
     */
    List<Long> selectExistsChineseMedicineIds(List<Long> goodsIds);

    /**
     * 查询计划新增商品
     * @param goodsIds
     * @return
     */
    List<GoodsVo> selectPlanGoodsList(List<Long> goodsIds);
}

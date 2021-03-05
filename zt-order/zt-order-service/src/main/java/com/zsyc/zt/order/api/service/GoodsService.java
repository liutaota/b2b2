package com.zsyc.zt.order.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.order.vo.GoodsVo;
import com.zsyc.zt.order.vo.ResultVo;
import com.zsyc.zt.order.entity.BmsStQtyLst;
import com.zsyc.zt.order.entity.GpcsPlacereturndtl;
import com.zsyc.zt.order.entity.PubGoods;

import java.util.List;


/**
 * @date
 * @author peiqy
 */
public interface GoodsService {

    ResultVo valid(Long customId, Integer entryId, List<Long> goodsIds);

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

    List<GpcsPlacereturndtl> selectQtyBy(Long goodsId, String lotId, String batchId, String storageId);

    Double selectPriceBy(String batchId);


    Double selectResalePriceBy(Long goodsId, Long customId);

    IPage<GoodsVo> selectPageList(Page page, List<Long> goodsIdList, String goodsName, String factoryName,Long customId);

    /**
     *  价钱
     * @param customId
     * @param goodsId
     * @param entryId
     * @return
     */
    Double getPrice(Long customId, Long goodsId, Integer entryId);
}

package com.zsyc.zt.order.api.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.order.api.service.CustomService;
import com.zsyc.zt.order.api.service.GoodsService;
import com.zsyc.zt.order.vo.CustomManageScopeVo;
import com.zsyc.zt.order.vo.CustomerVo;
import com.zsyc.zt.order.vo.GoodsVo;
import com.zsyc.zt.order.vo.ResultVo;
import com.zsyc.zt.order.entity.*;
import com.zsyc.zt.order.inca.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/7/24 14:09
 */
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Resource
    CustomService customService;


    @Resource
    PubGoodsMapper pubGoodsMapper;


    @Resource
    PubCustomerMapper pubCustomerMapper;


    @Resource
    PubGoodsClassMapper pubGoodsClassMapper;


    @Resource
    GspCompanyManagerageMapper gspCompanyManagerageMapper;


    @Resource
    GspCompanyLicenseMapper gspCompanyLicenseMapper;


    @Resource
    BmsForbidSalesMapper bmsForbidSalesMapper;

    @Resource
    BmsStQtyLstMapper bmsStQtyLstMapper;
    @Resource
    BmsBatchDefMapper bmsBatchDefMapper;
    @Resource
    PubGoodsPriceMapper pubGoodsPriceMapper;

    @Override
    public ResultVo valid(Long customId, Integer entryId, List<Long> goodsIds){

        ResultVo result = validGoodsData(goodsIds);
        result =  ResultVo.failAppend(result,validBusinessScope(customId,entryId,goodsIds));

        result =  ResultVo.failAppend(result,validRestrictForbidSale(customId,entryId,goodsIds));

        result =  ResultVo.failAppend(result,validOtc(customId,entryId,goodsIds));

        return result;
    }


    /**
     * 校验货品资料
     * @param goodsIds
     * @return
     */
    @Override
    public ResultVo validGoodsData(List<Long> goodsIds) {
        ResultVo result = null;
        Assert.notEmpty(goodsIds, "商品ID列表不能为空");
        QueryWrapper<PubGoods> queryWrapperGoods = new QueryWrapper<>();
        for (Long goodsId : goodsIds) {
            queryWrapperGoods.clear();
            queryWrapperGoods.lambda().eq(PubGoods::getGoodsid, goodsId);
            List<PubGoods> pubGoods = pubGoodsMapper.selectList(queryWrapperGoods);

            if (ObjectUtil.isEmpty(pubGoods)) {

                result = ResultVo.failErrorMessageAppend(result, "商品" + goodsId + "不存在：");
            }
        }
        return ResultVo.validReturn(result);
    }


    /**
     * 校验货品经营范围
      @param customId
     * @param goodsIds
     * @return
     */
    @Override
    public ResultVo validBusinessScope(Long customId,Integer entryId, List<Long> goodsIds) {
        ResultVo result = null;

        Assert.notNull(customId, "客户ID不能为空");

        Assert.notEmpty(goodsIds, "商品ID列表不能为空");

        outer: for (Long goodsId : goodsIds) {

            PubGoods pubGoods = pubGoodsMapper.selectById(goodsId);

            /**
             * 商品的经营类别
             */
            Long busiscope = pubGoods.getBusiscope();

            PubGoodsClass pubGoodsClass = pubGoodsClassMapper.selectById(busiscope);
            /**
             * 判定客户是否有经营类别，并且证照没有过期
             */
            List<CustomManageScopeVo> customManageScopeVos = gspCompanyManagerageMapper.selectBy(customId,entryId, busiscope, 1);

            if (ObjectUtil.isEmpty(customManageScopeVos)) {
                result = ResultVo.failErrorMessageAppend(result, "客户购买商品["+goodsId+","+pubGoods.getGoodsname() +"]，缺少"+ pubGoodsClass.getClassname() + "的经营范围：");
                continue;
            }

            /**
             * 判断证照有没有过期
             */
            for (CustomManageScopeVo customManageScopeVo : customManageScopeVos) {
                if (DateTime.now().toJdkDate().compareTo(customManageScopeVo.getValidenddate())<0) {
                    continue outer;
                }
            }

            result = ResultVo.failErrorMessageAppend(result, "客户购买商品["+goodsId+","+pubGoods.getGoodsname() +"]，缺少"+ pubGoodsClass.getClassname() + "的经营范围：");


        }



        return ResultVo.validReturn(result);
    }


    /**
     * 校验货品禁销，限销
     *
     * @param customId
     * @param goodsIds
     * @return
     */
    @Override
    public ResultVo validRestrictForbidSale(Long customId,Integer entryId, List<Long> goodsIds) {


        ResultVo result = null;

        Assert.notNull(customId, "客户ID不能为空");

        Assert.notEmpty(goodsIds, "商品ID列表不能为空");


        for (Long goodsId : goodsIds) {
            List<BmsForbidSales> bmsForbidSalesForbid =  bmsForbidSalesMapper.selectForbidBy(customId,goodsId,entryId,1);

            if (ObjectUtil.isEmpty(bmsForbidSalesForbid)) {
                continue;
            }
            for (BmsForbidSales bmsForbidSale : bmsForbidSalesForbid) {
                /**
                 *
                 */
                result = ResultVo.failErrorMessageAppend(result, "客户购买商品["+goodsId+"],被禁销集合"+bmsForbidSale.getForbidid() );
            }

            List<BmsForbidSales> bmsForbidSalesRestrict =  bmsForbidSalesMapper.selectRestrictBy(customId,goodsId,entryId,1);


            if (ObjectUtil.isEmpty(bmsForbidSalesRestrict)) {
                continue;
            }
            for (BmsForbidSales bmsForbidSale : bmsForbidSalesRestrict) {

                result = ResultVo.failErrorMessageAppend(result, "客户购买的商品["+goodsId+"],被仅销集合"+bmsForbidSale.getForbidid() );

            }

        }

        return ResultVo.validReturn(result);

    }

    @Override
    public ResultVo validOtc(Long customId, Integer entryId, List<Long> goodsIds) {

        ResultVo result = null;

        Assert.notNull(customId, "客户ID不能为空");

        Assert.notEmpty(goodsIds, "商品ID列表不能为空");

        /**peiqy 检查OTC flag 标志**/

        CustomerVo customer =  customService.getByCustomId(customId,entryId);

        for (Long goodsId : goodsIds) {
            PubGoods goods = pubGoodsMapper.selectById(goodsId);

            Assert.notNull(goods, "商品资料不存在!");

            Integer otcflag=goods.getOtcflag();

            if(ObjectUtil.isNull(otcflag)){
                log.info("货品无OTC标记");
               continue;
            }

            if(0==otcflag) {
                Integer zx_otcflag0=customer.getZxOtcflag0();
                if(!(1==zx_otcflag0)) {
                    result = ResultVo.failErrorMessageAppend(result,"商品["+goodsId+"]"+"有处方_凭处方零售标志,该客户没有维护处方_凭处方零售标志，无法继续！");

                }
            }else if(1 == otcflag) {
                Integer zx_otcflag1=customer.getZxOtcflag1();
                if(!(1==zx_otcflag1)) {
                    result = ResultVo.failErrorMessageAppend(result,"商品["+goodsId+"]"+"有非处方甲标志,该客户没有维护非处方甲标志，无法继续！");

                }
            }else if(2 == otcflag) {
                Integer zx_otcflag2=customer.getZxOtcflag2();
                if(!(1==zx_otcflag2)) {
                    result = ResultVo.failErrorMessageAppend(result,"商品["+goodsId+"]"+"有非处方乙标志,该客户没有维护非处方乙标志，无法继续！");

                }
            }else if(3 == otcflag) {
                Integer zx_otcflag3=customer.getZxOtcflag3();
                if(!(1==zx_otcflag3)) {
                    result = ResultVo.failErrorMessageAppend(result,"商品["+goodsId+"]"+"有处方_处方零售标志,该客户没有维护处方_处方零售标志，无法继续！");
                }
            }



        }


        return ResultVo.validReturn(result);
    }

//    @Override
//    public List<BmsStQtyLst> selectStockBy(Long customId, Long goodsId,String lotNo,Boolean isGift, List<Integer> storageList) {
//        return bmsStQtyLstMapper.selectStockBy(customId,goodsId,lotNo,isGift,storageList);
//    }


    @Override
    public List<BmsStQtyLst> selectStockBy(Long customId, Long goodsId, String lotNo, Integer goodsType, Integer storageId) {
        return bmsStQtyLstMapper.selectStockBy(customId, goodsId, lotNo, goodsType, storageId);
    }


    @Override
    public Integer getStockBy(Long customId, Long goodsId, String lotNo, Integer goodsType, Integer storageId) {

        return bmsStQtyLstMapper.getStockBy(customId, goodsId, lotNo, goodsType, storageId);
    }

    @Override
    public PubGoods getById(Long goodsId) {
        return pubGoodsMapper.selectById(goodsId);
    }

    @Override
    public List<GpcsPlacereturndtl> selectQtyBy(Long goodsId, String lotId, String batchId, String storageId) {
        return bmsStQtyLstMapper.selectQtyBy(goodsId, lotId, batchId, storageId);
    }

    @Override
    public Double selectPriceBy(String batchId) {
        return bmsBatchDefMapper.selectPriceBy(batchId);
    }

    @Override
    public Double selectResalePriceBy(Long goodsId, Long customId) {
        return pubGoodsPriceMapper.getResalePriceBy(goodsId,customId);
    }
    @Override
    public IPage<GoodsVo> selectPageList(Page page, List<Long> goodsIdList, String goodsName,String factoryName, Long customId) {
        return pubGoodsMapper.selectPageList(page, goodsIdList,goodsName, factoryName,customId);
    }

    @Override
    public Double getPrice(Long customId, Long goodsId, Integer entryId) {
        return pubGoodsMapper.getPrice(customId, goodsId,entryId);
    }

}

package com.zsyc.zt.b2b.service;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONArray;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.fs.service.B2BFileService;
import com.zsyc.zt.inca.service.CustomService;
import com.zsyc.zt.inca.vo.CustomBusinessScopeVo;
import com.zsyc.zt.inca.vo.GoodsVo;
import com.zsyc.zt.inca.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tang on 2020-07-27.
 */
//@Service(timeout = 60_000)
//@Transactional
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ArrivalNoticeMapper arrivalNoticeMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private B2BFileService fileService;
    @Autowired
    private EvaluateGoodsMapper evaluateGoodsMapper;
    @Reference
    private com.zsyc.zt.inca.service.GoodsService goodsService;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityGoodsMapper activityGoodsMapper;
    @Autowired
    private ActivityStrategyMapper activityStrategyMapper;
    @Autowired
    private ActivityGiftMapper activityGiftMapper;
    @Autowired
    private ActivitySetMapper activitySetMapper;
    @Autowired
    private AdminLogMapper adminLogMapper;
    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private CustomService customService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    @Cached(name = "GoodsService-getGoodsClassTypeVoList-", key = "#goodsClassTypeVo", expire = 1)
    public List<GoodsClassTypeVo> getGoodsClassTypeVoList(GoodsClassTypeVo goodsClassTypeVo) {
        List<GoodsClassTypeVo> goodsClassTypeVoIPage = this.goodsInfoMapper.getGoodsClassTypeVoList(new GoodsClassTypeVo());
        goodsClassTypeVoIPage.forEach(goodsClassTypeVo1 -> {
            goodsClassTypeVo1.setSubGoodsClassList(this.goodsInfoMapper.getGoodsClassTypeVoList(goodsClassTypeVo1));
            goodsClassTypeVo1.getSubGoodsClassList().forEach(goodsClassTypeVo2 -> {
                goodsClassTypeVo2.setSubGoodsClassList(this.goodsInfoMapper.getGoodsClassTypeVoList(goodsClassTypeVo2));

            });
        });
        return goodsClassTypeVoIPage;
    }

    @Override
    public IPage<GoodsInfoVo> getGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo) {
        //page.setDesc("goodsqty", goodsInfoVo.getSort());
        //page.setAsc("gv.goodsid", goodsInfoVo.getSort());
        AssertExt.hasId(goodsInfoVo.getMemberId(), "memberId为空");
        Member memberDB = this.memberMapper.selectById(goodsInfoVo.getMemberId());
        goodsInfoVo.setMemberId(memberDB.getErpUserId());
        if (null != goodsInfoVo.getGoodspinyin()) {
            goodsInfoVo.setGoodspinyin(goodsInfoVo.getGoodspinyin().trim());
        }
        goodsInfoVo.setWeek(LocalDateTime.now().getDayOfWeek().getValue());
        IPage<GoodsInfoVo> goodsInfoVoIPage = this.goodsInfoMapper.getGoodsInfoList(page, goodsInfoVo);
        List<CustomBusinessScopeVo> customerLicenseVoList = new ArrayList<>();
        if (null != goodsInfoVo.getBusiscopeName() && goodsInfoVo.getBusiscopeName().equals("busiscopeName")) {
            customerLicenseVoList = this.customService.selectBusinessScopeByCustomId(memberDB.getErpUserId());
        }
        if (null != goodsInfoVo.getFromSearch() && goodsInfoVo.getFromSearch().equals("validity")) {
            List<CustomBusinessScopeVo> finalCustomerLicenseVoList = customerLicenseVoList;
            goodsInfoVoIPage.getRecords().forEach(goodsInfoVo1 -> {
                goodsInfoVo.setGoodsid(goodsInfoVo1.getGoodsid());
                goodsInfoVo1.setActivityVoList(this.activityMapper.getActivityByGoods(goodsInfoVo1.getGoodsid(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));
                List<GoodsInfoVo> goodsInfoVo2 = this.goodsInfoMapper.getValidityGoodsInfo(goodsInfoVo);
                if (null != goodsInfoVo2) {
                    goodsInfoVo1.setVGoodsInfoVo(goodsInfoVo2);
                }
                if (finalCustomerLicenseVoList.size() > 0) {
                    if (!finalCustomerLicenseVoList.stream().anyMatch(item -> item.getScopeId().equals(goodsInfoVo1.getBusiscope()))) {
                        goodsInfoVo1.setBusiscopeName("缺少" + goodsInfoVo1.getBusiscopeName() + "经营范围");
                    }
                }
            });
        }



      /*  if (null != goodsInfoVo.getGoodspinyin()) {
            List<GoodsInfoVo> goodsInfoVoList = this.goodsInfoMapper.getGoodsInfoList(goodsInfoVo);
            if (goodsInfoVo.getGoodspinyin().matches("[0-9]+")) {
                goodsInfoVo.setGoodsid(Long.parseLong(goodsInfoVo.getGoodspinyin()));
                goodsInfoVo.setGoodspinyin(null);
                List<GoodsInfoVo> goodsInfoVoIPage1 = this.goodsInfoMapper.getGoodsInfoList(goodsInfoVo);
                if (null != goodsInfoVoIPage1 && goodsInfoVoIPage1.size() > 0) {
                    goodsInfoVoList.forEach(goodsInfoVo1 -> {
                        if (!goodsInfoVoIPage1.stream().anyMatch(item -> item.getGoodsid().equals(goodsInfoVo1.getGoodsid()))) {
                            goodsInfoVoIPage1.add(goodsInfoVo1);
                        }
                    });

                    *//*goodsInfoVoList.forEach(goodsInfoVo1 -> {
                            goodsInfoVoIPage1.add(goodsInfoVo1);
                    });
                    List<GoodsInfoVo>  goodsInfoVoList2=   goodsInfoVoIPage1.stream().collect(
                            collectingAndThen(
                                    toCollection(() -> new TreeSet<>(comparingLong(GoodsInfoVo::getGoodsid))), ArrayList::new)
                    );*//*
                    goodsInfoVoIPage.setRecords(goodsInfoVoIPage1);
                    return goodsInfoVoIPage;
                }

            } else {
                goodsInfoVo.setGoodsname(goodsInfoVo.getGoodspinyin());
                goodsInfoVo.setGoodspinyin(null);
                List<GoodsInfoVo> goodsInfoVoIPage2 = this.goodsInfoMapper.getGoodsInfoList(goodsInfoVo);
                if (null != goodsInfoVoIPage2 && goodsInfoVoIPage2.size() > 0) {
                    goodsInfoVoList.forEach(goodsInfoVo1 -> {
                        if (!goodsInfoVoIPage2.stream().anyMatch(item -> item.getGoodsid().equals(goodsInfoVo1.getGoodsid()))) {
                            goodsInfoVoIPage2.add(goodsInfoVo1);
                        }
                    });
                    //List<GoodsInfoVo>  goodsInfoVoList2 = goodsInfoVoIPage2.stream().distinct().collect(Collectors.toList());
                    goodsInfoVoIPage.setRecords(goodsInfoVoIPage2);
                    return goodsInfoVoIPage;
                }

            }
        }
*/
        return goodsInfoVoIPage;
    }

    @Override
    public IPage<GoodsInfoVo> getIntegralGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo) {
        Member memberDB = this.memberMapper.selectById(goodsInfoVo.getMemberId());
        goodsInfoVo.setMemberId(memberDB.getErpUserId());
        return this.goodsInfoMapper.getIntegralGoodsInfoList(page, goodsInfoVo);
    }

    @Override
    public IPage<GoodsInfoVo> getNewGoodsInfoVoList(Page page, GoodsInfoVo goodsInfoVo) {
        Member memberDB = this.memberMapper.selectById(goodsInfoVo.getMemberId());
        goodsInfoVo.setMemberId(memberDB.getErpUserId());
        IPage<GoodsInfoVo> goodsInfoVoIPage = this.goodsInfoMapper.getNewGoodsInfoVoList(page, goodsInfoVo);
        goodsInfoVoIPage.getRecords().forEach(goodsInfoVo1 -> {
            goodsInfoVo1.setActivityVoList(this.activityMapper.getActivityByGoods(goodsInfoVo1.getGoodsid(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));
        });

        return goodsInfoVoIPage;
    }

    @Override
    public List<FactoryVo> getFactoryVoList(GoodsInfoVo goodsInfoVo) {
        Member memberDB = this.memberMapper.selectById(goodsInfoVo.getMemberId());
        goodsInfoVo.setMemberId(memberDB.getErpUserId());
        List<FactoryVo> factoryVoList = this.factoryMapper.getFactoryVoList(goodsInfoVo);
        if (null != goodsInfoVo.getValidity() && goodsInfoVo.getValidity().equals("V")) {
            factoryVoList = this.factoryMapper.getValidityFactoryVoList(goodsInfoVo);
        }
/*
        List<FactoryVo> factoryVoList1 = new ArrayList<>();
        if (factoryVoList.size() > 1) {
            factoryVoList.forEach(factoryVo -> {
                if (null == factoryVo.getFactoryid()) return;

                if (!factoryVoList1.stream().anyMatch(item -> item.getFactoryid().equals(factoryVo.getFactoryid()))) {
                    factoryVoList1.add(factoryVo);
                }

            });
            return factoryVoList1;
        }*/

        return factoryVoList;
    }

    @Override
    public IPage<GoodsInfoVo> getAdminGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo) {
        AssertExt.notNull(page, "页码不为空");
        IPage<GoodsInfoVo> goodsVoIPage = this.goodsInfoMapper.getAdminGoodsInfoList(page, goodsInfoVo);
        log.info("page size {}", goodsVoIPage.getSize());
        return goodsVoIPage;
    }

    @Override
    public GoodsInfoVo getGoodsInfo(Long goodsid, Long customerId, Long lotid, Integer storageId) {
        AssertExt.hasId(goodsid, "goodsid为空");
        Member memberDB = this.memberMapper.selectById(customerId);
        AssertExt.notNull(memberDB, "客户不存在");
        AssertExt.notNull(storageId, "保管帐id为空");
        GoodsInfoVo goodsInfoVo = this.goodsInfoMapper.getGoodsInfo(goodsid, memberDB.getErpUserId(), storageId);

        Long[] storageIds = Constant.STO_RANGE_IDS;


        if (storageId == 5) {
            storageIds = Constant.STO_RANGE_IDS_V;
            goodsInfoVo = this.goodsInfoMapper.getValidityGoodsInfoById(goodsid, memberDB.getErpUserId(), lotid);
        } else if (storageId == 691) {
            storageIds = new Long[]{691L};
            goodsInfoVo = this.goodsInfoMapper.getIntegralGoodsInfo(goodsid, customerId);
        } else {
            goodsInfoVo.setActivityVoList(this.getActivityVoById(null, goodsInfoVo.getGoodsid(), memberDB.getErpUserId(), 1));
        }
        //log.info("goodsInfoVo.getGoodsid(){}", goodsInfoVo.getGoodsid());
        List<GoodsClassTypeVo> goodsClassTypeVoList = new ArrayList<>();
        if (null != goodsInfoVo.getClassnRow3()) {
            goodsClassTypeVoList = this.goodsInfoMapper.goodsClassTypeVoList(goodsInfoVo.getClassnRow3());
            goodsInfoVo.setGoodsClassTypeVoList(goodsClassTypeVoList);
        } else {
            goodsInfoVo.setGoodsClassTypeVoList(goodsClassTypeVoList);
        }
        if (null != goodsInfoVo.getId()) {
            goodsInfoVo.setGoodsClick(goodsInfoVo.getGoodsClick() + 1);
            this.goodsMapper.updateById(goodsInfoVo);
        }

        int stqty = this.goodsInfoMapper.getStqty(goodsid, memberDB.getErpUserId(), storageIds, goodsInfoVo.getAccflag(), lotid);

        goodsInfoVo.setStqty(stqty);

        return goodsInfoVo;
    }

    @Override
    public List<ActivityGiftVo> getActivityGiftVoList(Long asId) {
        List<ActivityGiftVo> activityGiftVoList = this.activityGiftMapper.getActivityGiftVoList(asId);
        return activityGiftVoList;
    }

    @Override
    public GoodsInfoVo getAdminGoodsInfo(Long goodsid, Integer storageId) {
        AssertExt.hasId(goodsid, "goodsid为空");
        return this.goodsInfoMapper.getAdminGoodsInfo(goodsid, storageId);
    }

    @Transactional
    @Override
    public void addMemberCart(List<Cart> cartList, Long memberId) {
        AssertExt.notNull(cartList, "数据为空");
        Member memberDB = new Member();
        if (null == memberId)
            memberDB = this.memberMapper.selectById(cartList.get(0).getMemberId());
        else memberDB = this.memberMapper.selectById(memberId);
        AssertExt.notNull(memberDB, "无效memberId");
        CustomerVo pubCustomerDB = this.memberMapper.getPhone(memberDB.getErpUserId());

        //客户销售状态
        AssertExt.isTrue(pubCustomerDB.getSausestatus().equals("可销售"), "不可销售状态，请联系客服了解详情");

        for (Cart cart : cartList) {
            AssertExt.hasId(cart.getErpGoodsId(), "erp商品id为空");
            //AssertExt.notNull(cart.getGoodsPrice(), "价格为空");
            AssertExt.hasId(cart.getStorageId(), "保管账id为空");
            AssertExt.notNull(cart.getGoodsName(), "商品名称为空");
            AssertExt.isTrue(cart.getGoodsNum() > 0, "数量要大于0");
            cart.setMemberId(memberDB.getId());
            Integer cartNum = this.getMemberCartTotal(memberDB.getId());
            AssertExt.isTrue(cartNum<200, "温馨提示：购物车商品数量已达200上限，请您先下单，然后再添加购物车！");

            /**
             * 限购数量
             */
            ActivityVo activityVo120 = this.activityMapper.getActivity90ByGoods(120L, cart.getErpGoodsId(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());
            if (null != activityVo120) {
                int count = this.orderInfoMapper.getOrderGoodsCountPurchase(memberId, activityVo120.getId(), cart.getErpGoodsId(), activityVo120.getTimesStrategy());
                log.info("count------{}", count);

                //限购
                List<ActivityStrategy> activityStrategyList100 = this.activityStrategyMapper.selectList(new QueryWrapper<ActivityStrategy>()
                        .eq("ACTIVITY_ID", activityVo120.getId()).eq("IS_USE", Constant.IS_USE.ON).orderByDesc("id"));
                cart.setGoodType(activityVo120.getActivityStrategy());
                for (ActivityStrategy activityStrategyDB : activityStrategyList100) {

                    ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityVo120.getId(), activityStrategyDB.getId(), cart.getErpGoodsId());
                    AssertExt.isFalse(0 == activityGoodsDB.getTopLimit(), "商品限购数量为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());


                    AssertExt.isTrue(count <= activityGoodsDB.getTopLimit(), "超过限购数量，请联系客服");
                    long num = activityGoodsDB.getTopLimit() - count;
                    AssertExt.isTrue(num >= cart.getGoodsNum(), "超过限购数量，请联系客服");

                }
            }


            QueryWrapper queryWrapper = new QueryWrapper<Cart>().eq("erp_goods_id", cart.getErpGoodsId()).eq("member_id", memberDB.getId());

            if (null != cart.getLotId()) {
                queryWrapper.eq("lot_id", cart.getLotId());
            } else {
                queryWrapper.isNull("lot_id");
            }
            Cart cartDB = this.cartMapper.selectOne(queryWrapper);
            cart.setGoodType(1);
            if (null != cartDB) {
                cartDB.setGoodsNum(cartDB.getGoodsNum() + cart.getGoodsNum());
                this.updateMemberCart(cartDB);
            } else {
                List<Long> longList = new ArrayList<>();
                longList.add(cart.getErpGoodsId());
                //经营类别校验
                ResultVo resultVo1 = this.goodsService.valid(memberDB.getErpUserId(), 1, longList);
                log.info("经营类别校验resultVo1--{}", resultVo1);
                if (resultVo1.getErrorCode() != 0) {
                    AssertExt.fail(resultVo1.getErrorMessage());
                }

                // GoodsInfoVo goodsInfoVo = this.getGoodsInfo(cart.getErpGoodsId(), memberId, cart.getLotId(), cart.getStorageId().intValue());
                Long[] storageIds = Constant.STO_RANGE_IDS;
                if (null != cart.getStorageId() && cart.getStorageId() == 5) {
                    storageIds = Constant.STO_RANGE_IDS_V;
                } else if (null != cart.getStorageId() && cart.getStorageId() == 691) {
                    storageIds = new Long[]{691L};
                }
                int stqty = this.goodsInfoMapper.getStqty(cart.getErpGoodsId(), memberDB.getErpUserId(), storageIds, cart.getAccflag(), cart.getLotId());

                AssertExt.isTrue(stqty - cart.getGoodsNum() >= 0, "[%s]库存不足,剩余库存[%s]", cart.getGoodsName(), stqty);

                //默认选中活动
                ActivityVo activityVo = this.activityMapper.getActivity30Or40ByGoods(cart.getErpGoodsId(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());

                if (null != activityVo) {
                    cart.setActivityId(activityVo.getId());
                }

                cart.setGoodType(1);

                Goods goodsDB = this.goodsMapper.selectOne(new QueryWrapper<Goods>().eq("ERP_GOODS_ID", cart.getErpGoodsId()));
                if (null != goodsDB) {
                    if (null != goodsDB.getErpAccFlag() && goodsDB.getErpAccFlag() == 5 && goodsDB.getIntegralGoods() == 1) {
                        cart.setGoodType(9);
                    }
                }

                cart.setPitchOn(1L);
                cart.setCreateTime(LocalDateTime.now());
                this.cartMapper.insert(cart);
            }
        }


    }

    @Transactional
    @Override
    public void updateMemberCart(Cart cart) {
        AssertExt.hasId(cart.getId(), "id为空");
        // AssertExt.hasId(cart.getStorageId(), "保管账id为空");
        AssertExt.isTrue(cart.getGoodsNum() > 0, "数量要大于0");
        //AssertExt.notNull(cart.getGoodsName(), "商品名称为空");
        Cart cartDB = this.cartMapper.selectById(cart.getId());
        AssertExt.notNull(cartDB, "无效id");
        Member memberDB = this.memberMapper.selectById(cart.getMemberId());
        Long[] storageIds = Constant.STO_RANGE_IDS;
        if (cartDB.getStorageId() == 5) {
            storageIds = Constant.STO_RANGE_IDS_V;
            //goodsInfoVo = this.goodsInfoMapper.getValidityGoodsInfoById(goodsid, memberDB.getErpUserId(), lotid);
        } else if (cartDB.getStorageId() == 691) {
            storageIds = new Long[]{691L};
        }
        int stqty = this.goodsInfoMapper.getStqty(cartDB.getErpGoodsId(), memberDB.getErpUserId(), storageIds, cartDB.getAccflag(), cartDB.getLotId());
        // AssertExt.isTrue(stqty - cart.getGoodsNum() >= 0, "[%s]库存不足", cartDB.getGoodsName());


        /**
         * 限购数量
         */
        ActivityVo activityVo120 = this.activityMapper.getActivity90ByGoods(120L, cartDB.getErpGoodsId(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());
        if (null != activityVo120) {
            int count = this.orderInfoMapper.getOrderGoodsCountPurchase(cart.getMemberId(), activityVo120.getId(), cartDB.getErpGoodsId(), activityVo120.getTimesStrategy());
            log.info("count------{}", count);

            //限购
            List<ActivityStrategy> activityStrategyList100 = this.activityStrategyMapper.selectList(new QueryWrapper<ActivityStrategy>()
                    .eq("ACTIVITY_ID", activityVo120.getId()).eq("IS_USE", Constant.IS_USE.ON).orderByDesc("id"));
            cart.setGoodType(activityVo120.getActivityStrategy());
            for (ActivityStrategy activityStrategyDB : activityStrategyList100) {

                ActivityGoods activityGoodsDB = this.activityGoodsMapper.getActivityGoodsInfo(activityVo120.getId(), activityStrategyDB.getId(), cartDB.getErpGoodsId());
                AssertExt.isFalse(0 == activityGoodsDB.getTopLimit(), "商品限购数量为空或者为0【%s】-【%s】-【%s】", activityGoodsDB.getGoodsName(), activityGoodsDB.getActivityId(), activityGoodsDB.getAsId());
                AssertExt.isTrue(count <= activityGoodsDB.getTopLimit(), "超过限购数量，请联系客服");
                long num = activityGoodsDB.getTopLimit() - count;
                AssertExt.isTrue(num >= cart.getGoodsNum(), "超过限购数量，请联系客服");
            }
        }

        BeanUtils.copyProperties(cart, cartDB);

        if (stqty - cart.getGoodsNum() < 0) {
            cartDB.setGoodsNum(stqty);
        }

        this.cartMapper.updateById(cartDB);
    }

    @Override
    public void updateCartActivity(Long memberId, Long activityId) {
        AssertExt.hasId(memberId, "id为空");
        AssertExt.hasId(activityId, "活动id为空");

        Member member = this.memberMapper.selectById(memberId);
        List<Cart> cartList = this.activityMapper.getCartActivity(activityId, member.getId(), member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());
        for (Cart cart : cartList) {
            this.cartMapper.updateById(cart);
        }
    }

    @Override
    public List<ActivityVo> getMyCartJoinActivity(Long memberId) {
        Member member = this.memberMapper.selectById(memberId);
        Integer count = this.cartMapper.selectCount(new QueryWrapper<Cart>().eq("member_id", memberId).isNotNull("ACTIVITY_ID"));
        if (count > 0) {
            return this.activityMapper.getMyCartJoinActivity(memberId, member.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());
        }
        return null;
    }

    @Transactional
    @Override
    public void isPitchOnCart(Long pitchOn, Long memberId) {
        AssertExt.hasId(memberId, "memberId为空");
        AssertExt.hasId(pitchOn, "是否选中为空");
        this.cartMapper.isPitchOnCart(pitchOn, memberId);
    }

    @Transactional
    @Override
    public void delMemberCart(Long[] ids) {
        AssertExt.notEmpty(ids, "id不能为空");
        for (Long id : ids) {
            this.cartMapper.deleteById(id);
        }
    }

    @Override
    public IPage<CartVo> getMemberCartList(Page page, CartVo cartVo) {
        IPage<CartVo> cartVoIPage = this.cartMapper.getMemberCartList(page, cartVo);
        Member memberDB = this.memberMapper.selectById(cartVo.getMemberId());
        cartVoIPage.getRecords().forEach(cartVo1 -> {
            cartVo1.setPubGoodsPriceList(this.goodsInfoMapper.getPubGoodsPriceList(cartVo1.getErpGoodsId(), memberDB.getErpUserId()));
            Long[] storageIds = Constant.STO_RANGE_IDS;
            if (cartVo1.getStorageId() == 5) {
                storageIds = Constant.STO_RANGE_IDS_V;

                cartVo1.setPubGoodsPriceList(this.goodsInfoMapper.getPubGoodsPriceList5(cartVo1.getErpGoodsId(), memberDB.getErpUserId()));
            } else if (cartVo1.getStorageId() == 691 || cartVo1.getGoodType() == 9) {
                storageIds = new Long[]{691L};
            } else {
                cartVo1.setPubGoodsPriceList(this.goodsInfoMapper.getPubGoodsPriceList(cartVo1.getErpGoodsId(), memberDB.getErpUserId()));
            }
            log.info("storageIds--{}", storageIds);

            log.info("cartVo1.getLotId()--{}", cartVo1.getLotId());
            cartVo1.setStqty(this.goodsInfoMapper.getStqty(cartVo1.getErpGoodsId(), memberDB.getErpUserId(), storageIds, cartVo1.getAccflag(), cartVo1.getLotId()));

            cartVo1.setActivityVoList(this.getActivityVoById(cartVo1.getActivityId(), cartVo1.getErpGoodsId(), memberDB.getErpUserId(), 0));

           /* if (null != cartVo1.getActivityVoList()) {
                cartVo1.getActivityVoList().forEach(activityVo -> {
                    List<ActivityGoods> activityGoodsList = this.activityGoodsMapper.selectList(new QueryWrapper<ActivityGoods>().eq("AS_ID", activityVo.getAsId()));
                    cartVo1.setActivityGoodsList(activityGoodsList);
                });
            }*/
            cartVo1.setActivityVo(this.activityMapper.getActivityAllDiscount(memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));


        });
        return cartVoIPage;
    }

    @Transactional
    @Override
    public List<CartVo> getMemberCartAll(CartVo cartVo) {
        List<CartVo> cartVoIPage = this.cartMapper.getMemberCartList(cartVo);
        Member memberDB = this.memberMapper.selectById(cartVo.getMemberId());
        cartVoIPage.forEach(cartVo1 -> {
            cartVo1.setPubGoodsPriceList(this.goodsInfoMapper.getPubGoodsPriceList(cartVo1.getErpGoodsId(), memberDB.getErpUserId()));
            Long[] storageIds = Constant.STO_RANGE_IDS;
            if (cartVo1.getStorageId() == 5) {
                storageIds = Constant.STO_RANGE_IDS_V;
            } else if (cartVo1.getStorageId() == 691 || cartVo1.getGoodType() == 9) {
                storageIds = new Long[]{691L};
            }
            log.info("storageIds--{}", storageIds);

            log.info("cartVo1.getLotId()--{}", cartVo1.getLotId());
            cartVo1.setStqty(this.goodsInfoMapper.getStqty(cartVo1.getErpGoodsId(), memberDB.getErpUserId(), storageIds, cartVo1.getAccflag(), cartVo1.getLotId()));
            List<ActivityVo> activityVoList = this.getActivityVoById(null, cartVo1.getErpGoodsId(), memberDB.getErpUserId(), 0);
            cartVo1.setActivityVoList(activityVoList);
            cartVo1.setActivityVo(this.activityMapper.getActivityAllDiscount(memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));

        });


      /*  cartVoIPage.stream()
                .filter(item -> item.getActivityVoList().size() > 0)
//                .filter(Utils.distinctByKey(Person::getId))
                .map(item -> new Cart().setId(item.getId()).setActivityId(item.getActivityVoList().get(0).getId()))
                .distinct()
                .forEach(cart -> {
                    this.cartMapper.updateCartActivityId(cart.getId(), cart.getActivityId());

                });*/

        for (CartVo cartVo1 : cartVoIPage) {
            if (cartVo1.getActivityVoList().size() > 0) {
                if (null == cartVo1.getActivityId()) {
                    this.cartMapper.updateCartActivityId(cartVo1.getId(), cartVo1.getActivityVoList().get(0).getId());
                }
            }
        }

        return cartVoIPage;
    }

    @Override
    public Integer getMemberCartTotal(Long memberId) {
        return this.cartMapper.selectCount(new QueryWrapper<Cart>().eq("MEMBER_ID", memberId));
    }

    @Override
    public List<ActivityVo> getActivityVoById(Long id, Long goodsId, Long erpUserId, Integer fromTo) {
        List<ActivityVo> activityVoList = this.activityMapper.getActivityById(id, goodsId, fromTo, erpUserId, LocalDateTime.now().getDayOfWeek().getValue());
       /* activityVoList.forEach(activityVo -> {
            if (null != activityVo.getAsId()) {
                List<ActivityGiftVo> activityGiftVoList = this.activityGiftMapper.getActivityGiftVoList(activityVo.getAsId());

                activityGiftVoList.forEach(activityGiftVo -> {
                    Long[] storageIds = new Long[]{activityGiftVo.getStorageId()};
                    activityGiftVo.setStqty(this.goodsInfoMapper.getStqty(activityGiftVo.getErpGoodsId(), erpUserId, storageIds, activityGiftVo.getErpAccflag(), activityGiftVo.getLotId()));
                });
                activityVo.setActivityGiftVoList(activityGiftVoList);
               *//* if (null != activityVo.getAsId()) {
                    List<ActivityGoods> activityGoodsList1 = this.activityGoodsMapper.selectList(new QueryWrapper<ActivityGoods>().eq("AS_ID", activityVo.getAsId()));
                    activityVo.setActivityGoodsList(activityGoodsList1);
                }*//*
            }
        });*/
        return activityVoList;
    }

    @Transactional
    @Override
    public List<Favorites> addMemberFavorites(List<Favorites> favoritesList, Long memberId) {
        AssertExt.notEmpty(favoritesList, "内容为空");
        for (Favorites favorites : favoritesList) {
            AssertExt.hasId(favorites.getFavId(), "favId为空");
            favorites.setMemberId(memberId);

            GoodsInfoVo goodsInfoVo = this.getGoodsInfo(favorites.getFavId(), memberId, favorites.getLotId(), null != favorites.getStorageId() ? favorites.getStorageId() : 0);
            Favorites favoritesDB = this.favoritesMapper.selectOne(new QueryWrapper<Favorites>().eq("fav_id", favorites.getFavId()).eq("member_id", memberId));
            favorites.setStorageId(null != favorites.getStorageId() ? favorites.getStorageId() : 0);
            favorites.setGcId(goodsInfoVo.getClassnRow3());
            favorites.setGoodsName(goodsInfoVo.getGoodsname());
            if (null != favorites.getStorageId() && favorites.getStorageId() != 691) {
                favorites.setPriceId(goodsInfoVo.getPriceid());
                favorites.setLogPrice((goodsInfoVo.getPrice() * (goodsInfoVo.getZxB2bNumLimit())));
            }

            favorites.setLotId(goodsInfoVo.getLotid());
            favorites.setLotNo(goodsInfoVo.getLotno());
            favorites.setGoodsImage(goodsInfoVo.getGoodsImg());
            favorites.setAccflag(goodsInfoVo.getAccflag());
            if (null == favoritesDB) {
                // if (favorites.getFavType().equals("goods")) {}
                Goods goodsDB = this.goodsMapper.selectOne(new QueryWrapper<Goods>().eq("erp_goods_id", favorites.getFavId()));
                if (null != goodsDB) {
                    goodsDB.setGoodsCollect(goodsDB.getGoodsCollect() + 1);
                    this.goodsMapper.updateById(goodsDB);
                }
                favorites.setCreateTime(LocalDateTime.now());
                this.favoritesMapper.insert(favorites);
            } else {
                favorites.setId(favoritesDB.getId());
            }
        }
        return favoritesList;
    }

    @Transactional
    @Override
    public void updateMemberFavorites(Favorites favorites) {
        AssertExt.hasId(favorites.getId(), "id为空");
        Favorites favoritesDB = this.favoritesMapper.selectById(favorites.getId());
        AssertExt.notNull(favoritesDB, "无效id");
        BeanUtils.copyProperties(favorites, favoritesDB);
        favoritesDB.setCreateTime(LocalDateTime.now());
        this.favoritesMapper.updateById(favoritesDB);
    }

    @Transactional
    @Override
    public void delMemberFavorites(Long[] ids) {
        AssertExt.notEmpty(ids, "id不能为空");
        for (Long id : ids) {
            this.favoritesMapper.deleteById(id);
        }
    }

    @Override
    public IPage<FavoritesVo> getMemberFavoritesList(Page page, Long memberId, Long goodsId) {
        Member memberDB = this.memberMapper.selectById(memberId);
        IPage<FavoritesVo> favoritesVoIPage = this.favoritesMapper.getMemberFavoritesList(page, memberId, goodsId);
        favoritesVoIPage.getRecords().forEach(favoritesVo -> {
            Long[] storageIds = Constant.STO_RANGE_IDS;
            if (null != favoritesVo.getStorageId() && favoritesVo.getStorageId() == 5) {
                storageIds = Constant.STO_RANGE_IDS_V;
            }
            favoritesVo.setStqty(this.goodsInfoMapper.getStqty(favoritesVo.getFavId(), memberDB.getErpUserId(), storageIds, favoritesVo.getAccflag(), favoritesVo.getLotId()));
            favoritesVo.setPubGoodsPriceList(this.goodsInfoMapper.getPubGoodsPriceList(favoritesVo.getFavId(), memberDB.getErpUserId()));

            favoritesVo.setActivityVoList(this.getActivityVoById(null, favoritesVo.getFavId(), memberDB.getErpUserId(), 1));

        });
        return favoritesVoIPage;
    }

    @Transactional
    @Override
    public void addArrivalNotice(ArrivalNotice arrivalNotice, Long userId) {
        AssertExt.hasId(arrivalNotice.getErpGoodsId(), "erp商品id为空");
        ArrivalNotice arrivalNoticeDB = this.arrivalNoticeMapper.selectOne(new QueryWrapper<ArrivalNotice>().eq("member_id", arrivalNotice.getMemberId()).eq("erp_goods_id", arrivalNotice.getErpGoodsId()));

        if (null != arrivalNoticeDB) {
            arrivalNotice.setAnStatus(ArrivalNotice.EAnStatus.UNTREATED.val());
            arrivalNoticeDB.setGoodsNum(arrivalNoticeDB.getGoodsNum() + arrivalNotice.getGoodsNum());
            this.arrivalNoticeMapper.updateById(arrivalNoticeDB);
        } else {
            arrivalNotice.setCreateTime(LocalDateTime.now());
            arrivalNotice.setAnStatus(ArrivalNotice.EAnStatus.UNTREATED.val());
            this.arrivalNoticeMapper.insert(arrivalNotice);
        }
    }

    @Transactional
    @Override
    public void editGoods(Goods goods, Long userId) {
        AssertExt.notNull(goods.getErpGoodsId(), "erp商品id为空");
        //AssertExt.notNull(goods.getGoodsImg(), "图片为空");
        Goods goodsDB = this.goodsMapper.selectOne(new QueryWrapper<Goods>().eq("erp_goods_id", goods.getErpGoodsId()));
        if (goods.getGoodsImg() != null) {
            List<GoodsImagesVo> goodsImageList = JSONArray.parseArray(goods.getGoodsImg(), GoodsImagesVo.class);
            String goodsImg = this.fileService.coverGoodsImage(goods.getErpGoodsId(), goodsImageList, Constant.IMAGE_PREFIX.GOODS);
            goods.setGoodsImg(goodsImg);
            if (null != goodsDB) {
                goods.setId(goodsDB.getId());
                goods.setImgNum(goodsImageList.size());
                goods.setUpdateUserId(userId);
                BeanUtils.copyProperties(goods, goodsDB);
                this.goodsMapper.updateById(goodsDB);
            } else {
                goods.setImgNum(goodsImageList.size());
                goods.setCreateUserId(userId);
                goods.setCreateTime(LocalDateTime.now());
                this.goodsMapper.insert(goods);
            }
        } else {
            goods.setGoodsImg("");
            if (null != goodsDB) {
                goods.setId(goodsDB.getId());
                goods.setImgNum(0);
                goods.setUpdateUserId(userId);
                BeanUtils.copyProperties(goods, goodsDB);
                this.goodsMapper.updateById(goodsDB);
            } else {
                goods.setImgNum(0);
                goods.setCreateUserId(userId);
                goods.setCreateTime(LocalDateTime.now());
                this.goodsMapper.insert(goods);
            }
        }
    }

    @Transactional
    @Override
    public void editIntegral(Goods goods) {
        AssertExt.notNull(goods.getErpGoodsId(), "erp商品id为空");
        Goods goodsDB = this.goodsMapper.selectOne(new QueryWrapper<Goods>().eq("erp_goods_id", goods.getErpGoodsId()));
        if (null != goodsDB) {
            AssertExt.isTrue(goodsDB.getErpAccFlag() == 5, "该商品[%s],非积分商品，无法编辑", goodsDB.getErpGoodsId());
            goods.setId(goodsDB.getId());
            BeanUtils.copyProperties(goods, goodsDB);
            this.goodsMapper.updateById(goodsDB);
        } else {
            AssertExt.notNull(goods.getErpAccFlag(), "accFlag标识为空");
            AssertExt.isTrue(goods.getErpAccFlag() == 5, "该商品[%s],非积分商品，无法编辑", goods.getErpGoodsId());
            goods.setConvertibleIntegral(0L);
            goods.setCreateTime(LocalDateTime.now());
            this.goodsMapper.insert(goods);
        }
    }

    @Override
    public void addFactory(Factory factory) {
        AssertExt.notNull(factory.getFactoryShort(), "厂家简称为空");
        factory.setCreateTime(LocalDateTime.now());
        this.factoryMapper.insert(factory);
    }

    @Transactional
    @Override
    public void updateFactory(Factory factory) {
        AssertExt.hasId(factory.getErpSupplyId(), "erp厂家为空");
        // AssertExt.notBlank(factory.getFactoryPic(), "图片为空");
        Factory factoryDB = this.factoryMapper.selectOne(new QueryWrapper<Factory>().eq("erp_supply_id", factory.getErpSupplyId()));
        List<Factory> factoryList = this.factoryMapper.selectList(new QueryWrapper<>());
        if (factory.getFactoryPic() != null) {
            String[] goodsImageList = factory.getFactoryPic().split(",");
            String factoryPic = this.fileService.coverImageById(factory.getErpSupplyId(), Constant.IMAGE_PREFIX.FACTORY, goodsImageList);
            factory.setFactoryPic(factoryPic);
            if (null != factoryDB) {
                factory.setId(factoryDB.getId());
                BeanUtils.copyProperties(factory, factoryDB);
                this.factoryMapper.updateById(factoryDB);
            } else {
                AssertExt.notNull(factory.getFactoryShort(), "厂家简称为空");
                List<Integer> sortList = factoryList.stream().map(Factory::getFactorySort).collect(Collectors.toList());
                factory.setFactorySort(Collections.max(sortList) + 1);
                factory.setCreateTime(LocalDateTime.now());
                this.factoryMapper.insert(factory);
            }
        } else {
            factory.setFactoryPic("");
            if (null != factoryDB) {
                factory.setId(factoryDB.getId());
                BeanUtils.copyProperties(factory, factoryDB);
                this.factoryMapper.updateById(factoryDB);
            } else {
                AssertExt.notNull(factory.getFactoryShort(), "厂家简称为空");
                List<Integer> sortList = factoryList.stream().map(Factory::getFactorySort).collect(Collectors.toList());
                factory.setFactorySort(Collections.max(sortList) + 1);
                factory.setCreateTime(LocalDateTime.now());
                this.factoryMapper.insert(factory);
            }
        }


    }

    @Transactional
    @Override
    public void updateFactoryRecommend(Long erpSupplyId, Integer factoryRecommend) {
        AssertExt.hasId(erpSupplyId, "erpSupplyId为空");
        AssertExt.notNull(factoryRecommend, "factoryRecommend为空");
        Factory factoryDB = this.factoryMapper.selectOne(new QueryWrapper<Factory>().eq("erp_supply_id", erpSupplyId));
        if (null != factoryDB) {
            factoryDB.setFactoryRecommend(factoryRecommend);
            this.factoryMapper.updateById(factoryDB);
        } else {
            factoryDB = new Factory();
            factoryDB.setErpSupplyId(erpSupplyId);
            factoryDB.setFactoryRecommend(factoryRecommend);
            factoryDB.setCreateTime(LocalDateTime.now());
            this.factoryMapper.insert(factoryDB);
        }
    }

    @Override
    public List<Factory> getAllFactory(String factoryName) {
        QueryWrapper<Factory> queryWrapper = new QueryWrapper<>();
        if (null != factoryName) {
            queryWrapper.like("factory_name", factoryName);
        }
        queryWrapper.orderByDesc("factory_sort");
        return this.factoryMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<FactoryVo> getFactoryList(Page page, FactoryVo factory) {

        return this.factoryMapper.getFactoryList(page, factory);
    }

    @Override
    public IPage<ArrivalNoticeVo> getArrivalNoticeList(Page page, ArrivalNoticeVo arrivalNoticeVo) {
        IPage<ArrivalNoticeVo> arrivalNoticeVoIPage = this.arrivalNoticeMapper.getArrivalNoticeList(page, arrivalNoticeVo);
        arrivalNoticeVoIPage.getRecords().forEach(arrivalNoticeVo1 -> {
            Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("ID", arrivalNoticeVo1.getMemberId()));
            arrivalNoticeVo1.setMemberName(member.getUserName());
        });

        return arrivalNoticeVoIPage;
    }

    @Override
    public IPage<EvaluateGoods> getEvaluateGoodsList(Page page, Long goodsId) {
        AssertExt.hasId(goodsId, "商品id为空");
        return this.evaluateGoodsMapper.selectPage(page, new QueryWrapper<EvaluateGoods>().eq("EVAL_GOODS_ID", goodsId));
    }

    @Override
    public IPage<BannedVo> getBannedList(Page page, BannedVo bannedVo) {
        return this.goodsInfoMapper.getBannedList(page, bannedVo);
    }

    @Override
    public IPage<BannedVo> getAdminGoodsList(Page page, BannedVo bannedVo) {
        return this.goodsInfoMapper.getAdminGoodsList(page, bannedVo);
    }

    @Override
    public IPage<BannedVo> getAdminGoodsListById(Page page, BannedVo bannedVo) {
        return this.goodsInfoMapper.getAdminGoodsListById(page, bannedVo);
    }

    @Transactional
    @Override
    public void informArrivalNotice(Long id) {
        AssertExt.hasId(id, "id为空");
        ArrivalNotice arrivalNoticeDB = this.arrivalNoticeMapper.selectById(id);
        AssertExt.notNull(arrivalNoticeDB, "无效id[%s]", id);
        arrivalNoticeDB.setAnStatus(ArrivalNotice.EAnStatus.HAVE_DELETED.val());
        this.arrivalNoticeMapper.updateById(arrivalNoticeDB);
    }

    @Override
    public List<ArrivalNoticeVo> getMemberArrivalNoticeList(Long memberId) {
        Member memberDB = this.memberMapper.selectById(memberId);
        List<ArrivalNoticeVo> arrivalNoticeVoList = this.arrivalNoticeMapper.getMemberArrivalNoticeList(memberId);
        arrivalNoticeVoList.forEach(arrivalNoticeVo -> {
            Long[] storageIds = Constant.STO_RANGE_IDS;
            if (null != arrivalNoticeVo.getStorageId() && arrivalNoticeVo.getStorageId() == 5) {
                storageIds = Constant.STO_RANGE_IDS_V;
            }
            arrivalNoticeVo.setPubGoodsPriceList(this.goodsInfoMapper.getPubGoodsPriceList(arrivalNoticeVo.getErpGoodsId(), memberDB.getErpUserId()));
            arrivalNoticeVo.setStqty(this.goodsInfoMapper.getStqty(arrivalNoticeVo.getErpGoodsId(), memberDB.getErpUserId(), storageIds, arrivalNoticeVo.getAccflag(), arrivalNoticeVo.getLotId()));
            List<ActivityVo> activityVoList = this.getActivityVoById(null, arrivalNoticeVo.getErpGoodsId(), memberDB.getErpUserId(), 0);
            arrivalNoticeVo.setActivityVoList(activityVoList);
            arrivalNoticeVo.setActivityVo(this.activityMapper.getActivityAllDiscount(memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));
        });
        return arrivalNoticeVoList;
    }

    @Transactional
    @Override
    public void updateArrivalNoticeStatus(Long[] ids) {
        for (Long id : ids) {
            ArrivalNotice arrivalNoticeDB = this.arrivalNoticeMapper.selectById(id);
            AssertExt.notNull(arrivalNoticeDB, "无效id[%s]", id);
            arrivalNoticeDB.setAnStatus(ArrivalNotice.EAnStatus.ADDED_SHOPPING_CART.val());
            this.arrivalNoticeMapper.updateById(arrivalNoticeDB);
        }
    }

    @Transactional
    @Override
    public void dealWithArrivalNotice() {
        List<ArrivalNotice> arrivalNoticeList = this.arrivalNoticeMapper.selectList(new QueryWrapper<ArrivalNotice>().eq("an_status", ArrivalNotice.EAnStatus.UNTREATED.val()));
        arrivalNoticeList.forEach(arrivalNotice -> {
            Member memberDB = this.memberMapper.selectById(arrivalNotice.getMemberId());
            Long[] storageIds = Constant.STO_RANGE_IDS;
           /* if (null != arrivalNotice.getStorageId() && arrivalNotice.getStorageId() == 5) {
                storageIds = Constant.STO_RANGE_IDS_V;
            }*/
            int stqty = this.goodsInfoMapper.getStqty(arrivalNotice.getErpGoodsId(), memberDB.getErpUserId(), storageIds, arrivalNotice.getAccflag(), arrivalNotice.getLotId());
            if (stqty > 0) {
                /*GoodsInfoVo goodsInfoVo = this.goodsInfoMapper.getGoodsInfo(arrivalNotice.getErpGoodsId(), memberDB.getErpUserId(), 0);
                if (null != arrivalNotice.getLotId()) {
                    goodsInfoVo = this.goodsInfoMapper.getValidityGoodsInfoById(arrivalNotice.getErpGoodsId(), memberDB.getErpUserId(), arrivalNotice.getLotId());
                }*/
                //GoodsVo goodsInfoVo = this.goodsService.getDetail(arrivalNotice.getErpGoodsId(),1);
                arrivalNotice.setStorageId(1L);
                arrivalNotice.setAnStatus(ArrivalNotice.EAnStatus.SENT_KNOW.val());
                arrivalNotice.setSendTime(LocalDateTime.now());
                this.arrivalNoticeMapper.updateById(arrivalNotice);
            }
        });
    }

    @Override
    public IPage<GoodsInfoVo> getValidityGoodsInfo(Page page, GoodsInfoVo goodsInfoVo) {
        Member memberDB = this.memberMapper.selectById(goodsInfoVo.getMemberId());
        goodsInfoVo.setMemberId(memberDB.getErpUserId());
        IPage<GoodsInfoVo> goodsInfoVoIPage = this.goodsInfoMapper.getValidityGoodsInfo(page, goodsInfoVo);
        goodsInfoVoIPage.getRecords().forEach(goodsInfoVo1 -> {
            goodsInfoVo1.setActivityVoList(this.activityMapper.getActivityByGoods(goodsInfoVo1.getGoodsid(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));
        });
        return goodsInfoVoIPage;
    }

    @Override
    public List<GoodsInfoVo> goodsFilter(Long memberId, List<Long> goodsIds) {
        return null;
    }

    @Override
    public List<GoodsInfoVo> getGoodsListNotPage(GoodsInfoVo goodsInfoVo) {
        return this.goodsInfoMapper.getGoodsListNotPage(goodsInfoVo);
    }

    @Transactional
    @Override
    public void syncGoodsQualityImage(LocalDateTime startDate) {
        String maxTime = this.stringRedisTemplate.opsForValue().get(Constant.REDIS_KEY_PREFIX.SYNC_GOODS_QUALITY_IMAGE_MAX_TIME);
        if (StringUtils.isNotBlank(maxTime)) {
            startDate = LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(maxTime));
        }
        log.info("get goods quality since {}", startDate);
        List<GoodsQualityVo> list = this.goodsInfoMapper.getGoodsQuality(startDate);
        log.info("syncing images size {}", list.size());

        Optional<LocalDateTime> startTimeOptional = list.stream().parallel().map(goodsQualityVo -> {
            this.fileService.saveGoodsQuality(
                    goodsQualityVo.getImgurlTop(),
                    goodsQualityVo.getFcheckid(),
                    goodsQualityVo.getFilename(),
                    String.format("%s/%s", Constant.IMAGE_PREFIX.QUALITY, goodsQualityVo.getImgurlBottom()));
            return goodsQualityVo.getCreateTime();
        }).max(LocalDateTime::compareTo);

        if (startTimeOptional.isPresent()) {
            log.info("save max time {}", startTimeOptional.get());
            this.stringRedisTemplate.opsForValue().set(
                    Constant.REDIS_KEY_PREFIX.SYNC_GOODS_QUALITY_IMAGE_MAX_TIME,
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(startTimeOptional.get()));

        }
    }

    @Override
    public IPage<ActivityVo> getActivityVoList(Page page, ActivityVo activityVo) {
        return this.activityMapper.getActivityVoList(page, activityVo);
    }

    @Override
    public IPage<ActivityGoods> getActivityGoodsList(Page page, Long asId) {
        AssertExt.hasId(asId, "asId为空");

        return this.activityGoodsMapper.selectPage(page, new QueryWrapper<ActivityGoods>().eq("AS_ID", asId));
    }

    @Override
    public IPage<ActivityStrategy> getActivityStrategyList(Page page, Long activityId) {
        AssertExt.hasId(activityId, "activityId为空");

        return this.activityStrategyMapper.selectPage(page, new QueryWrapper<ActivityStrategy>().eq("ACTIVITY_ID", activityId));
    }

    @Override
    public IPage<ActivitySetVo> getActivitySetList(Page page, Long activityId) {
        AssertExt.hasId(activityId, "activityId为空");

        return this.activitySetMapper.getActivitySetList(page, activityId);
    }

    @Override
    public IPage<ActivityGift> getActivityGiftList(Page page, Long erpGoodsId, Long asId) {
        // AssertExt.hasId(erpGoodsId, "erpGoodsId为空");
        AssertExt.hasId(asId, "asId为空");
        return this.activityGiftMapper.selectPage(page, new QueryWrapper<ActivityGift>().eq("AS_ID", asId));
    }

    @Override
    public List<FactoryVo> getHoldFactoryVoList() {
        return this.factoryMapper.getHoldFactoryVoList();
    }

    @Transactional
    @Override
    public void updateFactorySort(FactoryVo factoryVo) {
        AssertExt.hasId(factoryVo.getErpSupplyId(), "erp厂家id无效");
        AssertExt.notBlank(factoryVo.getSortType(), "排序类型为空");
        AssertExt.checkEnum(FactoryVo.ESortType.class, factoryVo.getSortType(), "排序类型不匹配");
        Factory factory = this.factoryMapper.selectOne(new QueryWrapper<Factory>().eq("ERP_SUPPLY_ID", factoryVo.getErpSupplyId()));
        List<Factory> factoryList = this.factoryMapper.selectList(new QueryWrapper<>());
        List<Integer> sortList = factoryList.stream().map(Factory::getFactorySort).collect(Collectors.toList());
        if (FactoryVo.ESortType.TOP.val().equals(factoryVo.getSortType())) {
            factory.setFactorySort(Collections.max(sortList) + 1);
            this.factoryMapper.updateById(factory);
        } else {
            factory.setFactorySort(Collections.min(sortList) - 1);
            this.factoryMapper.updateById(factory);
        }
    }

    @Transactional
    @Override
    public void factorySort(Long erpSupplyIdPrev, Long erpSupplyIdNext) {
        AssertExt.hasId(erpSupplyIdPrev, "erpSupplyIdPrev无效");
        AssertExt.hasId(erpSupplyIdNext, "erpSupplyIdNext无效");
        Factory factory = this.factoryMapper.selectById(erpSupplyIdPrev);
        Factory factory1 = this.factoryMapper.selectById(erpSupplyIdNext);
        Integer sort = 0;
        sort = factory.getFactorySort();
        factory.setFactorySort(factory1.getFactorySort());
        factory1.setFactorySort(sort);
        this.factoryMapper.updateById(factory);
        this.factoryMapper.updateById(factory1);
    }

    @Override
    public IPage<ArrivalNoticeVo> getPcArrivalNoticeList(Page page, ArrivalNoticeVo arrivalNoticeVo) {
        AssertExt.hasId(arrivalNoticeVo.getMemberId(), "客户ID无效");
        return this.arrivalNoticeMapper.getPcArrivalNoticeList(page, arrivalNoticeVo);
    }

    @Override
    public List<GoodsInfoVo> getGoodsInfoVoThree(Long customerId) {
        Member memberDB = this.memberMapper.selectById(customerId);
        List<GoodsInfoVo> goodsInfoVoList = this.goodsInfoMapper.getGoodsInfoVoThree(memberDB.getErpUserId());
      /*  goodsInfoVoList.forEach(goodsInfoVo -> {
            goodsInfoVo.setActivityVoList(this.activityMapper.getActivityByGoods(goodsInfoVo.getGoodsid(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));

        });*/
        return goodsInfoVoList;
    }

    @Override
    public List<GoodsInfoVo> getMyFootAll(Long memberId, Long[] goodsId) {
        AssertExt.isTrue(goodsId.length > 0, "商品id数组为空");
        Member memberDB = this.memberMapper.selectById(memberId);
        List<GoodsInfoVo> goodsInfoVoList = this.goodsInfoMapper.getMyFootAll(memberDB.getErpUserId(), goodsId);
        goodsInfoVoList.forEach(goodsInfoVo -> {
            goodsInfoVo.setActivityVoList(this.getActivityVoById(goodsInfoVo.getActivityId(), goodsInfoVo.getGoodsid(), memberDB.getErpUserId(), 1));
        });
        return goodsInfoVoList;
    }

    @Override
    public IPage<GoodsClassTypeVo> getAdminGoodsClassTypeVoList(Page page, GoodsClassTypeVo goodsClassTypeVo) {
        AssertExt.notNull(page, "页面为空[%s]", page.getCurrent());
        return this.goodsInfoMapper.getAdminGoodsClassTypeVoList(page, goodsClassTypeVo);
    }

    @Override
    public IPage<GoodsInfoVo> getGoodsInfoVoSecKill(Page page, GoodsInfoVo goodsInfoVo) {
        Member memberDB = this.memberMapper.selectById(goodsInfoVo.getMemberId());
        goodsInfoVo.setMemberId(memberDB.getErpUserId());
        goodsInfoVo.setWeek(LocalDateTime.now().getDayOfWeek().getValue());
        IPage<GoodsInfoVo> goodsInfoVoIPage = this.goodsInfoMapper.getGoodsInfoVoSecKill(page, goodsInfoVo);
        goodsInfoVoIPage.getRecords().forEach(goodsInfoVo1 -> {
            goodsInfoVo1.setActivityVoList(this.activityMapper.getActivityByGoods(goodsInfoVo1.getGoodsid(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));

        });
        return goodsInfoVoIPage;
    }

    @Override
    public List<SearchTipsVo> getGoodsInfoListReturnName(Long userId, String goodspinyin) {
        Member member = this.memberMapper.selectById(userId);
        AssertExt.notNull(member, "客户不存在");
        if (null != goodspinyin) {
            return this.goodsMapper.getGoodsInfoListReturnName(member.getErpUserId(), goodspinyin);
        }
        return null;
    }

    @Override
    public List<String> getHotSearch() {
        Setting setting = this.settingMapper.selectById(3L);
        if (null != setting) {
            if (null != setting.getValue()) {
                String[] str = setting.getValue().split(",");
                return new ArrayList<>(Arrays.asList(str));
            }
        }
        return null;
    }

    @Transactional
    @Override
    public void updateCartExpiredActivity() {
        List<Cart> cartList = this.cartMapper.getCartExpiredActivity();
        for (Cart cart : cartList) {
            Member memberDB = this.memberMapper.selectById(cart.getMemberId());
            //默认选中活动
            ActivityVo activityVo = this.activityMapper.getActivity30Or40ByGoods(cart.getErpGoodsId(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue());

            if (null != activityVo) {
                cart.setActivityId(activityVo.getId());
                this.cartMapper.updateById(cart);
            } else {
                this.cartMapper.updateCartSetNull(cart.getId());
            }


        }
    }

    @Override
    public IPage<GoodsInfoVo> getActivityGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo) {
        AssertExt.hasId(goodsInfoVo.getActivityId(), "活动id为空");
        Member memberDB = this.memberMapper.selectById(goodsInfoVo.getMemberId());
        goodsInfoVo.setMemberId(memberDB.getErpUserId());
        goodsInfoVo.setWeek(LocalDateTime.now().getDayOfWeek().getValue());
        IPage<GoodsInfoVo> goodsInfoVoIPage = this.goodsInfoMapper.getActivityGoodsInfoList(page, goodsInfoVo);
        goodsInfoVoIPage.getRecords().forEach(goodsInfoVo1 -> {
            goodsInfoVo1.setActivityVoList(this.getActivityVoById(null, goodsInfoVo1.getGoodsid(), memberDB.getErpUserId(), 1));
        });
        return goodsInfoVoIPage;
    }

    @Override
    public IPage<GoodsInfoVo> getCouponGoodsInfoList(Page page, Long customerId, Long couponId) {
        AssertExt.hasId(couponId, "优惠券id为空");
        Member memberDB = this.memberMapper.selectById(customerId);
        return this.goodsInfoMapper.getCouponGoodsInfoList(page, memberDB.getErpUserId(), couponId);
    }

    @Override
    public void delMemberStoreCart(Long memberId, Long storeId) {
        AssertExt.hasId(memberId, "b2b客户id为空");
        AssertExt.hasId(storeId, "b2b客户门店id为空");
        this.cartMapper.delMemberStoreCart(memberId, storeId);
    }


}

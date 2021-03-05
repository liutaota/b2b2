package com.zsyc.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.service.GoodsService;
import com.zsyc.zt.b2b.service.HomeMenuService;
import com.zsyc.zt.b2b.service.WebPageService;
import com.zsyc.zt.b2b.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Created by lcs on 2020/8/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class AdminAdvPositionTest {
    @Autowired
    private CouponMemberMapper couponMemberMapper;
    @Autowired
    private CouponGoodsMapper couponGoodsMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private AdvPositionMapper advPositionMapper;
    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private WebPageMapper webPageMapper;
    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private DeliveryAmountMapper deliveryAmountMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Reference
    private WebPageService webPageService;
    @Reference
    private GoodsService goodsService;

    @Test
    public void getMemberNum() {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        Integer memberNum = this.memberMapper.selectCount(memberQueryWrapper);
        log.info("客户总量 {}", memberNum);

        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        List<Cart> cartList = this.cartMapper.selectList(cartQueryWrapper);
        AtomicReference<Integer> goodsSum = new AtomicReference<>(0);
        cartList.forEach(cart -> {
            Integer goodsNum = cart.getGoodsNum();
            goodsSum.updateAndGet(v -> v + goodsNum);
        });
        log.info("购物车总量 {}", goodsSum);

        QueryWrapper<OrderGoods> orderGoodsQueryWrapper = new QueryWrapper<>();
        Integer orderNum = this.orderGoodsMapper.selectCount(orderGoodsQueryWrapper);
        log.info("订单总量 {}", orderNum);

    }

    private List<WebPageVo> getWebPageList(Long floorId) {
        QueryWrapper<WebPage> webPageQueryWrapper = new QueryWrapper<>();
        webPageQueryWrapper.eq("IS_DEL", Constant.IS_DEL.NO);
        List<WebPage> webPages = this.webPageMapper.selectList(webPageQueryWrapper);
        return webPages.stream().map(webPage -> {
            WebPageVo webPageVo = new WebPageVo();
            BeanUtils.copyProperties(webPage, webPageVo);
            return webPageVo;
        }).filter(webPageVo -> {
            return webPageVo.getPageSetList().stream().filter(pageSet -> WebPageInfoVo.PageSet.EType.FLOOR.val().equals(pageSet.getType()))
                    .filter(pageSet -> floorId.equals(pageSet.getFloorId())).count() > 0;
        }).collect(Collectors.toList());
    }

    @Test
    public void getFactoryList() {
        FactoryVo factoryVo = new FactoryVo();

        this.factoryMapper.getFactoryList(new Page(11, 500), factoryVo);


    }

    @Test
    public void get() {
        Integer erpSupplyIdPrev = 5709;
        Integer erpSupplyIdNext = 5713;
        Factory factory = this.factoryMapper.selectById(erpSupplyIdPrev);
        Factory factory1 = this.factoryMapper.selectById(erpSupplyIdNext);
        Integer sort = 0;
        sort = factory.getFactorySort();
        factory.setFactorySort(factory1.getFactorySort());
        factory1.setFactorySort(sort);
        this.factoryMapper.updateById(factory);
        this.factoryMapper.updateById(factory1);
    }

    @Test
    public void updateWebPageSort() {
        Long id = 79L;
        String type = "TOP";
        WebPage webPage = this.webPageMapper.selectOne(new QueryWrapper<WebPage>().eq("id", id));
        List<WebPage> webPageList = this.webPageMapper.selectList(new QueryWrapper<>());
        List<Integer> sortList = webPageList.stream().map(WebPage::getSortNum).collect(Collectors.toList());
        sortList.remove(null);
        log.info("sortList {}", sortList);
        if (WebPageVo.ESortType.TOP.val().equals(type)) {
            AssertExt.isFalse(webPage.getSortNum() != null && webPage.getSortNum() >= Collections.max(sortList), "此信息已在顶部");
            webPage.setSortNum(Collections.max(sortList) + 1);
            this.webPageMapper.updateById(webPage);
        } else {
            AssertExt.isFalse(webPage.getSortNum() != null && webPage.getSortNum() <= Collections.min(sortList), "此信息已在底部");
            webPage.setSortNum(Collections.min(sortList) - 1);
            this.webPageMapper.updateById(webPage);
        }
    }

    @Test
    public void getFloorList() {
        QueryWrapper<Floor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("IS_DEL", Constant.IS_DEL.NO);
        List<Floor> floorList = this.floorMapper.selectList(queryWrapper);
        log.info("floorList {}", floorList);

        List<Floor> floorLists = floorList.stream().filter(floor -> floor.getIsUse().equals("ON")).collect(Collectors.toList());
        log.info("floorLists {}", floorLists);

    }

    @Test
    public void updateFloorIsDel() {
        MemberVo memberVo = new MemberVo();
        memberVo.setErpUserId(13014L);
        memberVo.setTelephone("13172328877");
        // 1.先查ERP_CUSTOMER_V -->根据erpUserId
        CustomerVo customerVo = this.memberMapper.getCustomerVoInfo(memberVo.getErpUserId());
        log.info("customerVo {}", customerVo);
        // 2.然后查询MEMBER -->根据erpUserId/telephone
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("ERP_USER_ID", memberVo.getErpUserId())
                .or().eq("TELEPHONE", memberVo.getTelephone()));
        log.info("member {}", member);
        if (member == null) {
            Member memberDB = new Member();
            memberDB.setErpUserId(customerVo.getCustomid());
            if (customerVo.getTelephone() != null) {
                memberDB.setTelephone(customerVo.getTelephone());
            } else {
                memberDB.setTelephone(customerVo.getZxPhone().toString());
            }
            memberDB.setPassword(Constant.NEW_MEMBER.INITIAL_PWD);
            memberDB.setCreateTime(LocalDateTime.now());
            memberDB.setUserName(customerVo.getCustomname());
            memberDB.setIsErp(Constant.NEW_MEMBER.FORMER);
            this.memberMapper.insert(memberDB);
        }
    }

    @Test
    public void getAdvPositionAll() {
        LocalDateTime localDateTime = LocalDateTime.now();
        AdvPositionVo advPositionVo = new AdvPositionVo();
        AdvPosition advPosition = this.advPositionMapper.selectById(157);
        if (advPosition.getIsUse().equals(AdvPosition.EIsUse.OFF.val())) return;
        log.info("advPosition {}", advPosition);

        if (advPosition.getApDisplay().equals(AdvPosition.EApDisplay.TOP_BANNER.val())
                || advPosition.getApDisplay().equals(AdvPosition.EApDisplay.CENTER_ONLY.val())
                || advPosition.getApDisplay().equals(AdvPosition.EApDisplay.POPUP_BANNER.val())) {
            BeanUtils.copyProperties(advPosition, advPositionVo);
            advPositionVo.getImageInfoVoList().forEach(imageInfoVo -> {
                log.info("imageInfoVo {}", imageInfoVo);
            });
        } else {
            BeanUtils.copyProperties(advPosition, advPositionVo);
            List<ImageInfoVo> imageInfoList = new ArrayList<>();
            advPositionVo.getImageInfoVoList().forEach(imageInfoVo -> {
                if (imageInfoVo.getImageIsUse().equals(ImageInfoVo.EImageIsUse.ON.val())
                        && imageInfoVo.getImageStartTime().isBefore(localDateTime)
                        && imageInfoVo.getImageEndTime().isAfter(localDateTime)) {
                    imageInfoList.add(imageInfoVo);
                }
            });
            advPositionVo.setImageInfoVoList(imageInfoList);
            advPositionVo.getImageInfoVoList().forEach(imageInfoVo -> {
                log.info("imageInfoVo {}", imageInfoVo);
            });
        }
    }

    @Test
    public void getFloorLists() {
        IPage<FloorVo> floorVoIPage = this.floorMapper.getFloorList(new Page(1, 10), "", "");
        floorVoIPage.getRecords().forEach(floorVo -> {
            List<WebPageVo> webPageVoList = this.getWebPageList(floorVo.getId());
            String[] strings = new String[webPageVoList.size()];
            for (int i = 0; i < webPageVoList.size(); i++) {
                strings[i] = webPageVoList.get(i).getTitle();
            }
            floorVo.setLinkPage(strings);
            log.info("floorVo {}", floorVo);
        });
    }

    @Test
    public void getWebPage() {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Notice> noticeList = this.noticeMapper.selectList(new QueryWrapper<Notice>()
                .eq("IS_DEL", Constant.IS_DEL.NO)
                .eq("IS_USE", Notice.EIsUse.ON.val())
                .lt("START_TIME", localDateTime)
                .gt("END_TIME", localDateTime));
        noticeList.forEach(notice -> {
            log.info("notice {}", notice);
        });
    }

    @Test
    public void getCouponList() {
        IPage<CouponVo> couponVoIPage = this.couponMapper.getCouponList(new Page(1, 10), new CouponVo());

        couponVoIPage.getRecords().forEach(couponVo -> {


            List<CouponVo.CustomerSet> customerSetList = new ArrayList<>();//客户集合列表
            List<CouponVo.GoodsSet> goodsSetList = new ArrayList<>();//商品集合列表

            if (couponVo.getCustomSetType().equals(Coupon.ECustomSetType.ALL.val())) return;
            List<CouponMember> couponMemberList = this.couponMemberMapper.selectList(new QueryWrapper<CouponMember>().eq("COUPON_ID", couponVo.getId()));
            if (null == couponMemberList) return;
            couponMemberList.forEach(couponMember -> {
                CouponVo.CustomerSet customerSet = new CouponVo.CustomerSet(); //客户集合
                BannedVo bannedVo = this.memberMapper.getAdminMember(couponMember.getCustomSetId());
                if (null != bannedVo) {
                    customerSet.setCustomerSetId(bannedVo.getCustomsetid());
                    customerSet.setCustomerSetName(bannedVo.getCustomsetname());
                }
                customerSetList.add(customerSet);
            });
            couponVo.setCustomerSetList(customerSetList);

            if (couponVo.getGoodsSetType().equals(Coupon.EGoodsSetType.ALL.val()))return;
            List<CouponGoods> couponGoodsList = this.couponGoodsMapper.selectList(new QueryWrapper<CouponGoods>().eq("COUPON_ID",couponVo.getId()));
            if (null == couponGoodsList) return;
            couponGoodsList.forEach(couponGoods -> {
                CouponVo.GoodsSet goodsSet = new CouponVo.GoodsSet();//商品集合
                BannedVo bannedVo = this.goodsInfoMapper.getAdminGoods(couponGoods.getGoodsSetId());
                if (null != bannedVo){
                    goodsSet.setGoodsSetId(bannedVo.getGoodssetid());
                    goodsSet.setGoodsSetName(bannedVo.getGoodssetname());
                }
                goodsSetList.add(goodsSet);
            });
            couponVo.setGoodsSetList(goodsSetList);
        });


    }

    @Test
    public void getAdvPositionDetail(){
        this.webPageService.getAdvPositionDetail(157L,149L);
    }

    @Test
    public void getFloorDetail(){
        this.webPageService.getFloorDetail(new Page(1,10),149L,142L);
    }


    @Test
    public void testGetAvailableAdvPosition(){
        this.advPositionMapper.getAvailableAdvPosition(10L, 10L);
        this.floorMapper.getAvailableFloor(10L, 10L);
    }

    @Test
    public void getCart(){
        CartVo cartVo = new CartVo();
        cartVo.setMemberId(81L);
        List<CartVo> cartVoList = this.goodsService.getMemberCartAll(cartVo);
        System.out.println("--------------------");
        System.out.println(cartVoList);

        System.out.println("--------------------");

    }

    @Test
    public void  getWebPageList(){
        this.webPageService.getWebPageList(81L);
    }

}

package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private CouponMemberMapper couponMemberMapper;
    @Autowired
    private CouponGoodsMapper couponGoodsMapper;
    @Autowired
    private CouponReceiveMapper couponReceiveMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private CouponMemberServiceImpl couponMemberService;
    @Autowired
    private CouponGoodsServiceImpl couponGoodsService;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public void addCoupon(CouponVo couponVo, Long userId) {
        AssertExt.notNull(couponVo.getCouponName(), "请输入优惠券名称");
        AssertExt.notNull(couponVo.getCustomSetType(), "请选择客户集合类型");
        AssertExt.checkEnum(Coupon.ECustomSetType.class, couponVo.getCustomSetType(), "没有该客户集合类型，请重新选择");
        if (!couponVo.getCustomSetType().equals(Coupon.ECustomSetType.ALL.val())) {
            AssertExt.notNull(couponVo.getCustomerSetList(), "请选择客户集合");
        }
        AssertExt.notNull(couponVo.getGoodsSetType(), "请选择商品集合类型");
        AssertExt.checkEnum(Coupon.EGoodsSetType.class, couponVo.getGoodsSetType(), "没有该商品集合类型，请重新选择");

        if (!couponVo.getGoodsSetType().equals(Coupon.EGoodsSetType.ALL.val())) {
            AssertExt.notNull(couponVo.getGoodsSetList(), "请选择商品集合");
        }

        AssertExt.notNull(couponVo.getExplain(), "请输入优惠券说明");
        AssertExt.notNull(couponVo.getType(), "请选择优惠券类型");
        AssertExt.checkEnum(Coupon.EType.class, couponVo.getType(), "没有该优惠券类型，请重新选择");
        if (couponVo.getType().equals(Coupon.EType.FULL_REDUCTION_TICKET.val())) {
            AssertExt.notNull(couponVo.getFullAmount(), "请输入满足金额");
            AssertExt.notNull(couponVo.getReduceAmount(), "请输入优惠金额");
        } else if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
            AssertExt.notNull(couponVo.getDiscount(), "请输入折扣");
        } else {
            AssertExt.notNull(couponVo.getReduceAmount(), "请输入优惠金额");
        }
        AssertExt.notNull(couponVo.getCouponAccept(), "请输入总发放数量");
        AssertExt.notNull(couponVo.getLimitNum(), "请输入用户限领数量");
        AssertExt.notNull(couponVo.getReceiveStartTime(), "请输入发放开始时间");
        AssertExt.notNull(couponVo.getReceiveEndTime(), "请输入可领取结束时间");
        if (couponVo.getReceiveEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals("00:00:00")){
            couponVo.setReceiveEndTime(couponVo.getReceiveEndTime().with(LocalTime.MAX));
        }
        AssertExt.notNull(couponVo.getUseDay(), "请输入有效使用天数");
        AssertExt.notNull(couponVo.getUseStartTime(), "请输入可使用开始时间");
        AssertExt.notNull(couponVo.getUseEndTime(), "请输入可使用结束时间");
        if (couponVo.getUseEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals("00:00:00")){
            couponVo.setUseEndTime(couponVo.getUseEndTime().with(LocalTime.MAX));
        }
        couponVo.setCreateUserId(userId);
        couponVo.setRemainNum(couponVo.getCouponAccept());
        couponVo.setCreateTime(LocalDateTime.now());
        this.couponMapper.insert(couponVo);
        this.addCouponCustomerSet(couponVo, userId);
        this.addCouponGoodsSet(couponVo);
    }

    @Override
    public void updateCoupon(CouponVo couponVo, Long userId) {
        AssertExt.hasId(couponVo.getId(), "优惠券id无效");
        Coupon coupon = this.couponMapper.selectById(couponVo.getId());
        AssertExt.notNull(coupon, "优惠券不存在");
        AssertExt.isFalse(coupon.getRemainNum() > coupon.getCouponAccept(), "优惠已被领取过，不可修改！");
        AssertExt.notNull(couponVo.getCouponName(), "优惠券名称为空");
        AssertExt.notNull(couponVo.getCustomSetType(), "客户集合类型为空");
        AssertExt.checkEnum(Coupon.ECustomSetType.class, couponVo.getCustomSetType(), "客户集合类型不匹配，请重新选择");
        if (!couponVo.getCustomSetType().equals(Coupon.ECustomSetType.ALL.val())) {
            AssertExt.notNull(couponVo.getCustomerSetList(), "请选择客户集合");
        }

        AssertExt.notNull(couponVo.getGoodsSetType(), "商品集合类型为空");
        AssertExt.checkEnum(Coupon.EGoodsSetType.class, couponVo.getGoodsSetType(), "商品集合类型不匹配，请重新选择");
        if (!couponVo.getGoodsSetType().equals(Coupon.EGoodsSetType.ALL.val())) {
            AssertExt.notNull(couponVo.getGoodsSetList(), "请选择商品集合");
        }

        AssertExt.notNull(couponVo.getExplain(), "优惠券说明为空");
        AssertExt.notNull(couponVo.getType(), "优惠券类型为空");
        AssertExt.checkEnum(Coupon.EType.class, couponVo.getType(), "优惠券类型不匹配，请重新选择");
        if (couponVo.getType().equals(Coupon.EType.FULL_REDUCTION_TICKET.val())) {
            AssertExt.notNull(couponVo.getFullAmount(), "满足金额为空");
            AssertExt.notNull(couponVo.getReduceAmount(), "优惠金额为空");
        } else if (couponVo.getType().equals(Coupon.EType.FULL_PRESENT_TICKET.val())) {
            AssertExt.notNull(couponVo.getDiscount(), "折扣为空");
        } else {
            AssertExt.notNull(couponVo.getReduceAmount(), "优惠金额为空");
        }
        AssertExt.notNull(couponVo.getCouponAccept(), "总发放数量为空");
        AssertExt.notNull(couponVo.getRemainNum(), "剩余数量为空");
        AssertExt.notNull(couponVo.getLimitNum(), "用户限领数量为空");
        AssertExt.notNull(couponVo.getReceiveStartTime(), "发放开始时间为空");
        AssertExt.notNull(couponVo.getReceiveEndTime(), "可领取结束时间为空");
        if (couponVo.getReceiveEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals("00:00:00")){
            couponVo.setReceiveEndTime(couponVo.getReceiveEndTime().with(LocalTime.MAX));
        }
        AssertExt.notNull(couponVo.getUseDay(), "有效使用天数为空");
        AssertExt.notNull(couponVo.getUseStartTime(), "可使用开始时间为空");
        AssertExt.notNull(couponVo.getUseEndTime(), "可使用结束时间为空");
        if (couponVo.getUseEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals("00:00:00")){
            couponVo.setUseEndTime(couponVo.getUseEndTime().with(LocalTime.MAX));
        }
        BeanUtils.copyProperties(couponVo, coupon);
        this.couponMapper.updateById(coupon);
        this.addCouponCustomerSet(couponVo, userId);
        this.addCouponGoodsSet(couponVo);
    }

    @Override
    public void delCoupon(Long id) {
        AssertExt.hasId(id, "优惠券id无效");
        Coupon coupon = this.couponMapper.selectById(id);
        AssertExt.notNull(coupon, "优惠券不存在");
        coupon.setIsDel(Constant.IS_DEL.YES);
        this.couponMapper.updateById(coupon);
        this.couponReceiveMapper.updateByCouponReceive(id);
    }

    @Override
    public void closeCoupon(Long id, Integer isUse) {
        AssertExt.hasId(id, "优惠券id无效");
        Coupon coupon = this.couponMapper.selectById(id);
        AssertExt.notNull(coupon, "优惠券不存在");
        coupon.setIsUse(isUse);
        this.couponMapper.updateById(coupon);
    }

    @Override
    public IPage<CouponVo> getCouponList(Page page, CouponVo couponVo) {
        AssertExt.notNull(page, "页码为空");
        IPage<CouponVo> couponVoIPage = this.couponMapper.getCouponList(page, couponVo);
        couponVoIPage.getRecords().forEach(couponVo1 -> {


            List<CouponVo.CustomerSet> customerSetList = new ArrayList<>();//客户集合列表
            List<CouponVo.GoodsSet> goodsSetList = new ArrayList<>();//商品集合列表

            if (!couponVo1.getCustomSetType().equals(Coupon.ECustomSetType.ALL.val())) {
                List<CouponMember> couponMemberList = this.couponMemberMapper.selectList(new QueryWrapper<CouponMember>().eq("COUPON_ID", couponVo1.getId()));
                if (null == couponMemberList) return;
                couponMemberList.forEach(couponMember -> {
                    CouponVo.CustomerSet customerSet = new CouponVo.CustomerSet(); //客户集合
                    BannedVo bannedVo = this.memberMapper.getAdminMember(couponMember.getCustomSetId());
                    if (null != bannedVo) {
                        customerSet.setCustomerSetId(bannedVo.getCustomsetid());
                        customerSet.setCustomerSetName(bannedVo.getCustomsetname());
                        customerSet.setStatus(bannedVo.getUsestatus());
                    }
                    customerSetList.add(customerSet);
                });
                couponVo1.setCustomerSetList(customerSetList);
            }

            if (!couponVo1.getGoodsSetType().equals(Coupon.EGoodsSetType.ALL.val())) {
                List<CouponGoods> couponGoodsList = this.couponGoodsMapper.selectList(new QueryWrapper<CouponGoods>().eq("COUPON_ID", couponVo1.getId()));
                if (null == couponGoodsList) return;
                couponGoodsList.forEach(couponGoods -> {
                    CouponVo.GoodsSet goodsSet = new CouponVo.GoodsSet();//商品集合
                    BannedVo bannedVo = this.goodsInfoMapper.getAdminGoods(couponGoods.getGoodsSetId());
                    if (null != bannedVo) {
                        goodsSet.setGoodsSetId(bannedVo.getGoodssetid());
                        goodsSet.setGoodsSetName(bannedVo.getGoodssetname());
                        goodsSet.setStatus(bannedVo.getUsestatus());
                    }
                    goodsSetList.add(goodsSet);
                });
                couponVo1.setGoodsSetList(goodsSetList);
            }
        });

        return couponVoIPage;
    }

    @Override
    public List<CouponVo> getCouponListIsNotPage(CouponVo couponVo) {
        return this.couponMapper.getCouponListIsNotPage(couponVo);
    }

    @Override
    public CouponGiveRecordVo adminGiveCouponToMemberSet(CouponVo couponVo) {
        List<CouponGiveRecordVo.Member> invalidVisibleMemberList = new ArrayList<>();// 可见类型-->部分可见 无效客户
        List<CouponGiveRecordVo.Member> invalidInvisibleMemberList = new ArrayList<>();// 可见类型-->部分不可见 无效客户
        String visibleMessage = "以上客户不存在可见客户集合中！！没有赠送该优惠券";
        String invisibleMessage = "以上客户存在不可见客户集合中,没有赠送该优惠券";
        AssertExt.hasId(couponVo.getCouponId(), "优惠券id无效");
        AssertExt.notNull(couponVo.getGiveNum(), "赠送数量为空");
        Coupon coupon = this.couponMapper.selectById(couponVo.getCouponId());
        AssertExt.notNull(coupon, "优惠券不存在");
        AssertExt.notNull(couponVo.getCustomerSetList(), "客户集合为空");
        CouponGiveRecordVo couponGiveRecordVo = new CouponGiveRecordVo();
        if (null != coupon.getLimitNum()) {
            AssertExt.isFalse(couponVo.getGiveNum() > coupon.getLimitNum(), "用户限领数量[%s]", coupon.getLimitNum());
        }
        List<Long> setIdList = couponVo.getCustomerSetList().stream().map(CouponVo.CustomerSet::getCustomerSetId).collect(Collectors.toList());// 选择的客户集合id
        List<BannedVo> bannedVoList = this.memberMapper.getAdminMemberListByIdIsNotPage(setIdList);// 获取不重复质量可用的客户
        couponVo.setCustomerIdList(bannedVoList.stream().map(BannedVo::getCustomid).collect(Collectors.toList()));// 需要赠送的客户id
        // 循环所有需要赠送的客户id-->判断是否存在对应客户-->判断优惠券可见类型-->赠送
        for (Long memberId : couponVo.getCustomerIdList()) {
            Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("ERP_USER_ID", memberId));
            if (null != member) {
                Integer couponNum = this.couponReceiveMapper.selectCount(new QueryWrapper<CouponReceive>().eq("MEMBER_ID", member.getErpUserId()).eq("COUPON_ID", coupon.getId()));

                AssertExt.isFalse(couponNum > coupon.getLimitNum(), "用户限领数量[%s]", coupon.getLimitNum());

                if (coupon.getCustomSetType().equals(Coupon.ECustomSetType.ALL.val())) {
                    for (int j = 0; j < couponVo.getGiveNum(); j++) {
                        CouponReceive couponReceive = new CouponReceive();
                        couponReceive.setMemberId(member.getErpUserId());
                        couponReceive.setCouponId(coupon.getId());
                        couponReceive.setCouponCode(this.getStringRandom());
                        couponReceive.setStatus(CouponReceive.EStatus.TO_RECEIVE.val());
                        couponReceive.setIsDel(Constant.IS_DEL.NO);
                        couponReceive.setSource(CouponReceive.ESource.ADMIN.val());
                        couponReceive.setCreateTime(LocalDateTime.now());
                        this.couponReceiveMapper.insert(couponReceive);
                    }
                } else if (coupon.getCustomSetType().equals(Coupon.ECustomSetType.VISIBLE.val())) {
                    List<Long> longList = this.couponMemberMapper.selectList(new QueryWrapper<CouponMember>().eq("coupon_id", coupon.getId()).eq("VISIBLE_TYPE", Coupon.ECustomSetType.VISIBLE.val())).stream().map(CouponMember::getCustomSetId).collect(Collectors.toList());
                    int count = this.couponMapper.isExistMemberSet(longList, member.getErpUserId());
                    if (count > 0) {
                        for (int i = 0; i < couponVo.getGiveNum(); i++) {
                            CouponReceive couponReceive = new CouponReceive();
                            couponReceive.setMemberId(member.getErpUserId());
                            couponReceive.setCouponId(coupon.getId());
                            couponReceive.setCouponCode(this.getStringRandom());
                            couponReceive.setStatus(CouponReceive.EStatus.TO_RECEIVE.val());
                            couponReceive.setIsDel(Constant.IS_DEL.NO);
                            couponReceive.setSource(CouponReceive.ESource.ADMIN.val());
                            couponReceive.setCreateTime(LocalDateTime.now());
                            this.couponReceiveMapper.insert(couponReceive);
                        }
                    } else {
                        CouponGiveRecordVo.Member member1 = new CouponGiveRecordVo.Member();
                        Long invalid = 0L;// 当前无效id
                        invalid = member.getErpUserId();
                        if (invalid > 0) {
                            member1.setId(invalid);
                            member1.setName(member.getUserName());
                            invalidVisibleMemberList.add(member1);
                        }
                    }
                } else {
                    // 不可见客户id
                    List<Long> longList = this.couponMapper.getErpCustomSetByIdNoSee(coupon.getId());
                    Long invalid = 0L;// 当前无效id
                    for (Long id : longList) {
                        // 判断客户是否在不可见集合中
                        if (id.equals(member.getErpUserId())) {
                            invalid = member.getErpUserId();
                            break;
                        }
                    }
                    CouponGiveRecordVo.Member member4 = new CouponGiveRecordVo.Member();
                    if (invalid > 0) {
                        member4.setId(invalid);
                        member4.setName(member.getUserName());
                        invalidInvisibleMemberList.add(member4);
                    } else {
                        for (int i = 0; i < couponVo.getGiveNum(); i++) {
                            CouponReceive couponReceive = new CouponReceive();
                            couponReceive.setMemberId(member.getErpUserId());
                            couponReceive.setCouponId(coupon.getId());
                            couponReceive.setCouponCode(this.getStringRandom());
                            couponReceive.setStatus(CouponReceive.EStatus.TO_RECEIVE.val());
                            couponReceive.setIsDel(Constant.IS_DEL.NO);
                            couponReceive.setSource(CouponReceive.ESource.ADMIN.val());
                            couponReceive.setCreateTime(LocalDateTime.now());
                            this.couponReceiveMapper.insert(couponReceive);
                        }
                    }
                }
            }
        }
        int giveMemberNum = 0;// 赠送客户数量
        int giveCouponNum = 0;// 赠送优惠券数量
        if (coupon.getCustomSetType().equals(Coupon.ECustomSetType.VISIBLE.val())) {
            couponGiveRecordVo.setMemberList(invalidVisibleMemberList);
            couponGiveRecordVo.setMessage(visibleMessage);
            giveMemberNum = couponVo.getCustomerIdList().size() - invalidVisibleMemberList.size();
            giveCouponNum = giveMemberNum * couponVo.getGiveNum();
        } else if (coupon.getCustomSetType().equals(Coupon.ECustomSetType.UN_VISIBLE.val())) {
            couponGiveRecordVo.setMemberList(invalidInvisibleMemberList);
            couponGiveRecordVo.setMessage(invisibleMessage);
            giveMemberNum = couponVo.getCustomerIdList().size() - invalidInvisibleMemberList.size();
            giveCouponNum = giveMemberNum * couponVo.getGiveNum();
        } else {
            giveMemberNum = couponVo.getCustomerIdList().size();
            giveCouponNum = giveMemberNum * couponVo.getGiveNum();
        }
        AssertExt.isFalse(coupon.getRemainNum() - giveCouponNum < 0, "不好意思，送券超出剩余数量！请慎重");
        coupon.setRemainNum(coupon.getRemainNum() - giveCouponNum);
        this.couponMapper.updateById(coupon);
        return couponGiveRecordVo;
    }

    @Override
    public CouponGiveRecordVo adminGiveCouponToMember(CouponVo couponVo) {
        AssertExt.notNull(couponVo.getCustomerIdList(), "客户id为空");
        List<CouponGiveRecordVo.Member> invalidVisibleMemberList = new ArrayList<>();// 可见类型-->部分可见 无效客户
        List<CouponGiveRecordVo.Member> invalidInvisibleMemberList = new ArrayList<>();// 可见类型-->部分不可见 无效客户
        String visibleMessage = "以上客户不存在可见客户集合中！！没有赠送该优惠券";
        String invisibleMessage = "以上客户存在不可见客户集合中,没有赠送该优惠券";
        Coupon coupon = this.couponMapper.selectById(couponVo.getCouponId());
        AssertExt.notNull(coupon, "优惠券不存在");
        for (Long customerId : couponVo.getCustomerIdList()) {
            Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("ERP_USER_ID", customerId));
            AssertExt.notNull(member, "客户不存在，无法选为赠送对象[%s]", couponVo.getMemberId());
            AssertExt.hasId(couponVo.getCouponId(), "优惠券id为空");
            AssertExt.notNull(couponVo.getGiveNum(), "赠送数量为空");
            if (null != coupon.getLimitNum()) {
                AssertExt.isFalse(couponVo.getGiveNum() > coupon.getLimitNum(), "用户限领数量[%s]", coupon.getLimitNum());
            }
            if (null != member) {
                Integer couponNum = this.couponReceiveMapper.selectCount(new QueryWrapper<CouponReceive>().eq("MEMBER_ID", member.getErpUserId()).eq("COUPON_ID", coupon.getId()));

                AssertExt.isFalse(couponNum > coupon.getLimitNum(), "用户限领数量[%s]", coupon.getLimitNum());
                if (coupon.getCustomSetType().equals(Coupon.ECustomSetType.ALL.val())) {
                    for (int i = 0; i < couponVo.getGiveNum(); i++) {
                        CouponReceive couponReceive = new CouponReceive();
                        couponReceive.setMemberId(member.getErpUserId());
                        couponReceive.setCouponId(coupon.getId());
                        couponReceive.setCouponCode(this.getStringRandom());
                        couponReceive.setStatus(CouponReceive.EStatus.TO_RECEIVE.val());
                        couponReceive.setIsDel(Constant.IS_DEL.NO);
                        couponReceive.setSource(CouponReceive.ESource.ADMIN.val());
                        couponReceive.setCreateTime(LocalDateTime.now());
                        this.couponReceiveMapper.insert(couponReceive);
                    }
                } else if (coupon.getCustomSetType().equals(Coupon.ECustomSetType.VISIBLE.val())) {
                    List<Long> longList = this.couponMemberMapper.selectList(new QueryWrapper<CouponMember>().eq("coupon_id", coupon.getId()).eq("VISIBLE_TYPE", Coupon.ECustomSetType.VISIBLE.val())).stream().map(CouponMember::getCustomSetId).collect(Collectors.toList());
                    int count = this.couponMapper.isExistMemberSet(longList, member.getErpUserId());
                    if (count > 0) {
                        for (int i = 0; i < couponVo.getGiveNum(); i++) {
                            CouponReceive couponReceive = new CouponReceive();
                            couponReceive.setMemberId(member.getErpUserId());
                            couponReceive.setCouponId(coupon.getId());
                            couponReceive.setCouponCode(this.getStringRandom());
                            couponReceive.setStatus(CouponReceive.EStatus.TO_RECEIVE.val());
                            couponReceive.setIsDel(Constant.IS_DEL.NO);
                            couponReceive.setSource(CouponReceive.ESource.ADMIN.val());
                            couponReceive.setCreateTime(LocalDateTime.now());
                            this.couponReceiveMapper.insert(couponReceive);
                        }
                    } else {
                        CouponGiveRecordVo.Member member2 = new CouponGiveRecordVo.Member();
                        Long invalid = 0L;// 当前无效id
                        invalid = member.getErpUserId();
                        if (invalid > 0) {
                            member2.setId(invalid);
                            member2.setName(member.getUserName());
                            invalidVisibleMemberList.add(member2);
                        }
                    }
                } else {
                    // 不可见客户id
                    List<Long> longList = this.couponMapper.getErpCustomSetByIdNoSee(coupon.getId());
                    Long invalid = 0L;// 当前无效id
                    for (Long memberId : longList) {
                        // 判断客户是否在不可见集合中
                        if (memberId.equals(member.getErpUserId())) {
                            invalid = member.getErpUserId();
                            break;
                        }
                    }
                    CouponGiveRecordVo.Member member3 = new CouponGiveRecordVo.Member();
                    if (invalid > 0) {
                        member3.setId(invalid);
                        member3.setName(member.getUserName());
                        invalidInvisibleMemberList.add(member3);
                    } else {
                        for (int i = 0; i < couponVo.getGiveNum(); i++) {
                            CouponReceive couponReceive = new CouponReceive();
                            couponReceive.setMemberId(member.getErpUserId());
                            couponReceive.setCouponId(coupon.getId());
                            couponReceive.setCouponCode(this.getStringRandom());
                            couponReceive.setStatus(CouponReceive.EStatus.TO_RECEIVE.val());
                            couponReceive.setIsDel(Constant.IS_DEL.NO);
                            couponReceive.setSource(CouponReceive.ESource.ADMIN.val());
                            couponReceive.setCreateTime(LocalDateTime.now());
                            this.couponReceiveMapper.insert(couponReceive);
                        }
                    }
                }
            }
        }
        CouponGiveRecordVo couponGiveRecordVo = new CouponGiveRecordVo();
        int giveMemberNum = 0;// 赠送客户数量
        int giveCouponNum = 0;// 赠送优惠券数量
        if (coupon.getCustomSetType().equals(Coupon.ECustomSetType.VISIBLE.val())) {
            couponGiveRecordVo.setMemberList(invalidVisibleMemberList);
            couponGiveRecordVo.setMessage(visibleMessage);
            giveMemberNum = couponVo.getCustomerIdList().size() - invalidVisibleMemberList.size();
            giveCouponNum = giveMemberNum * couponVo.getGiveNum();
        } else if (coupon.getCustomSetType().equals(Coupon.ECustomSetType.UN_VISIBLE.val())) {
            couponGiveRecordVo.setMemberList(invalidInvisibleMemberList);
            couponGiveRecordVo.setMessage(invisibleMessage);
            giveMemberNum = couponVo.getCustomerIdList().size() - invalidInvisibleMemberList.size();
            giveCouponNum = giveMemberNum * couponVo.getGiveNum();
        } else {
            couponGiveRecordVo.setMemberList(null);
            couponGiveRecordVo.setMessage("所有客户都获得此优惠券！！");
            giveMemberNum = couponVo.getCustomerIdList().size();
            giveCouponNum = giveMemberNum * couponVo.getGiveNum();
        }
        AssertExt.isFalse(coupon.getRemainNum() - giveCouponNum < 0, "不好意思，送券超出剩余数量！请慎重");
        coupon.setRemainNum(coupon.getRemainNum() - giveCouponNum);
        this.couponMapper.updateById(coupon);
        return couponGiveRecordVo;
    }

    @Override
    public Coupon getCouponById(Long id) {
        AssertExt.hasId(id, "优惠券id无效");
        Coupon coupon = this.couponMapper.selectById(id);
        AssertExt.notNull(coupon, "优惠券不存在");
        return coupon;
    }

    @Override
    public void cutIsReceive(Long id, Integer isReceive) {
        AssertExt.hasId(id, "优惠券id无效");
        AssertExt.notNull(isReceive, "切换发放状态为空");
        Coupon coupon = this.couponMapper.selectById(id);
        AssertExt.notNull(coupon, "优惠券id无效[%s]", id);
        coupon.setIsReceive(isReceive);
        this.couponMapper.updateById(coupon);
    }

    @Override
    public void addCouponCustomerSet(CouponVo couponVo, Long memberId) {
        this.couponMemberService.remove(new QueryWrapper<CouponMember>().eq("COUPON_ID", couponVo.getId()));
        if (null != couponVo.getCustomerSetList() && couponVo.getCustomerSetList().size() > 0) {
            for (int i = 0; i < couponVo.getCustomerSetList().size(); i++) {
                CouponMember couponMember = new CouponMember();
                couponMember.setCouponId(couponVo.getId());
                couponMember.setCustomSetId(couponVo.getCustomerSetList().get(i).getCustomerSetId());
                couponMember.setVisibleType(couponVo.getCustomSetType());
                couponMember.setCreateTime(LocalDateTime.now());
                couponMember.setAdminId(couponVo.getCreateUserId());
                this.couponMemberMapper.insert(couponMember);
            }
        } else {
            CouponMember couponMember = new CouponMember();
            couponMember.setCouponId(couponVo.getId());
            couponMember.setCustomSetId(null);
            couponMember.setVisibleType(couponVo.getCustomSetType());
            couponMember.setCreateTime(LocalDateTime.now());
            couponMember.setAdminId(couponVo.getCreateUserId());
            this.couponMemberMapper.insert(couponMember);
        }
    }

    @Override
    public void addCouponGoodsSet(CouponVo couponVo) {
        this.couponGoodsService.remove(new QueryWrapper<CouponGoods>().eq("COUPON_ID", couponVo.getId()));
        if (null != couponVo.getGoodsSetList() && couponVo.getGoodsSetList().size() > 0) {
            for (int i = 0; i < couponVo.getGoodsSetList().size(); i++) {
                CouponGoods couponGoods = new CouponGoods();
                couponGoods.setCouponId(couponVo.getId());
                couponGoods.setGoodsSetId(couponVo.getGoodsSetList().get(i).getGoodsSetId());
                couponGoods.setIsUse(Constant.IS_USE.ON);
                couponGoods.setVisibleType(couponVo.getGoodsSetType());
                couponGoods.setCreateTime(LocalDateTime.now());
                this.couponGoodsMapper.insert(couponGoods);
            }
        } else {
            CouponGoods couponGoods = new CouponGoods();
            couponGoods.setCouponId(couponVo.getId());
            couponGoods.setGoodsSetId(null);
            couponGoods.setIsUse(Constant.IS_USE.ON);
            couponGoods.setVisibleType(couponVo.getGoodsSetType());
            couponGoods.setCreateTime(LocalDateTime.now());
            this.couponGoodsMapper.insert(couponGoods);
        }
    }

    @Override
    public List<CustomerVo> getCustomerByCustomerInfoVo(CustomerInfoVo customerInfoVo) {
        return this.memberMapper.getCustomerByCustomerInfoVo(customerInfoVo);
    }

    //优惠券码生成
    private String getStringRandom() {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < 9; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(String.valueOf(random.nextInt(10)));
            }
        }
        return val.toString();
    }
}

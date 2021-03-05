package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.DeliveryAmount;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.mapper.DeliveryAmountMapper;
import com.zsyc.zt.b2b.mapper.MemberMapper;
import com.zsyc.zt.b2b.vo.DeliveryAmountVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class DeliveryAmountServiceImpl implements DeliveryAmountService {
    @Autowired
    private DeliveryAmountMapper deliveryAmountMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public IPage<DeliveryAmount> getDeliveryAmount(Page page, DeliveryAmountVo deliveryAmountVo) {
        return this.deliveryAmountMapper.getDeliveryAmount(page, deliveryAmountVo);
    }

    @Override
    public void addDeliveryAmount(DeliveryAmount deliveryAmount, Long userId) {
        AssertExt.notBlank(deliveryAmount.getDaName(), "起送规则名称为空");
        AssertExt.notBlank(deliveryAmount.getAreaId(), "erp地区ID为空");
        AssertExt.notBlank(deliveryAmount.getAreaName(), "erp地区名称为空");
        // 新增：防重复地区名称/起送规则名-->需要则去掉注释
        List<DeliveryAmount> deliveryAmountList = this.deliveryAmountMapper.selectList(new QueryWrapper<>());
        List<String> daNameList = deliveryAmountList.stream().filter(deliveryAmount1 -> !deliveryAmount1.getId().equals(deliveryAmount.getId())).map(DeliveryAmount::getDaName).collect(Collectors.toList());
        daNameList.forEach(s -> AssertExt.isFalse(deliveryAmount.getDaName().equals(s),"起送规则名称已存在，请重新输入"));
        List<String> areaNameList = deliveryAmountList.stream().filter(deliveryAmount1 -> !deliveryAmount1.getId().equals(deliveryAmount.getId())).map(DeliveryAmount::getAreaName).collect(Collectors.toList());
        areaNameList.forEach(s -> AssertExt.isFalse(deliveryAmount.getAreaName().equals(s),"地区已存在，请重新输入"));

        AssertExt.notBlank(deliveryAmount.getType(), "地区类型为空");
        AssertExt.checkEnum(DeliveryAmount.EType.class, deliveryAmount.getType(), "类型无效");
        AssertExt.notNull(deliveryAmount.getDaAmount(), "起送金额为空");
        deliveryAmount.setIsUse(DeliveryAmount.EIsUse.OFF.val());
        deliveryAmount.setCreateUserId(userId);
        deliveryAmount.setCreateTime(LocalDateTime.now());
        this.deliveryAmountMapper.insert(deliveryAmount);
    }

    @Override
    public void updateDeliveryAmountIsUse(Long id, String isUse, Long userId) {
        AssertExt.hasId(id, "ID无效");
        DeliveryAmount deliveryAmount = this.deliveryAmountMapper.selectById(id);
        AssertExt.notNull(deliveryAmount, "起送规则不存在");
        AssertExt.notBlank(isUse, "开启状态为空");
        AssertExt.checkEnum(DeliveryAmount.EIsUse.class, deliveryAmount.getIsUse(), "开启状态不匹配");
        deliveryAmount.setIsUse(isUse);
        deliveryAmount.setUpdateUserId(userId);
        deliveryAmount.setUpdateTime(LocalDateTime.now());
        this.deliveryAmountMapper.updateById(deliveryAmount);
    }

    @Override
    public void updateDeliveryAmount(DeliveryAmount deliveryAmount, Long userId) {
        AssertExt.hasId(deliveryAmount.getId(), "无效ID");
        DeliveryAmount deliveryAmountDB = this.deliveryAmountMapper.selectById(deliveryAmount.getId());
        AssertExt.notNull(deliveryAmountDB, "起送规则不存在");
        List<DeliveryAmount> deliveryAmountList = this.deliveryAmountMapper.selectList(new QueryWrapper<>());
        List<String> daNameList = deliveryAmountList.stream().filter(deliveryAmount1 -> !deliveryAmount1.getId().equals(deliveryAmount.getId())).map(DeliveryAmount::getDaName).collect(Collectors.toList());
        daNameList.forEach(s -> AssertExt.isFalse(deliveryAmount.getDaName().equals(s),"起送规则名称已存在，请重新输入"));
        List<String> areaNameList = deliveryAmountList.stream().filter(deliveryAmount1 -> !deliveryAmountDB.getId().equals(deliveryAmount1.getId())).map(DeliveryAmount::getAreaName).collect(Collectors.toList());
        areaNameList.forEach(s -> AssertExt.isFalse(deliveryAmount.getAreaName().equals(s),"不可重复地区"));

        BeanUtils.copyProperties(deliveryAmount, deliveryAmountDB);
        deliveryAmountDB.setUpdateUserId(userId);
        deliveryAmountDB.setUpdateTime(LocalDateTime.now());
        this.deliveryAmountMapper.updateById(deliveryAmountDB);
    }

    @Override
    public Long getDeliveryAmountByCustomerId(Long id) {
        AssertExt.hasId(id, "id为空");
        Member memberDB = this.memberMapper.selectById(id);
        Long amount1=this.deliveryAmountMapper.getDeliveryAmountByCustomerId(memberDB.getErpUserId());
        Long amount = this.deliveryAmountMapper.selectById(1L).getDaAmount();
        if(amount1>0){
            return amount1;
        }
        return amount;
    }

    @Override
    public void delDeliveryAmount(Long id) {
        AssertExt.hasId(id,"id无效");
        DeliveryAmount deliveryAmount = this.deliveryAmountMapper.selectById(id);
        AssertExt.notNull(deliveryAmount,"起送规则不存在");
        this.deliveryAmountMapper.deleteById(id);
    }
}

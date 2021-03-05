package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.AdvPosition;
import com.zsyc.zt.b2b.entity.AdvPositionCustomerSet;
import com.zsyc.zt.b2b.mapper.AdvPositionCustomerSetMapper;
import com.zsyc.zt.b2b.mapper.AdvPositionMapper;
import com.zsyc.zt.b2b.vo.AdvPositionVo;
import com.zsyc.zt.b2b.vo.ImageInfoVo;
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
import java.util.stream.Collectors;

/**
 * <p>
 * 广告位 服务实现类
 * </p>
 *
 * @author MP
 * @since 2020-08-18
 */
@Service
@Transactional
@Slf4j
public class AdvPositionServiceImpl extends ServiceImpl<AdvPositionMapper, AdvPosition> implements AdvPositionService {
    @Autowired
    private AdvPositionMapper advPositionMapper;
    @Autowired
    private AdvPositionCustomerSetMapper advPositionCustomerSetMapper;
    @Autowired
    private AdvPositionCustomerSetServiceImpl advPositionCustomerSetService;

    @Override
    public IPage<AdvPositionVo> getAdvPosition(Page page, String advName, String apDisplay) {
        return this.advPositionMapper.getAdvPosition(page, advName, apDisplay);
    }

    @Override
    public void addAdvPosition(AdvPositionVo advPositionVo, Long userId) {
        AssertExt.notNull(advPositionVo.getAdvName(), "请输入广告名称");
        List<AdvPosition> advPositionList = this.advPositionMapper.selectList(new QueryWrapper<AdvPosition>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<String> nameList = advPositionList.stream().filter(advPosition -> !advPosition.getId().equals(advPositionVo.getId())).map(AdvPosition::getAdvName).collect(Collectors.toList());
        nameList.forEach(s -> AssertExt.isFalse(advPositionVo.getAdvName().equals(s), "广告名称已被使用，请重新输入"));
        AssertExt.notNull(advPositionVo.getVisibleSet(), "广告可见客户集合为空");
        advPositionVo.getVisibleSet().forEach(visibleSet -> {
            AssertExt.notBlank(visibleSet.getType(), "可见类型为空");
            AssertExt.checkEnum(ImageInfoVo.VisibleSet.EType.class, visibleSet.getType(), "可见类型不匹配");
            if (ImageInfoVo.VisibleSet.EType.ALL_VISIBLE.val().equals(visibleSet.getType())) return;
            AssertExt.notEmpty(visibleSet.getMemberSetList(), "客户集合为空");
            visibleSet.getMemberSetList().forEach(memberSet -> {
                AssertExt.hasId(memberSet.getMemberSetId(), "客户集合Id为空");
                AssertExt.notBlank(memberSet.getMemberSetName(), "客户集合名称为空");
            });
        });

        AssertExt.notNull(advPositionVo.getApDisplay(), "请选择广告样式");
        AssertExt.checkEnum(AdvPosition.EApDisplay.class, advPositionVo.getApDisplay(), "广告样式不存在");
        if (advPositionVo.getApDisplay().equals(AdvPosition.EApDisplay.TOP_BANNER.val()) || advPositionVo.getApDisplay().equals(AdvPosition.EApDisplay.CENTER_ONLY.val()) || advPositionVo.getApDisplay().equals(AdvPosition.EApDisplay.POPUP_BANNER.val()) || advPositionVo.getApDisplay().equals(AdvPositionVo.EApDisplay.DISCOUNTS_BANNER.val())) {
            AssertExt.notNull(advPositionVo.getImageInfoVoList(), "广告图片信息为空");
            advPositionVo.getImageInfoVoList().forEach(imageInfoVo -> {
                AssertExt.notNull(imageInfoVo.getImage(), "请上传Pc广告图片");
                //AssertExt.notNull(imageInfoVo.getImageWx(), "请上传小程序广告图片");
                AssertExt.notNull(imageInfoVo.getClickEvent(), "请选择点击事件");
                switch (imageInfoVo.getClickEvent()) {
                    case "erpGoodsId":
                        AssertExt.notNull(imageInfoVo.getErpGoodsId(), "请输入商品编号");
                        break;
                    case "imageUrl":
                        AssertExt.notNull(imageInfoVo.getImageUrl(), "请输入图片链接地址");
                        break;
                    case "goodsName":
                        AssertExt.notNull(imageInfoVo.getGoodsList(), "请选择商品");
                        break;
                }
            });
        } else {
            AssertExt.notNull(advPositionVo.getImageInfoVoList(), "请填写广告图片信息");
            advPositionVo.getImageInfoVoList().forEach(imageInfoVo -> {
                AssertExt.notNull(imageInfoVo.getImage(), "请上传Pc广告图片");
                //AssertExt.notNull(imageInfoVo.getImageWx(), "请上传小程序广告图片");
                AssertExt.notNull(imageInfoVo.getVisibleSet().getType(), "请选择可见类型");
                AssertExt.checkEnum(ImageInfoVo.VisibleSet.EType.class, imageInfoVo.getVisibleSet().getType(), "可见类型不正确");
                if (imageInfoVo.getVisibleSet().getType().equals(ImageInfoVo.VisibleSet.EType.ALL_VISIBLE.val()))
                    return;
                AssertExt.notNull(imageInfoVo.getVisibleSet().getMemberSetList(), "可见客户集合为空");
                imageInfoVo.getVisibleSet().getMemberSetList().forEach(memberSet -> {
                    AssertExt.notNull(memberSet.getMemberSetId(), "客户集合Id为空");
                    AssertExt.notNull(memberSet.getMemberSetName(), "客户集合名称为空");
                });
                AssertExt.notNull(imageInfoVo.getClickEvent(), "请选择点击事件");
                switch (imageInfoVo.getClickEvent()) {
                    case "erpGoodsId":
                        AssertExt.notNull(imageInfoVo.getErpGoodsId(), "请输入商品编号");
                        break;
                    case "imageUrl":
                        AssertExt.notNull(imageInfoVo.getImageUrl(), "请输入图片链接地址");
                        break;
                    case "goodsName":
                        AssertExt.notNull(imageInfoVo.getGoodsList(), "请选择商品");
                        break;
                }
                AssertExt.notNull(imageInfoVo.getImageStartTime(), "广告图片开始时间");
                AssertExt.notNull(imageInfoVo.getImageEndTime(), "广告图片结束时间");
                if (imageInfoVo.getImageEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals("00:00:00")){
                    imageInfoVo.setImageEndTime(imageInfoVo.getImageEndTime().with(LocalTime.MAX));
                }
                AssertExt.notNull(imageInfoVo.getImageIsUse(), "广告图片是否开启");
                AssertExt.checkEnum(ImageInfoVo.EImageIsUse.class, imageInfoVo.getImageIsUse(), "广告图片开启状态无效");
            });
        }
        advPositionVo.setImgNum(advPositionVo.getImageInfoVoList().size());
        AssertExt.notNull(advPositionVo.getAdvStartDate(), "请选择广告开始时间");
        AssertExt.notNull(advPositionVo.getAdvEndDate(), "请选择广告结束时间");
        if (advPositionVo.getAdvEndDate().format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals("00:00:00")){
            advPositionVo.setAdvEndDate(advPositionVo.getAdvEndDate().with(LocalTime.MAX));
        }
        AssertExt.notNull(advPositionVo.getIsUse(), "启用状态为空");
        AssertExt.checkEnum(AdvPosition.EIsUse.class, advPositionVo.getIsUse(), "请选择正确的启用状态");
        advPositionVo.setCreateUserId(userId);
        advPositionVo.setCreateTime(LocalDateTime.now());
        this.advPositionMapper.insert(advPositionVo);
        this.addAdvPositionCustomerSet(advPositionVo);
    }

    @Override
    public void updateAdvPositionIsUse(Long id, String isUse, Long userId) {
        AssertExt.hasId(id, "广告位ID无效");
        AdvPosition advPosition = this.advPositionMapper.selectById(id);
        AssertExt.notNull(advPosition, "广告位不存在");
        AssertExt.notNull(isUse, "启用状态有误");
        AssertExt.checkEnum(AdvPosition.EIsUse.class, advPosition.getIsUse(), "启用状态不匹配");
        advPosition.setIsUse(isUse);
        advPosition.setUpdateUserId(userId);
        advPosition.setUpdateTime(LocalDateTime.now());
        this.advPositionMapper.updateById(advPosition);
    }

    @Override
    public void updateAdvPositionIsDel(Long id, Long userId) {
        AssertExt.hasId(id, "广告位ID无效");
        AdvPosition advPosition = this.advPositionMapper.selectById(id);
        AssertExt.notNull(advPosition, "广告位不存在");
        AssertExt.isFalse(advPosition.getIsUse().equals(AdvPosition.EIsUse.ON.val()), "广告位正在启用，暂时无法删除，请先关闭再操作！");
        advPosition.setIsDel(Constant.IS_DEL.YES);
        advPosition.setUpdateUserId(userId);
        advPosition.setUpdateTime(LocalDateTime.now());
        this.advPositionMapper.updateById(advPosition);
    }

    @Override
    public void updateAdvPosition(AdvPositionVo advPositionVo, Long userId) {
        AssertExt.notNull(advPositionVo.getAdvName(), "请输入广告名称");
        List<AdvPosition> advPositionList = this.advPositionMapper.selectList(new QueryWrapper<AdvPosition>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<String> nameList = advPositionList.stream().filter(advPosition -> !advPosition.getId().equals(advPositionVo.getId())).map(AdvPosition::getAdvName).collect(Collectors.toList());
        nameList.forEach(s -> AssertExt.isFalse(advPositionVo.getAdvName().equals(s), "广告名称已被使用，请重新输入"));
        AssertExt.notNull(advPositionVo.getVisibleSet(), "广告可见客户集合为空");
        advPositionVo.getVisibleSet().forEach(visibleSet -> {
            AssertExt.notBlank(visibleSet.getType(), "可见类型为空");
            AssertExt.checkEnum(ImageInfoVo.VisibleSet.EType.class, visibleSet.getType(), "可见类型不匹配");
            if (ImageInfoVo.VisibleSet.EType.ALL_VISIBLE.val().equals(visibleSet.getType())) return;
            AssertExt.notEmpty(visibleSet.getMemberSetList(), "客户集合为空");
            visibleSet.getMemberSetList().forEach(memberSet -> {
                AssertExt.hasId(memberSet.getMemberSetId(), "客户集合Id为空");
                AssertExt.notBlank(memberSet.getMemberSetName(), "客户集合名称为空");
            });
        });

        AssertExt.notNull(advPositionVo.getApDisplay(), "请选择广告样式");
        AssertExt.checkEnum(AdvPosition.EApDisplay.class, advPositionVo.getApDisplay(), "广告样式不存在");
        if (advPositionVo.getApDisplay().equals(AdvPosition.EApDisplay.TOP_BANNER.val()) || advPositionVo.getApDisplay().equals(AdvPosition.EApDisplay.CENTER_ONLY.val()) || advPositionVo.getApDisplay().equals(AdvPosition.EApDisplay.POPUP_BANNER.val()) || advPositionVo.getApDisplay().equals(AdvPositionVo.EApDisplay.DISCOUNTS_BANNER.val())) {
            AssertExt.notNull(advPositionVo.getImageInfoVoList(), "广告图片信息为空");
            advPositionVo.getImageInfoVoList().forEach(imageInfoVo -> {
                AssertExt.notNull(imageInfoVo.getImage(), "请上传Pc广告图片");
                //AssertExt.notNull(imageInfoVo.getImageWx(), "请上传小程序广告图片");
                AssertExt.notNull(imageInfoVo.getClickEvent(), "请选择点击事件");
                switch (imageInfoVo.getClickEvent()) {
                    case "erpGoodsId":
                        AssertExt.notNull(imageInfoVo.getErpGoodsId(), "请输入商品编号");
                        break;
                    case "imageUrl":
                        AssertExt.notNull(imageInfoVo.getImageUrl(), "请输入图片链接地址");
                        break;
                    case "goodsName":
                        AssertExt.notNull(imageInfoVo.getGoodsList(), "请选择商品");
                        break;
                }
            });
        } else {
            AssertExt.notNull(advPositionVo.getImageInfoVoList(), "广告图片信息为空");
            advPositionVo.getImageInfoVoList().forEach(imageInfoVo -> {
                AssertExt.notNull(imageInfoVo.getImage(), "请上传Pc广告图片");
                //AssertExt.notNull(imageInfoVo.getImageWx(), "请上传小程序广告图片");
                AssertExt.notNull(imageInfoVo.getVisibleSet().getType(), "请选择可见类型");
                AssertExt.checkEnum(ImageInfoVo.VisibleSet.EType.class, imageInfoVo.getVisibleSet().getType(), "可见类型不正确");
                if (imageInfoVo.getVisibleSet().getType().equals(ImageInfoVo.VisibleSet.EType.ALL_VISIBLE.val()))
                    return;
                AssertExt.notNull(imageInfoVo.getVisibleSet().getMemberSetList(), "可见客户集合为空");
                imageInfoVo.getVisibleSet().getMemberSetList().forEach(memberSet -> {
                    AssertExt.notNull(memberSet.getMemberSetId(), "客户集合Id为空");
                    AssertExt.notNull(memberSet.getMemberSetName(), "客户集合名称为空");
                });
                AssertExt.notNull(imageInfoVo.getClickEvent(), "请选择点击事件");
                switch (imageInfoVo.getClickEvent()) {
                    case "erpGoodsId":
                        AssertExt.notNull(imageInfoVo.getErpGoodsId(), "请输入商品编号");
                        break;
                    case "imageUrl":
                        AssertExt.notNull(imageInfoVo.getImageUrl(), "请输入图片链接地址");
                        break;
                    case "goodsName":
                        AssertExt.notNull(imageInfoVo.getGoodsList(), "请选择商品");
                        break;
                }
                AssertExt.notNull(imageInfoVo.getImageStartTime(), "广告图片开始时间");
                AssertExt.notNull(imageInfoVo.getImageEndTime(), "广告图片结束时间");
                if (imageInfoVo.getImageEndTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals("00:00:00")){
                    imageInfoVo.setImageEndTime(imageInfoVo.getImageEndTime().with(LocalTime.MAX));
                }
                AssertExt.notNull(imageInfoVo.getImageIsUse(), "广告图片是否开启");
                AssertExt.checkEnum(ImageInfoVo.EImageIsUse.class, imageInfoVo.getImageIsUse(), "广告图片开启状态无效");
            });
        }
        advPositionVo.setImgNum(advPositionVo.getImageInfoVoList().size());
        AssertExt.notNull(advPositionVo.getAdvStartDate(), "请选择广告开始时间");
        AssertExt.notNull(advPositionVo.getAdvEndDate(), "请选择广告结束时间");
        if (advPositionVo.getAdvEndDate().format(DateTimeFormatter.ofPattern("HH:mm:ss")).equals("00:00:00")){
            advPositionVo.setAdvEndDate(advPositionVo.getAdvEndDate().with(LocalTime.MAX));
        }
        AssertExt.notNull(advPositionVo.getIsUse(), "启用状态为空");
        AssertExt.checkEnum(AdvPosition.EIsUse.class, advPositionVo.getIsUse(), "请选择正确的启用状态");
        advPositionVo.setUpdateUserId(userId);
        advPositionVo.setUpdateTime(LocalDateTime.now());
        this.advPositionMapper.updateById(advPositionVo);
        this.addAdvPositionCustomerSet(advPositionVo);
    }

    @Override
    public AdvPositionVo getAdvPositionById(Long id) {
        AssertExt.hasId(id, "广告位ID无效");
        AdvPosition advPosition = this.advPositionMapper.selectById(id);
        AssertExt.notNull(advPosition, "广告不存在");
        AdvPositionVo advPositionVo = new AdvPositionVo();
        BeanUtils.copyProperties(advPosition, advPositionVo);
        return advPositionVo;
    }

    @Override
    public List<AdvPositionVo> getAdvPositionAll(AdvPositionVo advPositionVo) {
        List<AdvPositionVo> advPositionVoList = this.advPositionMapper.getAdvPositionAll(advPositionVo);
        LocalDateTime localDateTime = LocalDateTime.now();

        advPositionVoList.forEach(advPosition -> {
            List<ImageInfoVo> imageInfoList = new ArrayList<>();
            advPosition.getImageInfoVoList().forEach(imageInfoVo -> {
                if (imageInfoVo.getImageIsUse().equals(ImageInfoVo.EImageIsUse.ON.val())
                        && imageInfoVo.getImageStartTime().isBefore(localDateTime)
                        && imageInfoVo.getImageEndTime().isAfter(localDateTime)) {
                    imageInfoList.add(imageInfoVo);
                }
            });
            advPosition.setImageInfoVoList(imageInfoList);
        });


        return advPositionVoList;
    }

    @Override
    public List<AdvPosition> isNotPageAdvPositionList() {
        return this.advPositionMapper.selectList(new QueryWrapper<AdvPosition>().eq("IS_DEL", Constant.IS_DEL.NO));
    }

    @Override
    public void addAdvPositionCustomerSet(AdvPositionVo advPositionVo) {
        log.info("advPositionVo remove {}", advPositionVo.getId());
        this.advPositionCustomerSetService.remove(new QueryWrapper<AdvPositionCustomerSet>()
                .eq("ADV_ID", advPositionVo.getId()));
        if (null != advPositionVo.getVisibleSet()) {
            advPositionVo.getVisibleSet().forEach(visibleSet -> {
                AdvPositionCustomerSet advPositionCustomerSet = new AdvPositionCustomerSet();
                if (visibleSet.getType().equals(ImageInfoVo.VisibleSet.EType.ALL_VISIBLE.val())) {
                    advPositionCustomerSet.setAdvId(advPositionVo.getId());
                    advPositionCustomerSet.setType(visibleSet.getType());
                    advPositionCustomerSet.setCreateTime(LocalDateTime.now());
                    this.advPositionCustomerSetMapper.insert(advPositionCustomerSet);
                } else {
                    visibleSet.getMemberSetList().forEach(memberSet -> {
                        advPositionCustomerSet.setAdvId(advPositionVo.getId());
                        advPositionCustomerSet.setCreateTime(LocalDateTime.now());
                        advPositionCustomerSet.setType(visibleSet.getType());
                        advPositionCustomerSet.setCustomerSetId(memberSet.getMemberSetId());
                        this.advPositionCustomerSetMapper.insert(advPositionCustomerSet);
                    });
                }
            });
        }
    }
}

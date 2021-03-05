package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.entity.ActivityGift;
import com.zsyc.zt.b2b.entity.ActivityGoods;
import com.zsyc.zt.b2b.entity.Goods;
import com.zsyc.zt.inca.entity.BmsForbidSales;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "GoodsInfoVo对象", description = "货品列表筛选参数")
public class GoodsInfoVo extends Goods implements Serializable {
    @ApiModelProperty("是否销售，1联系客服")
    private Integer b2bNotSaleFlag;

    @ApiModelProperty("条形码")
    private String barcode;

    @ApiModelProperty("通用名")
    private String goodsname;

    @ApiModelProperty("起始有效期")
    private String startTime;

    @ApiModelProperty("结束有效期")
    private String endTime;

    @ApiModelProperty("是否有图片")
    private Boolean isImg;

    @ApiModelProperty("是否有最小销售数量")
    private Boolean isMinimumSales;

    @ApiModelProperty("商品是否有上架")
    private Integer isRacking;

    @ApiModelProperty("赠品是否有上架")
    private Integer isIntegralGoods;

    @ApiModelProperty("是否积分商品--赠品")
    private Boolean isAccFlag;

    @ApiModelProperty(value = "图片数量")
    private Integer imgNum;

    //价格表
    List<PubGoodsPriceVo> pubGoodsPriceList;

    @ApiModelProperty("库存")
    private Integer stqty;

    /**
     * accflag == 5 表示是赠品 ，有效期字段可以为空  ，其他就必须有效期这个字段非空
     */
    @ApiModelProperty("是否赠品标志")
    private Long accflag;

    /**
     * 商品图片，分号隔开
     */
    @ApiModelProperty(value = "商品图片，分号隔开")
    @TableField("GOODS_IMG")
    private String goodsImg;

    @ApiModelProperty("客户id")
    private Long memberId;

    @ApiModelProperty("货品分类名全称")
    private String classname3;

    @ApiModelProperty("货品分类名")
    private String classname;

    @ApiModelProperty("商品定位， X为新品")
    private String goodsidGps;

    @ApiModelProperty("剂型")
    private String medicinetypename;

    @ApiModelProperty("商品名称")
    private String currencyname;

    @ApiModelProperty("基本单位")
    private String goodsunit;

    @ApiModelProperty("批发二价")
    private double price2;

    @ApiModelProperty("价格id")
    private Long priceid;

    @ApiModelProperty("下单价")
    private double price;

    @ApiModelProperty("连锁价")
    private double priceLs;

    @ApiModelProperty("批发9价")
    private double price9;

    @ApiModelProperty("商品指导价")
    private double priceTry;

    @ApiModelProperty("批准文号")
    private String approvedocno;

    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("产品id")
    private Long goodsid;

    @ApiModelProperty("规格")
    private String goodstype;

    @ApiModelProperty("商品可销库存")
    private Integer goodsqty;

    @ApiModelProperty("商品最近的一个效期")
    private String invaliddate;

    @ApiModelProperty("OTC标志")
    private String otcflag;

    @ApiModelProperty("运输条件")
    private String storagecondition;

    @ApiModelProperty("产地")
    private String prodarea;

    @ApiModelProperty("运输条件")
    private String transcondition;

    @ApiModelProperty("用法用量")
    private String dosage;

    @ApiModelProperty("用药时间")
    private String usegoodstime;

    @ApiModelProperty("诊断信息")
    private String diagnosticinfo;

    @ApiModelProperty("运输时限")
    private String ransporttime;

    @ApiModelProperty("疗程说明")
    private String treatment;

    @ApiModelProperty("备注")
    private String memo;

    @ApiModelProperty("商品搜索")
    private String goodspinyin;

    @ApiModelProperty(value = "是否上架")
    private Long isputaway;

    @ApiModelProperty(value = "分类号")
    private Long classnRow3;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime credate;

    @ApiModelProperty(value = "分类编号1")
    private Long classnRow1;

    @ApiModelProperty("分类1")
    private String classname1;

    @ApiModelProperty("分类2")
    private String classname2;

    @ApiModelProperty(value = "分类编号2")
    private Long classnRow2;

    @ApiModelProperty("排序")
    private String sort;

    @ApiModelProperty(value = "厂家id")
    private Long factoryid;

    @ApiModelProperty(value = "采购ID")
    private Long supplyerid;

    @ApiModelProperty(value = "采购员中名称")
    private String employeename;

    @ApiModelProperty(value = "B2B最小销售数量")
    private double zxB2bNumLimit;

    @ApiModelProperty(value = "经营范围id")
    private Long busiscope;

    @ApiModelProperty(value = "经营范围名称")
    private String busiscopeName;

    @ApiModelProperty(value = "收藏id")
    private Long isFavorites;

    @ApiModelProperty(value = "保管帐ID")
    private Integer storageid;

    @ApiModelProperty(value = "保管帐名称")
    private String storagename;

    @ApiModelProperty(value = "从那个模块进来")
    private String fromSearch;

    @ApiModelProperty(value = "近效期商品")
    private List<GoodsInfoVo> vGoodsInfoVo;

    @ApiModelProperty(value = "批号")
    private String lotno;

    @ApiModelProperty(value = "批号")
    private Long lotid;

    @ApiModelProperty(value = "生产日期")
    private String proddate;

    @ApiModelProperty(value = "近效期")
    private String validity;

    /**
     * 商品详情-分类
     */
    List<GoodsClassTypeVo> goodsClassTypeVoList;

    /**
     * [{img/video:xxx,code:xxx}]
     *
     * @return
     */
    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public List<GoodsImagesVo> getGoodsImgList() {
        if (StringUtils.isBlank(this.goodsImg)) {
            return Collections.emptyList();
        }
        return JSONArray.parseArray((this.goodsImg), GoodsImagesVo.class);
    }

    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public void setGoodsImgList(List imageList) {
        if (CollectionUtils.isEmpty(imageList)) {
            this.goodsImg = null;
        } else {
            this.goodsImg = JSON.toJSONString(imageList);
        }

    }

    /**
     * 活动相关字段
     */
    @ApiModelProperty(value = "活动id")
    private Long activityId;

    @ApiModelProperty(value = "活动策略ID")
    private Long asId;


    @ApiModelProperty(value = "策略满足数量")
    private Long conditionNum;


    @ApiModelProperty(value = "商品上限0为无上限，默认为0,活动商品表的")
    private Long topLimit;

    @ApiModelProperty(value = "策略满足金额")
    private Long conditionPrice;

    @ApiModelProperty(value = "商品活动价格")
    private Long goodsPrice;

    @ApiModelProperty(value = "是否启用")
    private String isUse;

    @ApiModelProperty(value = "促销内容")
    private String content;

    @ApiModelProperty(value = "活动简称")
    private String abbreviation;

    @ApiModelProperty(value = "客户集合类型")
    private String customSetType;

    @ApiModelProperty(value = "活动策略")
    private Long activityStrategy;

    @ApiModelProperty(value = "预热时间")
    private LocalDateTime warmTime;

    @ApiModelProperty(value = "有效开始时段")
    private String startEffectiveTime;

    @ApiModelProperty(value = "有效结束时段")
    private String endEffectiveTime;

    @ApiModelProperty(value = "活动开始时间")
    private LocalDateTime aStartTime;

    @ApiModelProperty(value = "活动结束时间")
    private LocalDateTime aEndTime;

    @ApiModelProperty(value = "同一客户次数限制")
    private Long times;

    @ApiModelProperty(value = "是否叠加使用,0否，1是，默认0")
    private Integer isSuperposition;

    @ApiModelProperty(value = "星期1-7")
    private Integer week;

    @ApiModelProperty(value = "限制策略，0全场一次，1每天，2周，3月，4年")
    private String timesStrategy;

    @ApiModelProperty(value = "策略名字")
    private String strategyName;

    @ApiModelProperty(value = "商品策略，10-任选商品数量，20-任选商品策略数量，40-任选商品金额，20-任选商品策略金额")
    private String goodsStrategy;

    @ApiModelProperty(value = "满足数量")
    private Integer meetQuantity;

    @ApiModelProperty(value = "商品上限0为无上限，默认为0，策略表的")
    private Integer asTopLimit;


    @ApiModelProperty(value = "满足金额")
    private Long amountSatisfied;


    @ApiModelProperty(value = "赠送数量")
    private Integer giftNum;

    @ApiModelProperty(value = "减少金额")
    private Long reducedAmount;

    @ApiModelProperty(value = "折扣")
    private Long discount;

    @ApiModelProperty(value = "返现金")
    private Long cash;

    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    @ApiModelProperty(value = "赠品策略")
    private String giftStrategy;

    @ApiModelProperty(value = "赠品信息")
    List<ActivityGiftVo> activityGiftVoList;


    @ApiModelProperty(value = "专区id")
    private Long webPageId;

    @ApiModelProperty(value = "二级分类名称")
    private String accessName;

    @ApiModelProperty(value = "一级分类名称")
    private String stairClassify;

    @ApiModelProperty(value = "楼层id")
    private Long floorId;

    @ApiModelProperty(value = "广告里商品id搜索")
    private String AdvGoodsId;

    @ApiModelProperty(value = "标签")
    private String title;

    @ApiModelProperty(value = "活动")
    List<ActivityVo> activityVoList;

    @ApiModelProperty(value = "策略数据")
    private List<ActivityGoods> activityGoodsList;

    @ApiModelProperty(value = "集合id")
    private Long setId;

    @ApiModelProperty(value = "是否限销禁销")
    private String forbidden;

    @ApiModelProperty(value = "奖品数量")
    private Integer prizeNum;
}

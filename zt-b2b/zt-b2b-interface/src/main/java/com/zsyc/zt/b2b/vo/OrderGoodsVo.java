package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.entity.ActivityGift;
import com.zsyc.zt.b2b.entity.ActivityGoods;
import com.zsyc.zt.b2b.entity.OrderGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by tang on 2020/07/30.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "OrderGoodsVo对象", description = "订单商品信息")
public class OrderGoodsVo extends OrderGoods {
    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @TableField("GOODS_IMAGE")
    private String goodsImage;

    @ApiModelProperty("批准文号")
    private String approvedocno;

    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("货品分类名")
    private String classname;

    @ApiModelProperty("商品定位， X为新品")
    private String goodsidGps;

    @ApiModelProperty("剂型")
    private String medicinetypename;

    @ApiModelProperty("商品名称")
    private String currencyname;

    @ApiModelProperty(value = "数量变化的商品名称")
    private String updateGoodsName;

    @ApiModelProperty(value = "数量变化的商品数量")
    private double updateGoodsNum;

    @ApiModelProperty("基本单位")
    private String goodsunit;

    @ApiModelProperty("批发二价")
    private double price2;

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

    @ApiModelProperty("发货单类型")
    private String fromtype;

    @ApiModelProperty("客户id")
    private Long companyid;

    @ApiModelProperty("客户名称")
    private String companyname;

    @ApiModelProperty("商品id")
    private Long goodsid;

    @ApiModelProperty("数量")
    private double dtlgoodsqty;

    @ApiModelProperty("单价")
    private double unitprice;

    @ApiModelProperty("小计")
    private double total;

    @ApiModelProperty("批号")
    private String lotno;

    @ApiModelProperty("生产日期")
    private String proddate;

    @ApiModelProperty("批号id")
    private Long lotid;

    @ApiModelProperty("批次id")
    private Long batchid;

    @ApiModelProperty("订单id")
    private String b2bOrderId;

    @ApiModelProperty("订单号")
    private String  b2bOrderNo;

    @ApiModelProperty("单据类型ID")
    private Long goodsSource;

    @ApiModelProperty("货品细单id")
    private Long goodsdtlid;

    @ApiModelProperty("--等级 1=统计，2=选")
    private Integer zxLevel;

    @ApiModelProperty("销售总单ID")
    private Long SrcErpOrderId;

    @ApiModelProperty("销售细单ID")
    private Long SrcErpOrderDtlId;

    @ApiModelProperty("保管帐名称")
    private String storagename;

    @ApiModelProperty("优惠单价")
    private Long b2bPriceDiscount;

    @ApiModelProperty("优惠金额")
    private Long b2bAmountTotal;

    @ApiModelProperty("订单商品id")
    private Long b2bOrderDtlId;

    @ApiModelProperty("药检图片")
    private String qualityImg;

    @ApiModelProperty("药检名称")
    private String filename;

    @ApiModelProperty("ERP 的出库单ID")
    private String salesid;

    @ApiModelProperty("商品规格")
    private String goodsType;

    /**
     * 保管帐id
     */
    @ApiModelProperty(value = "保管帐id")
    private Integer storageId;

    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public List<GoodsImagesVo> getGoodsImgList() {
        if (StringUtils.isBlank(this.goodsImage)) {
            return Collections.emptyList();
        }
        return JSONArray.parseArray((this.goodsImage), GoodsImagesVo.class);
    }

    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public void setGoodsImgList(List imageList) {
        if (CollectionUtils.isEmpty(imageList)) {
            this.goodsImage = null;
        } else {
            this.goodsImage = JSON.toJSONString(imageList);
        }

    }

    @ApiModelProperty(value = "活动策略ID")
    private Long asId;

    @ApiModelProperty(value = "赠品信息")
    List<OrderGoods> giftList;

    @ApiModelProperty(value = "活动")
    List<ActivityVo> activityVoList;

    @ApiModelProperty("库存")
    private Integer stqty;

    @ApiModelProperty("价格id")
    private Long priceid;

    @ApiModelProperty("下单价")
    private Long price;

    @ApiModelProperty("是否赠品标志")
    private Long accflag;

    @ApiModelProperty("活动内容")
    private String content;

    @ApiModelProperty(value = "策略数据")
    private List<ActivityGoods> activityGoodsList;

    @ApiModelProperty(value = "全场折扣信息")
    private ActivityVo  activityVo;

    //价格表
    List<PubGoodsPriceVo> pubGoodsPriceList;

    @ApiModelProperty("购物车计算满减的金额")
    private Long reducePrice;

    @ApiModelProperty("购物车计算满减的金额")
    private Long acId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("客户名称")
    private String userName;

    @ApiModelProperty("erp客户id")
    private Long erpUserId;
}

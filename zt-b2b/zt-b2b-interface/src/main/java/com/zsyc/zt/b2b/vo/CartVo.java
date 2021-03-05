package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.zt.b2b.entity.ActivityGift;
import com.zsyc.zt.b2b.entity.ActivityGoods;
import com.zsyc.zt.b2b.entity.Cart;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by tang on 2020/07/29.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "CartVo对象", description = "购物车信息")
public class CartVo extends Cart {
    @ApiModelProperty("是否销售，1联系客服")
    private Integer b2bNotSaleFlag;

    /**
     * 厂家简称
     */
    @ApiModelProperty(value = "厂家简称")
    private String factoryShort;

    @ApiModelProperty("库存")
    private Integer stqty;

    @ApiModelProperty("通用名")
    private String currencyname;

    @ApiModelProperty("规格")
    private String goodstype;

    @ApiModelProperty("基本单位")
    private String goodsunit;

    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("批准文号")
    private String approvedocno;

    @ApiModelProperty("剂型")
    private String medicinetypename;

    @ApiModelProperty("条形码")
    private String barcode;

    @ApiModelProperty(value = "B2B最小销售数量")
    private double zxB2bNumLimit;

    @ApiModelProperty(value = "收藏id")
    private Long isFavorites;

    @ApiModelProperty(value = "策略数据")
    private List<ActivityGoods> activityGoodsList;


    //价格表
    List<PubGoodsPriceVo> pubGoodsPriceList;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    private String goodsImage;

    /**
     * [{img:xxx},{video:xxx}]
     *
     * @return
     */
    @ApiModelProperty(value = "[{img:xxx},{video:xxx}]")
    public List getGoodsImgList() {
        if (StringUtils.isBlank(this.goodsImage)) {

            return Collections.emptyList();
        }
        return JSON.parseArray(this.goodsImage);
    }

    @ApiModelProperty(value = "活动")
    List<ActivityVo> activityVoList;

    @ApiModelProperty(value = "全场折扣信息")
    private ActivityVo activityVo;

    @ApiModelProperty("商品定位， X为新品")
    private String goodsidGps;

    @ApiModelProperty("批发二价")
    private double price2;

    @ApiModelProperty("优惠价")
    private double goodsPayPrice;

    @ApiModelProperty("限购加")
    private double limGoodsPayPrice;

    /**
     * 小计
     */
    @ApiModelProperty(value = "小计")
    private double amountNum;

    /**
     * 优惠后小计
     */
    @ApiModelProperty(value = "优惠后小计")
    private double amountPay;

    /**
     * 优惠后小计-用来叠加减
     */
    @ApiModelProperty(value = "优惠后小计")
    private double amountPay1;

    /**
     * 数量小计-用来叠加减
     */
    @ApiModelProperty(value = "数量小计")
    private double numPay;

    /**
     * 数量小计
     */
    @ApiModelProperty(value = "数量小计")
    private double numTotal;

    @ApiModelProperty("秒杀/折扣")
    private String killOrDiscount;

    @ApiModelProperty("购物车计算满减的金额")
    private double reducePrice;

    @ApiModelProperty("赠送数量")
    private Long giftNum;

    @ApiModelProperty("赠品")
    List<ActivityGiftVo> activityGiftList;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("赠品临时表id")
    private Long cartGiftTmpId;

    @ApiModelProperty("赠送策略")
    private Long giftStrategy;

    @ApiModelProperty("赠送商品个数")
    private Integer giftCount;

    /**
     * 可选数量
     */
    @ApiModelProperty(value = "可选数量")
    private double num;

    /**
     * 积分商品上下架：0为下架，1为上架
     */
    @ApiModelProperty(value = "积分商品上下架：0为下架，1为上架")
    private Long integralGoods;

    /**
     * 可兑换积分
     */
    @ApiModelProperty(value = "可兑换积分")
    private Long convertibleIntegral;

    /**
     * ERP商品表类型=5为赠品
     */
    @ApiModelProperty(value = "ERP商品表类型=5为赠品")
    private Integer erpAccFlag;

    @ApiModelProperty("积分")
    private double scoreTotal;

    @ApiModelProperty("赠品经营范围")
    private String giftRemark;

    @ApiModelProperty("首次下单减")
    private double orderPrice;

    @ApiModelProperty(value = "优惠金额")
    private double rptAmount;

    @ApiModelProperty(value = "限购活动id")
    @TableField("PURCHASE_ID")
    private Long purchaseId;

    @ApiModelProperty(value = "限购数量备注")
    private String limTop;

    @ApiModelProperty(value = "限购数量")
    private double topLimit;

    @ApiModelProperty(value = "抽奖次数")
    @TableField("LOT_NUM")
    private Integer lotNum;
}

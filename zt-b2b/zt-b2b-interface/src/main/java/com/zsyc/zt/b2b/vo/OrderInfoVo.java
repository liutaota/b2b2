package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.entity.OrderGoods;
import com.zsyc.zt.b2b.entity.OrderInfo;
import com.zsyc.zt.inca.entity.PubGoodsPrice;
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
@ApiModel(value = "OrderInfoVo对象", description = "订单信息")
public class OrderInfoVo extends OrderInfo {

    @ApiModelProperty(value = "异常确定人")
    private String uxName;

    @ApiModelProperty(value = "异常确定时间")
    private LocalDateTime uxTime;

    @ApiModelProperty(value = "异常处理备注")
    private String userRemark;

    @ApiModelProperty(value = "退款id")
    private Long recDocId;

    @ApiModelProperty(value = "确定人")
    private String confirmName;

    @ApiModelProperty(value = "确定备注")
    private String confirmRemark;

    /**
     * 确定时间
     */
    @ApiModelProperty(value = "确定时间")
    private LocalDateTime confirmDate;

    /**
     * 确定人
     */
    @ApiModelProperty(value = "确定人")
    private Long confirmUserId;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip")
    private String ip;

    /**
     * openid
     */
    @ApiModelProperty(value = "openid")
    private String openid;

    /**
     * 订单商品
     */
    List<OrderGoodsVo> orderGoodsList;

    @ApiModelProperty("短减/整单不出退款金额")
    private Long returnAmount;

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

    /**
     * accflag == 5 表示是赠品 ，有效期字段可以为空  ，其他就必须有效期这个字段非空
     */
    @ApiModelProperty("是否赠品标志")
    private Long accflag;

    /**
     * 客户
     */
    @ApiModelProperty(value = "客户")
    private String memberName;

    /**
     * 联系手机
     */
    @ApiModelProperty(value = "联系手机")
    private String memberPhone;


    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String employeename;

    /**
     * 发货单
     */
    List<OrderGoodsVo> orderGoodsDeliveryList;

    /**
     * 异常时间
     */
    @ApiModelProperty(value = "异常时间")
    private LocalDateTime expTime;

    /**
     * 异常备注
     */
    @ApiModelProperty(value = "异常备注")
    private String logMsg;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private LocalDateTime doneTime;

    /**
     * 异常状态
     */
    @ApiModelProperty(value = "异常状态")
    private String orderStatus;

    /**
     * 下单人手机号
     */
    @ApiModelProperty(value = "下单人手机号")
    private String telephone;

    List<PubGoodsPrice> pubGoodsPriceList;

    @ApiModelProperty("商品id")
    private Long goodsid;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "规格")
    private String goodstype;

    @ApiModelProperty("基本单位")
    private String goodsunit;

    @ApiModelProperty("数量")
    private String   goodsNum;

    @ApiModelProperty("单品销售次数")
    private String singGoodsNum;

    @ApiModelProperty("销售次数排行")
    private String salesSort;
    @ApiModelProperty("单品销售数量")
    private String goodsqty;

    @ApiModelProperty("销售数量排行")
    private String salesNumSort;

    @ApiModelProperty("下单价")
    private double price;
    @ApiModelProperty("下单价")
    private double amountPay;

    @ApiModelProperty("保管账id")
    private Long storageid;

    @ApiModelProperty("最小销售数量")
    private double zxB2bNumLimit;

    @ApiModelProperty("厂家")
    private String factoryname;


    @ApiModelProperty(value = "商品图片")
    private String goodsImage;

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

    @ApiModelProperty(value = "erp订单id")
    private String erpOrder;

    @ApiModelProperty(value = "erp订单号")
    private String erpOrderNo;

    @ApiModelProperty(value = "erp账号名称")
    private String employeeName;

    @ApiModelProperty(value = "工号")
    private String employeeNo;

    @ApiModelProperty(value = "收款人")
    private String userName;

    @ApiModelProperty("取消订单")
    private Integer cancelOrder;

    @ApiModelProperty("待拦截订单")
    private Integer interceptOrder;

    @ApiModelProperty("异常订单")
    private Integer expOrder;

    @ApiModelProperty("退款退货订单")
    private Integer refundOrder;

    @ApiModelProperty("待收货订单")
    private Integer toDeliveryOrder;

    @ApiModelProperty("线路客户订单")
    List<OrderInfoVo> orderInfoVoList;

    @ApiModelProperty("erp订单")
    List<ErpOrderInfo> erpOrderInfoList;

    @ApiModelProperty("待收货订单总额")
    private double goodsAmounts;


    @ApiModelProperty("订单来源  1是web,2是小程序")
    private String erpOrderFrom;

    @ApiModelProperty("客户ID")
    private Long customid;

    @ApiModelProperty("客户名称")
    private String customname;

    @ApiModelProperty("客户手机号")
    private String zxPhone;

    @ApiModelProperty("支付方式")
    private String settletype;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("路线")
    private String tranposname;

    @ApiModelProperty("线序")
    private String transortno;

    @ApiModelProperty("待收货订单总额")
    private double payTotaline;

    @ApiModelProperty("剩余待收货订单总额")
    private double erpTotal;

    @ApiModelProperty("路线")
    private String[]  translinenames;

    @ApiModelProperty("优惠券名称")
    private String couponName;

    @ApiModelProperty("优惠券金额")
    private String couponAmount;

    @ApiModelProperty("优惠券限额")
    private String couponLimAmount;

    @ApiModelProperty("产地")
    private String prodarea;

}

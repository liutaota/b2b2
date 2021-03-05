package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.entity.Coupon;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "CouponVo对象", description = "优惠券信息")
public class CouponVo extends Coupon {
    @ApiModelProperty(value = "客户集合")
    private List<CustomerSet> customerSetList;

    @ApiModelProperty(value = "商品集合")
    private List<GoodsSet> goodsSetList;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "优惠券id")
    private Long couponId;

    @ApiModelProperty(value = "赠送数量")
    private Integer giveNum;

    @ApiModelProperty(value = "客户id")
    private Long memberId;

    @ApiModelProperty(value = "客户集合id")
    private Long memberSetId;

    @ApiModelProperty(value = "客户id列表")
    private List<Long> customerIdList;

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "CustomerSet对象", description = "优惠券客户集合信息")
    public static class CustomerSet implements Serializable {
        @ApiModelProperty(value = "客户集合id")
        private Long customerSetId;

        @ApiModelProperty(value = "客户集合名称")
        private String customerSetName;

        @ApiModelProperty(value = "集合状态")
        private Long status;
    }

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "Customer对象", description = "优惠券客户信息")
    public static class Customer implements Serializable {
        @ApiModelProperty(value = "客户id")
        private Long customerId;
        @ApiModelProperty(value = "客户名称")
        private String customerName;
    }

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "GoodsSet对象", description = "优惠券商品集合信息")
    public static class GoodsSet implements Serializable {
        @ApiModelProperty(value = "商品集合id")
        private Long goodsSetId;

        @ApiModelProperty(value = "商品集合名称")
        private String goodsSetName;

        @ApiModelProperty(value = "集合状态")
        private Long status;
    }

    @ApiModelProperty(value = "商品id")
    private List<Long> erpGoodsList;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "优惠券折扣之后的优惠金额")
    private double couponDisAccount;

    @ApiModelProperty(value = "还差多少金额满足")
    private double differenceAmount;
}

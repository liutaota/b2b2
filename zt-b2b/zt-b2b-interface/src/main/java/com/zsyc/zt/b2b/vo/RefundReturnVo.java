package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.entity.RefundDetail;
import com.zsyc.zt.b2b.entity.RefundReturn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RefundReturnVo对象", description = "退货订单信息")
public class RefundReturnVo extends RefundReturn {

    @ApiModelProperty("客户手机")
    private String memberPhone;

    @ApiModelProperty("客户名称")
    private String userName;

    @ApiModelProperty("客户ID")
    private Long memberId;

    @ApiModelProperty(value = "原单号")
    private String orderNo;

    @ApiModelProperty(value = "退单号")
    private String applyNo;

    /**
     * 退货订单详情
     */
    List<RefundDetailVo> refundDetailList;

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
     * 关键字搜索
     */
    @ApiModelProperty(value = "关键字搜索")
    private String keyword;

    @ApiModelProperty(value = "确认收货人")
    private String receiveName;

    /**
     * 地址详情
     */
    @ApiModelProperty(value = "地址详情")
    private String addressDetail;

    /**
     * 线序
     */
    @ApiModelProperty(value = "线序")
    private Long zxPhOrder;

    /**
     * 运输路线
     */
    @ApiModelProperty(value = "运输路线")
    private String translinename;

    /**
     * erp客户id
     */
    @ApiModelProperty(value = "erp客户id")
    private Long erpCustomerId;

    @ApiModelProperty(value = "送货地址id")
    private Long targetPosId;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    private String payMethod;

    @ApiModelProperty(value = "1WEB2mobile")
    private Integer orderFrom;

    @ApiModelProperty(value = "客户手机号")
    private String telephone;

    @ApiModelProperty(value = "细单数量")
    private Long goodsNum;
}

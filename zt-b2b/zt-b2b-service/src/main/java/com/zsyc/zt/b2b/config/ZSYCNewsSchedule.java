package com.zsyc.zt.b2b.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.zsyc.zt.b2b.entity.B2bErpSyncConfig;
import com.zsyc.zt.b2b.mapper.B2bErpSyncConfigMapper;
import com.zsyc.zt.b2b.mapper.ErpB2bOrderRecDocMapper;
import com.zsyc.zt.b2b.service.*;
import com.zsyc.zt.b2b.vo.ErpB2bPayOrderFlowNoSyncVo;
import com.zsyc.zt.inca.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by lcs on 2019-07-01.
 */
@Configuration
@Slf4j
public class ZSYCNewsSchedule {
	@Autowired
	private OrderInfoService orderInfoService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private RefundReturnService refundReturnService;

	@Autowired
	private CouponReceiveService couponReceiveService;

	@Autowired
	private StateMentService stateMentService;
	@Autowired
	private LicenceService licenceService;
	@Autowired
	private DayBillService dayBillService;

	/**
	 * 回写支付单号，支付流水号到1070
	 */
	@Autowired
	private OrderService orderService;

	@Autowired
	ErpB2bOrderRecDocMapper erpB2bOrderRecDocMapper;

	@Autowired
	B2bErpSyncConfigMapper b2bErpSyncConfigMapper;

	@XxlJob("demoJobHandler")
	public ReturnT<String> execute(String param) {
		XxlJobLogger.log("hello world. {}", param);
		log.info("hello world. {}", param);
		return ReturnT.SUCCESS;
	}

    /**
     *
     * 5分钟下发erp
     */
	@XxlJob("minutesToErpOrder")
	public ReturnT<String> minutesToErpOrder(String param){
        log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "minutesToErpOrder");
        this.orderInfoService.minutesToErpOrder();
		return ReturnT.SUCCESS;
    }

	/**
	 *
	 * test下发erp
	 */
	@XxlJob("testToErpOrder")
	public ReturnT<String> testToErpOrder(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "testToErpOrder");
		this.orderInfoService.reErpOrder(Long.valueOf(param));
		return ReturnT.SUCCESS;
	}

	/**
	 * 判断库存，处理到货通知
	 */
	@XxlJob("dealWithArrivalNotice")
	public ReturnT<String> dealWithArrivalNotice(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "dealWithArrivalNotice");
		this.goodsService.dealWithArrivalNotice();
		return ReturnT.SUCCESS;
	}

	/**
	 * 一分钟处理一个申请退货请求
	 */
	@XxlJob("dealWithApplyRefundOrder")
	public ReturnT<String> dealWithApplyRefundOrder(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "dealWithApplyRefundOrder");
		this.refundReturnService.dealWithApplyRefundOrder();
		return ReturnT.SUCCESS;
	}

	/**
	 * 订单状态变更
	 */
	@XxlJob("updateOrderState")
	public ReturnT<String> updateOrderState(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "updateOrderState");
		this.orderInfoService.updateOrderState();
		return ReturnT.SUCCESS;
	}


	/**
	 * 同步erp药检报告到oss
	 */
	@XxlJob("syncGoodsQualityImage")
	public ReturnT<String> syncGoodsQualityImage(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "syncGoodsQualityImage");
		this.goodsService.syncGoodsQualityImage(LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(param)));
		return ReturnT.SUCCESS;
	}

	/**
	 * erp确认收货
	 */
	@XxlJob("sureOrderToDeliveryJob")
	public ReturnT<String>  sureOrderToDeliveryJob(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "sureOrderToDeliveryJob");
		this.orderInfoService.sureOrderToDeliveryJob();
		return ReturnT.SUCCESS;
	}


	/**
	 * 修改优惠券过期状态
	 */
	@XxlJob("updateCouponReceiveStatus")
	public ReturnT<String>  updateCouponReceiveStatus(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "updateCouponReceiveStatus");
		this.couponReceiveService.updateCouponReceiveStatus();
		return ReturnT.SUCCESS;
	}


	/**
	 * 替换购物车过期活动
	 */
	@XxlJob("updateCartExpiredActivity")
	public ReturnT<String>  updateCartExpiredActivity(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "updateCartExpiredActivity");
		this.goodsService.updateCartExpiredActivity();
		return ReturnT.SUCCESS;
	}


	/**
	 * 7天自动确认收货
	 */
	@XxlJob("autoOrderToDelivery")
	public ReturnT<String>  autoOrderToDelivery(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "autoOrderToDelivery");
		this.orderInfoService.autoOrderToDelivery();
		return ReturnT.SUCCESS;
	}

	/**
	 * 半小时自动取消未支付订单
	 */
	@XxlJob("autoCancelOrder")
	public ReturnT<String>  autoCancelOrder(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "autoCancelOrder");
		this.orderInfoService.autoCancelOrder();
		return ReturnT.SUCCESS;
	}

	/**
	 * 每月一号生成账单
	 */
	@XxlJob("addStateMent")
	public ReturnT<String>  addStateMent(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "addStateMent");
		this.stateMentService.addStateMent();
		return ReturnT.SUCCESS;
	}

	/**
	 * 订单确认收货核销
	 */
	@XxlJob("autoFinanceOrder")
	public ReturnT<String>  autoFinanceOrder(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "autoFinanceOrder");
		this.orderInfoService.autoFinanceOrder();

		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "autoAppFinanceOrder");
		this.stateMentService.autoAppFinanceOrder();
		return ReturnT.SUCCESS;
	}

	/**
	 * 订单确认收货短减、整单不出自动退款
	 */
	@XxlJob("autoReturnOrder")
	public ReturnT<String>  autoReturnOrder(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "autoReturnOrder");
		this.orderInfoService.autoReturnOrder();
		return ReturnT.SUCCESS;
	}


	/**
	 * 更新所有客户证照使用状态
	 */
	@XxlJob("updateLicenceStatus")
	public ReturnT<String>  updateLicenceStatus(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "updateLicenceStatus");
		this.licenceService.updateLicenceStatus();
		return ReturnT.SUCCESS;
	}

	/**
	 * 生成每日报表
	 */
	@XxlJob("addDayBill")
	public ReturnT<String>  addDayBill(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "addDayBill");
		this.dayBillService.addDayBill();
		return ReturnT.SUCCESS;
	}

	/**
	 * 手动核销/app核销
	 */
	@XxlJob("handFinanceRecDoc")
	public ReturnT<String>  handFinanceRecDoc(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "handFinanceRecDoc");
		this.stateMentService.handFinanceRecDoc();

		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "handAppFinanceErpRecDoc");
		this.stateMentService.handAppFinanceErpRecDoc();
		return ReturnT.SUCCESS;
	}


	/**
	 * 金额异常
	 */
	@XxlJob("toDayExpAmountOrder")
	public ReturnT<String>  toDayExpAmountOrder(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "toDayExpAmountOrder");
		this.orderInfoService.toDayExpAmountOrder();
		return ReturnT.SUCCESS;
	}


	/**
	 * 回写 订单支付单号，订单支付流水号
	 */
	@XxlJob("writeBackPayOrderAndFlowNo")
	public ReturnT<String>  writeBackPayOrderAndFlowNo(String param){
		log.info(">>>>>>>>>>>>>>{}<<<<<<<<<<<<<<<<<", "toDayExpAmountOrder");
		LocalDateTime now = LocalDateTime.now();

		B2bErpSyncConfig b2bErpSyncConfig = b2bErpSyncConfigMapper.getByVarName("APP_REC_B2B_ERP_SYNC");

		LocalDateTime updateTime = DateUtil.parseLocalDateTime(b2bErpSyncConfig.getVarValue(), DatePattern.NORM_DATETIME_PATTERN);

		List<ErpB2bPayOrderFlowNoSyncVo> erpB2bPayOrderFlowNoSyncVoList =  erpB2bOrderRecDocMapper.selectByUpdateTime(updateTime);
		if(ObjectUtil.isNotEmpty(erpB2bPayOrderFlowNoSyncVoList) ){
			for (ErpB2bPayOrderFlowNoSyncVo erpB2bPayOrderFlowNoSyncVo : erpB2bPayOrderFlowNoSyncVoList) {
				if(ObjectUtil.isNotEmpty(erpB2bPayOrderFlowNoSyncVo.getOrderIds())){
					this.orderService.writeBackPayOrderAndFlowNo(erpB2bPayOrderFlowNoSyncVo.getErpUserId(),1,erpB2bPayOrderFlowNoSyncVo.getOrderIds(),erpB2bPayOrderFlowNoSyncVo.getPayFlowNo(),erpB2bPayOrderFlowNoSyncVo.getTmpNo());
				}
			}
		}
		b2bErpSyncConfig.setVarValue(DateUtil.format(now, DatePattern.NORM_DATETIME_PATTERN));
		b2bErpSyncConfigMapper.updateById(b2bErpSyncConfig);
		return ReturnT.SUCCESS;
	}
}

/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.zsyc.zt.fapiao.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import com.zsyc.framework.util.NetUtil;
import com.zsyc.zt.fapiao.service.KaiPiaoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.SocketException;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;

/**
 * @author lengleng
 * @date 2019-09-18
 */
@Slf4j
@Component
public class KaiPiaoJob {


/*	@Value("${scheduler.address}")
	private String schedulerAddress;*/

	@Reference
	KaiPiaoService kaiPiaoService;
	/**
	 * 开票信息回写调度
	 * @param s
	 * @return
	 */
	@XxlJob("kpxxWbJob")
	public ReturnT<String> kpxxWbJob(String s) throws SocketException {
		ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();


		/*if(NetUtil.isNotAddress(schedulerAddress)){
			XxlJobLogger.log("调度不允许执行--不是指定的机器--"+schedulerAddress);
			return ReturnT.FAIL;
		}*/

		XxlJobLogger.log("开票信息回写调度---调度----任务开始");

		kaiPiaoService.kpxxWb();

/*
		log.info("This is a demo job." + shardingVO);
		XxlJobLogger.log("This is a demo job." + shardingVO);
*/


		XxlJobLogger.log("开票信息回写调度---调度----任务结束");
		return SUCCESS;
	}

}
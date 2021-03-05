package com.zsyc.zt.order.vo;

import lombok.Builder;
import lombok.Data;

/**
 * Created by lcs on 2018/11/17.
 */
@Data
@Builder
public class ResponseJson {
	/**
	 * 处理码
	 */
	private String errorCode;
	/**
	 * 错误信息
	 */
	private String errorMessage;
	/**
	 * 业务内容
	 */
	private Object bizContent;
}

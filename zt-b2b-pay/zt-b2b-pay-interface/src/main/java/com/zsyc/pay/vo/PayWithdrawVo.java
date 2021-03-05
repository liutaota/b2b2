package com.zsyc.pay.vo;

import com.zsyc.pay.entity.PayWithdraw;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 提现
 * </p>
 *
 * @author MP
 * @since 2019-06-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayWithdrawVo extends PayWithdraw {

	private enum EMetaData {telephone,request,response,errorMessage,queryErrorMessage,resultRecord,hasCallBack}

	/**
	 * telephone
	 *
	 * @return
	 */
	public String getTelephone() {
		return super.getMetaData(EMetaData.telephone.name());
	}

	/**
	 * telephone
	 *
	 * @return
	 */
	public void setTelephone(String telephone) {
		super.setMetaData(EMetaData.telephone.name(), telephone);
	}


	/**
	 * errorMessage
	 *
	 * @return
	 */
	public String getErrorMessage() {
		return super.getMetaData(EMetaData.errorMessage.name());
	}

	/**
	 * errorMessage
	 *
	 * @return
	 */
	public void setErrorMessage(String errorMessage) {
		super.setMetaData(EMetaData.errorMessage.name(), errorMessage);
	}

	/**
	 * queryErrorMessage
	 *
	 * @return
	 */
	public String getQueryErrorMessage() {
		return super.getMetaData(EMetaData.queryErrorMessage.name());
	}

	/**
	 * queryErrorMessage
	 *
	 * @return
	 */
	public void setQueryErrorMessage(String errorMessage) {
		super.setMetaData(EMetaData.queryErrorMessage.name(), errorMessage);
	}


	/**
	 * request
	 *
	 * @return
	 */
	public String getRequest() {
		return super.getMetaData(EMetaData.request.name());
	}

	/**
	 * request
	 *
	 * @return
	 */
	public void setRequest(String request) {
		super.setMetaData(EMetaData.request.name(), request);
	}

	/**
	 * response
	 *
	 * @return
	 */
	public String getResponse() {
		return super.getMetaData(EMetaData.response.name());
	}

	/**
	 * request
	 *
	 * @return
	 */
	public void setResponse(String response) {
		super.setMetaData(EMetaData.response.name(), response);
	}

	/**
	 * add result record
	 * @param status
	 */
	public void addResultRecord(String status){
		ArrayList list = super.getMetaData(EMetaData.resultRecord.name(), ArrayList.class);
		list = list == null ? new ArrayList<>() : list;
		list.add(String.format("%TF %1$TT %s", new Date(), status));
		super.setMetaData(EMetaData.resultRecord.name(), list);
	}

	/**
	 * hasCallBack
	 *
	 * @return
	 */
	public boolean getHasCallBack() {
		Boolean res = super.getMetaData(EMetaData.hasCallBack.name(), Boolean.class);
		return res == null ? false : res;
	}

	/**
	 * hasCallBack
	 *
	 * @return
	 */
	public void setHasCallBack(Boolean request) {
		super.setMetaData(EMetaData.hasCallBack.name(), request);
	}


}

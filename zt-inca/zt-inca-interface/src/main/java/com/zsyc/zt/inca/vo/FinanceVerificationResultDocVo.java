package com.zsyc.zt.inca.vo;

import cn.hutool.core.util.ObjectUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author peiqy
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class FinanceVerificationResultDocVo extends ResultVo {

    private static final long serialVersionUID = 4906907455350015655L;
    private String orderNo;
    private Long customId;


    //核销失败
    public FinanceVerificationResultDocVo(String orderNo,Long customId,String errorMessage) {
        this.orderNo = orderNo;
        this.customId = customId;
        this.errorMessage = errorMessage;
        this.setErrorCode(1);
    }


    /**
     * 未完成的订单详情
     */
    private List<FinanceVerificationResultDtlVo> undoneDtlList;

    //核销成功
    public FinanceVerificationResultDocVo(String orderNo, Long customId) {
        this.orderNo = orderNo;
        this.customId = customId;
        this.setErrorCode(0);
    }

    /**
     * 校验输出
     *
     * @param result
     * @return
     */

    public static FinanceVerificationResultDocVo validReturn(FinanceVerificationResultDocVo result, String orderNo, Long customId) {
        if (ObjectUtil.isNull(result)) {
            return new FinanceVerificationResultDocVo(orderNo,customId);
        }
        return result;
    }


    //失败
    public static FinanceVerificationResultDocVo failErrorMessageAppend(FinanceVerificationResultDocVo result, String errorMessage,String orderNo,Long customId) {
        if (ObjectUtil.isNull(result)) {
            return new FinanceVerificationResultDocVo(orderNo, customId,errorMessage);
        }
        result.setErrorCode(result.getErrorCode()+1);
        result.setErrorMessage(result.getErrorMessage() + "\n" + errorMessage);
        return result;
    }





}

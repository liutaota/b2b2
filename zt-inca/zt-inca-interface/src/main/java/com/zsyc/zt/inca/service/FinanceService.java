package com.zsyc.zt.inca.service;

import com.zsyc.zt.inca.vo.FinanceVerificationDocVo;
import com.zsyc.zt.inca.vo.FinanceVerificationResultDocVo;

/**
 *
 * 财务相关的业务类
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/7 18:22
 */
public interface FinanceService {
    FinanceVerificationResultDocVo verificationV3(FinanceVerificationDocVo financeVerificationDocVo);

    FinanceVerificationResultDocVo verificationV2(FinanceVerificationDocVo financeVerificationDocVo);

    /**
     * 核销
     * @param financeVerificationDocVo
     */
    FinanceVerificationResultDocVo verification(FinanceVerificationDocVo financeVerificationDocVo);
}

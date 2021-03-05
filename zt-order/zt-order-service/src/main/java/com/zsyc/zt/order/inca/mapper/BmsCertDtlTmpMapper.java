package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsCertDtlTmp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface BmsCertDtlTmpMapper extends BaseMapper<BmsCertDtlTmp> {
    /**
     * 获取transId ：bms_cert_dtl_tmp_trans_seq.nextval
     * @return
     */
    Long getTransactionId();


}

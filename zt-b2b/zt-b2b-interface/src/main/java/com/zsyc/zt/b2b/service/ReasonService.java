package com.zsyc.zt.b2b.service;

import com.zsyc.zt.b2b.entity.Reason;

import java.util.List;

public interface ReasonService {
    /**
     * 退货原因列表
     * @return
     */
    List<Reason> getReasonList();

    /**
     * 新增退货原因
     * @param reason
     * @param userId
     */
    void addReason(Reason reason,Long userId);

    /**
     * 编辑退货原因
     * @param reason
     * @param userId
     */
    void updateReason(Reason reason,Long userId);

    /**
     * 删除退货原因
     * @param id
     * @param userId
     */
    void updateReasonIsDel(Long id,Long userId);
}

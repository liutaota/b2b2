package com.zsyc.zt.inca.vo;

import lombok.Data;

import java.util.List;

@Data
public class BmsSaByFaPiaoVo {

    /**
     * 取自 bms_sa_doc
     */
    private List<Long> erpOrderIdList;

    private String b2bOrderNo;

    private Long b2bOrderId;
}

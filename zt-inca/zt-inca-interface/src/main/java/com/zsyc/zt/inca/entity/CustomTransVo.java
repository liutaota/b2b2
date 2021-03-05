package com.zsyc.zt.inca.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CustomTransVo implements Serializable {
    /**
     * 客户ID
     */
    private Long customId;
    /**
     * 路单ID
     */
    private Long transDocId;
    /**
     * 销售单号！
     */
    private List<Long>  salesIdList;
}

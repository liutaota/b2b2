package com.zsyc.zt.inca.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FapiaoAddressVo implements Serializable {
    private String address;
    private List<Long> erpOrderIdList;
    private Long b2bOrderId;
}

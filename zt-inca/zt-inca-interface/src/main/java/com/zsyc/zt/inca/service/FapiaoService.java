package com.zsyc.zt.inca.service;

import com.zsyc.zt.inca.vo.FapiaoAddressVo;
import com.zsyc.zt.inca.vo.FapiaoOrderVo;

import java.util.List;

/**
 * 发票相关业务
 */
public interface FapiaoService {

    /**
     * 根据订单号，获取开票信息
     * @param b2bOrderId
     * @return
     */
    public FapiaoOrderVo getKpInfo(Long b2bOrderId);

    /**
     * 根据订单号，获取开票信息
     * @param b2bOrderId
     * @return
     */
    public List<FapiaoOrderVo> getKpInfo(List<Long> b2bOrderId);



    /**
     * 根据订单号，获取开票之后的地址链接
     * @param b2bOrderId
     * @return
     */
    public FapiaoAddressVo getKpAddressInfo(Long b2bOrderId);

    /**
     * 根据订单号，获取开票之后的地址链接
     * @param b2bOrderId
     * @return
     */
    public List<FapiaoAddressVo> getKpAddressInfo(List<Long> b2bOrderId);

}

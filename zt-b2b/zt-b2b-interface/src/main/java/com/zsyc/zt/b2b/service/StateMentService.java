package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.vo.PayOrderVo;
import com.zsyc.zt.b2b.entity.ErpB2bOrderRecDoc;
import com.zsyc.zt.b2b.entity.RecDoc;
import com.zsyc.zt.b2b.entity.Statement;
import com.zsyc.zt.b2b.entity.TmpOrderRecDoc;
import com.zsyc.zt.b2b.vo.*;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StateMentService {
    /**
     * 每月一号生成账单
     */
    void addStateMent();

    /**
     * 账单列表
     *
     * @param page
     * @param statementVo
     * @return
     */
    IPage<StatementVo> getStateMentList(Page page, StatementVo statementVo);

    /**
     * 收款单
     *
     * @param page
     * @param recDocVo
     * @return
     */
    IPage<RecDocVo> getRecDocList(Page page, RecDocVo recDocVo);

    /**
     * 核销
     */
    void updateRecDocStatus(Long[] ids, String remark, Long userId);

    /**
     * 手动核销
     */
    void handFinanceRecDoc();

    /**
     * 我的收款单
     *
     * @param page
     * @param recDocVo
     * @return
     */
    IPage<RecDocVo> getMyRecDocList(Page page, RecDocVo recDocVo);

    /**
     * 我的账单列表
     *
     * @param page
     * @param statementVo
     * @return
     */
    IPage<StatementVo> getMyStateMentList(Page page, StatementVo statementVo);

    /**
     * 还款
     *
     * @param id
     * @param ip
     * @param openid
     * @return
     */
    PayOrder payMyStatement(Long id, String ip, String openid, String orderFrom,String paymentType);

    /**
     * 账单明细
     *
     * @param statementVo
     * @return
     */
    StatementVo getMyRecDocAll(StatementVo statementVo);

    /**
     * 后台还款
     *
     * @param id
     * @param remark
     */
    void updateStatement(Long id, String remark);

    /**
     * 退款
     */
    void refundRecDocOrder(Long id, String refundRemark, Long userId);

    /**
     * 手动生成账单
     */
    Statement addStateMentByRecDoc(List<RecDoc> recDocList);

    /**
     * 根据客户id生成账单
     */
    Statement addStateMentByMemberId(Long memberId);

    /**
     * 生成现结账单
     * @param orderIds
     * @param userId
     * @return
     */
    PayOrder addTmpOrderRecDoc(Long[] orderIds,String ip,Long userId,String paymentType);

    /**
     * 生成现结账单--erp
     * @param erpOrderInfoList
     * @param userId
     * @return
     */
    PayOrder addTmpErpOrderRecDoc(List<ErpOrderInfo> erpOrderInfoList, String ip, Long userId);

    /**
     * 现结账单详情
     * @param tmpNo
     * @return
     */
    ErpB2bOrderRecDoc getTmpOrderRecDoc(String tmpNo);

    /**
     * 生成现结账单--erp
     * @param salesids
     * @param ip
     * @param userId
     * @param paymentType
     * @param cashAmount
     * @return
     */
    PayOrder addErpB2bOrderRecDoc(Long[] salesids,Long erpUserId,String ip,Long userId,String paymentType,double cashAmount,String tranposname,String transortno,String payType,String openid);

    /**
     * app收款单列表
     * @param page
     * @param erpB2bOrderRecDocVo
     * @return
     */
    IPage<ErpB2bOrderRecDocVo> getErpB2bOrderRecDocVoList(Page page,ErpB2bOrderRecDocVo erpB2bOrderRecDocVo);

    /**
     * app收款单核销
     * @param id
     * @param financeRemark
     * @param userId
     */
    void financeErpB2bOrderRecDocVo(Long id,String financeRemark, Long userId);

    /**
     * app收款单核销修改状态-单个
     * @param id
     * @param financeRemark
     * @param userId
     */
    void updateFinanceErpB2bOrderRecDocVo(Long id,String financeRemark, Long userId,Integer financeTrue);

    /**
     * app收款单核销修改状态-多个
     * @param ids
     * @param financeRemark
     * @param userId
     */
    void updateFinanceErpB2bOrderRecDocVos(Long[] ids,String financeRemark, Long userId,Integer financeTrue);

    /**
     * app-手动核销
     */
    void handAppFinanceErpRecDoc();

    /**
     * app退款
     * @param id
     * @param userId
     * @param refundRemark
     */
    void refundErpB2bOrderRecDoc(Long id,Long userId,String refundRemark);

    /**
     * 确认收款-单个--erp订单
     * @param id
     * @param userId
     * @param confirmRemark
     */
    void confirmErpB2bOrderRecDoc(Long id,Long userId,String confirmRemark,LocalDateTime confirmTime);

    /**
     * 确认收款-多个-erp订单
     * @param ids
     * @param userId
     * @param confirmRemark
     */
    void confirmErpB2bOrderRecDocList(Long[] ids,Long userId,String confirmRemark,LocalDateTime confirmTime);

    /**
     * 确认收款-多个-b2b订单
     * @param ids
     * @param userId
     * @param confirmRemark
     */
    void confirmB2bOrderRecDocList(Long[] ids,Long userId,String confirmRemark,LocalDateTime confirmTime);


    /**
     * 删除APP收款单
     * @param id
     * @param userId
     */
    void delErpB2bOrderRecDoc(Long id,Long userId,String delRemark);

    /**
     * 已结算erp订单
     * @return
     */
    IPage<TmpOrderRecDocVo> getTmpOrderRecDocList(Page page, TmpOrderRecDocVo tmpOrderRecDoc);

    /**
     * 已结算erp订单
     * @return
     */
    IPage<TmpOrderRecDocVo> getAdminTmpOrderRecDocList(Page page, TmpOrderRecDocVo tmpOrderRecDoc);

    /**
     * app自动核销
     */
     void autoAppFinanceOrder();

    /**
     * 修改退款状态
     * @param id
     * @param userId
     * @param refundRemark
     */
     void updateRecDocRefundState(Long id ,Long userId,String refundRemark,String refundState);
}

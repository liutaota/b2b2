package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.service.FinancialStatementService;
import com.zsyc.zt.b2b.service.StateMentService;
import com.zsyc.zt.b2b.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Api
@RestController
@RequestMapping("api/stateMent")
public class AdminStateMentController {

    @Reference
    private StateMentService stateMentService;
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private AdminService adminService;
    @Reference
    private FinancialStatementService financialStatementService;

    @ApiOperation("账单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping(value = "getStateMentList")
    public IPage<StatementVo> getStateMentList(Page page, StatementVo statementVo) {
        return this.stateMentService.getStateMentList(page, statementVo);
    }

    @ApiOperation("收款单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "id", value = "收款单id", dataType = "Long"),
            @ApiImplicitParam(name = "erpUserId", value = "客户id", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "recType", value = "结算方式", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping(value = "getRecDocList")
    public IPage<RecDocVo> getRecDocList(Page page, RecDocVo recDocVo) {
        return this.stateMentService.getRecDocList(page, recDocVo);
    }

    @ApiOperation("收款单核销")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收款单id", dataType = "Long"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "Long"),
    })
    @GetMapping(value = "updateRecDocStatus")
    public void updateRecDocStatus(Long[] ids, String remark, HttpServletRequest request) {
        this.stateMentService.updateRecDocStatus(ids, remark, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("收款单核销：ID-->" + ids).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&updateRecDocStatus"));

    }

    @ApiOperation("后台还款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收款单id", dataType = "Long"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "Long"),
    })
    @GetMapping(value = "updateStatement")
    public void updateStatement(Long id, String remark, HttpServletRequest request) {
        this.stateMentService.updateStatement(id, remark);
        this.adminService.addAdminLog(new AdminLog().setContent("收款单核销：ID-->" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&updateStatement"));

    }

    @ApiOperation("b2b退款单-退款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收款单id", dataType = "Long"),
            @ApiImplicitParam(name = "refundRemark", value = "refundRemark", dataType = "Long"),
    })
    @GetMapping(value = "refundRecDocOrder")
    public void refundRecDocOrder(Long id, String refundRemark, HttpServletRequest request) {
        this.stateMentService.refundRecDocOrder(id, refundRemark, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("b2b退款单-退款：ID-->" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&refundRecDocOrder"));
    }

    @ApiOperation("app退款-退款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "Long"),
            @ApiImplicitParam(name = "refundRemark", value = "refundRemark", dataType = "Long"),
    })
    @GetMapping(value = "refundErpB2bOrderRecDoc")
    public void refundErpB2bOrderRecDoc(Long id, String refundRemark, HttpServletRequest request) {
        this.stateMentService.refundErpB2bOrderRecDoc(id, this.accountHelper.getUserId(), refundRemark);
        this.adminService.addAdminLog(new AdminLog().setContent("app退款：ID-->" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&refundErpB2bOrderRecDoc"));
    }

    @ApiOperation("手动生成账单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收款单id", dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "memberId", dataType = "Long"),
    })
    @PostMapping(value = "addStateMentByRecDoc")
    public Statement addStateMentByRecDoc(@RequestBody List<RecDoc> recDocList, HttpServletRequest request) {

        this.adminService.addAdminLog(new AdminLog().setContent("手动生成账单").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&addStateMentByRecDoc"));
        return this.stateMentService.addStateMentByRecDoc(recDocList);
    }


    @ApiOperation("根据客户id生成账单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "memberId", dataType = "Long"),
    })
    @GetMapping(value = "addStateMentByMemberId")
    public Statement addStateMentByMemberId(Long memberId) {
        return this.stateMentService.addStateMentByMemberId(memberId);
    }

    @ApiOperation("生成现结账单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "orderIds", dataType = "Long"),
    })
    @GetMapping(value = "addTmpOrderRecDoc")
    public PayOrder addTmpOrderRecDoc(Long[] id, HttpServletRequest request, String paymentType) {
        this.adminService.addAdminLog(new AdminLog().setContent("生成现结账单").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&addTmpOrderRecDoc"));

        return this.stateMentService.addTmpOrderRecDoc(id, this.accountHelper.getIP(request), this.accountHelper.getUserId(), paymentType);
    }


    @ApiOperation("生成现结账单--erp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salesids", value = "orderIds", dataType = "String[]"),
            @ApiImplicitParam(name = "paymentType", value = "支付类型，默认A", dataType = "String"),
            @ApiImplicitParam(name = "erpUserId", value = "客户id", dataType = "Long"),
    })
    @PostMapping(value = "addTmpErpOrderRecDoc")
    public PayOrder addTmpErpOrderRecDoc(@RequestBody List<ErpOrderInfo> erpOrderInfoList, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("生成现结账单--erp").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&addTmpErpOrderRecDoc"));

        return this.stateMentService.addTmpErpOrderRecDoc(erpOrderInfoList, this.accountHelper.getIP(request), this.accountHelper.getUserId());
    }

    @ApiOperation("现结账单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tmpNo", value = "tmpNo", dataType = "Long"),
    })
    @GetMapping(value = "getTmpOrderRecDoc")
    public ErpB2bOrderRecDoc getTmpOrderRecDoc(String tmpNo) {
        return this.stateMentService.getTmpOrderRecDoc(tmpNo);
    }

    @ApiOperation("生成现结账单--erp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salesids", value = "salesids", dataType = "Long[]"),
            @ApiImplicitParam(name = "paymentType", value = "支付类型，默认A", dataType = "String"),
            @ApiImplicitParam(name = "erpUserId", value = "客户id", dataType = "Long"),
            @ApiImplicitParam(name = "cashAmount", value = "现金金额", dataType = "Long"),
    })
    @GetMapping(value = "addErpB2bOrderRecDoc")
    public PayOrder addErpB2bOrderRecDoc(Long[] salesids, Long erpUserId, HttpServletRequest request, String paymentType, double cashAmount, String tranposname, String transortno, String payType) {
        this.adminService.addAdminLog(new AdminLog().setContent("生成现结账单--erp").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&addErpB2bOrderRecDoc"));
        if (paymentType.equals("B")) {
            return this.stateMentService.addErpB2bOrderRecDoc(salesids, erpUserId, this.accountHelper.getIP(request), 0L, paymentType, cashAmount, tranposname, transortno, payType, this.accountHelper.getOriginOpenid());
        } else if (paymentType.equals("C")) {
            return this.stateMentService.addErpB2bOrderRecDoc(salesids, erpUserId, this.accountHelper.getIP(request), 0L, "A", cashAmount, tranposname, transortno, payType, null);
        } else if (paymentType.equals("6")) {
            return this.stateMentService.addErpB2bOrderRecDoc(salesids, erpUserId, this.accountHelper.getIP(request), 0L, paymentType, cashAmount, tranposname, transortno, payType, null);
        } else {
            return this.stateMentService.addErpB2bOrderRecDoc(salesids, erpUserId, this.accountHelper.getIP(request), this.accountHelper.getUserId(), paymentType, cashAmount, tranposname, transortno, payType, null);
        }
    }

    @ApiOperation("app收款单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpUserId", value = "客户id", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "confirm", value = "1/2是否确认", dataType = "String"),
    })
    @GetMapping(value = "getErpB2bOrderRecDocVoList")
    public IPage<ErpB2bOrderRecDocVo> getErpB2bOrderRecDocVoList(Page page, ErpB2bOrderRecDocVo erpB2bOrderRecDocVo) {
        return this.stateMentService.getErpB2bOrderRecDocVoList(page, erpB2bOrderRecDocVo);
    }

    @ApiOperation("app收款单核销")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "financeRemark", value = "核销备注", dataType = "String"),
    })
    @GetMapping(value = "financeErpB2bOrderRecDocVo")
    public void financeErpB2bOrderRecDocVo(Long id, String financeRemark, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("app收款单核销").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&financeErpB2bOrderRecDocVo"));
        this.stateMentService.financeErpB2bOrderRecDocVo(id, financeRemark, this.accountHelper.getUserId());
    }

    @ApiOperation("app收款单核销修改状态-单个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "financeRemark", value = "核销备注", dataType = "String"),
            @ApiImplicitParam(name = "financeTrue", value = "传1：已核销，0未核销", dataType = "Integer"),
    })
    @GetMapping(value = "updateFinanceErpB2bOrderRecDocVo")
    public void updateFinanceErpB2bOrderRecDocVo(Long id, String financeRemark, HttpServletRequest request, Integer financeTrue) {
        this.adminService.addAdminLog(new AdminLog().setContent("app收款单核销修改状态-单个").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&updateFinanceErpB2bOrderRecDocVo"));
        this.stateMentService.updateFinanceErpB2bOrderRecDocVo(id, financeRemark, this.accountHelper.getUserId(), financeTrue);
    }


    @ApiOperation("app收款单核销修改状态-多个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "financeRemark", value = "核销备注", dataType = "String"),
            @ApiImplicitParam(name = "financeTrue", value = "传1：已核销，0未核销", dataType = "Integer"),
    })
    @GetMapping(value = "updateFinanceErpB2bOrderRecDocVos")
    public void updateFinanceErpB2bOrderRecDocVos(Long[] ids, String financeRemark, HttpServletRequest request, Integer financeTrue) {
        this.adminService.addAdminLog(new AdminLog().setContent("app收款单核销修改状态-多个").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&updateFinanceErpB2bOrderRecDocVos"));
        this.stateMentService.updateFinanceErpB2bOrderRecDocVos(ids, financeRemark, this.accountHelper.getUserId(), financeTrue);

    }

    @ApiOperation("确认收款-单个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "confirmRemark", value = "确认备注", dataType = "String"),
    })
    @GetMapping(value = "confirmErpB2bOrderRecDoc")
    public void confirmErpB2bOrderRecDoc(Long id, HttpServletRequest request, String confirmRemark, LocalDateTime confirmTime) {
        this.adminService.addAdminLog(new AdminLog().setContent("确认收款").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&confirmErpB2bOrderRecDoc"));
        this.stateMentService.confirmErpB2bOrderRecDoc(id, this.accountHelper.getUserId(), confirmRemark,confirmTime);
    }

    @ApiOperation("确认收款-多个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "confirmRemark", value = "确认备注", dataType = "String"),
    })
    @GetMapping(value = "confirmErpB2bOrderRecDocList")
    public void confirmErpB2bOrderRecDocList(Long[] ids, String confirmRemark, HttpServletRequest request,LocalDateTime confirmTime) {
        this.adminService.addAdminLog(new AdminLog().setContent("确认收款").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&confirmErpB2bOrderRecDocList"));
        this.stateMentService.confirmErpB2bOrderRecDocList(ids, this.accountHelper.getUserId(), confirmRemark,confirmTime);
    }

    @ApiOperation("确认收款-多个-b2b订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "confirmRemark", value = "确认备注", dataType = "String"),
    })
    @GetMapping(value = "confirmB2bOrderRecDocList")
    public void confirmB2bOrderRecDocList(Long[] ids,String confirmRemark,LocalDateTime confirmDate, HttpServletRequest request){
        this.adminService.addAdminLog(new AdminLog().setContent("确认收款").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&confirmB2bOrderRecDocList"));
        this.stateMentService.confirmB2bOrderRecDocList(ids, this.accountHelper.getUserId(), confirmRemark,confirmDate);
    }

    @ApiOperation("删除APP收款单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "delRemark", value = "删除备注", dataType = "String"),
    })
    @GetMapping(value = "delErpB2bOrderRecDoc")
    public void delErpB2bOrderRecDoc(Long id, String delRemark, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("删除APP收款单").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminStateMentController&delErpB2bOrderRecDoc"));
        this.stateMentService.delErpB2bOrderRecDoc(id, this.accountHelper.getUserId(), delRemark);

    }

    @ApiOperation("修改退款状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "refundRemark", value = "备注", dataType = "String"),
    })
    @GetMapping(value = "updateRecDocRefundState")
    public void updateRecDocRefundState(Long id, String refundRemark,String refundState) {
        this.stateMentService.updateRecDocRefundState(id, this.accountHelper.getUserId(), refundRemark,refundState);
    }

    @ApiOperation("财务报表总计-明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpUserId", value = "客户id", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "confirm", value = "1/2是否确认", dataType = "String"),
    })
    @GetMapping(value = "getFinancialStatementTotalList")
    public IPage<OrderInfoVo> getFinancialStatementTotalList(Page page, OrderInfoVo orderInfoVo){
        return this.financialStatementService.getFinancialStatementTotalList(page, orderInfoVo);

    }


    @ApiOperation("财务报表底表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpUserId", value = "客户id", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "confirm", value = "1/2是否确认", dataType = "String"),
    })
    @GetMapping(value = "getFinancialStatementList")
    public IPage<OrderInfoVo> getFinancialStatementList(Page page, OrderInfoVo orderInfoVo){
        return this.financialStatementService.getFinancialStatementList(page, orderInfoVo);
    }

    @ApiOperation("已结算erp订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping(value = "getAdminTmpOrderRecDocList")
    public  IPage<TmpOrderRecDocVo> getTmpOrderRecDocList(Page page, TmpOrderRecDocVo tmpOrderRecDoc){
        return this.stateMentService.getAdminTmpOrderRecDocList(page,tmpOrderRecDoc);
    }
}

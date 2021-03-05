package com.zsyc.zt.b2b.api.controller.PCWeb;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.vo.PayOrderVo;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.AdminLog;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.service.StateMentService;
import com.zsyc.zt.b2b.vo.RecDocVo;
import com.zsyc.zt.b2b.vo.StatementVo;
import com.zsyc.zt.b2b.vo.TmpOrderRecDocVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api
@RestController
@RequestMapping("api/stateMent")
public class PCStateMentController {

    @Reference
    private StateMentService stateMentService;
    @Autowired
    private AccountHelper accountHelper;

    @ApiOperation("我的收款单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "id", value = "收款单id", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "recType", value = "结算方式", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping(value = "getMyRecDocList")
    public IPage<RecDocVo>  getMyRecDocList(Page page, RecDocVo recDocVo){
        recDocVo.setMemberId(this.accountHelper.getUserId());
        return this.stateMentService.getMyRecDocList(page,recDocVo);
    }

    @ApiOperation("我的账单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping(value = "getMyStateMentList")
    public IPage<StatementVo> getMyStateMentList(Page page, StatementVo statementVo){
        statementVo.setMemberId(this.accountHelper.getUserId());
        return this.stateMentService.getMyStateMentList(page,statementVo);
    }

    @ApiOperation("还款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "orderFrom", value = "来源", required = true, dataType = "String"),
    })
    @GetMapping(value = "payMyStatement")
    public PayOrder payMyStatement(Long id,String orderFrom, HttpServletRequest request,String paymentType){
        if (orderFrom.equals("6")) {
            return this.stateMentService.payMyStatement(id,this.accountHelper.getIP(request),null,orderFrom,paymentType);
        } else if (orderFrom.equals("5")) {
            return this.stateMentService.payMyStatement(id,this.accountHelper.getIP(request),this.accountHelper.getOriginOpenid(),orderFrom,paymentType);
        }
        return null;
    }

    @ApiOperation("账单明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping(value = "getMyRecDocAll")
    public StatementVo getMyRecDocAll(StatementVo statementVo){
        return this.stateMentService.getMyRecDocAll(statementVo);
    }

    @ApiOperation("已结算erp订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping(value = "getTmpOrderRecDocList")
    public  IPage<TmpOrderRecDocVo> getTmpOrderRecDocList(Page page, TmpOrderRecDocVo tmpOrderRecDoc){
        tmpOrderRecDoc.setErpUserId(this.accountHelper.getUserId());
        return this.stateMentService.getTmpOrderRecDocList(page,tmpOrderRecDoc);
    }

}

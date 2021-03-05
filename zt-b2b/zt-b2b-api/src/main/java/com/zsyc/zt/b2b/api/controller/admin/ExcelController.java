package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.AdminLog;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.service.ExcelService;
import com.zsyc.zt.b2b.vo.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api
@RestController
@RequestMapping("api/excel")
@Slf4j
public class ExcelController {

    @Reference
    private ExcelService excelService;
    @Reference
    private AdminService adminService;
    @Autowired
    private AccountHelper accountHelper;

    @ApiOperation("订单导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping(value = "getOrderInfoOrderGoodsExcel")
    public String getOrderInfoOrderGoodsExcel(OrderInfoVo orderInfoVo) {
        return this.excelService.getOrderInfoOrderGoodsExcel(orderInfoVo);
    }

    @ApiOperation("货品列表导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsid", value = "货品id", dataType = "Long"),
            @ApiImplicitParam(name = "barcode", value = "条形码", dataType = "String"),
            @ApiImplicitParam(name = "goodsname", value = "商品名", dataType = "String"),
            @ApiImplicitParam(name = "factoryname", value = "生产商", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "isImg", value = "无图片", dataType = "Boolean"),
            @ApiImplicitParam(name = "imgNum", value = "图片数量", dataType = "Integer"),
            @ApiImplicitParam(name = "classnRow3", value = "分类编号", dataType = "Long"),
    })
    @GetMapping(value = "getGoodsListExcel")
    public String getGoodsListExcel(GoodsInfoVo goodsInfoVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("货品列表导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getGoodsListExcel"));
        return this.excelService.getGoodsListExcel(goodsInfoVo);
    }

    @ApiOperation("缺货列表导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getArrivalNoticeListExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品编号", dataType = "Long"),
            @ApiImplicitParam(name = "anStatus", value = "状态", dataType = "String"),
            @ApiImplicitParam(name = "goodsPinYin", value = "商品编号/名称查询", dataType = "String")
    })
    public String getArrivalNoticeListExcel(ArrivalNoticeVo arrivalNoticeVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("缺货列表导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getArrivalNoticeListExcel"));
        return this.excelService.getArrivalNoticeListExcel(arrivalNoticeVo);
    }

    @ApiOperation("新品列表导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getNewProductExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "approvedocno", value = "批准文号", dataType = "String"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", dataType = "String"),
    })
    public String getNewProductExcel(NewProductVo newProductVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("新品列表导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getNewProductExcel"));
        return this.excelService.getNewProductExcel(newProductVo);
    }

    @ApiOperation("客户列表导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getCustomerExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zxPhone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public String getCustomerExcel(CustomerInfoVo customerInfoVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("客户列表导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getCustomerExcel"));
        return this.excelService.getCustomerExcel(customerInfoVo);
    }

    @ApiOperation("APP收款单列表导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getErpB2bOrderRecDocExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpUserId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public String getErpB2bOrderRecDocExcel(ErpB2bOrderRecDocVo erpB2bOrderRecDocVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("APP收款单列表导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getErpB2bOrderRecDocExcel"));
        return this.excelService.getErpB2bOrderRecDocExcel(erpB2bOrderRecDocVo);
    }


    @ApiOperation("收款单列表导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getRecDocListExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpUserId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public String getRecDocListExcel(RecDocVo recDocVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("收款单列表导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getRecDocList"));
        return this.excelService.getRecDocList(recDocVo);
    }

    @ApiOperation("退款单列表导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getRefundRecDocListExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpUserId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public String getRefundRecDocListExcel(RecDocVo recDocVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("退款单列表导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getRefundRecDocListExcel"));
        return this.excelService.getRefundRecDocList(recDocVo);
    }

    @ApiOperation("财务报表总计-明细导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getFinancialStatementTotalListExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpUserId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public String getFinancialStatementTotalList(OrderInfoVo orderInfoVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("财务报表总计-明细").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getFinancialStatementTotalList"));
        return this.excelService.getFinancialStatementTotalList(orderInfoVo);
    }

    @ApiOperation("财务报表底表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getFinancialStatementListExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpUserId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public String getFinancialStatementList(OrderInfoVo orderInfoVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("财务报表底表").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getFinancialStatementList"));
        return this.excelService.getFinancialStatementList(orderInfoVo);
    }


    @ApiOperation("订单列表导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getOrderInfoListExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpUserId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public String getOrderInfoListExcel(OrderInfoVo orderInfoVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("订单列表导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getOrderInfoListExcel"));
        return this.excelService.getOrderInfoListExcel(orderInfoVo);
    }

    @ApiOperation("送货列表导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getErpOrderInfoListExcel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpUserId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public String getErpOrderInfoListExcel(OrderInfoVo orderInfoVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("送货列表导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getErpOrderInfoListExcel"));
        return this.excelService.getErpOrderInfoListExcel(orderInfoVo);
    }

    @ApiOperation("支付流水导出")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getPayFlowNoOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpUserId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public String getPayFlowNoOrder(RecDocVo recDocVo, HttpServletRequest request){
        this.adminService.addAdminLog(new AdminLog().setContent("支付流水导出").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("excel&getPayFlowNoOrder"));
        return this.excelService.getPayFlowNoOrder(recDocVo);
    }
}

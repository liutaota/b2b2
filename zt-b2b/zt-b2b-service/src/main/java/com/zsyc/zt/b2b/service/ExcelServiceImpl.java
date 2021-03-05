package com.zsyc.zt.b2b.service;

import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.common.IncaUtils;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.fs.service.AliyunFileService;
import com.zsyc.zt.fs.service.B2BFileService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private ArrivalNoticeMapper arrivalNoticeMapper;
    @Autowired
    private NewProductMapper newProductMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private AliyunFileService aliyunFileService;
    @Autowired
    private B2BFileService fileService;
    @Autowired
    private ErpB2bOrderRecDocMapper erpB2bOrderRecDocMapper;
    @Autowired
    private RecDocMapper recDocMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private CouponMapper couponMapper;

    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String getOrderInfoOrderGoodsExcel(OrderInfoVo orderInfoVo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("订单数据表");
        createTitle(workbook, sheet);

        List<OrderInfoVo> orderInfoVoList = this.orderInfoMapper.getOrderInfoOrderGoodsExcel(orderInfoVo);
        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for (OrderInfoVo orderVo : orderInfoVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(DATE_TIME_FORMATTER.format(orderVo.getCreateTime()) + "");
            row.createCell(1).setCellValue("");
            row.createCell(2).setCellValue(orderVo.getOrderNo());
            /*row.createCell(3).setCellValue(orderVo.getCustomerName());
            row.createCell(4).setCellValue(orderVo.getGoodName());
            row.createCell(5).setCellValue(orderVo.getSpec());
            row.createCell(6).setCellValue(orderVo.getUnit());

            if (null != orderVo.getPrice()) {
                row.createCell(7).setCellValue((double) orderVo.getPrice() / 100 + "");
                row.createCell(9).setCellValue((double) orderVo.getPrice() * orderVo.getNum() / 100 + "");
            } else if (null != orderVo.getTaxPrice()) {
                row.createCell(7).setCellValue((double) orderVo.getTaxPrice() / 100 + "");
                row.createCell(9).setCellValue((double) orderVo.getTaxPrice() * orderVo.getNum() / 100 + "");
            } else {
                row.createCell(7).setCellValue("");
                row.createCell(9).setCellValue("");
            }

            row.createCell(8).setCellValue(orderVo.getNum());

            row.createCell(10).setCellValue(orderVo.getRealName());
            row.createCell(11).setCellValue(orderVo.getDepartmentName() + "");*/
            row.createCell(12).setCellValue(orderVo.getPayMethod());
            row.createCell(13).setCellValue(orderVo.getRemark() + "");

            HSSFCell cell = row.createCell(14);
            cell.setCellStyle(style);
            rowNum++;
        }

        return this.aliyunFileService.write(o -> {
            try {
                workbook.write(o);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException ", e);
            }

        });
    }


    //创建订单表头
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 18; i++) {
            sheet.setColumnWidth(i, 17 * 256);
        }

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("下单日期");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("送货单号");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("客户");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("品名");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("规格");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("单位");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("单价");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("数量");
        cell.setCellStyle(style);


        cell = row.createCell(9);
        cell.setCellValue("总金额");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("业务员");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("部门");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("收款方式");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("订单备注");
        cell.setCellStyle(style);
    }

    @Override
    public String getGoodsListExcel(GoodsInfoVo goodsInfoVo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("货品数据表");
        createGoodsTitle(workbook, sheet);

        List<GoodsInfoVo> goodsInfoVoList = this.goodsInfoMapper.getGoodsListExcel(goodsInfoVo);
        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for (GoodsInfoVo goodsVo : goodsInfoVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(goodsVo.getGoodsid());
            row.createCell(1).setCellValue(goodsVo.getGoodsname());
            row.createCell(2).setCellValue(goodsVo.getGoodsunit());
            row.createCell(3).setCellValue(goodsVo.getZxB2bNumLimit());
            row.createCell(4).setCellValue(goodsVo.getGoodstype());
            row.createCell(5).setCellValue(goodsVo.getPrice2() != 0 ? IncaUtils.toErpPriceDouble(goodsVo.getPrice2()) : 0.0);
            row.createCell(6).setCellValue(goodsVo.getIsputaway() != null && goodsVo.getIsputaway() == 1 ? "是" : "否");
            row.createCell(7).setCellValue(goodsVo.getGoodsqty() != null ? goodsVo.getGoodsqty() : 0);
            row.createCell(8).setCellValue(goodsVo.getBarcode() != null ? goodsVo.getBarcode() : "-");
            row.createCell(9).setCellValue(goodsVo.getImgNum() != null ? goodsVo.getImgNum() : 0);
            row.createCell(10).setCellValue(goodsVo.getFactoryname());
            row.createCell(11).setCellValue(goodsVo.getCurrencyname());
            row.createCell(12).setCellValue(goodsVo.getMedicinetypename());
            row.createCell(13).setCellValue(goodsVo.getStoragecondition() + "," + goodsVo.getTranscondition());
            row.createCell(14).setCellValue(goodsVo.getClassname());
            row.createCell(15).setCellValue(goodsVo.getApprovedocno());
            row.createCell(16).setCellValue(goodsVo.getInvaliddate());
            HSSFCell cell = row.createCell(17);
            cell.setCellStyle(style);
            rowNum++;
        }
        return this.fileService.exportGoodsList(Constant.EXCEL_PREFIX.GOODS + String.format("/%tF %s.xls", new Date(), "货品列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });
    }

    // 创建货品表头
    private void createGoodsTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 17; i++) {
            sheet.setColumnWidth(i, 17 * 256);
        }

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("货品ID");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("货品名");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("单位");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("最小销售数量");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("规格");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("采购二价");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("是否上架");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("库存");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("商品条码");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("商品图片数量");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("厂家");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("通用名");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("剂型");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("贮藏");
        cell.setCellStyle(style);

        cell = row.createCell(14);
        cell.setCellValue("作用分类");
        cell.setCellStyle(style);

        cell = row.createCell(15);
        cell.setCellValue("批准文号");
        cell.setCellStyle(style);

        cell = row.createCell(16);
        cell.setCellValue("有效期");
        cell.setCellStyle(style);
    }

    @Override
    public String getArrivalNoticeListExcel(ArrivalNoticeVo arrivalNoticeVo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("缺货登记表");
        createArrivalNoticeTitle(workbook, sheet);

        List<ArrivalNoticeVo> arrivalNoticeVoList = this.arrivalNoticeMapper.getArrivalNoticeListExcel(arrivalNoticeVo);
        arrivalNoticeVoList.forEach(arrivalNoticeVo1 -> {
            Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("ID", arrivalNoticeVo1.getMemberId()));
            arrivalNoticeVo1.setMemberName(member.getUserName());
        });
        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (ArrivalNoticeVo arrivalNoticeVo1 : arrivalNoticeVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(arrivalNoticeVo1.getGoodsName());
            row.createCell(1).setCellValue(arrivalNoticeVo1.getErpGoodsId());
            row.createCell(2).setCellValue(arrivalNoticeVo1.getGoodsNum());
            row.createCell(3).setCellValue(arrivalNoticeVo1.getMemberId());
            row.createCell(4).setCellValue(arrivalNoticeVo1.getMemberName());
            row.createCell(5).setCellValue(arrivalNoticeVo1.getAnMobile());
            if (arrivalNoticeVo1.getAnStatus().equals(ArrivalNotice.EAnStatus.UNTREATED.val())) {
                row.createCell(6).setCellValue("未处理");
            } else if (arrivalNoticeVo1.getAnStatus().equals(ArrivalNotice.EAnStatus.ADDED_SHOPPING_CART.val())) {
                row.createCell(6).setCellValue("已加入购物车");
            } else if (arrivalNoticeVo1.getAnStatus().equals(ArrivalNotice.EAnStatus.HAVE_DELETED.val())) {
                row.createCell(6).setCellValue("已删除");
            } else {
                row.createCell(6).setCellValue("已发通知");
            }
            row.createCell(7).setCellValue(arrivalNoticeVo1.getRemark() != null ? arrivalNoticeVo1.getRemark() : "-");
            row.createCell(8).setCellValue(arrivalNoticeVo1.getSellTime() != null ? arrivalNoticeVo1.getSellTime() : "-");
            row.createCell(9).setCellValue(arrivalNoticeVo1.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            HSSFCell cell = row.createCell(10);
            cell.setCellStyle(style);
            rowNum++;
        }
        return this.fileService.exportArrivalNoticeList(Constant.EXCEL_PREFIX.ARRIVAL_NOTICE + String.format("/%tF %s.xls", new Date(), "缺货登记列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });
    }

    // 创建缺货登记表头
    private void createArrivalNoticeTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 17 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("商品编号");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("商品数量");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("会员ID");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("会员名称");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("状态");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("截止时间");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("添加时间");
        cell.setCellStyle(style);
    }

    @Override
    public String getNewProductExcel(NewProductVo newProductVo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("新品登记表");
        createNewProductTitle(workbook, sheet);

        List<NewProductVo> newProductVoList = this.newProductMapper.getNewProductExcel(newProductVo);
        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (NewProductVo newProductVo1 : newProductVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(newProductVo1.getGoodsName());
            row.createCell(1).setCellValue(newProductVo1.getSpec());
            row.createCell(2).setCellValue(newProductVo1.getNum());
            row.createCell(3).setCellValue(newProductVo1.getMemberName());
            row.createCell(4).setCellValue(newProductVo1.getTelephone());
            row.createCell(5).setCellValue(newProductVo1.getApprovedocno());
            row.createCell(7).setCellValue(newProductVo1.getApprovedocno() != null ? newProductVo1.getApprovedocno() : "-");
            row.createCell(8).setCellValue(newProductVo1.getPrice());
            row.createCell(9).setCellValue(newProductVo1.getChannel() != null ? newProductVo1.getChannel() : "-");
            row.createCell(10).setCellValue(newProductVo1.getPhotos() != null ? newProductVo1.getPhotos() : "-");
            row.createCell(11).setCellValue(newProductVo1.getCompanyName() != null ? newProductVo1.getCompanyName() : "-");
            row.createCell(12).setCellValue(newProductVo1.getRemark() != null ? newProductVo1.getRemark() : "-");
            row.createCell(13).setCellValue(newProductVo1.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            HSSFCell cell = row.createCell(14);
            cell.setCellStyle(style);
            rowNum++;
        }

        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.NEW_PRODUCT + String.format("/%tF %s.xls", new Date(), "新品登记列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });
    }

    @Override
    public String getCustomerExcel(CustomerInfoVo customerVo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("客户列表");
        createCustomerTitle(workbook, sheet);

        List<CustomerVo> customerVoList = this.memberMapper.getCustomerByCustomerInfoVo(customerVo);
        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (CustomerVo customerVo1 : customerVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(customerVo1.getZxPhone());
            if (customerVo1.getIsErp() == 1) row.createCell(1).setCellValue("是");
            else row.createCell(1).setCellValue("否");
            row.createCell(2).setCellValue(customerVo1.getCustomid());
            row.createCell(3).setCellValue(customerVo1.getCustomname());
            row.createCell(4).setCellValue(customerVo1.getRegistadd());
            row.createCell(5).setCellValue(customerVo1.getTaxnumber());
            row.createCell(7).setCellValue(customerVo1.getInvmethodName());
            row.createCell(8).setCellValue(customerVo1.getLegalperson());
            row.createCell(9).setCellValue(customerVo1.getGspusestatusname());
            row.createCell(10).setCellValue(customerVo1.getCredate() + "-");
            HSSFCell cell = row.createCell(14);
            cell.setCellStyle(style);
            rowNum++;
        }

        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.CUSTOMER_INFO + String.format("/%tF %s.xls", new Date(), "客户列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });

    }

    @Override
    public String getErpB2bOrderRecDocExcel(ErpB2bOrderRecDocVo erpB2bOrderRecDocVo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("app收款单");
        createAPPRecDocTitle(workbook, sheet);


        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        List<ErpB2bOrderRecDocVo> erpB2bOrderRecDocVoList = this.erpB2bOrderRecDocMapper.getErpB2bOrderRecDocVoExcel(erpB2bOrderRecDocVo);
        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        double cashAmount = 0;
        double onlineAmount = 0;
        double differenceAmount = 0;
        for (ErpB2bOrderRecDocVo erpB2bOrderRecDocVo1 : erpB2bOrderRecDocVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            cashAmount += erpB2bOrderRecDocVo1.getCashTotal();
            onlineAmount += erpB2bOrderRecDocVo1.getOnlineTotal();
            differenceAmount += erpB2bOrderRecDocVo1.getDifferenceAmount();
            row.createCell(0).setCellValue(erpB2bOrderRecDocVo1.getTranposname());
            row.createCell(1).setCellValue(erpB2bOrderRecDocVo1.getTransortno());

            row.createCell(2).setCellValue(erpB2bOrderRecDocVo1.getErpUserId());
            row.createCell(3).setCellValue(erpB2bOrderRecDocVo1.getUserName());
            row.createCell(4).setCellValue(erpB2bOrderRecDocVo1.getTmpNo());
            row.createCell(5).setCellValue(erpB2bOrderRecDocVo1.getSalesids());
            row.createCell(6).setCellValue(erpB2bOrderRecDocVo1.getPayFlowNo());
            row.createCell(7).setCellValue(erpB2bOrderRecDocVo1.getCashTotal());
            row.createCell(8).setCellValue(erpB2bOrderRecDocVo1.getOnlineTotal());
            row.createCell(9).setCellValue(erpB2bOrderRecDocVo1.getCashTotal() + erpB2bOrderRecDocVo1.getOnlineTotal());
            row.createCell(10).setCellValue(erpB2bOrderRecDocVo1.getDifferenceAmount());
            row.createCell(11).setCellValue(erpB2bOrderRecDocVo1.getTelephone());
            row.createCell(12).setCellValue(erpB2bOrderRecDocVo1.getPayTime() + "");
            row.createCell(13).setCellValue(erpB2bOrderRecDocVo1.getCreateTime() + "");
            row.createCell(14).setCellValue(erpB2bOrderRecDocVo1.getSErpId());
            row.createCell(15).setCellValue(erpB2bOrderRecDocVo1.getSName());

            row.createCell(16).setCellValue(erpB2bOrderRecDocVo1.getFinanceStatus());
            row.createCell(17).setCellValue(erpB2bOrderRecDocVo1.getFinanceRemark());
            row.createCell(18).setCellValue(erpB2bOrderRecDocVo1.getErpFinanceRemark());
            if (null != erpB2bOrderRecDocVo1.getFinanceErpId()) {
                row.createCell(19).setCellValue(erpB2bOrderRecDocVo1.getFinanceErpId());
            } else row.createCell(19).setCellValue("-");

            row.createCell(20).setCellValue(erpB2bOrderRecDocVo1.getFinanceName());

            if (null != erpB2bOrderRecDocVo1.getFinanceTime()) {
                row.createCell(21).setCellValue(erpB2bOrderRecDocVo1.getFinanceTime() + " ");
            } else row.createCell(21).setCellValue("-");

            if (null != erpB2bOrderRecDocVo1.getConfirmErpId()) {
                row.createCell(22).setCellValue(erpB2bOrderRecDocVo1.getConfirmErpId());
            } else row.createCell(22).setCellValue("-");

            row.createCell(23).setCellValue(erpB2bOrderRecDocVo1.getConfirmName());

            if (null != erpB2bOrderRecDocVo1.getConfirmTime()) {
                row.createCell(24).setCellValue(erpB2bOrderRecDocVo1.getConfirmTime() + " ");
            } else row.createCell(24).setCellValue("-");


            HSSFCell cell = row.createCell(25);
            cell.setCellStyle(style);
            rowNum++;
        }
        double amount = cashAmount + onlineAmount;
        HSSFRow row = sheet.createRow(rowNum + 1);
        row.createCell(7).setCellValue("现金支付小计：" + IncaUtils.toErpPriceDouble4(cashAmount));
        row.createCell(8).setCellValue("扫码支付小计：" + IncaUtils.toErpPriceDouble4(onlineAmount));
        row.createCell(9).setCellValue("合计：" + IncaUtils.toErpPriceDouble4(amount));
        row.createCell(10).setCellValue("差异小计：" + IncaUtils.toErpPriceDouble4(differenceAmount));

        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.APP_ERP + String.format("/%tF %s.xls", new Date(), "APP收款单列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });

    }

    @Override
    public String getRecDocList(RecDocVo recDocVo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("收款单");
        createRecDocTitle(workbook, sheet);


        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        List<RecDocVo> erpB2bOrderRecDocVoList = this.recDocMapper.getRecDocList(recDocVo);
        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (RecDocVo erpB2bOrderRecDocVo1 : erpB2bOrderRecDocVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(erpB2bOrderRecDocVo1.getId());
            row.createCell(1).setCellValue(erpB2bOrderRecDocVo1.getErpUserId());
            row.createCell(2).setCellValue(erpB2bOrderRecDocVo1.getUserName());
            row.createCell(3).setCellValue(erpB2bOrderRecDocVo1.getOrderNo());
            row.createCell(4).setCellValue(erpB2bOrderRecDocVo1.getPayAbcNo());
            row.createCell(5).setCellValue(erpB2bOrderRecDocVo1.getPayFlowNo());
            row.createCell(6).setCellValue(erpB2bOrderRecDocVo1.getFinanceName());
            row.createCell(7).setCellValue(erpB2bOrderRecDocVo1.getTotal());
            row.createCell(8).setCellValue(erpB2bOrderRecDocVo1.getTotal());
            row.createCell(9).setCellValue(erpB2bOrderRecDocVo1.getErpFinanceRemark());
            row.createCell(10).setCellValue(OrderInfo.EPayMethod.valueOf(erpB2bOrderRecDocVo1.getPayMethod()).desc());
            if (erpB2bOrderRecDocVo1.getFinanceTrue() == 0) {
                row.createCell(11).setCellValue("未核销");
            } else if (erpB2bOrderRecDocVo1.getFinanceTrue() == 1) {
                row.createCell(11).setCellValue("已核销");
            } else if (erpB2bOrderRecDocVo1.getFinanceTrue() == 2) {
                row.createCell(11).setCellValue("核销中");
            } else {
                row.createCell(11).setCellValue("核销失败");
            }
            row.createCell(12).setCellValue(erpB2bOrderRecDocVo1.getCreateTime() + "");
            row.createCell(13).setCellValue(RecDoc.EStatus.valueOf(erpB2bOrderRecDocVo1.getStatus()).desc());
            if (null != erpB2bOrderRecDocVo1.getFinanceTime()) {
                row.createCell(14).setCellValue(erpB2bOrderRecDocVo1.getFinanceTime() + " ");
            } else row.createCell(14).setCellValue("-");

            row.createCell(15).setCellValue(erpB2bOrderRecDocVo1.getRemark());
            row.createCell(16).setCellValue(erpB2bOrderRecDocVo1.getConfirmName());
            if (null != erpB2bOrderRecDocVo1.getConfirmDate()) {
                row.createCell(17).setCellValue(erpB2bOrderRecDocVo1.getConfirmDate() + "");
            } else row.createCell(17).setCellValue("-");
            row.createCell(18).setCellValue(erpB2bOrderRecDocVo1.getConfirmRemark());

            HSSFCell cell = row.createCell(25);
            cell.setCellStyle(style);
            rowNum++;
        }

        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.APP_ERP + String.format("/%tF %s.xls", new Date(), "收款单列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });

    }

    @Override
    public String getRefundRecDocList(RecDocVo recDocVo) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("退款单");
        createRefundRecDocTitle(workbook, sheet);


        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        List<RecDocVo> erpB2bOrderRecDocVoList = this.recDocMapper.getRecDocList(recDocVo);
        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (RecDocVo erpB2bOrderRecDocVo1 : erpB2bOrderRecDocVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(erpB2bOrderRecDocVo1.getId());
            row.createCell(1).setCellValue(erpB2bOrderRecDocVo1.getOrderId());

            row.createCell(2).setCellValue(erpB2bOrderRecDocVo1.getErpUserId());
            row.createCell(3).setCellValue(erpB2bOrderRecDocVo1.getUserName());
            row.createCell(4).setCellValue(erpB2bOrderRecDocVo1.getRefundName());
            row.createCell(5).setCellValue(erpB2bOrderRecDocVo1.getFinanceName());
            row.createCell(6).setCellValue(erpB2bOrderRecDocVo1.getRecDocNo());
            row.createCell(7).setCellValue(erpB2bOrderRecDocVo1.getOrderNo());
            row.createCell(8).setCellValue(erpB2bOrderRecDocVo1.getApplyNo());
            row.createCell(9).setCellValue(erpB2bOrderRecDocVo1.getAbcMessage());
            row.createCell(10).setCellValue(erpB2bOrderRecDocVo1.getPayFlowNo());
            row.createCell(11).setCellValue(erpB2bOrderRecDocVo1.getPayAbcNo());
            row.createCell(12).setCellValue(erpB2bOrderRecDocVo1.getApplyNo());
            row.createCell(13).setCellValue(RecDocVo.ERefundState.valueOf(erpB2bOrderRecDocVo1.getRefundState()).desc());
            row.createCell(14).setCellValue(erpB2bOrderRecDocVo1.getTotal());
            row.createCell(15).setCellValue(erpB2bOrderRecDocVo1.getRefundMoney());

            if (erpB2bOrderRecDocVo1.getFinanceTrue() == 0) {
                row.createCell(16).setCellValue("未核销");
            } else if (erpB2bOrderRecDocVo1.getFinanceTrue() == 1) {
                row.createCell(16).setCellValue("已核销");
            } else if (erpB2bOrderRecDocVo1.getFinanceTrue() == 2) {
                row.createCell(16).setCellValue("核销中");
            } else {
                row.createCell(16).setCellValue("核销失败");
            }

            row.createCell(17).setCellValue(erpB2bOrderRecDocVo1.getErpFinanceRemark());
            row.createCell(18).setCellValue(OrderInfo.EPayMethod.valueOf(erpB2bOrderRecDocVo1.getPayMethod()).desc());
            row.createCell(19).setCellValue(erpB2bOrderRecDocVo1.getCreateTime() + "");


            if (null != erpB2bOrderRecDocVo1.getRefundTime()) {
                row.createCell(20).setCellValue(erpB2bOrderRecDocVo1.getRefundTime() + "");
            } else row.createCell(20).setCellValue("-");

            if (null != erpB2bOrderRecDocVo1.getFinanceTime()) {
                row.createCell(21).setCellValue(erpB2bOrderRecDocVo1.getFinanceTime() + " ");
            } else row.createCell(21).setCellValue("-");

            row.createCell(22).setCellValue(erpB2bOrderRecDocVo1.getRemark());

            row.createCell(23).setCellValue(erpB2bOrderRecDocVo1.getRefundRemark());

            row.createCell(24).setCellValue(erpB2bOrderRecDocVo1.getConfirmName());
            if (null != erpB2bOrderRecDocVo1.getConfirmDate()) {
                row.createCell(25).setCellValue(erpB2bOrderRecDocVo1.getConfirmDate() + "");
            } else row.createCell(25).setCellValue("-");
            row.createCell(26).setCellValue(erpB2bOrderRecDocVo1.getConfirmRemark());

            HSSFCell cell = row.createCell(28);
            cell.setCellStyle(style);
            rowNum++;
        }


        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.APP_ERP + String.format("/%tF %s.xls", new Date(), "退款单列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });

    }

    @Override
    public String getFinancialStatementTotalList(OrderInfoVo orderInfoVo) {
        List<OrderInfoVo> orderInfoVoIPage = this.orderInfoMapper.getFinancialStatementTotalList(orderInfoVo);
        orderInfoVoIPage.forEach(orderInfoVo1 -> {
            orderInfoVo1.setOrderGoodsList(this.orderGoodsMapper.getOrderGoodsVoInfo(orderInfoVo1.getId()));
            String couponName = "";
            String couponAmount = "";
            String couponLimAmount = "";
            if (StringUtils.isNotBlank(orderInfoVo.getCouponIds())) {
                String[] couponIds1 = StringUtils.split(orderInfoVo.getCouponIds(), ',');
                List<Coupon> couponList = this.couponMapper.selectList(new QueryWrapper<Coupon>().in("id", couponIds1));

                for (Coupon coupon : couponList) {
                    couponName += "-" + coupon.getCouponName();
                    couponAmount += "-" + coupon.getReduceAmount();
                    couponLimAmount += "-" + coupon.getFullAmount();
                }
            }
            orderInfoVo1.setCouponName(couponName);
            orderInfoVo1.setCouponAmount(couponAmount);
            orderInfoVo1.setCouponLimAmount(couponLimAmount);
        });
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("财务报表总计");
        HSSFSheet sheet1 = workbook.createSheet("财务报表底表");
        createFinancialStatementTotalTitle(workbook, sheet);
        createFinancialStatementTitle(workbook, sheet1);


        List<OrderInfoVo> orderInfoVoIPage1 = this.orderInfoMapper.getFinancialStatementList(orderInfoVo);
        orderInfoVoIPage.forEach(orderInfoVo1 -> {
            String couponName = "";
            String couponAmount = "";
            String couponLimAmount = "";
            if (StringUtils.isNotBlank(orderInfoVo.getCouponIds())) {
                String[] couponIds1 = StringUtils.split(orderInfoVo.getCouponIds(), ',');
                List<Coupon> couponList = this.couponMapper.selectList(new QueryWrapper<Coupon>().in("id", couponIds1));

                for (Coupon coupon : couponList) {
                    couponName += "-" + coupon.getCouponName();
                    couponAmount += "-" + coupon.getReduceAmount();
                    couponLimAmount += "-" + coupon.getFullAmount();
                }
            }
            orderInfoVo1.setCouponName(couponName);
            orderInfoVo1.setCouponAmount(couponAmount);
            orderInfoVo1.setCouponLimAmount(couponLimAmount);
        });


        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        // 新增数据行，并且设置单元数据
        int rowNum1 = 1;
        for (OrderInfoVo orderInfoVo1 : orderInfoVoIPage1) {
            HSSFRow row = sheet1.createRow(rowNum1);
            row.createCell(0).setCellValue(orderInfoVo1.getOrderNo());
            row.createCell(1).setCellValue(orderInfoVo1.getUserName());
            row.createCell(2).setCellValue(orderInfoVo1.getErpCustomerId());
            row.createCell(3).setCellValue(orderInfoVo1.getGoodsAmount());
            row.createCell(4).setCellValue(orderInfoVo1.getActuallyMoney());
            row.createCell(5).setCellValue(orderInfoVo1.getRefundAmount());
            row.createCell(6).setCellValue(orderInfoVo1.getCouponName());
            row.createCell(7).setCellValue(orderInfoVo1.getCouponLimAmount());
            row.createCell(8).setCellValue(orderInfoVo1.getCouponAmount());
            row.createCell(9).setCellValue(OrderInfo.EPayMethod.valueOf(orderInfoVo1.getPayMethod()).desc());
            row.createCell(10).setCellValue(orderInfoVo1.getCreateTime() + "");
            row.createCell(11).setCellValue(orderInfoVo1.getGoodsid());
            row.createCell(12).setCellValue(orderInfoVo1.getGoodsName());
            row.createCell(13).setCellValue(orderInfoVo1.getGoodstype());
            row.createCell(14).setCellValue(orderInfoVo1.getGoodsunit());
            row.createCell(15).setCellValue(orderInfoVo1.getGoodsNum());
            row.createCell(16).setCellValue(orderInfoVo1.getPrice());
            row.createCell(17).setCellValue(orderInfoVo1.getAmountPay());
            row.createCell(18).setCellValue(orderInfoVo1.getProdarea());
            row.createCell(19).setCellValue(orderInfoVo1.getFactoryname());
            row.createCell(20).setCellValue(orderInfoVo1.getOrderAmount() - orderInfoVo1.getGoodsAmount());
            HSSFCell cell = row.createCell(25);
            cell.setCellStyle(style);
            rowNum1++;
        }



      /*  // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
*/
        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (OrderInfoVo orderInfoVo1 : orderInfoVoIPage) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(orderInfoVo1.getOrderNo());
            row.createCell(1).setCellValue(orderInfoVo1.getUserName());
            row.createCell(2).setCellValue(orderInfoVo1.getErpCustomerId());
            row.createCell(3).setCellValue(orderInfoVo1.getGoodsAmount());
            row.createCell(4).setCellValue(orderInfoVo1.getActuallyMoney());
            row.createCell(5).setCellValue(orderInfoVo1.getRefundAmount());
            row.createCell(6).setCellValue(orderInfoVo1.getCouponName());
            row.createCell(7).setCellValue(orderInfoVo1.getCouponLimAmount());
            row.createCell(8).setCellValue(orderInfoVo1.getCouponAmount());
            row.createCell(9).setCellValue(OrderInfo.EPayMethod.valueOf(orderInfoVo1.getPayMethod()).desc());
            row.createCell(10).setCellValue(orderInfoVo1.getCreateTime() + "");

            HSSFCell cell = row.createCell(25);
            cell.setCellStyle(style);
            rowNum++;
        }


        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.APP_ERP + String.format("/%tF %s.xls", new Date(), "财务报表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });

    }

    @Override
    public String getFinancialStatementList(OrderInfoVo orderInfoVo) {
        List<OrderInfoVo> orderInfoVoIPage = this.orderInfoMapper.getFinancialStatementList(orderInfoVo);
        orderInfoVoIPage.forEach(orderInfoVo1 -> {
            String couponName = "";
            String couponAmount = "";
            String couponLimAmount = "";
            if (StringUtils.isNotBlank(orderInfoVo.getCouponIds())) {
                String[] couponIds1 = StringUtils.split(orderInfoVo.getCouponIds(), ',');
                List<Coupon> couponList = this.couponMapper.selectList(new QueryWrapper<Coupon>().in("id", couponIds1));

                for (Coupon coupon : couponList) {
                    couponName += "-" + coupon.getCouponName();
                    couponAmount += "-" + coupon.getReduceAmount();
                    couponLimAmount += "-" + coupon.getFullAmount();
                }
            }
            orderInfoVo1.setCouponName(couponName);
            orderInfoVo1.setCouponAmount(couponAmount);
            orderInfoVo1.setCouponLimAmount(couponLimAmount);
        });
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("财务报表底表");
        createFinancialStatementTitle(workbook, sheet);


        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (OrderInfoVo orderInfoVo1 : orderInfoVoIPage) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(orderInfoVo1.getOrderNo());
            row.createCell(1).setCellValue(orderInfoVo1.getUserName());
            row.createCell(2).setCellValue(orderInfoVo1.getErpCustomerId());
            row.createCell(3).setCellValue(orderInfoVo1.getGoodsAmount());
            row.createCell(4).setCellValue(orderInfoVo1.getActuallyMoney());
            row.createCell(5).setCellValue(orderInfoVo1.getRefundAmount());
            row.createCell(6).setCellValue(orderInfoVo1.getCouponName());
            row.createCell(7).setCellValue(orderInfoVo1.getCouponLimAmount());
            row.createCell(8).setCellValue(orderInfoVo1.getCouponAmount());
            row.createCell(9).setCellValue(OrderInfo.EPayMethod.valueOf(orderInfoVo1.getPayMethod()).desc());
            row.createCell(10).setCellValue(orderInfoVo1.getCreateTime() + "");
            row.createCell(11).setCellValue(orderInfoVo1.getGoodsid());
            row.createCell(12).setCellValue(orderInfoVo1.getGoodsName());
            row.createCell(13).setCellValue(orderInfoVo1.getGoodstype());
            row.createCell(14).setCellValue(orderInfoVo1.getGoodsunit());
            row.createCell(15).setCellValue(orderInfoVo1.getGoodsNum());
            row.createCell(16).setCellValue(orderInfoVo1.getPrice());
            row.createCell(17).setCellValue(orderInfoVo1.getAmountPay());
            row.createCell(18).setCellValue(orderInfoVo1.getProdarea());
            row.createCell(19).setCellValue(orderInfoVo1.getFactoryname());
            row.createCell(20).setCellValue(orderInfoVo1.getOrderAmount() - orderInfoVo1.getGoodsAmount());
            HSSFCell cell = row.createCell(25);
            cell.setCellStyle(style);
            rowNum++;
        }


        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.APP_ERP + String.format("/%tF %s.xls", new Date(), "财务报表底表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });

    }

    @Override
    public String getOrderInfoListExcel(OrderInfoVo orderInfoVo) {
        List<OrderInfoVo> orderInfoVoList = this.orderInfoMapper.getAdminOrderInfoList(orderInfoVo);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("订单列表");
        createOrderTitle(workbook, sheet);


        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (OrderInfoVo orderInfoVo1 : orderInfoVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(orderInfoVo1.getId());
            row.createCell(1).setCellValue(orderInfoVo1.getOrderNo());
            row.createCell(2).setCellValue(orderInfoVo1.getPayMethod());
            row.createCell(3).setCellValue(orderInfoVo1.getPayFlowNo());
            row.createCell(4).setCellValue(orderInfoVo1.getErpCustomerId());
            row.createCell(5).setCellValue(orderInfoVo1.getUserName());
            row.createCell(6).setCellValue(OrderInfo.EOrderState.valueOf(orderInfoVo1.getOrderState()).desc());
            row.createCell(7).setCellValue(OrderInfo.EExpStatus.valueOf(orderInfoVo1.getExpStatus()).desc());
            row.createCell(8).setCellValue(orderInfoVo1.getTelephone());
            row.createCell(9).setCellValue(orderInfoVo1.getZxPhone());
            row.createCell(10).setCellValue(orderInfoVo1.getCreateTime() + "");
            row.createCell(11).setCellValue(OrderInfo.EPayMethod.valueOf(orderInfoVo1.getPayMethod()).desc());
            row.createCell(12).setCellValue(orderInfoVo1.getPayType());
            row.createCell(13).setCellValue(orderInfoVo1.getPayTypeDoc());
            row.createCell(14).setCellValue(orderInfoVo1.getOrderAmount());
            row.createCell(15).setCellValue(orderInfoVo1.getActuallyMoney());
            row.createCell(16).setCellValue(orderInfoVo1.getGoodsAmount());
            row.createCell(17).setCellValue(orderInfoVo1.getOrderAmount() - orderInfoVo1.getGoodsAmount());
            row.createCell(18).setCellValue(orderInfoVo1.getErpAmount());
            row.createCell(19).setCellValue(orderInfoVo1.getOrderFrom());
            if (orderInfoVo1.getOrderFrom() == 1) {
                row.createCell(19).setCellValue("PC商城");
            } else if (orderInfoVo1.getOrderFrom() == 2) {
                row.createCell(19).setCellValue("小程序");
            } else {
                row.createCell(19).setCellValue("后台下单");
            }
            row.createCell(20).setCellValue(orderInfoVo1.getInvType());
            row.createCell(21).setCellValue(OrderInfo.EFpStatus.valueOf(orderInfoVo1.getFpStatus()).desc());
            row.createCell(22).setCellValue(orderInfoVo1.getExpRemark());
            row.createCell(23).setCellValue(orderInfoVo1.getDeleteState());
            if (orderInfoVo1.getDeleteState() == 1) {
                row.createCell(19).setCellValue("放入回收站");
            } else if (orderInfoVo1.getDeleteState() == 2) {
                row.createCell(19).setCellValue("彻底删除");
            } else {
                row.createCell(19).setCellValue("未删除");
            }
            row.createCell(24).setCellValue(OrderInfo.EInterceptStatus.valueOf(orderInfoVo1.getInterceptStatus()).desc());
            row.createCell(25).setCellValue(orderInfoVo1.getInterceptRemark());
            row.createCell(26).setCellValue(orderInfoVo1.getRemark());
            row.createCell(27).setCellValue(orderInfoVo1.getRemark());
            if (null != orderInfoVo1.getInterceptTime()) {
                row.createCell(28).setCellValue(orderInfoVo1.getInterceptTime() + "");
            } else row.createCell(28).setCellValue("-");

            row.createCell(29).setCellValue(orderInfoVo1.getAddressDetail());


            HSSFCell cell = row.createCell(36);
            cell.setCellStyle(style);
            rowNum++;
        }

        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.ORDER + String.format("/%tF %s.xls", new Date(), "订单列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });

    }

    @Override
    public String getErpOrderInfoListExcel(OrderInfoVo orderInfoVo) {
        List<OrderInfoVo> orderInfoVoList = this.orderInfoMapper.getOrderInfoVoByTranslinename(orderInfoVo);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("送货列表");
        createErpOrderInfoTitle(workbook, sheet);


        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (OrderInfoVo orderInfoVo1 : orderInfoVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(orderInfoVo1.getCustomid());
            row.createCell(1).setCellValue(orderInfoVo1.getCustomname());
            row.createCell(2).setCellValue(orderInfoVo1.getSettletype());
            row.createCell(3).setCellValue(orderInfoVo1.getTranposname());
            row.createCell(4).setCellValue(orderInfoVo1.getTransortno());
            row.createCell(5).setCellValue(orderInfoVo1.getZxPhone());
            row.createCell(6).setCellValue(orderInfoVo1.getPayTotaline());
            row.createCell(7).setCellValue(orderInfoVo1.getAddress());
            HSSFCell cell = row.createCell(25);
            cell.setCellStyle(style);
            rowNum++;
        }


        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.ORDER + String.format("/%tF %s.xls", new Date(), "送货列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });

    }

    @Override
    public String getPayFlowNoOrder(RecDocVo recDocVo) {
        recDocVo.setRecType(RecDoc.ERecType.ON_LINE.val());
        List<RecDocVo> recDocVoList = this.recDocMapper.getRecDocList(recDocVo);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("支付流水");
        createPayFlowNoOrderTitle(workbook, sheet);


        // 设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        // 新增数据行，并且设置单元数据
        int rowNum = 1;
        for (RecDocVo recDocVo1 : recDocVoList) {
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(recDocVo1.getMemberId());
            row.createCell(1).setCellValue(recDocVo1.getErpUserId());
            row.createCell(2).setCellValue(recDocVo1.getUserName());
            row.createCell(3).setCellValue(recDocVo1.getOrderNo());
            row.createCell(4).setCellValue(recDocVo1.getPayAbcNo());
            row.createCell(5).setCellValue(RecDoc.ERecType.valueOf(recDocVo1.getRecType()).desc());
            if (null != recDocVo1.getPayTime()) {
                row.createCell(6).setCellValue(recDocVo1.getPayTime() + "");
                row.createCell(7).setCellValue(recDocVo1.getPayTypeDoc());
            } else {
                row.createCell(6).setCellValue("");
                row.createCell(7).setCellValue("");
            }

            row.createCell(8).setCellValue(recDocVo1.getTotal());
            row.createCell(9).setCellValue(RecDoc.ERecMethod.valueOf(recDocVo1.getRecMethod()).desc());
            row.createCell(10).setCellValue(recDocVo1.getRefundMoney());
            if (null != recDocVo1.getPayTime()) {
                row.createCell(11).setCellValue(recDocVo1.getPayTime() + "");
            } else row.createCell(11).setCellValue("");

            row.createCell(12).setCellValue(RecDoc.EStatus.valueOf(recDocVo1.getStatus()).desc());

            row.createCell(13).setCellValue(recDocVo1.getRemark());
            row.createCell(14).setCellValue(recDocVo1.getCreateTime()+"");

            HSSFCell cell = row.createCell(25);
            cell.setCellStyle(style);
            rowNum++;
        }

        return this.fileService.exportNewProductList(Constant.EXCEL_PREFIX.ORDER + String.format("/%tF %s.xls", new Date(), "支付流水列表"), outputStream -> {
            try {
                workbook.write(outputStream);
                workbook.close();
            } catch (IOException e) {
                log.info("IOException", e);
            }
        });

    }

    // 创建客户列表表头
    private void createPayFlowNoOrderTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 17 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("b2b用户id");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("erp用户id");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("客户名称");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("支付单号");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("支付时间");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("支付说明");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("实收款");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("收款类型");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("退款金额");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("收款时间");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("收款状态");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        cell = row.createCell(14);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);

    }


    // 创建客户列表表头
    private void createCustomerTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 17 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("客户手机");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("erp");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("客户id");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("客户名称");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("注册地址");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("税号");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("开票策略");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("法人代表");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("质量状态");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);

    }

    // 创建新品登记表头
    private void createNewProductTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 17 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("规格");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("需求商数量");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("会员名称");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("批准文号");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("建议价格");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("货源渠道");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("图片");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("厂家");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("添加时间");
        cell.setCellStyle(style);
    }

    // 创建APP收款单表头
    private void createAPPRecDocTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 18 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("线路");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("线序");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("客户id");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("客户名称");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("支付单号");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("erp订单id");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("支付流水号");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("收款现金");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("在线支付");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("合计");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("差异金额");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("手机号");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("支付时间");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);


        cell = row.createCell(14);
        cell.setCellValue("收款人id");
        cell.setCellStyle(style);

        cell = row.createCell(15);
        cell.setCellValue("收款人");
        cell.setCellStyle(style);


        cell = row.createCell(16);
        cell.setCellValue("核销状态");
        cell.setCellStyle(style);

        cell = row.createCell(17);
        cell.setCellValue("核销备注");
        cell.setCellStyle(style);

        cell = row.createCell(18);
        cell.setCellValue("erp核销备注");
        cell.setCellStyle(style);

        cell = row.createCell(19);
        cell.setCellValue("核销人id");
        cell.setCellStyle(style);

        cell = row.createCell(20);
        cell.setCellValue("核销人");
        cell.setCellStyle(style);

        cell = row.createCell(21);
        cell.setCellValue("核销时间");
        cell.setCellStyle(style);

        cell = row.createCell(22);
        cell.setCellValue("确认收款人id");
        cell.setCellStyle(style);

        cell = row.createCell(23);
        cell.setCellValue("确认收款人");
        cell.setCellStyle(style);

        cell = row.createCell(24);
        cell.setCellValue("确认收款时间");
        cell.setCellStyle(style);


    }


    // 创建收款单表头
    private void createRecDocTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 18 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("收款单id");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("客户id");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("客户名称");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("收款流水号");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("支付流水号");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("核销操作人");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("应收款");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("实收款");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("erp核销备注");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("结算方式");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("是否核销");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("收款状态");
        cell.setCellStyle(style);


        cell = row.createCell(14);
        cell.setCellValue("核销时间");
        cell.setCellStyle(style);

        cell = row.createCell(15);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        cell = row.createCell(16);
        cell.setCellValue("确认人");
        cell.setCellStyle(style);

        cell = row.createCell(17);
        cell.setCellValue("确认时间");
        cell.setCellStyle(style);

        cell = row.createCell(18);
        cell.setCellValue("确认备注");
        cell.setCellStyle(style);

    }

    // 创建退款单表头
    private void createRefundRecDocTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 18 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("退款单id");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("订单id");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("客户id");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("客户名称");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("退款操作人");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("核销操作人");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("退货单号");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("退款单号");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("回调报文");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("支付流水号");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("退款流水号");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("退货单号");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("退款状态");
        cell.setCellStyle(style);


        cell = row.createCell(14);
        cell.setCellValue("应退款");
        cell.setCellStyle(style);

        cell = row.createCell(15);
        cell.setCellValue("实退款");
        cell.setCellStyle(style);


        cell = row.createCell(16);
        cell.setCellValue("核销状态");
        cell.setCellStyle(style);

        cell = row.createCell(17);
        cell.setCellValue("erp核销备注");
        cell.setCellStyle(style);

        cell = row.createCell(18);
        cell.setCellValue("结算方式");
        cell.setCellStyle(style);

        cell = row.createCell(19);
        cell.setCellValue("创建时间");
        cell.setCellStyle(style);

        cell = row.createCell(20);
        cell.setCellValue("退款时间");
        cell.setCellStyle(style);

        cell = row.createCell(21);
        cell.setCellValue("核销时间");
        cell.setCellStyle(style);

        cell = row.createCell(22);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        cell = row.createCell(23);
        cell.setCellValue("退款备注");
        cell.setCellStyle(style);

        cell = row.createCell(24);
        cell.setCellValue("确认人");
        cell.setCellStyle(style);

        cell = row.createCell(25);
        cell.setCellValue("确认时间");
        cell.setCellStyle(style);

        cell = row.createCell(26);
        cell.setCellValue("确认备注");
        cell.setCellStyle(style);
    }

    // 创建财务总表表头
    private void createFinancialStatementTotalTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 18 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("订单编号");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("客户名称");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("客户id");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("应付金额");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("实收金额");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("退款金额");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("优惠券名称");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("优惠券限额");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("优惠券金额");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("费用发生时间");
        cell.setCellStyle(style);

    }

    // 创建财务底表表头
    private void createFinancialStatementTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 18 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("订单编号");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("客户名称");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("客户id");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("应付金额");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("实收金额");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("退款金额");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("优惠券名称");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("优惠券限额");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("优惠券金额");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("费用发生时间");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("商品id");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("商品规格");
        cell.setCellStyle(style);

        cell = row.createCell(14);
        cell.setCellValue("商品单位");
        cell.setCellStyle(style);

        cell = row.createCell(15);
        cell.setCellValue("商品数量");
        cell.setCellStyle(style);

        cell = row.createCell(16);
        cell.setCellValue("单价");
        cell.setCellStyle(style);

        cell = row.createCell(17);
        cell.setCellValue("小计");
        cell.setCellStyle(style);

        cell = row.createCell(18);
        cell.setCellValue("商品产地");
        cell.setCellStyle(style);

        cell = row.createCell(19);
        cell.setCellValue("厂家");
        cell.setCellStyle(style);

        cell = row.createCell(20);
        cell.setCellValue("平台优惠金额");
        cell.setCellStyle(style);
    }


    // 创建订单表头
    private void createOrderTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 18 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("订单id");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("订单号");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("支付单号");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("支付流水号");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("客户id");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("客户名称");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("订单状态");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("异常状态");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("注册手机号");
        cell.setCellStyle(style);

        cell = row.createCell(9);
        cell.setCellValue("下单电话");
        cell.setCellStyle(style);

        cell = row.createCell(10);
        cell.setCellValue("下单时间");
        cell.setCellStyle(style);

        cell = row.createCell(11);
        cell.setCellValue("结算方式");
        cell.setCellStyle(style);

        cell = row.createCell(12);
        cell.setCellValue("支付码");
        cell.setCellStyle(style);

        cell = row.createCell(13);
        cell.setCellValue("支付说明");
        cell.setCellStyle(style);


        cell = row.createCell(14);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);

        cell = row.createCell(15);
        cell.setCellValue("实付金额");
        cell.setCellStyle(style);


        cell = row.createCell(16);
        cell.setCellValue("应付金额");
        cell.setCellStyle(style);

        cell = row.createCell(17);
        cell.setCellValue("优惠金额");
        cell.setCellStyle(style);

        cell = row.createCell(18);
        cell.setCellValue("到货金额");
        cell.setCellStyle(style);

        cell = row.createCell(19);
        cell.setCellValue("订单来源");
        cell.setCellStyle(style);

        cell = row.createCell(20);
        cell.setCellValue("发票类型");
        cell.setCellStyle(style);

        cell = row.createCell(21);
        cell.setCellValue("发票状态");
        cell.setCellStyle(style);

        cell = row.createCell(22);
        cell.setCellValue("异常备注");
        cell.setCellStyle(style);

        cell = row.createCell(23);
        cell.setCellValue("删除状态");
        cell.setCellStyle(style);

        cell = row.createCell(24);
        cell.setCellValue("拦截状态");
        cell.setCellStyle(style);

        cell = row.createCell(25);
        cell.setCellValue("异常备注");
        cell.setCellStyle(style);

        cell = row.createCell(26);
        cell.setCellValue("随货备注");
        cell.setCellStyle(style);

        cell = row.createCell(27);
        cell.setCellValue("下单备注");
        cell.setCellStyle(style);

        cell = row.createCell(28);
        cell.setCellValue("拦截时间");
        cell.setCellStyle(style);

        cell = row.createCell(29);
        cell.setCellValue("地址");
        cell.setCellStyle(style);
    }

    // 创建送货表表头
    private void createErpOrderInfoTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        for (int i = 0; i < 10; i++) {
            sheet.setColumnWidth(i, 18 * 256);
        }
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("客户id");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("客户名称");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("付款方式");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("线路");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("线序");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("注册手机号");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("订单金额");
        cell.setCellStyle(style);

        cell = row.createCell(7);
        cell.setCellValue("地址");
        cell.setCellStyle(style);

    }

}

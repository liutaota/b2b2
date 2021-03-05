package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.mapper.B2bFinancialStatementMapper;
import com.zsyc.zt.inca.vo.others.B2bFinancialStatementVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class B2bFinancialStatementServiceImpl implements B2bFinancialStatementService {

    @Autowired
    private B2bFinancialStatementMapper b2bFinancialStatementMapper;

    @Override
    public IPage<B2bFinancialStatementVo> getB2bStatementList(Page<B2bFinancialStatementVo> page, B2bFinancialStatementVo b2bFinancialStatementVo) {
        AssertExt.notNull(page, "页码数据为空");
        IPage<B2bFinancialStatementVo> b2bStatementList = this.b2bFinancialStatementMapper.getB2bStatementList(page, b2bFinancialStatementVo);
        log.info("page size {}", b2bStatementList.getSize());
        return b2bStatementList;
    }

    @Override
    public IPage<B2bFinancialStatementVo> selectExportStatementDataPage(IPage page, B2bFinancialStatementVo b2bFinancialStatementVo) {
        AssertExt.notNull(page, "页码数据为空");
        AssertExt.notNull(b2bFinancialStatementVo,"数据为空");
        /*AssertExt.notNull(b2bFinancialStatementVo.getStartDate(),"数据为空");
        AssertExt.notNull(b2bFinancialStatementVo.getEndDate(),"数据为空");*/
        //LocalDateTime time = LocalDateTime.of(2020, 12, 1, 0, 0, 0);
       /* if (b2bFinancialStatementVo.getStartDate().isAfter(time)){
            b2bFinancialStatementVo.setStartDate(time);
        }*/
        return this.b2bFinancialStatementMapper.selectExportStatementDataPage(page,b2bFinancialStatementVo);
    }
}

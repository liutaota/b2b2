package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.inca.dto.BmsSaDocDto;
import com.zsyc.zt.inca.dto.ReportDto;
import com.zsyc.zt.inca.entity.BmsSaDoc;
import com.zsyc.zt.inca.vo.BmsSaDocVo;
import com.zsyc.zt.inca.vo.OrderDetailVo;
import com.zsyc.zt.inca.vo.ReportVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
public interface BmsSaDocService extends IService<BmsSaDoc> {

    void updateInvInfo(String invoiceCode, String invoiceNo, LocalDateTime invoiceDate, List<Long> salesIdList);

    void deleteInvInfo(String invoiceCode, String invoiceNo, List<Long> salesIdList);
}

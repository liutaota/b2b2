package com.zsyc.zt.fapiao.service;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xxl.job.core.log.XxlJobLogger;
import com.zsyc.zt.fapiao.commom.SyncConstant;
import com.zsyc.zt.fapiao.entity.BwfpFpxx;
import com.zsyc.zt.inca.service.BmsSaDocService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 发票回传信息中的货物明细 服务实现类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Service
public class KaiPiaoServiceImpl implements KaiPiaoService {

    @Reference
    SyncConfService syncConfService;

    @Reference
    BwfpFpxxService bwfpFpxxService;


    @Reference
    BmsSaDocService bmsSaDocService;

    @Override
    public void kpxxWb() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime syncBeginTime = syncConfService.getSyncBeginTime(SyncConstant.KPXX_WB_SYNC);

        /**
         * 拿到所有的发票数据
         */
         List<BwfpFpxx>  result = bwfpFpxxService.selectList(syncBeginTime);


        if (ObjectUtil.isNotEmpty(result)) {
            XxlJobLogger.log("需要回写的数据"+result.size());
            result.stream().forEach(item -> {
                if (ObjectUtil.isEmpty((item.getOriginalBillCode()))) {
                    return;
                }
                List<Long> salesIdList = Arrays.asList(item.getOriginalBillCode().split(",")).
                        stream().
                        map(xx -> {
                            XxlJobLogger.log("单号："+xx);
                                    if (NumberUtil.isNumber(xx)) {
                                        return Long.valueOf(xx);
                                    } else {
                                        return 0L;
                                    }
                                }
                        ).
                        collect(Collectors.toList());

                if (ObjectUtil.equal(item.getInvoiceType(), 0)) {
                    bmsSaDocService.updateInvInfo(item.getInvoiceCode(), item.getInvoiceNo(), LocalDateTime.parse(item.getInvoiceDate().toString(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), salesIdList);
                }

                if (ObjectUtil.equal(item.getInvoiceType(), 2)) {
                    bmsSaDocService.deleteInvInfo(item.getInvoiceCode(), item.getInvoiceNo(), salesIdList);
                }
            });
        } else {
            XxlJobLogger.log("当前没有需要回写的发票数据");
        }
        syncConfService.updateyncBeginTime(SyncConstant.KPXX_WB_SYNC, now);

    }
}

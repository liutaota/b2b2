package com.zsyc.zt.inca.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.inca.entity.BmsSaDoc;
import com.zsyc.zt.inca.mapper.BmsSaDocMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Service
public class BmsSaDocServiceImpl extends ServiceImpl<BmsSaDocMapper, BmsSaDoc> implements BmsSaDocService {
    @Autowired
    private BmsSaDocMapper bmsSaDocMapper;

    @Override
    public void updateInvInfo(String invoiceCode, String invoiceNo, LocalDateTime invoiceDate, List<Long> salesIdList) {
        salesIdList.forEach(item -> {
            BmsSaDoc bmsSaDoc = getById(item);

            if (ObjectUtil.isEmpty(bmsSaDoc)) {
                return;
            }
            if (ObjectUtil.isNotEmpty(bmsSaDoc.getZxInvno()) && bmsSaDoc.getZxInvno().contains(invoiceNo)) {
                return;
            }
            if (ObjectUtil.isEmpty(bmsSaDoc.getZxInvno())) {
                bmsSaDoc.setZxInvcode(invoiceCode);
                bmsSaDoc.setZxInvno(invoiceNo);
                bmsSaDoc.setZxInvdate(invoiceDate);
                bmsSaDoc.setZxInvstatus(2);
                bmsSaDoc.setZxInvbackdate(LocalDateTime.now());
            } else {
                bmsSaDoc.setZxInvcode(bmsSaDoc.getZxInvcode() + "," + invoiceCode);
                bmsSaDoc.setZxInvno(bmsSaDoc.getZxInvno() + "," + invoiceNo);
            }

            this.updateById(bmsSaDoc);

        });
    }

    @Override
    public void deleteInvInfo(String invoiceCode, String invoiceNo, List<Long> salesIdList) {
        salesIdList.forEach(item -> {
            BmsSaDoc bmsSaDoc = getById(item);

            if (ObjectUtil.isEmpty(bmsSaDoc.getZxInvcode())) {
                return;
            }
            bmsSaDoc.setZxInvcode(bmsSaDoc.getZxInvcode().replace(invoiceCode, ""));
            bmsSaDoc.setZxInvno(bmsSaDoc.getZxInvno().replace(invoiceNo, ""));
            this.updateById(bmsSaDoc);
        });
    }
}

package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Licence;
import com.zsyc.zt.b2b.vo.LicenceVo;

import java.util.List;

public interface LicenceService {

    /**
     * 更新所有客户证照使用状态
     */
    void updateLicenceStatus();
}

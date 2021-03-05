package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Licence;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.LicenceVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户证照 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-09-03
 */
public interface LicenceMapper extends BaseMapper<Licence> {
    /**
     * 所有证件照信息-审核通过的
     * @param page
     * @param licenceVo
     * @return
     */
    IPage<LicenceVo> getLicenceVoList(@Param("page") Page page, @Param("licenceVo") LicenceVo licenceVo);


    /**
     * 已经过期的证照
     */
    void updateLicenceDoneValid();


    /**
     * 即将过期的证照
     */
    void updateLicenceTOValid();
}

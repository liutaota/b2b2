package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.inca.entity.PdaVersionUpdate;
import com.zsyc.zt.inca.vo.PdaVersionUpdateVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2021-01-13
 */
public interface PdaVersionUpdateMapper extends BaseMapper<PdaVersionUpdate> {

    IPage<PdaVersionUpdateVo> getPdaVersionDesList(Page<PdaVersionUpdateVo> page,
                                                   @Param("pdaVersionUpdateVo") PdaVersionUpdateVo pdaVersionUpdateVo);

    /**
     * 获取PDA最新版本信息
     * @return
     */
    PdaVersionUpdate getPdaNewestVersion();
}

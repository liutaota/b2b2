package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.inca.entity.PdaVersionUpdate;
import com.zsyc.zt.inca.vo.PdaVersionUpdateVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP
 * @since 2021-01-13
 */
public interface IPdaVersionUpdateService extends IService<PdaVersionUpdate> {

    /**
     * 获取PDA版本历史信息
     * @param page
     * @param pdaVersionUpdateVo
     * @return
     */
    IPage<PdaVersionUpdateVo> getPdaVersionDesList(Page<PdaVersionUpdateVo> page, PdaVersionUpdateVo pdaVersionUpdateVo);

    /**
     * 保存PDA新版本信息
     * @param pdaVersionUpdateVo
     * @return
     */
    Map<String, Object> saveVersionDes(PdaVersionUpdateVo pdaVersionUpdateVo);

    /**
     * 获取PDA最新版本
     * @return
     */
    Map<String,String> getPdaVersion(String pdaUploadPath);

    /**
     * 更新备注
     * @param id
     * @param newRemark
     * @param updateUserId
     * @return
     */
    Map<String, String> updateRemark(Long id, String newRemark, Long updateUserId);
}

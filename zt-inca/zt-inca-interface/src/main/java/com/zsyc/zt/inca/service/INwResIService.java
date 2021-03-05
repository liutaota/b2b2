package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.inca.dto.NwResIDto;
import com.zsyc.zt.inca.entity.NwResI;
import com.zsyc.zt.inca.vo.NwResIVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-25
 */
public interface INwResIService extends IService<NwResI> {


    List<NwResIVo> getByErpId(String erpId);

    List<NwResIVo> getByPackId(String packId);

    IPage<NwResIVo> selectNwResI(IPage<NwResIVo> page, NwResIDto nwResIDto);


    /**
     * 根据箱号查询客户信息
     * @param packId
     * @return
     */
    NwResIVo getCustom(String packId);

    /**
     * 查询销售订单及箱号对应的信息
     * @param erpId
     * @param packId
     * @return
     */
    List<NwResIVo> getNwResIList(String erpId, String packId);

    /**
     * 查询当前用户剩余未录入箱号
     * @param currentCustomId
     * @return
     */
    List<String> querySurplusPackId(Long currentCustomId);
}

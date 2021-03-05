package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.inca.dto.NwResIDto;
import com.zsyc.zt.inca.entity.NwResI;
import com.zsyc.zt.inca.mapper.NwResIMapper;
import com.zsyc.zt.inca.vo.NwResIVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-25
 */
@Primary
@Service
public class NwResIServiceImpl extends ServiceImpl<NwResIMapper, NwResI> implements INwResIService {

    @Autowired
    private  NwResIMapper nwResIMapper;

    @Override
    public List<NwResIVo> getByErpId(String erpId) {
        if(StringUtils.isEmpty(erpId)){
            return null;
        }
        return nwResIMapper.getByErpId(erpId);
    }

    @Override
    public List<NwResIVo> getByPackId(String packId) {
        if(StringUtils.isEmpty(packId)){
            return null;
        }
        return nwResIMapper.getByPackId(packId);
    }


    @Override
    public IPage<NwResIVo> selectNwResI(IPage<NwResIVo> page, NwResIDto nwResIDto) {
        return nwResIMapper.selectNwResI(page,nwResIDto);
    }

    /**
     * 根据货品箱号查询订单号，客户信息
     * @param packId
     * @return
     */
    @Override
    public NwResIVo getCustom(String packId) {

        return nwResIMapper.getCustomByPackId(packId);
    }

    /**
     *
     * @param erpId
     * @param packId
     * @return
     */
    @Override
    public List<NwResIVo> getNwResIList(String erpId, String packId) {
        return nwResIMapper.selectListByErpIdPackId(erpId,packId);
    }

    @Override
    public List<String> querySurplusPackId(Long currentCustomId) {

        return nwResIMapper.querySurplusPackId(currentCustomId);
    }
}

package com.zsyc.zt.inca.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.dto.CkCarScheduleDocDto;
import com.zsyc.zt.inca.entity.CkCarScheduleDoc;
import com.zsyc.zt.inca.mapper.CkCarScheduleDocMapper;
import com.zsyc.zt.inca.vo.CkCarScheduleDocVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peiqy
 * @since 2020-12-02
 */
@Service
@Primary
public class CkCarScheduleDocServiceImpl extends ServiceImpl<CkCarScheduleDocMapper, CkCarScheduleDoc> implements ICkCarScheduleDocService {

    @Autowired
    private CkCarScheduleDocMapper ckCarScheduleDocMapper;

    @Override
    public List<CkCarScheduleDocVo> listByObj() {
        return this.baseMapper.selectByObj();
    }

    @Override
    public List<CkCarScheduleDocVo> selectByObjOption(CkCarScheduleDocDto paramObj) {
        AssertExt.isTrue(ObjectUtil.isNotNull(paramObj),"参数为空!");
        return this.baseMapper.selectByObjOption(paramObj);
    }

    @Override
    public CkCarScheduleDocVo getByScheduleId(CkCarScheduleDocDto paramObj) {
        AssertExt.isTrue(ObjectUtil.isNotNull(paramObj),"参数为空!");
        return this.baseMapper.getByScheduleId(paramObj);
    }

    @Override
    public CkCarScheduleDocVo getByScheduleBill(Long vehicleId) {
        AssertExt.hasId(vehicleId ,"车牌号为空!");
        CkCarScheduleDocVo docVo = ckCarScheduleDocMapper.getByScheduleBill(vehicleId);
        AssertExt.notNull(docVo,"出车调度单信息不存在");
        return docVo;
    }

}

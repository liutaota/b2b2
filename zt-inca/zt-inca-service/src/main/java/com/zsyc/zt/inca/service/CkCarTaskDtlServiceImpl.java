package com.zsyc.zt.inca.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.dto.CkCarTaskDtlDto;
import com.zsyc.zt.inca.entity.CkCarTaskDtl;
import com.zsyc.zt.inca.mapper.CkCarTaskDtlMapper;
import com.zsyc.zt.inca.vo.CkCarTaskDtlVo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peiqy
 * @since 2020-12-05
 */
@Primary
@Service
public class CkCarTaskDtlServiceImpl extends ServiceImpl<CkCarTaskDtlMapper, CkCarTaskDtl> implements ICkCarTaskDtlService {

    @Autowired
    private CkCarTaskDtlMapper ckCarTaskDtlMapper;

    @Autowired
    private ICkCarTaskBoxService iCkCarTaskBoxService;

    @Autowired
    private ICkCarTaskDocService iCkCarTaskDocService;

    @Override
    public List<CkCarTaskDtlVo> selectByObj(CkCarTaskDtlDto paramObj) {
        AssertExt.hasId(paramObj.getScheduleId(),"出车调度单不存在");
        return this.baseMapper.selectByObj(paramObj);
    }

    @Override
    public CkCarTaskDtlVo getByTaskDtlId(CkCarTaskDtlDto paramObj) {
        return this.baseMapper.getByTaskDtlId(paramObj);
    }

    /**
     * 根据taskId查询细单
     * @param taskId
     * @return
     */
    @Override
    public List<CkCarTaskDtlVo> selectByTaskId(Long taskId) {

        return ckCarTaskDtlMapper.getCarTaskDtlList(taskId);
    }

    /**
     * 查询任务对应的所有细单
     * @param taskId
     * @return
     */
    @Override
    public List<CkCarTaskDtlVo> getCarTaskDtlList(Long taskId) {
        return ckCarTaskDtlMapper.getCarTaskDtlList(taskId);
    }

    /**
     * 查询箱号对应的任务总单、细单是否存在
     * @param customId
     * @return
     */
    @Override
    public CkCarTaskDtlVo getCustomByPackId(Long taskId,Long customId) {
        return ckCarTaskDtlMapper.getCustomByPackId(taskId,customId);
    }

    /**
     * 查询细单是否存在
     * @param ckCarTaskDtlDto
     * @return
     */
    @Override
    public int getDtlCount(CkCarTaskDtlDto ckCarTaskDtlDto) {

        return ckCarTaskDtlMapper.getDtlCount(ckCarTaskDtlDto);
    }

    /**
     * 更新任务细单状态
     * @param taskId
     * @return
     */
    @Override
    public int updateDtlStatus(Long taskId) {
        return ckCarTaskDtlMapper.updateDtlStatus(taskId);
    }

    /**
     * 删除任务细单及所有箱号记录
     * @param taskDtlId
     * @return
     */
    @Override
    public Map<String, Integer> deleteTaskDtlById(Long taskId,Long taskDtlId) {
        AssertExt.isTrue(ObjectUtil.isNotNull(taskDtlId),"任务ID不存在");
        AssertExt.isTrue(ObjectUtil.isNotNull(taskDtlId),"任务细单ID不存在");
        Map<String, Integer> rsMap = new HashMap<>(3);

        int usestatus = ckCarTaskDtlMapper.queryUseStatus(taskDtlId);
        AssertExt.isTrue(usestatus == 2,"细单为正式数据");

        int dtlRS = ckCarTaskDtlMapper.deleteTaskDtlById(taskDtlId);
        int boxRS = iCkCarTaskBoxService.deleteTaskBoxByTaskDtlId(taskDtlId);
        int reduceDtlLinesRS = iCkCarTaskDocService.reduceDtlLines(taskId);

        AssertExt.isTrue(dtlRS > 0 && boxRS >=0 && reduceDtlLinesRS >0,"删除失败");
        rsMap.put("delDtlCount",dtlRS);
        rsMap.put("delBoxCount",boxRS);
        rsMap.put("reduceDtlLinesRS",reduceDtlLinesRS);
        return rsMap;
    }

    @Override
    public int queryUseStatus(Long taskDtlId) {
        return ckCarTaskDtlMapper.queryUseStatus(taskDtlId);
    }

    @Override
    public Long getTaskByCustomId(Long currentCustomId) {

        return ckCarTaskDtlMapper.getTaskByCustomId(currentCustomId);
    }

    @Override
    public List<CkCarTaskDtlVo> getCustomList(Long scheduleId) {
        AssertExt.hasId(scheduleId,"出车调度单ID不存在");
        //List<CkCarTaskDtlVo> customList = ckCarTaskDtlMapper.getCustomList(scheduleId);
        //AssertExt.notEmpty(customList,"客户集为空");
        return ckCarTaskDtlMapper.getCustomList(scheduleId);
    }

    @Override
    public List<CkCarTaskDtlVo> searchCustom(Long scheduleId, String searchStr) {
        AssertExt.notBlank(searchStr,"搜索内容为空");
        AssertExt.hasId(scheduleId,"出车调度单ID不存在");
        String trimSearchStr = searchStr.trim();
        List<CkCarTaskDtlVo> dtlVoList = null;
        if (isStrOrNum(trimSearchStr)) {
            Long searchCustomId = Long.parseLong(trimSearchStr);
            dtlVoList = ckCarTaskDtlMapper.searchCustom(scheduleId,searchCustomId,"");
        }else{
            dtlVoList = ckCarTaskDtlMapper.searchCustom(scheduleId,-1L,trimSearchStr);
        }
        return dtlVoList;
    }

    /**
     * 判断当前字符串是否可以转换为数字
     * @param str
     * @return
     */
    public boolean isStrOrNum(String str){
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public CkCarTaskDtlVo getDtlByCustomId(Long taskId, Long currentCustomId) {
        return ckCarTaskDtlMapper.getDtlByCustomId(taskId, currentCustomId);
    }

}

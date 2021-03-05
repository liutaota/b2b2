package com.zsyc.zt.inca.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.dto.CkCarTaskBoxDto;
import com.zsyc.zt.inca.dto.CkCarTaskDtlDto;
import com.zsyc.zt.inca.entity.CkCarTaskDoc;
import com.zsyc.zt.inca.mapper.CkCarTaskDocMapper;
import com.zsyc.zt.inca.vo.CkCarTaskDocVo;
import com.zsyc.zt.inca.vo.CkCarTaskDtlVo;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Transactional
public class CkCarTaskDocServiceImpl extends ServiceImpl<CkCarTaskDocMapper, CkCarTaskDoc> implements ICkCarTaskDocService {

    @Autowired
    private CkCarTaskDocMapper ckCarTaskDocMapper;

    @Autowired
    private ICkCarTaskDtlService iCkCarTaskDtlService;

    @Autowired
    private ICkCarTaskBoxService iCkCarTaskBoxService;

    @ApiOperation(value =  "根据出车调度ID查询任务总单及细单信息")
    @Override
    public CkCarTaskDocVo getCarTask(Long scheduleId) {
        AssertExt.hasId(scheduleId,"参数为空!");
        // 非当天出车调度单不允许分配任务
        CkCarTaskDocVo data = ckCarTaskDocMapper.getTaskByScheduleId(scheduleId);
        CkCarTaskDocVo dataNew = new CkCarTaskDocVo();
        if(data==null){
            // 生成新任务
            CkCarTaskDoc ck = createTaskDoc(scheduleId);
            ckCarTaskDocMapper.insert(ck);
            BeanUtils.copyProperties(ck,dataNew);
            return dataNew;
        }
        // 查询总单下所有细单
        List<CkCarTaskDtlVo> ckCarTaskDtls = iCkCarTaskDtlService.getCarTaskDtlList(data.getTaskId());
        for (int i = 0; i < ckCarTaskDtls.size(); i++) {
            String sales = new String("");
            for (int j = 0; j < ckCarTaskDtls.get(i).getCkCarTaskBoxList().size(); j++) {
                sales=ckCarTaskDtls.get(i).getCkCarTaskBoxList().get(j).getSalesIdList();
                if (j+1 == ckCarTaskDtls.get(i).getCkCarTaskBoxList().size()){
                    break;
                }
                sales = sales+",";
            }
            ckCarTaskDtls.get(i).setSalesList(sales);
        }
        BeanUtils.copyProperties(data,dataNew);
        dataNew.setCkCarTaskDtlList(ckCarTaskDtls);
        return dataNew;
    }

    /**
     * 公共方法：根据出车调度ID生成新的任务对象
     * @param scheduleId
     * @return
     */
    public CkCarTaskDoc createTaskDoc(Long scheduleId){
        CkCarTaskDoc doc = new CkCarTaskDoc();
        //doc.setInputmanId(UserUtils.getUserId());
        doc.setInputmanId(16L);
        doc.setScheduleId(scheduleId);
        doc.setCredate(LocalDateTime.now());
        doc.setUsestatus(1);
        doc.setTaskDate(LocalDate.now());
        return doc;
    }

    @ApiOperation(value =  "保存任务数据")
    @Override
    @Transactional
    public Map<String,String> saveCarTask(List<CkCarTaskDtlDto> dtlList) {

        Map<String, String> resultMap = new HashMap<String ,String>();
        AssertExt.hasId(dtlList.get(0).getTaskId(),"任务不存在！");

        int result = ckCarTaskDocMapper.queryTaskCount(dtlList.get(0).getTaskId());

//        QueryWrapper<CkCarTaskDoc> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(CkCarTaskDoc::getTaskId,dtlList.get(0).getTaskId());
//        int count1 = this.count(queryWrapper);

        AssertExt.isTrue(result == 1,"任务不存在");
        // 任务总单存在
        // 查询细单及装箱信息是否已录入
        for (int i = 0; i < dtlList.size(); i++) {
            CkCarTaskDtlDto dDto = dtlList.get(i);
            int count = iCkCarTaskDtlService.getDtlCount(dDto);
            List<CkCarTaskBoxDto> boxList = dDto.getCkCarTaskBoxList();

            // 箱号信息为空，不允许上传数据
            AssertExt.notEmpty(boxList,"存在箱号记录为空的客户，请求继续录入或删除该客户记录!");

            if (count != 1) {  // 细单不存在
                String packId = "";
                for (int k = 0; k < boxList.size(); k++) {
                    packId = packId.concat(","+boxList.get(k).getPackId());
                }
                resultMap.put(dDto.getCustomName(),packId);
                continue;
            }

             // 查询细单中箱号是否都已录入
            String packId = "";
            for (int j = 0; j < boxList.size(); j++) {
                CkCarTaskBoxDto boxDto = boxList.get(j);
                int boxCount = iCkCarTaskBoxService.getBoxCount(boxDto);
                if (boxCount == 1){
                   continue;
                }
                packId = packId.concat(boxDto.getPackId());
                resultMap.put(dDto.getCustomName(),packId);
            }

            // 查询应录入箱号 与 已有箱号对比，是否存在还未录入箱号
            Long tackId = dDto.getTaskId();
            Long customId = dDto.getCustomId();
            List<Long> packIdList = iCkCarTaskBoxService.selectNotInputPackId(tackId,customId);

             AssertExt.isTrue(packIdList.size() > 0,"上传失败，还有未录入箱号!未录完单号:"+packIdList.toString());
        }

        if (resultMap.size() == 0){
            // 更新任务状态（总单及细单）,确认人、确认时间
            //Long confirmManId = UserUtils.getUserId();
            LocalDate confirmDate = LocalDate.now();
            int docStatus = ckCarTaskDocMapper.updateTaskStatus(dtlList.get(0).getTaskId(),16L,confirmDate);
            int dtlStatus = iCkCarTaskDtlService.updateDtlStatus(dtlList.get(0).getTaskId());
            AssertExt.isTrue(docStatus < 1 || dtlStatus < 1,"数据更新失败！");
        }else{
            resultMap.put("failedMsg","部分箱号尚未录入,不在录入!");
        }
        return resultMap;
    }

    /**
     * 更新细单数量: 增加1
     * @param taskId
     * @return
     */
    @Override
    public int updateDtlLines(Long taskId) {
        return ckCarTaskDocMapper.updateDtlLines(taskId);
    }

    /**
     * 更新细单数量: 减少1
     * @param taskId
     * @return
     */
    @Override
    public int reduceDtlLines(Long taskId) {
        return ckCarTaskDocMapper.updateReduceDtlLines(taskId);
    }

    @Override
    public Integer getUseStatusById(Long taskId) {
        return ckCarTaskDocMapper.getUseStatusById(taskId);
    }




}

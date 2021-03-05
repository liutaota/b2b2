package com.zsyc.zt.inca.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.commom.TaskErrorEnum;
import com.zsyc.zt.inca.dto.CkCarTaskBoxDto;
import com.zsyc.zt.inca.entity.CkCarTaskBox;
import com.zsyc.zt.inca.entity.CkCarTaskDoc;
import com.zsyc.zt.inca.entity.CkCarTaskDtl;
import com.zsyc.zt.inca.mapper.CkCarTaskBoxMapper;
import com.zsyc.zt.inca.vo.CkCarTaskBoxVo;
import com.zsyc.zt.inca.vo.CkCarTaskDtlVo;
import com.zsyc.zt.inca.vo.NwResIVo;
import com.zsyc.zt.inca.vo.others.CarTaskBmsDocVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peiqy
 * @since 2020-12-07
 */
@Primary
@Service
@Slf4j
public class CkCarTaskBoxServiceImpl extends ServiceImpl<CkCarTaskBoxMapper, CkCarTaskBox> implements ICkCarTaskBoxService {

    @Autowired
    private CkCarTaskBoxMapper ckCarTaskBoxMapper;

    @Autowired
    private ICkCarTaskDtlService iCkCarTaskDtlService;

    @Autowired
    private INwResIService iNwResIService;

    @Autowired
    private ICkCarTaskDocService iCkCarTaskDocService;

    /**
     * 根据任务创建一个新的Dtl对象
     * @param taskId
     * @return
     */
    public void createDtlAndBox(Long taskId, NwResIVo custom, CkCarTaskDtl dtl, CkCarTaskBox box){
        // 查询总单信息
        CkCarTaskDoc doc = iCkCarTaskDocService.getById(taskId);
        if (dtl != null){
            BeanUtils.copyProperties(doc,dtl);
            BeanUtils.copyProperties(custom,dtl);
        }
        if (box != null){
            BeanUtils.copyProperties(doc,box);
            BeanUtils.copyProperties(custom,box);
            box.setCreateDate(LocalDateTime.now());
        }

    }

    /**
     * 录入客户信息、箱号、订单信息业务
     * @param taskId
     * @param packId
     * @param currentCustomId
     * @return
     */
    @Override
    public Map<String, Object> getCustomAndPackId(Long taskId, String packId, Long currentCustomId) {

        AssertExt.hasId(taskId,"任务ID不存在");
        AssertExt.hasId(currentCustomId,"当前客户ID不存在");

        Map<String,Object> resultMap = new HashMap<>();
        // 正式任务单不允许录入信息
        Integer useStatus = iCkCarTaskDocService.getUseStatusById(taskId);
        if (useStatus == null || useStatus == 2){
            resultMap.put("advices","no");
            resultMap.put("existsPack",false);
            resultMap.put("nextCustomId",currentCustomId);
            resultMap.put("errorCode", TaskErrorEnum.FAIL_INPUT_TASK_NOT_EXISTS.getCode());
            resultMap.put("errorMsg",TaskErrorEnum.FAIL_INPUT_TASK_NOT_EXISTS.getMsg());
            return resultMap;
        }

        Long nextCustomId;
        // 1.查询当前 箱号信息 及 客户信息
        NwResIVo custom = iNwResIService.getCustom(packId);
        if (custom == null) {
            resultMap.put("advices","no");
            resultMap.put("existsPack",false);
            resultMap.put("nextCustomId",currentCustomId);
            resultMap.put("errorCode", TaskErrorEnum.FAIL_INPUT_PACK_NOT_EXISTS.getCode());
            resultMap.put("errorMsg","箱号："+packId+",信息不存在！");
            return resultMap;
        }

        // 首次录入客户、箱号、订单信息
        if (currentCustomId <= 0){
            currentCustomId = custom.getCustomId();
        }

        // 查询剩余未录入箱号
        List<String> packIdList = iNwResIService.querySurplusPackId(currentCustomId);
        String salesList = "";

        // 查询当日是否有其它任务单录入该箱号信息
        Long otherTaskId = iCkCarTaskDtlService.getTaskByCustomId(currentCustomId);
        if (ObjectUtil.isNotNull(otherTaskId) && taskId.longValue() != otherTaskId.longValue()){
            resultMap.put("advices","no");
            resultMap.put("existsPack",true);
            resultMap.put("surplusPackIds",packIdList.toString());
            resultMap.put("nextCustomId",currentCustomId);
            resultMap.put("ckCarTaskDtlVo",null);
            resultMap.put("errorCode", TaskErrorEnum.FAIL_INPUT_CUSTOMER_EXISTS.getCode());
            resultMap.put("errorMsg","客户:"+custom.getCustomId()+"，客户名称："+custom.getCustomName()+",已被其它出车任务录入，请勿重复录入!");
            return resultMap;
        }

        // 判断当前用户 箱号是否录入完毕
        if(packIdList != null && custom.getCustomId().longValue()  != currentCustomId.longValue()){   // 选择切换用户,null
            if (packIdList.size() > 0){
                CkCarTaskDtlVo vo = iCkCarTaskDtlService.getDtlByCustomId(taskId,currentCustomId);
                // 返回提示消息、及剩余未录入箱号列表
                resultMap.put("advices","no");
                resultMap.put("existsPack",true);
                resultMap.put("surplusPackIds",packIdList.toString());
                resultMap.put("nextCustomId",custom.getCustomId());
                resultMap.put("ckCarTaskDtlVo",null);
                resultMap.put("dsc","下一个客户ID："+custom.getCustomId()+", 客户名称："+custom.getCustomName());
                resultMap.put("errorCode", TaskErrorEnum.FAIL_INPUT_CUSTOMER_REPLACE.getCode());
                resultMap.put("errorMsg","客户ID："+ currentCustomId
                        + "，客户名称："+vo.getCustomName()+"，未录入完所有箱号！是否录入下一个客户？");
                return resultMap;
            }
        }

        // 查询对应箱号的任务、细单是否存在
        CkCarTaskDtlVo dtlVo = iCkCarTaskDtlService.getCustomByPackId(taskId,custom.getCustomId());
        boolean result = false;
        // 需要录入的客户下是否存在还没录入箱号信息
        if (packIdList.size() > 0){
            resultMap.put("existsPack",true);
        }else{
            resultMap.put("existsPack",false);
        }

        // 3.判断细单是否存在
        if (dtlVo != null ){

            List<CkCarTaskBoxVo> boxList = dtlVo.getCkCarTaskBoxList();
            for (int i=0;i<boxList.size();i++){
                if (ObjectUtil.isNotNull(boxList.get(i).getSalesIdList())){
                    salesList=salesList+boxList.get(i).getSalesIdList()+",";
                }
                if (boxList.get(i).getPackId().equals(packId)) {
                    result = true;
                    break;
                }
                if (boxList.size() == i+1){
                    break;
                }
                salesList = salesList+",";
            }
            // 细单存在，箱号已录入，直接返回结果集
            if (result){
                dtlVo.setSalesList(salesList);
                resultMap.put("advices","no");
                resultMap.put("surplusPackIds",packIdList.toString());
                resultMap.put("nextCustomId",custom.getCustomId());
                resultMap.put("ckCarTaskDtlVo",dtlVo);
                resultMap.put("errorCode", TaskErrorEnum.FAIL_INPUT_PACK_EXISTS.getCode());
                resultMap.put("errorMsg","箱号:"+packId+"，已录入系统，请勿重复录入！");
                return resultMap;
            }
            // 细单存在，箱号未录入，根据细单录入箱号信息
            CkCarTaskBox box = new CkCarTaskBox();
            this.createDtlAndBox(taskId,custom,null,box);
            BeanUtils.copyProperties(dtlVo,box);
            ckCarTaskBoxMapper.insert(box);

            CkCarTaskBoxVo boxVo = new CkCarTaskBoxVo();
            BeanUtils.copyProperties(box,boxVo);
            dtlVo.getCkCarTaskBoxList().add(boxVo);
            dtlVo.setSalesList(salesList);

            resultMap.put("advices","yes");
            resultMap.put("surplusPackIds",packIdList.toString());
            resultMap.put("nextCustomId",dtlVo.getCustomId());
            resultMap.put("ckCarTaskDtlVo",dtlVo);
            return resultMap;
        }

        // 2.不存在对应细单，生产细单保存箱号信息
        dtlVo = new CkCarTaskDtlVo();
        dtlVo.setCkCarTaskBoxList(new ArrayList<>());
        CkCarTaskDtl dtl = new CkCarTaskDtl();
        CkCarTaskBox box = new CkCarTaskBox();
        CkCarTaskBoxVo boxVo = new CkCarTaskBoxVo();

        this.createDtlAndBox(taskId,custom,dtl,box);
        boolean save = iCkCarTaskDtlService.save(dtl);
        iCkCarTaskDocService.updateDtlLines(taskId); // 更新细单数量
        box.setTaskDtlId(dtl.getTaskDtlId());
        box.setTaskId(dtl.getTaskId());
        int insert = ckCarTaskBoxMapper.insert(box);

        // 查询细单下所有销售单集合
        List<String> salesId = ckCarTaskBoxMapper.querySalesListByDtlId(dtl.getTaskDtlId());
        for (int i = 0; i < salesId.size(); i++) {
            salesList=salesId.get(i);
            if (salesId.size()==i+1){
                break;
            }
            salesList = salesList+",";
        }

        AssertExt.isTrue(save,"保存细单信息失败!");
        AssertExt.isTrue(insert >=1,"保存箱号信息失败!");
        BeanUtils.copyProperties(dtl,dtlVo);
        BeanUtils.copyProperties(box,boxVo);
        dtlVo.getCkCarTaskBoxList().add(boxVo);
        dtlVo.setSalesList(salesList);

        resultMap.put("advices","yes");
        resultMap.put("nextCustomId",dtlVo.getCustomId());
        resultMap.put("surplusPackIds",packIdList.toString());
        resultMap.put("ckCarTaskDtlVo",dtlVo);

        return resultMap;
    }

    /**
     * 查询箱号是否录入
     * @param boxDto
     * @return
     */
    @Override
    public int getBoxCount(CkCarTaskBoxDto boxDto) {
        return ckCarTaskBoxMapper.getBoxCount(boxDto);
    }

    /**
     * 删除细单下所有箱号信息
     * @param taskDtlId
     * @return
     */
    @Override
    public int deleteTaskBoxByTaskDtlId(Long taskDtlId) {

        return ckCarTaskBoxMapper.deleteTaskBoxByTaskDtlId(taskDtlId);
    }

    /**
     * 删除：根据箱号信息ID删除箱号信息
     * @param taskDtlId
     * @param taskBoxId
     * @return
     */
    @Override
    public Map<String, Integer> deleteTaskBoxByTaskBoxId(Long taskDtlId, Long taskBoxId) {
        AssertExt.hasId(taskDtlId,"任务细单ID不存在");
        AssertExt.hasId(taskBoxId,"任务箱号信息ID不存在");
        Map<String, Integer> rsMap = new HashMap<>();
        int useStatus = iCkCarTaskDtlService.queryUseStatus(taskDtlId);
        AssertExt.isTrue( useStatus == 2,"正式数据不允许删除");
        int boxRS = ckCarTaskBoxMapper.deleteTaskBoxByTaskBoxId(taskDtlId,taskBoxId);
        rsMap.put("delPackCount",boxRS);
        return rsMap;
    }

    /**
     * 查询运输任务客户所有订单、金额、箱号
     * @param customId
     * @return
     */
    @Override
    public Map<String, Object> getSalesAndReceiptList(Long taskDtlId, Long customId) {

        AssertExt.hasId(taskDtlId,"细单ID不存在");
        AssertExt.hasId(taskDtlId,"客户ID不存在");

        Map<String, Object> rsMap = new HashMap<>();
        // 查询当前客户细单下所有箱号、订单信息
        List<CkCarTaskBoxVo> boxList = ckCarTaskBoxMapper.getCkCarTaskBoxByTaskDtlId(taskDtlId);
        AssertExt.isTrue(ObjectUtil.isNotNull(boxList) && ObjectUtil.isNotEmpty(boxList),"查询失败，箱号信息不存在!");
        List<Long> salesIdList = this.convertSales(boxList);

        // 客户ID及销售单查询 收款(货物)交接凭证信息
        List<CarTaskBmsDocVo> rsList = ckCarTaskBoxMapper.getSalesAndReceiptList(customId,salesIdList);
        AssertExt.isTrue(ObjectUtil.isNotNull(rsList),"查询失败，销售单收款交接信息不存在!");
        // 计算总价
        BigDecimal total = this.customTotalMoney(rsList);
        rsMap.put("total",total);
        rsMap.put("salesList",rsList);
        rsMap.put("queryMsg","查询成功!");
        return rsMap;
    }

    /**
     * 把箱号中订单字符串转换为Long类型List
     * @param boxList
     * @return
     */
    public List<Long> convertSales(List<CkCarTaskBoxVo> boxList){

        List<Long> salesIdList = new ArrayList<>();
        for (CkCarTaskBoxVo box:boxList) {
            String[] splitArr = box.getSalesIdList().split(",");
            for (String salesStr : splitArr) {
                long salesLong = Long.parseLong(salesStr);
                if (!salesIdList.contains(salesLong)){
                    salesIdList.add(salesLong);
                }
            }
        }

        return salesIdList;
    }

    /**
     * 计算出车任务单个车辆所有订单货物金额
     * @param list
     * @return
     */
    public BigDecimal customTotalMoney(List<CarTaskBmsDocVo> list){

        BigDecimal total = new BigDecimal(0);

        for (CarTaskBmsDocVo vo: list) {
            total = total.add(vo.getDtlCost());
        }

        return total;
    }

    /**
     * 查询任务下未录入箱号
     * @param tackId
     * @param customId
     * @return
     */
    @Override
    public List<Long> selectNotInputPackId(Long tackId, Long customId) {
        return ckCarTaskBoxMapper.selectNotInputPackId(tackId,customId);
    }
}

package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.entity.PdaVersionUpdate;
import com.zsyc.zt.inca.mapper.PdaVersionUpdateMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.inca.vo.PdaVersionUpdateVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP
 * @since 2021-01-13
 */
@Primary
@Service
@Slf4j
public class PdaVersionUpdateServiceImpl extends ServiceImpl<PdaVersionUpdateMapper, PdaVersionUpdate> implements IPdaVersionUpdateService {

    @Autowired
    private PdaVersionUpdateMapper pdaVersionUpdateMapper;

    @Override
    public IPage<PdaVersionUpdateVo> getPdaVersionDesList(Page<PdaVersionUpdateVo> page, PdaVersionUpdateVo pdaVersionUpdateVo) {
        AssertExt.notNull(page, "页码不为空");
        IPage<PdaVersionUpdateVo> pdaVersionDesList = pdaVersionUpdateMapper.getPdaVersionDesList(page, pdaVersionUpdateVo);
        log.info("page size {}", pdaVersionDesList.getSize());
        return pdaVersionDesList;
    }

    @Override
    public Map<String, Object> saveVersionDes(PdaVersionUpdateVo pdaVersionUpdateVo) {

        Map<String, Object> rsMap = new HashMap<>();
        AssertExt.isTrue(pdaVersionUpdateVo.getVersion() != null && !"".equals(pdaVersionUpdateVo.getVersion()),"版本号为空");
        AssertExt.isTrue(pdaVersionUpdateVo.getFilePath() != null && !"".equals(pdaVersionUpdateVo.getFilePath()),"存储路径为空");

        PdaVersionUpdate pdaVersion = new PdaVersionUpdate();
        pdaVersion.setVersion(pdaVersionUpdateVo.getVersion());
        pdaVersion.setFilePath(pdaVersionUpdateVo.getFilePath());
        pdaVersion.setRemark(pdaVersionUpdateVo.getRemark());
        pdaVersion.setCreateTime(LocalDateTime.now());
        pdaVersion.setCreateUserId(pdaVersionUpdateVo.getCreateUserId());
        pdaVersion.setIsDel(0L);
        int insert = pdaVersionUpdateMapper.insert(pdaVersion);
        if(insert > 0){
            rsMap.put("result","success");
        }else{
            rsMap.put("result","failed");
        }
        return rsMap;
    }

    @Override
    public Map<String, String> getPdaVersion(String pdaUploadPath) {
        Map<String, String> rsMap = new HashMap<>(3);
        PdaVersionUpdate newestVersion = pdaVersionUpdateMapper.getPdaNewestVersion();
        AssertExt.notNull(newestVersion,"最新版本信息为空");

        String filePath = newestVersion.getFilePath();
        int length = pdaUploadPath.length();
        String newestFile = filePath.substring(length);

        rsMap.put("newestVersion",newestVersion.getVersion());
        rsMap.put("newestFile",newestFile);
        rsMap.put("newestRemark",newestVersion.getRemark());
        return rsMap;
    }

    @Override
    public Map<String, String> updateRemark(Long id, String newRemark, Long updateUserId) {
        AssertExt.hasId(id,"id为空");
        AssertExt.hasId(updateUserId,"当前用户ID为空");
        AssertExt.isTrue(newRemark != null && !"".equals(newRemark),"备注内容为空");

        Map<String, String> rsMap = new HashMap<>();
        PdaVersionUpdate pdaVersionUpdate = new PdaVersionUpdate();
        pdaVersionUpdate.setRemark(newRemark);
        pdaVersionUpdate.setUpdateUserId(updateUserId);
        pdaVersionUpdate.setUpdateTime(LocalDateTime.now());
        int rs = pdaVersionUpdateMapper.update(pdaVersionUpdate, new QueryWrapper<PdaVersionUpdate>().eq("id", id));
        if (rs > 0){
            rsMap.put("result","success");
        }else {
            rsMap.put("result","failed");
        }
        return rsMap;
    }
}

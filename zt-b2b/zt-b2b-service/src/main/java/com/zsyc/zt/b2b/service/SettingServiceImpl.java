package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Setting;
import com.zsyc.zt.b2b.mapper.SettingMapper;
import com.zsyc.zt.b2b.vo.FloorVo;
import com.zsyc.zt.b2b.vo.SettingVo;
import com.zsyc.zt.b2b.vo.WebPageVo;
import com.zsyc.zt.fs.service.B2BFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SettingServiceImpl implements SettingService {
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private B2BFileService fileService;

    @Override
    public void addSetting(SettingVo settingVo) {
        AssertExt.notNull(settingVo.getType(), "请选择类型");
        AssertExt.checkEnum(Setting.EType.class, settingVo.getType(), "类型不匹配");
        if (settingVo.getType().equals(Setting.EType.SYSTEM.val())) {
            AssertExt.notNull(settingVo.getName(), "名称为空");
            AssertExt.notNull(settingVo.getValue(), "值为空");
            AssertExt.notNull(settingVo.getKey(), "key为空");
            Setting settingDB = this.settingMapper.selectOne(new QueryWrapper<Setting>().eq("key", settingVo.getKey()));
            AssertExt.isTrue(settingDB == null, "key[%s]已存在", settingVo.getKey());
            List<Setting> settingList = this.settingMapper.selectList(new QueryWrapper<>());
            List<Integer> sortList = settingList.stream().map(Setting::getSort).collect(Collectors.toList());
            if (sortList.size() < 1) {
                settingVo.setSort(1);
            } else {
                settingVo.setSort(Collections.max(sortList) + 1);
            }
            settingVo.setCreateTime(LocalDateTime.now());
            this.settingMapper.insert(settingVo);
        } else {
            AssertExt.notNull(settingVo.getValue(), "值为空");
            AssertExt.notNull(settingVo.getKey(), "key为空");
            Setting settingDB = this.settingMapper.selectOne(new QueryWrapper<Setting>().eq("key", settingVo.getKey()));
            AssertExt.isTrue(settingDB == null, "key[%s]已存在", settingVo.getKey());
            List<Setting> settingList = this.settingMapper.selectList(new QueryWrapper<>());
            List<Integer> sortList = settingList.stream().map(Setting::getSort).collect(Collectors.toList());
            if (sortList.size() < 1) {
                settingVo.setSort(1);
            } else {
                settingVo.setSort(Collections.max(sortList) + 1);
            }
            settingVo.setCreateTime(LocalDateTime.now());
            this.settingMapper.insert(settingVo);
        }
    }

    @Override
    public void updateSetting(SettingVo settingVo) {
        AssertExt.hasId(settingVo.getId(), "id为空");

        Setting settingDB = this.settingMapper.selectById(settingVo.getId());
        AssertExt.notNull(settingDB, "无效[%s]id", settingVo.getId());
        settingDB.setUpdateTime(LocalDateTime.now());
        if (settingDB.getType().equals(Setting.EType.SYSTEM.val())) {
            settingDB.setName(settingVo.getName());
            settingDB.setValue(settingVo.getValue());
            settingDB.setRemark(settingVo.getRemark());
            this.settingMapper.updateById(settingDB);

            if (null != settingVo.getVersion()) {
                if (settingDB.getKey().equals("APP_LINK")) {
                    Setting setting1 = this.settingMapper.selectOne(new QueryWrapper<Setting>().eq("key", "APP_VERSION"));
                    setting1.setValue(settingVo.getVersion());
                    this.settingMapper.updateById(setting1);
                }
            }


        } else {
          /*  if (settingVo.getIsImg()){
                String[] imgList = settingVo.getValue().split(",");
                String img = this.fileService.coverImageById(settingVo.getId(), Constant.IMAGE_PREFIX.STORE,imgList);
                settingDB.setValue(img);
            }else {
                settingDB.setValue(settingVo.getValue());
            }*/
            settingDB.setValue(settingVo.getValue());
            //settingDB.setKey(settingVo.getKey());
            settingDB.setRemark(settingVo.getRemark());
            this.settingMapper.updateById(settingDB);
        }
    }

    @Override
    public IPage<Setting> getSettingList(Page page, Setting setting) {
        return this.settingMapper.getSettingList(page, setting);
    }

    @Override
    public void updateSettingSort(SettingVo settingVo) {
        AssertExt.hasId(settingVo.getId(), "参数设置id无效");
        AssertExt.notBlank(settingVo.getSortType(), "排序类型为空");
        AssertExt.checkEnum(FloorVo.ESortType.class, settingVo.getSortType(), "排序类型不匹配");
        Setting setting = this.settingMapper.selectOne(new QueryWrapper<Setting>().eq("id", settingVo.getId()));
        List<Setting> webPageList = this.settingMapper.selectList(new QueryWrapper<>());
        List<Integer> sortList = webPageList.stream().map(Setting::getSort).collect(Collectors.toList());
        if (WebPageVo.ESortType.TOP.val().equals(settingVo.getSortType())) {
            AssertExt.isFalse(setting.getSort() >= Collections.max(sortList), "此信息已在顶部");
            setting.setSort(Collections.max(sortList) + 1);
            this.settingMapper.updateById(setting);
        } else {
            AssertExt.isFalse(setting.getSort() <= Collections.min(sortList), "此信息已在底部");
            setting.setSort(Collections.min(sortList) - 1);
            this.settingMapper.updateById(setting);
        }
    }

    @Override
    public void settingSort(Long settingIdPrev, Long settingIdNext) {
        AssertExt.hasId(settingIdPrev, "settingIdPrev无效");
        AssertExt.hasId(settingIdNext, "settingIdNext无效");
        Setting setting = this.settingMapper.selectById(settingIdPrev);
        Setting setting1 = this.settingMapper.selectById(settingIdNext);
        Integer sort = 0;
        sort = setting.getSort();
        setting.setSort(setting1.getSort());
        setting1.setSort(sort);
        this.settingMapper.updateById(setting);
        this.settingMapper.updateById(setting1);
    }

    public Long getOrderCancelTime() {
        Setting setting = this.getSetting("ORDER_TO_ERP");
        if (setting == null) return 10L;
        return Long.valueOf(setting.getValue());
    }

    @Override
    public Long getOrderScore(String keys) {
        Setting setting = this.getSetting(keys);
        if (setting == null) return 0L;
        return Long.valueOf(setting.getValue());
    }

    private Setting getSetting(String key) {
        return this.settingMapper.selectOne(new QueryWrapper<Setting>().eq("key", key));
    }

    @Override
    public List<Setting> getPcSetting(String keys) throws AccessDeniedException {

        if (keys.equals("b2b")) {
            Setting setting = this.genPCMINTime("PC_STATUS");
            if (setting.getValue().equals("0")) {
                throw new AccessDeniedException("b2b no OAuth2Authentication");
            }
        } else if (keys.equals("wechat")) {
            Setting setting = this.genPCMINTime("MIN_STATUS");
            if (setting.getValue().equals("0")) {
                throw new AccessDeniedException("wechat no OAuth2Authentication");
            }
        }

        return this.settingMapper.selectList(new QueryWrapper<Setting>().eq("TYPE", "STORE").gt("ID", 29).lt("ID", 50));
    }

    @Override
    public String getOneOrderPay(String keys) {
        Setting setting = this.getSetting(keys);
        if (setting == null) return null;
        return setting.getValue();
    }

    @Override
    public Setting getAPPVersion(String version) {
        AssertExt.notNull(version, "版本号为空");
        Setting setting = this.settingMapper.selectOne(new QueryWrapper<Setting>().eq("key", "APP_VERSION"));
        if (!version.equals(setting.getValue())) {
           /* setting.setValue(version);
            this.settingMapper.updateById(setting);*/
            Setting settingLink = this.settingMapper.selectOne(new QueryWrapper<Setting>().eq("key", "APP_LINK"));
            return settingLink;
        }
        return null;
    }

    @Override
    public Setting genPCMINTime(String keys) {
        AssertExt.notNull(keys, "keys为空");
        Setting setting = this.settingMapper.selectOne(new QueryWrapper<Setting>().eq("key", keys));
        return setting;
    }
}

package com.zsyc.zt.b2b.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Setting;
import com.zsyc.zt.b2b.vo.SettingVo;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface SettingService {

    /**
     * 添加设置
     *
     * @param settingVo
     */
    void addSetting(SettingVo settingVo);

    /**
     * 修改设置
     *
     * @param settingVo
     */
    void updateSetting(SettingVo settingVo);

    /**
     * 设置列表
     *
     * @param page
     * @param setting
     * @return
     */
    IPage<Setting> getSettingList(Page page, Setting setting);

    /**
     * 参数设置置顶/置底排序
     *
     * @param settingVo
     */
    void updateSettingSort(SettingVo settingVo);

    /**
     * 参数设置上下排序
     *
     * @param settingIdPrev
     * @param settingIdNext
     */
    void settingSort(Long settingIdPrev, Long settingIdNext);

    /**
     * 获取系统设置订单可取消时间
     *
     * @return
     */
    Long getOrderCancelTime();


    /**
     * 获取系统设置订单赠送积分/自动确认收货时间
     *
     * @return
     */
    Long getOrderScore(String keys);

    /**
     * 商城设置
     *
     * @return
     */
    List<Setting> getPcSetting(String keys) throws AccessDeniedException;

    /**
     * 获取首次下单排除的商品
     *
     * @param keys
     * @return
     */
    @Cached(name = "SettingService-getOneOrderPay-", key = "#keys", expire = 10)
    String getOneOrderPay(String keys);

    /**
     * app版本号
     *
     * @param version
     * @return
     */
    Setting getAPPVersion(String version);

    /**
     * 系统预计启动时间
     */
    Setting genPCMINTime(String keys);
}

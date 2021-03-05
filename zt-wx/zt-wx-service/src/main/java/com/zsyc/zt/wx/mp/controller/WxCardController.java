package com.zsyc.zt.wx.mp.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.zsyc.framework.util.R;
import com.zsyc.zt.wx.mp.config.WxMpProperties;
import com.zsyc.zt.wx.mp.vo.MemberCardVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.card.WxMpCardCreateResult;
import me.chanjar.weixin.mp.bean.card.membercard.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Edward
 */
@RestController
@RequestMapping("/wx/card/{agentId}")
@Slf4j
public class WxCardController implements InitializingBean {
    @Autowired
    WxMpService wxService;
    @Autowired
    WxMpProperties properties;

    Map<String, WxMpProperties.MpConfig> mpConfigMap;


    public WxMpProperties.MpConfig beforeRequest(String agentId){
        WxMpProperties.MpConfig mpConfig = mpConfigMap.get(agentId);

        String appId = mpConfig.getAppId();

        if (!this.wxService.switchover(appId)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appId));
        }
        return mpConfig;
    }

    @PostMapping("/create")
    public String greetUser(@PathVariable String agentId, @RequestParam(required = false) String code, @RequestParam(required = false) String createMemberCardJson, ModelMap map) {

        WxMpProperties.MpConfig mpConfig = beforeRequest(agentId);
        try {

            WxMpCardCreateResult wxMpCardCreateResult = wxService.getMemberCardService().createMemberCard(createMemberCardJson);


          /*  WxMpUser user = wxService.getOAuth2Service().getUserInfo(accessToken, null);
            map.put("user", user);*/
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return "greet_user";
    }

    @GetMapping("/get")
    public R<MemberCardVo> get(@PathVariable String agentId, @RequestParam(required = true) String code) {
        WxMpProperties.MpConfig mpConfig = beforeRequest(agentId);
        String cardId = mpConfig.getMemberCardId();
        try {
            WxMpMemberCardUserInfoResult userInfo = wxService.getMemberCardService().getUserInfo(cardId, code);
            NameValues[] customFieldList = userInfo.getUserInfo().getCommonFieldList();
            MemberCardVo memberCardVo = new MemberCardVo();
            memberCardVo.setCode(code);
            memberCardVo.setCardId(cardId);

            Arrays.asList(customFieldList).forEach(item -> {
                if ("USER_FORM_INFO_FLAG_MOBILE".equals(item.getName())) {
                    memberCardVo.setMobile(item.getValue());
                }
                if ("USER_FORM_INFO_FLAG_SEX".equals(item.getName())) {

                    /**
                     * 男  女
                     */
                    memberCardVo.setSex(item.getValue());
                }
                if ("USER_FORM_INFO_FLAG_BIRTHDAY".equals(item.getName())) {
                    memberCardVo.setBirthday(LocalDateTimeUtil.parse(item.getValue(),"yyyy-M-d").toLocalDate());
                }
                if ("USER_FORM_INFO_FLAG_NAME".equals(item.getName())) {
                    memberCardVo.setName(item.getValue());
                }
            });
            return R.ok(memberCardVo, "获取数据成功");

        } catch (Exception e) {
            log.error("执行出错了！！", e);
            return R.failed("获取数据失败" + e.getMessage());
        }

    }

    @PostMapping("/updateBonus")
    public R<WxMpMemberCardUpdateResult> updateBonus(@PathVariable String agentId, @RequestParam String code, @RequestParam Integer bonus) {
        WxMpProperties.MpConfig mpConfig = beforeRequest(agentId);
        String cardId = mpConfig.getMemberCardId();
        try {
            WxMpMemberCardUpdateMessage wxMpMemberCardUpdateMessage = new WxMpMemberCardUpdateMessage();
            wxMpMemberCardUpdateMessage.setCardId(cardId);
            wxMpMemberCardUpdateMessage.setCode(code);
            wxMpMemberCardUpdateMessage.setBonus(bonus);

            WxMpMemberCardUpdateResult wxMpMemberCardUpdateResult = wxService.getMemberCardService().updateUserMemberCard(wxMpMemberCardUpdateMessage);

            return R.ok(wxMpMemberCardUpdateResult, "获取数据成功");
        } catch (Exception e) {
            log.error("执行出错了！！", e);
            return R.failed("获取数据失败" + e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.mpConfigMap = new HashMap<>();
        this.properties.getConfigs().stream().forEach(item->{
            mpConfigMap.put(item.getAgentId(),item);
        });
    }
}

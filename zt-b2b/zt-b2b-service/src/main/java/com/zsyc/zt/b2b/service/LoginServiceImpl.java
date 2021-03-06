package com.zsyc.zt.b2b.service;

import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.base.ZSYCOAuthException;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.framework.util.HttpUtil;
import com.zsyc.framework.util.LongConflate;
import com.zsyc.zt.b2b.common.AESCBCUtil;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.config.WechatAccessToken;
import com.zsyc.zt.b2b.config.WechatConfig;
import com.zsyc.zt.b2b.config.WechatConfigProperties;
import com.zsyc.zt.b2b.entity.CacheCustomerGoods;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.entity.Setting;
import com.zsyc.zt.b2b.entity.SmsLog;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.CustomerVo;
import com.zsyc.zt.b2b.vo.MemberLogVo;
import com.zsyc.zt.b2b.vo.ScanInfoVo;
import com.zsyc.zt.fs.service.AliyunFileService;
import com.zsyc.zt.inca.service.CustomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.apache.http.Consts;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.zsyc.zt.b2b.config.WechatConfig.WECHAT_API_HOST;

/**
 * Created by lcs on 2019-05-21.
 */
@Service
@Transactional
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AliyunService aliyunService;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberLogMapper memberLogMapper;
    @Autowired
    private WechatConfig wechatConfig;
    @Autowired
    private SmsLogMapper smsLogMapper;
    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private CustomService customService;
    @Autowired
    private CacheCustomerGoodsServiceImpl cacheCustomerGoodsService;
    @Autowired
    private CacheCustomerGoodsMapper cacheCustomerGoodsMapper;
    @Autowired
    private AliyunFileService aliyunFileService;
    @Autowired
    private WechatAccessToken wechatAccessToken;
    @Autowired
    private SettingMapper settingMapper;

    private final static String REDIS_PREFIX = "telephone:code:";
    private final static String REDIS_CAPTCHA_PREFIX = "telephone:captcha:";
    private static final String REDIS_SCAN_CODE = "scan:code:";
    @Value("${zsyc.zt.scan-code.expire:1800}")
    private Integer expire;

    @Override
    public String sendSmsCode(String telephone, String logType, String ip) {
        AssertExt.matches("1\\d{10}", telephone, "?????????????????????");
        String redisKey = REDIS_PREFIX + telephone;
        String code = this.stringRedisTemplate.opsForValue().get(redisKey);
        AssertExt.isTrue(code == null, "???????????????????????????");
        code = RandomStringUtils.random(6, false, true);

        this.aliyunService.sendSms(telephone, code);
        SmsLog smsLog = new SmsLog();
        Member memberDB = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("telephone", telephone));
        if (null != memberDB) {
            memberDB.setSendAcodeTimes(memberDB.getSendAcodeTimes() + 1);
            this.memberMapper.updateById(memberDB);
            smsLog.setMemberId(memberDB.getId());
            smsLog.setMemberName(memberDB.getUserName());
        }
        smsLog.setLogCaptcha(code);
        smsLog.setLogPhone(telephone);
        smsLog.setLogMsg(code);
        smsLog.setLogType(logType);
        smsLog.setLogIp(ip);
        smsLog.setCreateTime(LocalDateTime.now());
        this.smsLogMapper.insert(smsLog);

        this.stringRedisTemplate.opsForValue().set(redisKey, code, 1, TimeUnit.MINUTES);
        return code;
    }

    @Override
    public String genCaptchaCode(String codeKey) {
        AssertExt.notBlank(codeKey, "??????keyCode");
        AssertExt.matches("1\\d{10}", codeKey, "?????????????????????");
        String redisKey = String.format("%s:%s", REDIS_CAPTCHA_PREFIX, codeKey);
        String code = RandomStringUtils.random(4, false, true);
        this.stringRedisTemplate.opsForValue().set(redisKey, code, 1, TimeUnit.MINUTES);
        return code;
    }

    @Override
    public void validateCaptchaCode(String telephone, String code) {
        AssertExt.notBlank(telephone, "telephone");
        String redisKey = String.format("%s:%s", REDIS_CAPTCHA_PREFIX, telephone);
        String redisCode = this.stringRedisTemplate.opsForValue().get(redisKey);
        AssertExt.notNull(redisCode, "??????????????????");
        AssertExt.isTrue(redisCode.equals(code), "??????????????????");
    }

    @Override
    public void validateSmsCode(String telephone, String code) {
        AssertExt.matches("1\\d{10}", telephone, "?????????????????????");
        String redisKey = REDIS_PREFIX + telephone;
        String redisCode = this.stringRedisTemplate.opsForValue().get(redisKey);
        AssertExt.notNull(redisCode, "??????????????????");
        AssertExt.isTrue(redisCode.equals(code), "??????????????????");

    }


    @Override
    public Member memberLogin(String telephone, String password, String code, String ip) {
        AssertExt.notBlank(telephone, "???????????????");
        AssertExt.isFalse(StringUtils.isAllBlank(password, code), "????????????????????????");




      /*  Member memberDB = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("erp_user_id", pubCustomerDB.getCustomid()));

        if (null != password) {
            if (null != memberDB && !memberDB.getTelephone().equals(memberDB.getPassword())) {
                boolean check = DigestUtils.sha1Hex(password + memberDB.getSalt()).equals(memberDB.getPassword());
                AssertExt.isTrue(check, "????????????");

            }

        } else {
            this.validateSmsCode(telephone, code);
        }

        if (memberDB == null) {
            AssertExt.isTrue(telephone.equals(password), "????????????");
            memberDB = new Member();
            memberDB.setUserName(pubCustomerDB.getCustomname());
            memberDB.setPassword(telephone);
            memberDB.setTelephone(telephone);
            memberDB.setCreateTime(LocalDateTime.now());
            memberDB.setErpUserId(pubCustomerDB.getCustomid());
            memberDB.setMemberLoginNum(1);
            memberDB.setIsErp(1);
            this.memberMapper.insert(memberDB);

        } else {
            if (!telephone.equals(memberDB.getTelephone())) {
                memberDB.setPassword(telephone);
                memberDB.setTelephone(telephone);
            }
            memberDB.setErpUserId(pubCustomerDB.getCustomid());
            memberDB.setMemberLoginNum(memberDB.getMemberLoginNum() + 1);
            this.memberMapper.updateById(memberDB);
        }*/

        Member memberDB = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", telephone));
        AssertExt.isTrue(memberDB != null, "??????????????????????????????");

        if (null != password) {
            if (null != memberDB && !memberDB.getTelephone().equals(memberDB.getPassword())) {
                boolean check = DigestUtils.sha1Hex(password + memberDB.getSalt()).equals(memberDB.getPassword());
                AssertExt.isTrue(check, "????????????");

            } else {
                AssertExt.isTrue(memberDB.getTelephone().equals(password), "????????????");
            }
        } else {
            this.validateSmsCode(telephone, code);
        }

        //ERP??????
        CustomerVo pubCustomerDB = this.memberMapper.getPhone(memberDB.getErpUserId());
        AssertExt.notNull(pubCustomerDB, "???????????????");

        AssertExt.isTrue(pubCustomerDB.getGspusestatus() == 1, "????????????");

        memberDB.setMemberLoginNum(memberDB.getMemberLoginNum() + 1);
        this.memberMapper.updateById(memberDB);

        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setMemberId(memberDB.getId());
        memberLog.setIp(ip);
        memberLog.setContent("??????");
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);

        this.cacheCustomerGoodsService.customerCanBuyGoods(memberDB.getErpUserId());

        return memberDB;
    }

    @Override
    public Member telephoneAuth(String telephone, String code, String loginCode, String password) {
        AssertExt.notNull(loginCode, "loginCode ??????");
        AssertExt.notNull(telephone, "???????????????");

        JSONObject jsonObject = jscode2session(loginCode, this.wechatConfig.getZhongTian());
        AssertExt.isTrue(jsonObject.getString("errmsg") == null, "????????????");
        String openId = jsonObject.getString("openid");

        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("telephone", telephone));
        AssertExt.notNull(member, "????????????,??????????????????");
        if (null != code) {
            this.validateSmsCode(telephone, code);
        } else {
            if (null != member && !member.getTelephone().equals(member.getPassword())) {
                boolean check = DigestUtils.sha1Hex(password + member.getSalt()).equals(member.getPassword());
                AssertExt.isTrue(check, "????????????");
            }
        }
        this.memberMapper.updateMemberOpenidSetNull(member.getId(), openId);
        if (!member.getTelephone().equals(Constant.TELEPHONE)) {
            member.setOpenid(openId);
        }

        /*if (null ==member.getOpenid()) {
            member.setOpenid(openId);
        }else if (!member.getOpenid().equals(openId)) {
            throw new ZSYCOAuthException(String.format("??????%s?????????????????????????????????", telephone), "");
        }*/

        member.setMemberLoginNum(member.getMemberLoginNum() + 1);
        this.memberMapper.updateById(member);

        this.cacheCustomerGoodsService.customerCanBuyGoods(member.getErpUserId());

        Member member1 = new Member();
        member1.setId(member.getId());
        member1.setOpenid(openId);
        member1.setTelephone(member.getTelephone());
        return member1;
    }

    @Override
    public void addCustomerCanBuyGoods() {
        List<Long> longList = this.memberMapper.getCustomerGspusestatus();
        longList.stream().map(longCustomerId -> {
            this.cacheCustomerGoodsService.customerCanBuyGoods(longCustomerId);
            return 0;
        }).collect(Collectors.toList());
    }

    @Override
    public Member register(String telephone, String code, String password, String rePassword, String ip) {
        AssertExt.notBlank(telephone, "???????????????");
        AssertExt.notBlank(code, "???????????????");
        AssertExt.isTrue(password.equals(rePassword), "?????????????????????");
        AssertExt.matches(".{6,16}", password, "????????????6???16???");

        this.validateSmsCode(telephone, code);

        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", telephone));
        // AssertExt.isTrue(member == null, "???????????????????????????????????????");
        if (null == member) {
            member = new Member();
            member.setTelephone(telephone);
            member.setIsErp(0);
            member.setSalt(RandomStringUtils.random(10, true, true));
            member.setPassword(DigestUtils.sha1Hex(password + member.getSalt()));
            member.setCreateTime(LocalDateTime.now());
            member.setAuthModifyPwdTime(LocalDateTime.now());
            this.memberMapper.insert(member);
        }
        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setContent("????????????");
        memberLog.setMemberId(member.getId());
        memberLog.setIp(ip);
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);
        return member;
    }

    @Override
    public String getScanCode() {
        ScanInfoVo scanInfoVo = new ScanInfoVo();
        scanInfoVo.setScanCode(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
        scanInfoVo.setStatus(ScanInfoVo.EStatus.PENDING.val());
        this.stringRedisTemplate.opsForValue().set(REDIS_SCAN_CODE + scanInfoVo.getScanCode(),
                JSONObject.toJSONString(scanInfoVo),
                this.expire,
                TimeUnit.SECONDS);

        return scanInfoVo.getScanCode();
    }

    @Override
    public void scanCodeConfirmAuth(String scanCode, String loginCode) {
        AssertExt.notNull(scanCode, "scanCode??????");
        AssertExt.notNull(scanCode, "loginCode ??????");

        ScanInfoVo scanInfoVo = JSONObject.parseObject(this.stringRedisTemplate.opsForValue().get(REDIS_SCAN_CODE + scanCode), ScanInfoVo.class);
        AssertExt.notNull(scanInfoVo, "scanCode?????????/?????????");
        scanInfoVo.setLoginCode(loginCode);
        scanInfoVo.setStatus(ScanInfoVo.EStatus.CONFIRM.val());
        this.stringRedisTemplate.opsForValue().set(REDIS_SCAN_CODE + scanInfoVo.getScanCode(),
                JSONObject.toJSONString(scanInfoVo),
                this.expire,
                TimeUnit.SECONDS);

    }

    @Override
    public ScanInfoVo getScanInfoByScanCode(String scanCode) {
        ScanInfoVo scanInfoVo = JSONObject.parseObject(this.stringRedisTemplate.opsForValue().get(REDIS_SCAN_CODE + scanCode), ScanInfoVo.class);
        AssertExt.notNull(scanInfoVo, "scanCode?????????/?????????");
        return scanInfoVo;
    }

    @Override
    public Member wechatAuth(String code, String scanCode) {
        AssertExt.notNull(code, "code ??????");

        JSONObject jsonObject = jscode2session(code, this.wechatConfig.getZhongTian());
        AssertExt.isTrue(jsonObject.getString("errmsg") == null, "????????????");
        String openId = jsonObject.getString("openid");

        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("openid", openId));
        AssertExt.notNull(member, "????????????,??????????????????");

        if (StringUtils.isNoneBlank(scanCode)) {
            ScanInfoVo scanInfoVo = JSONObject.parseObject(this.stringRedisTemplate.opsForValue().get(REDIS_SCAN_CODE + scanCode), ScanInfoVo.class);
            if (null != scanInfoVo) {
                scanInfoVo.setStatus(ScanInfoVo.EStatus.SCAN_SUCCESS.val());
                this.stringRedisTemplate.opsForValue().set(REDIS_SCAN_CODE + scanInfoVo.getScanCode(),
                        JSONObject.toJSONString(scanInfoVo),
                        this.expire,
                        TimeUnit.SECONDS);
            }

        }

        return member;
    }

    private static JSONObject jscode2session(String code, WechatConfigProperties wechatConfigProperties) {
        String url = WECHAT_API_HOST + "/sns/jscode2session?appid=" + wechatConfigProperties.getAppId() + "&secret=" + wechatConfigProperties.getAppSecret() + "&js_code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = null;
        try {
            jsonObject = HttpUtil.httpGet(url);
        } finally {
            log.info("jscode2session request {}, response {}", url, jsonObject == null ? "null" : jsonObject.toJSONString());
        }
        return jsonObject;
    }

    @Override
    public Member wechatTelephoneAuth(String encryptedData, String iv, String loginCode) {
        AssertExt.notBlank(loginCode, "??????login code");
        String redisKey = String.format("wechat:session_key:%s:%s", this.wechatConfig.getZhongTian().getAppId(), loginCode);
        //String sessionKeyInfoStr = this.stringRedisTemplate.opsForValue().get(redisKey);
        //JSONObject sessionKeyInfo = JSON.parseObject(sessionKeyInfoStr);
        //AssertExt.notBlank(sessionKeyInfoStr, "session key????????????");
        JSONObject sessionKeyInfo = jscode2session(loginCode, this.wechatConfig.getZhongTian());
        AssertExt.isTrue(sessionKeyInfo.getString("errmsg") == null, "????????????");

        String openId = sessionKeyInfo.getString("openid");
        AssertExt.notNull(encryptedData, "encryptedData??????");
        AssertExt.notNull(iv, "iv??????");
        String result = AESCBCUtil.decrypt(encryptedData, sessionKeyInfo.getString("session_key"), iv, "UTF-8");
        log.info("AESCBCUtil.decrypt result {}", result);
        AssertExt.notBlank(result, "AESCBCUtil.decrypt error");
        JSONObject phoneInfoJSON = JSONObject.parseObject(result);
        String telephone = phoneInfoJSON.getString("phoneNumber");

        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("telephone", telephone));
        AssertExt.notNull(member, "?????????????????????");

        this.memberMapper.updateMemberOpenidSetNull(member.getId(), openId);

      //  if (!member.getTelephone().equals(Constant.TELEPHONE)) {
            member.setOpenid(openId);


        /*if (null ==member.getOpenid()) {
            member.setOpenid(openId);
        }else if (!member.getOpenid().equals(openId)) {
            throw new ZSYCOAuthException(String.format("??????%s?????????????????????????????????", telephone), "");
        }*/

        if (StringUtils.isBlank(member.getOpenid()) || StringUtils.isBlank(member.getTelephone())) {
            member.setOpenid(openId);
            member.setTelephone(telephone);
            this.memberMapper.updateById(member);
        }


        return member;
    }

    @Override
    public Member telephoneAppAuth(String telephone, String code, String password) {
        AssertExt.notNull(telephone, "???????????????");

        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("telephone", telephone));
        AssertExt.notNull(member, "????????????,??????????????????");
        if (null != code) {
            this.validateSmsCode(telephone, code);
        } else {
            if (null != member && !member.getTelephone().equals(member.getPassword())) {
                boolean check = DigestUtils.sha1Hex(password + member.getSalt()).equals(member.getPassword());
                AssertExt.isTrue(check, "????????????");
            }
        }

        member.setMemberLoginNum(member.getMemberLoginNum() + 1);
        this.memberMapper.updateById(member);

        this.cacheCustomerGoodsService.customerCanBuyGoods(member.getErpUserId());
        return member;
    }

    @Override
    public boolean loginList(String telephone) {
        AssertExt.notNull(telephone, "???????????????");
        String loginList = this.settingMapper.selectOne(new QueryWrapper<Setting>().eq("key", "LOGIN_LIST")).getValue();
        String[] stringList = loginList.split(",");
        if (Arrays.asList(stringList).contains(telephone)) {
            return false;
        }
        return true;
    }

}

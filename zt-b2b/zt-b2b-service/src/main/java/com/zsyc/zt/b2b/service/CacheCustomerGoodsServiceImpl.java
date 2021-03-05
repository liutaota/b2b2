package com.zsyc.zt.b2b.service;

import com.alibaba.fastjson.JSONObject;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.CacheCustomerGoods;
import com.zsyc.zt.b2b.mapper.CacheCustomerGoodsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.b2b.vo.ScanInfoVo;
import com.zsyc.zt.inca.service.CustomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户可购货品缓存表 服务实现类
 * </p>
 *
 * @author MP
 * @since 2020-08-15
 */
@Service
@Transactional
@Slf4j
public class CacheCustomerGoodsServiceImpl extends ServiceImpl<CacheCustomerGoodsMapper, CacheCustomerGoods>{

    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private CustomService customService;
    @Autowired
    private CacheCustomerGoodsServiceImpl cacheCustomerGoodsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${zsyc.zt.customer-code.expire:1800}")
    private Integer expire;

    private final static String REDIS_CUSTOMER = "customer:code:";

    /**
     * 客户可买商品
     */
    //@Cached(name = "customerCanBuyGoods-", key = "#customerId", expire = 10)
    public void customerCanBuyGoods(Long customerId) {
        Long id = JSONObject.parseObject(this.stringRedisTemplate.opsForValue().get(REDIS_CUSTOMER + customerId), Long.class);
        if (null != id) return;

        this.stringRedisTemplate.opsForValue().set(REDIS_CUSTOMER + customerId,
                JSONObject.toJSONString(customerId),
                this.expire,
                TimeUnit.SECONDS);

        log.info("start selectCanBuy {}", customerId);
        List<Long> longList = this.customService.selectCanBuy(customerId, 1);
        log.debug("longList {}", StringUtils.join(longList, ","));
        log.info("end selectCanBuy {} , {}", customerId, longList.size());

        log.info("start remove {}", customerId);
        this.cacheCustomerGoodsService.remove(new QueryWrapper<CacheCustomerGoods>().eq("customer_id", customerId));
        log.info("end remove {}", customerId);

        log.info("start saveBatch {}", customerId);
        List<CacheCustomerGoods> cacheCustomerGoodsList = longList.stream().map(longGoods -> {
            CacheCustomerGoods cacheCustomerGoods = new CacheCustomerGoods();
            cacheCustomerGoods.setCustomerId(customerId);
            cacheCustomerGoods.setGoodsId(longGoods);
            return cacheCustomerGoods;
        }).collect(Collectors.toList());
        this.cacheCustomerGoodsService.saveBatch(cacheCustomerGoodsList, 3000);
//
//		final int batchSize = 600;
//		for (int i = 0, toIndex = 0; i < longList.size(); i = toIndex) {
//			toIndex = i + batchSize;
//			toIndex = Math.min(toIndex, longList.size());
//			log.debug("goodsIds {}", StringUtils.join(longList.subList(i, toIndex)));
//			this.cacheCustomerGoodsMapper.addCacheCustomerGoodsMapper(customerId, StringUtils.join(longList.subList(i, toIndex), ","), toIndex - i);
//		}
        log.info("end saveBatch {}", customerId);
    }
}

package com.zsyc.zt.order.api.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.order.api.service.GoodsService;
import com.zsyc.zt.order.vo.GoodsVo;
import com.zsyc.zt.order.config.security.ApiUserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author peiqy
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Resource
    GoodsService goodsService;
    @RequestMapping("/list")
    @PreAuthorize("isAuthenticated()")
    public IPage<GoodsVo> selectPageList( @RequestParam(name = "goods_id_list",required = false) List<Long> goodsIdList,
                                              @RequestParam(name = "goods_name",required = false)String goodsName,
                                          @RequestParam(name = "factory_name",required = false)String factoryName,
                                          @RequestParam(name = "page_no", defaultValue = "1") Integer pageNo,
                                          @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize) {
        ApiUserDetails userDetails = (ApiUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.notNull(userDetails.getCustomId()," customId参数不能为空");
        Page<GoodsVo> userPage = new Page<>(pageNo, pageSize);
        IPage<GoodsVo>   result = goodsService.selectPageList(userPage, goodsIdList, goodsName,factoryName, userDetails.getCustomId());
        return result;
    }




}

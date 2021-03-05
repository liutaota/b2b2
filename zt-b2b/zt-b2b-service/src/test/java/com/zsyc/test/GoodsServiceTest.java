package com.zsyc.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.common.IncaUtils;
import com.zsyc.zt.b2b.entity.Reason;
import com.zsyc.zt.b2b.entity.WebPage;
import com.zsyc.zt.b2b.mapper.GoodsInfoMapper;
import com.zsyc.zt.b2b.mapper.WebPageMapper;
import com.zsyc.zt.b2b.service.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.fs.service.B2BFileService;
import com.zsyc.zt.inca.entity.PubGoodsClasstype;
import com.zsyc.zt.inca.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.util.UriUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Created by tang on 2020/07/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class GoodsServiceTest {
    @Autowired
    private ExcelService excelService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private ReasonService reasonService;
    @Autowired
    private B2BFileService fileService;

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Autowired
    private WebPageService webPageService;
    @Autowired
    private WebPageMapper webPageMapper;


    @Test
    public  void getGoodsClassTypeVoList(){
        val a = new  GoodsClassTypeVo();
        a.setClassname("-");
        this.goodsService.getGoodsClassTypeVoList(a);
    }

    @Test
    public  void getGoodsInfoList(){
        val a = new GoodsInfoVo();
        a.setClassname("0");
        a.setMemberId(4L);
        IPage<GoodsInfoVo> goodsInfoVoIPage = this.goodsService.getGoodsInfoList(new Page(1,100),a);
    }

    @Test
    public  void getGoodsInfo(){
        //this.goodsService.getGoodsInfo(2086L,81L,0);
         Long i=205L;
        log.info("{}",String.format("%.2f", (double)i/10000));

        log.info("{}",IncaUtils.toErpPriceDouble(i));
    }

    @Test
    public void goodsList(){
        GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
        goodsInfoVo.setMemberId(4L);
        this.goodsService.getGoodsInfoList(new Page(1,10),goodsInfoVo);
    }

    @Test
    public void getBannedList(){
        BannedVo bannedVo = new BannedVo();

        this.goodsService.getBannedList(new Page(2,40),bannedVo);
    }

    @Test
    public void getAdminGoodsList(){
        BannedVo bannedVo = new BannedVo();
        this.goodsService.getAdminGoodsList(new Page(1,10),bannedVo);
    }

    @Test
    public void getAdminGoodsListById(){
        BannedVo bannedVo = new BannedVo();
        bannedVo.setGoodsid(18670L);
        this.goodsService.getAdminGoodsListById(new Page(1,10),bannedVo);
    }

    @Test
    public void getAdminOrderInfoList(){
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        this.orderInfoService.getAdminOrderInfoList(new Page(1,10),orderInfoVo);
    }

    @Test
    public void updateReasonIsDel(){
        Long id = 13L;
        // this.reasonService.updateReasonIsDel(id);
    }

    @Test
    public void getFactoryList(){
        FactoryVo factoryVo = new FactoryVo();

        this.goodsService.getFactoryList(new Page(1,10),factoryVo);
    }

    @Test
    public void getGoodsListExcel(){
        GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
        String goodsListExcel  = this.excelService.getGoodsListExcel(goodsInfoVo);
        log.debug("goodsListExcel",goodsListExcel);
    }

    @Test
    public void testGoodsQuality(){
        this.goodsService.syncGoodsQualityImage(LocalDateTime.now().plusDays(-1));
    }

    @Test
    public void testSaveGoodsQuality(){
        GoodsQualityVo goodsQualityVo =
                this.goodsInfoMapper.getGoodsQuality(LocalDateTime.now().plusMonths(-1)).stream().filter(a -> a.getFcheckid().equals(386893L)).collect(Collectors.toList()).get(0);
        this.fileService.saveGoodsQuality(
                goodsQualityVo.getImgurlTop(),
                goodsQualityVo.getFcheckid(),
                goodsQualityVo.getFilename(),
                String.format("%s/%s", Constant.IMAGE_PREFIX.QUALITY, goodsQualityVo.getImgurlBottom()));
    }

    @Test
    public void testAddWebPageSearch(){
        WebPage webPage = this.webPageMapper.selectById(61);
        WebPageVo webPageVo = new WebPageVo();
        BeanUtils.copyProperties(webPage, webPageVo);
        this.webPageService.addWebPageSearch(webPageVo);
    }

}

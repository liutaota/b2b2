package com.zsyc.zt.fs.service;

import com.alibaba.fastjson.JSON;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.vo.AdvertiseImagesVo;
import com.zsyc.zt.b2b.vo.ClassImagesVo;
import com.zsyc.zt.b2b.vo.GoodsImagesVo;
import com.zsyc.zt.b2b.vo.NewProductImageVo;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * b2b 文件储存处理
 * 参考 4.规则定义.md
 * http://172.168.200.6/thirdparty/b2b_doc/blob/master/%E6%96%87%E6%A1%A3/4.%E7%B3%BB%E7%BB%9F%E5%BC%80%E5%8F%91/4.%E8%A7%84%E5%88%99%E5%AE%9A%E4%B9%89.md
 * Created by lcs on 2020/8/14.
 */
@Service
@Slf4j
public class B2BFileService {

    @Autowired
    private AliyunFileService fileService;

    /**
     * 货品图片读写
     *
     * @param erpGoodsId
     * @param goodsImageList
     * @param url
     * @return
     */
    public String coverGoodsImage(Long erpGoodsId, List<GoodsImagesVo> goodsImageList, String url) {
        int[] index = {0};
        int[] index1 = {0};
        goodsImageList.stream().map(goodsImagesVo -> {
            index[0]++;
            String key = url + "/" + erpGoodsId + "/" + index[0];
            byte[] data = this.fileService.read(goodsImagesVo.getCode());
            goodsImagesVo.setCode(String.format("%s.%s", key, goodsImagesVo.getType().equals("img") ? "png" : "mp4"));
            return data;
        }).forEach(bytes -> {
            this.fileService.write(goodsImageList.get(index1[0]).getCode(), new ByteArrayInputStream(bytes));
            index1[0]++;
        });
        return JSON.toJSONString(goodsImageList);
    }

    /**
     * 新品登记图片读写
     *
     * @param approvedocno
     * @param newProductImageVoList
     * @param url
     * @return
     */
    public String coverNewProductImage(String approvedocno, List<NewProductImageVo> newProductImageVoList, String url) {
        int[] index = {0};
        int[] index1 = {0};
        newProductImageVoList.stream().map(newProductImageVo -> {
            index[0]++;
            String key = url + "/" + approvedocno + "/" + index[0];
            byte[] data = this.fileService.read(newProductImageVo.getCode());
            newProductImageVo.setCode(String.format("%s.%s", key, newProductImageVo.getType().equals("img") ? "png" : "mp4"));
            return data;
        }).forEach(bytes -> {
            this.fileService.write(newProductImageVoList.get(index1[0]).getCode(), new ByteArrayInputStream(bytes));
            index1[0]++;
        });
        return JSON.toJSONString(newProductImageVoList);
    }

    /**
     * 货品分类图片读写
     * @param classid
     * @param classImagesVoList
     * @param url
     * @return
     */
    public String coverClassImage(Long classid, List<ClassImagesVo> classImagesVoList, String url) {
        int[] index = {0};
        int[] index1 = {0};
        classImagesVoList.stream().map(classImagesVo -> {
            index[0]++;
            String key = url + "/" + classid + "/" + index[0];
            byte[] data = this.fileService.read(classImagesVo.getCode());
            classImagesVo.setCode(String.format("%s.%s", key, classImagesVo.getType().equalsIgnoreCase("img") ? "png" : "mp4"));
            return data;
        }).forEach(bytes -> {
            this.fileService.write(classImagesVoList.get(index1[0]).getCode(),new ByteArrayInputStream(bytes));
        });
        return JSON.toJSONString(classImagesVoList);
    }

    /**
     * 图片数据转存
     *
     * @param id
     * @param prefix
     * @param imageList
     * @return 多个图片code，逗号隔开
     */
    public String coverImageById(Long id, String prefix, List<String> imageList) {
        int[] index = {0};
        return imageList.stream().map(code -> {
            index[0]++;
            String key = String.format("%s/%s/%s.jpg", prefix, id, index[0]);
            byte[] data = this.fileService.read(code);
            return ImageData.builder().data(data).key(key);
        }).map(imageData -> {
            this.fileService.write(imageData.key, new ByteArrayInputStream(imageData.data));
            return imageData.key;
        }).collect(Collectors.joining(","));
    }

    /**
     * @param id
     * @param prefix
     * @param imageArray
     * @return
     * @see #coverImageById(Long, String, List)
     */
    public String coverImageById(Long id, String prefix, String[] imageArray) {
        return this.coverImageById(id, prefix, Arrays.asList(imageArray));
    }

    /**
     * 广告图片读写
     *
     * @param id
     * @param advertiseImageList
     * @param url
     * @return
     */
    public String coverAdvertiseImage(Long id, List<AdvertiseImagesVo> advertiseImageList, String url) {
        int[] index = {0};
        int[] index1 = {0};
        advertiseImageList.stream().map(advertiseImagesVo -> {
            index[0]++;
            String key = url + "/" + id + "/" + index[0];
            byte[] data = this.fileService.read(advertiseImagesVo.getCode());
            advertiseImagesVo.setCode(String.format("%s.%s", key, advertiseImagesVo.getType().equals("img") ? "png" : "mp4"));
            return data;
        }).forEach(bytes -> {
            this.fileService.write(advertiseImageList.get(index1[0]).getCode(), new ByteArrayInputStream(bytes));
            index1[0]++;
        });
        return JSON.toJSONString(advertiseImageList);
    }

    /**
     * 通过url将图片写到OSS
     *
     * @param url
     * @param ossPath
     * @return
     */
    public String saveGoodsQuality(String url, Long fcheckid, String fileName, String ossPath) {
        AssertExt.notBlank(url, "url is blank");
        AssertExt.notBlank(url, "fcheckid is blank");
        AssertExt.notBlank(url, "fileName is blank");
        String imageUrl = String.format("%s%s/%s", url, fcheckid, fileName);

        try {
            try {
                log.info("save image url to oss {} => {}", imageUrl, ossPath);
                return this.fileService.write(ossPath, Request.Get(imageUrl).execute().returnContent().asStream());
            } catch (IllegalArgumentException e) {
                log.error("IllegalArgumentException", e);
                imageUrl = String.format("%s%s/%s", url, fcheckid, URLEncoder.encode(fileName, "utf-8")
                        .replaceAll("\\+", "%20")
                        .replaceAll("\\!", "%21")
                        .replaceAll("\\'", "%27")
                        .replaceAll("\\(", "%28")
                        .replaceAll("\\)", "%29")
                        .replaceAll("\\~", "%7E"));
                log.info("save encoded image url to oss {} => {}", imageUrl, ossPath);
                return this.fileService.write(ossPath, Request.Get(imageUrl).execute().returnContent().asStream());
            }
        } catch (IOException e) {
            log.error("saveGoodsQuality", e);
        }
        return null;
    }


    /**
     * 图片数据转存结构
     */
    @Data
    @Builder
    private static class ImageData {
        public byte[] data;
        public String key;
    }

    /**
     * 货品导出excel
     *
     * @param pathName
     * @param is
     * @return
     */
    public String exportGoodsList(String pathName, AliyunFileService.CovertOutputStream is) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        is.outputStream(o);
        return this.fileService.write(pathName, new ByteArrayInputStream(o.toByteArray()));
    }

    /**
     * 缺货登记表导出excel
     *
     * @param pathName
     * @param is
     * @return
     */
    public String exportArrivalNoticeList(String pathName, AliyunFileService.CovertOutputStream is) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        is.outputStream(o);
        return this.fileService.write(pathName, new ByteArrayInputStream(o.toByteArray()));
    }

    /**
     * 新品登记表导出excel
     *
     * @param pathName
     * @param is
     * @return
     */
    public String exportNewProductList(String pathName, AliyunFileService.CovertOutputStream is) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        is.outputStream(o);
        return this.fileService.write(pathName, new ByteArrayInputStream(o.toByteArray()));
    }

}

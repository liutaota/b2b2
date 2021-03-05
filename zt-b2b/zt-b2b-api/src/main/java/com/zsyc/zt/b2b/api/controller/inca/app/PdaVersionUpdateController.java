package com.zsyc.zt.b2b.api.controller.inca.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.inca.service.IPdaVersionUpdateService;
import com.zsyc.zt.inca.vo.PdaVersionUpdateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api(tags = "PDA设置业务处理")
@RestController
@RequestMapping("api/pda")
@Slf4j
public class PdaVersionUpdateController {

    @Reference
    private IPdaVersionUpdateService iPdaVersionUpdateService;

    @Autowired
    private AccountHelper accountHelper;

    @Value("${pda.upload.path}")
    private String pdaUploadPath;

    @ApiOperation(value =   "获取PDA版本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long")
    })
    @GetMapping("/list")
    public IPage<PdaVersionUpdateVo> getPdaVersionDesList(Page<PdaVersionUpdateVo> page,PdaVersionUpdateVo pdaVersionUpdateVo){
        return iPdaVersionUpdateService.getPdaVersionDesList(page,pdaVersionUpdateVo);
    }

    @ApiOperation(value =   "上传PDA版本文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "应用文件", required = true, dataType = "MultipartFile")
    })
    @PostMapping("/upload")
    public synchronized Map<String, Object> uploadApplicationFile(@RequestParam("file") MultipartFile file) {

        AssertExt.notNull(file,"文件为空");

        Map<String, Object> map=new HashMap<>();
        //文件夹名称
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());
        String filePath=this.pdaUploadPath;
        File savePathFile = new File(filePath);
        if (!savePathFile.exists()) {
            savePathFile.mkdirs();
        }
        String fileName =null;
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //存储数据库的文件
        fileName=date+"_"+UUID.randomUUID().toString().replace("-", "")+"_" + originalFilename;
        try {
            file.transferTo(new File(savePathFile,fileName));
            map.put("code","上传成功");
            map.put("pdaPath",filePath+fileName);
            log.info("版本文件上传成功:"+filePath+fileName);
        } catch (IOException e) {
            log.info("版本文件上传失败:"+e.getMessage());
            map.put("code","上传失败");
        }
        return map;
    }

    @ApiOperation(value =   "保存PDA版本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "filePath", value = "文件路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", required = true, dataType = "String")
    })
    @GetMapping("/saveVersionDes")
    public Map<String, Object> saveVersionDes(PdaVersionUpdateVo pdaVersionUpdateVo){
        AssertExt.notNull(pdaVersionUpdateVo,"文件为空");
        pdaVersionUpdateVo.setCreateUserId(this.accountHelper.getUserId());
        return iPdaVersionUpdateService.saveVersionDes(pdaVersionUpdateVo);
    }

    @ApiOperation(value =   "获取PDA最新版本信息")
    @GetMapping("/getPdaVersion")
    public Map<String, String> getPdaVersion(){
        return this.iPdaVersionUpdateService.getPdaVersion(this.pdaUploadPath);
    }

    @ApiOperation(value =   "更新PDA版本备注")
    @GetMapping("/updateRemark")
    public Map<String,String> updateRemark(@RequestParam("id") Long id,@RequestParam("newRemark") String newRemark){
        return iPdaVersionUpdateService.updateRemark(id,newRemark,this.accountHelper.getUserId());
    }

    @ApiOperation(value =   "下载PDA应用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", required = true, dataType = "String")
    })
    @GetMapping("/download")
    public void downloadApp(@RequestParam("newestFile") String newestFile, HttpServletRequest request, HttpServletResponse response){
        AssertExt.isTrue(newestFile != null && !"".equals(newestFile),"文件名为空");
        String fileName = newestFile;
        String path = this.pdaUploadPath+File.separator+fileName;
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 文件名转码
            fileName = getBrowser(request, fileName);
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            log.info("APP版本文件下载成功: "+newestFile);
        } catch (IOException ex) {
            log.info("APP版本文件下载失败: "+ex.getMessage());
        }

    }
    private String getBrowser (HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        //获取浏览器名（IE/Chrome/firefox）
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("Firefox")) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        } else if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else if (userAgent.contains("Chrome")) {
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        }
        return fileName;
    }

}

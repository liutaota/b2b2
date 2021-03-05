package com.zsyc.zt.fs.controller;

import com.zsyc.webapp.config.OauthClientProperties;
import com.zsyc.zt.fs.config.AliyunOssProperties;
import com.zsyc.zt.fs.config.MinioOssProperties;
import com.zsyc.zt.fs.service.AliyunFileService;
import com.zsyc.zt.fs.service.MinioFileService;
import io.minio.errors.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcs on 2020/7/25.
 */

@CrossOrigin(value="*", methods = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class FileController {

	@Autowired
	private AliyunFileService aliyunFileService;
	@Autowired
	private MinioFileService minioFileService;
	@Autowired
	private MinioOssProperties minioOssProperties;

	@Autowired
	private AliyunOssProperties aliyunOssProperties;
	/**
	 * 上件
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping("upload")
	@ResponseBody
	public Object upload(MultipartFile file) throws IOException {
		Map<String, String> map = new HashMap<>();
		String code = this.aliyunFileService.write(file.getInputStream());
		if (StringUtils.isEmpty(code)) {
			return map;
		}
		map.put("code",code);
		map.put("id", code);
		return map;
	}

	/**
	 * 上件  私有云
	 *
	 * @param file
	 * @return
	 */
	@RequestMapping("uploadPri")
	@ResponseBody
	public Object uploadPri(MultipartFile file) throws IOException, ServerException, InternalException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, XmlParserException, InvalidKeyException, ErrorResponseException {
		Map<String, String> map = new HashMap<>();
		String code = this.minioFileService.write(file.getInputStream());
		if (StringUtils.isEmpty(code)) {
			return map;
		}
		map.put("code",code);
		map.put("id", code);
		return map;
	}
//
//	/**
//	 * 下载
//	 */
//	@RequestMapping(value="download/**/{path}/{fingerprint}.{suffix}", method = RequestMethod.GET)
//	public void download(@PathVariable("path") String path, @PathVariable("fingerprint") String fingerprint, @PathVariable("suffix") String suffix,
//						 HttpServletRequest request,
//						 HttpServletResponse response){
//		StringBuffer realPath = new StringBuffer(this.path);
//		realPath.append("/").append(path).append("/").append(fingerprint);
//		ZSYCFile file = fileBiz.get(fingerprint);
//		if(file == null){
//			return;
//		}
//		String userAgent = request.getHeader("User-Agent");
//		response.setHeader("Content-Type", file.getContentType());
//		try {
//			response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", new String(userAgent.contains("MSIE") ? file.getName().getBytes() : file.getName().getBytes("UTF-8"), "ISO-8859-1")));
//			FileUtil.download(response.getOutputStream(), realPath.toString());
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 文件列表
//	 */
//	@GetMapping("listFile")
//	@ResponseBody
//	public List<ZSYCFile> listFile(Long fileId, Integer limit) {
//		return this.fileBiz.listFile(fileId, limit);
//	}
//
	/**
	 * 判断文件是否存在
	 *
	 * @return
	 */
	@RequestMapping("exist")
	@ResponseBody
	public Object exist(String code) {
		code = this.aliyunFileService.exist(String.format("zs/%s", code));
		Map<String, String> map = new HashMap<>();

		if (StringUtils.isEmpty(code)) {
			return map;
		}
		map.put("code", code);
		map.put("id", code);
		return map;
	}

	/**
	 * 判断文件是否存在
	 *
	 * @return
	 */
	@RequestMapping("existPri")
	@ResponseBody
	public Object existPri(String code) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
		code = this.minioFileService.exist(String.format("zs/%s", code));
		Map<String, String> map = new HashMap<>();

		if (StringUtils.isEmpty(code)) {
			return map;
		}
		map.put("code", code);
		map.put("id", code);
		return map;
	}


	/**
	 * 获取imageHost
	 *
	 * @return
	 */
	@GetMapping("imageHostPri")
	@ResponseBody
	public String imageHostPri(String code) {
		return String.format("https://%s.%s", this.minioOssProperties.getBucketName(), this.minioOssProperties.getEndpoint().substring(8));
	}

	/**
	 * 获取imageHost
	 *
	 * @return
	 */
	@GetMapping("imageHost")
	@ResponseBody
	public String imageHost(String code) {
		return String.format("https://%s.%s", this.aliyunOssProperties.getBucketName(), this.aliyunOssProperties.getEndpoint().substring(8));
	}
}
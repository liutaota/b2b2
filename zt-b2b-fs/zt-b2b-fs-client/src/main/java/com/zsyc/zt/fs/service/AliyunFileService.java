package com.zsyc.zt.fs.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.fs.config.AliyunOssProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by lcs on 2020/7/25.
 */
@Service
@Slf4j
public class AliyunFileService {
	@Autowired
	private OSS ossClient;
	@Autowired
	private AliyunOssProperties aliyunOssProperties;

	/**
	 * 上传文件到oss
	 * @param inputStream
	 * @return
	 */
	private String putObject(InputStream inputStream) {
		AssertExt.notNull(inputStream, "inputStream not null");
		byte[] buf = inputStream2Bytes(inputStream);
		return this.write(genKey(new ByteArrayInputStream(buf)), new ByteArrayInputStream(buf));
	}

	/**
	 * 上传文件到oss
	 * @param key
	 * @param inputStream
	 * @return
	 */
	public String write(String key, InputStream inputStream) {
		AssertExt.notNull(key, "key not null");
		AssertExt.notNull(inputStream, "inputStream not null");
		this.ossClient.putObject(this.aliyunOssProperties.getBucketName(), key, inputStream);
		return key;
	}

	/**
	 * 从oss读一个文件
	 * @param key
	 * @return
	 * @throws IOException
	 */
	private byte[] getObject(String key) throws IOException {
		AssertExt.notNull(key, "key not null");
		OSSObject ossObject = this.ossClient.getObject(this.aliyunOssProperties.getBucketName(), key);
		return inputStream2Bytes(ossObject.getObjectContent());
	}


	private static final int buf_len = 100;
	/**
	 * inputStream 转 Bytes
	 * @param inStream
	 * @return
	 */
	private static byte[] inputStream2Bytes(InputStream inStream){
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		byte[] buff = new byte[buf_len];
		int rc = 0;
		try {
			while ((rc = inStream.read(buff, 0, buf_len)) > 0) {
				arrayOutputStream.write(buff, 0, rc);
			}
		} catch (IOException e) {
			log.error("IOException error", e);
			throw new RuntimeException(e);
		}
		return arrayOutputStream.toByteArray();
	}

	/**
	 * 文件写入
	 * @param file
	 * @return
	 */
	public String write(File file) {
		AssertExt.notNull(file, "file not null");
		try {
			return this.putObject(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			log.error("file error", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 流文件写入
	 * @param inputStream
	 * @return
	 */
	public String write(InputStream inputStream) {
		return this.putObject(inputStream);
	}

	/**
	 * OutputStream写入
	 * @param is
	 * @return
	 */
	public String write(CovertOutputStream is){
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		is.outputStream(o);
		return this.write(new ByteArrayInputStream(o.toByteArray()));
	}

	/**
	 * 文件读取
	 *
	 * @param key
	 * @return
	 */
	public byte[] read(String key) {
		AssertExt.notNull(key, "key not null");
		try {
			return this.getObject(key);
		} catch (IOException e) {
			log.error("IOException error", e);
			throw new RuntimeException(e);
		}
	}
	/**
	 * key是否已经存在
	 * @param key
	 * @return
	 */
	public String exist(String key)  {
		AssertExt.notBlank(key, "key not null");
		if (this.ossClient.doesObjectExist(this.aliyunOssProperties.getBucketName(), key)) {
			return key;
		}
		return null;

	}

	/**
	 * 文件是否已经存在
	 * @param inputStream
	 * @return
	 */
	public String exist(InputStream inputStream)  {
		return this.exist(genKey(inputStream));
	}

	/**
	 * 生效文件key
	 *
	 * @return
	 */
	private static String genKey(InputStream in) {
		try {
			String code = DigestUtils.sha1Hex(in).toUpperCase();
			return String.format("zs/%s", code);
		} catch (IOException e) {
			log.error("sha1Hex error", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从output stream 输入
	 *
	 */
	public interface CovertOutputStream {
		void outputStream(OutputStream outputStream);
	}
}

package com.zsyc.zt.fs.service;

import cn.hutool.core.util.ObjectUtil;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.fs.config.MinioOssProperties;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lcs on 2020/7/25.
 */
@Service
@Slf4j
public class MinioFileService {
	@Autowired
	private MinioClient minioClient;
	@Autowired
	private MinioOssProperties minioOssProperties;

	/**
	 * 上传文件到oss
	 * @param inputStream
	 * @return
	 */
	private String putObject(InputStream inputStream) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
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
	public String write(String key, InputStream inputStream) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
		AssertExt.notNull(key, "key not null");
		AssertExt.notNull(inputStream, "inputStream not null");
		this.minioClient.putObject(PutObjectArgs.builder().bucket(this.minioOssProperties.getBucketName()).object(key).stream(inputStream,-1,10485760).build());
		return key;
	}

	/**
	 * 从oss读一个文件
	 * @param key
	 * @return
	 * @throws IOException
	 */
	private byte[] getObject(String key) throws IOException, ServerException, InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, XmlParserException, ErrorResponseException {
		AssertExt.notNull(key, "key not null");
		GetObjectResponse object = this.minioClient.getObject(GetObjectArgs.builder().bucket(this.minioOssProperties.getBucketName()).object(key).build());
		return inputStream2Bytes(object);
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
		} catch (IOException | InvalidResponseException | InvalidKeyException | NoSuchAlgorithmException | ServerException | ErrorResponseException | XmlParserException | InsufficientDataException | InternalException e) {
			log.error("file error", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 流文件写入
	 * @param inputStream
	 * @return
	 */
	public String write(InputStream inputStream) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InsufficientDataException, ErrorResponseException {
		return this.putObject(inputStream);
	}

	/**
	 * OutputStream写入
	 * @param is
	 * @return
	 */
	public String write(CovertOutputStream is) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
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
		} catch (IOException | ServerException | InsufficientDataException | InternalException | InvalidResponseException | InvalidKeyException | NoSuchAlgorithmException | XmlParserException | ErrorResponseException e) {
			log.error("IOException error", e);
			throw new RuntimeException(e);
		}
	}
	/**
	 * key是否已经存在
	 * @param key
	 * @return
	 */
	public String exist(String key) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
		AssertExt.notBlank(key, "key not null");

		StatObjectResponse stat =
				minioClient.statObject(
						StatObjectArgs.builder().bucket(this.minioOssProperties.getBucketName()).object(key).build());
		if(ObjectUtil.isEmpty(stat.size())){
			return null;
		}
		return key;

	}

	/**
	 * 文件是否已经存在
	 * @param inputStream
	 * @return
	 */
	public String exist(InputStream inputStream) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
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
			return String.format("zs/%s%s", code,".apk");
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

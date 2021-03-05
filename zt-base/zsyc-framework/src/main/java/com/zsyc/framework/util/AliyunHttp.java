package com.zsyc.framework.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.stream.Collectors;

/**
 * Created by lcs on 2020/3/27.
 */
@Slf4j
public class AliyunHttp {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	static {
		DATE_FORMAT.setTimeZone(new SimpleTimeZone(0, "GMT"));
	}
	private  HttpClient httpclient = HttpClients.custom().build();;
	private static final String HMAC_ALGORITHM = "HmacSHA1";
	private static final String CONTENT_CHARSET = "UTF-8";

	@Getter
	@Setter
	private PublicParams publicParams;

	public JSONObject get(Map<String, String> requestParam) {
		Map<String, String> params = new HashMap<>(requestParam);
		params.put("Format", "JSON");
		params.put("Version", this.publicParams.getVersion());
		params.put("SignatureMethod", "HMAC-SHA1");
		params.put("SignatureVersion", "1.0");
		params.put("AccessKeyId", this.publicParams.getAccessKeyId());
		params.put("Timestamp", DATE_FORMAT.format(new Date(System.currentTimeMillis())));
		params.put("SignatureNonce", RandomStringUtils.random(32, true, true));

		String paramString = params.entrySet().stream()
				.filter(entry -> StringUtils.isNotBlank(entry.getValue()))
				.map(entry -> String.format("%s=%s", entry.getKey(), percentEncode(entry.getValue()))).sorted().collect(Collectors.joining("&"));
		log.debug("paramString {}", paramString);

		String stringToSign = String.format("GET&%s&%s", percentEncode("/"), percentEncode(paramString));
		log.debug("stringToSign {}", stringToSign);

		Mac mac = initMac();

		String signature = new String(Base64.encodeBase64(mac.doFinal(stringToSign.getBytes())));
		signature = percentEncode(signature);
		log.debug("signature {}", signature);

		String url = String.format("%s?%s&Signature=%s", this.publicParams.getHost(), paramString, signature);
		log.debug("url {}", url);

		HttpGet httpGet = new HttpGet(url);
		String bodyAsString = null;

		try {
			bodyAsString = httpclient.execute(httpGet, responseHandler);
			return JSONObject.parseObject(bodyAsString);
		} catch (IOException e) {
			log.error("httpGet execute error", e);
			throw new RuntimeException(e);
		} finally {
			log.info("http get {},{}", url, bodyAsString);
		}

	}
	/**
	 * 特殊字符替换为转义字符
	 * @param value
	 * @return
	 */
	private  String percentEncode(String value) {
		try {
			String urlEncodeOriginStr = URLEncoder.encode(value, CONTENT_CHARSET);
			String plusReplaced = urlEncodeOriginStr.replace("+", "%20");
			String starReplaced = plusReplaced.replace("*", "%2A");
			String waveReplaced = starReplaced.replace("%7E", "~");
			return waveReplaced;
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException", e);
		}
		return value;
	}

	private Mac initMac(){
		try {
			Mac mac = Mac.getInstance(HMAC_ALGORITHM);
			SecretKeySpec signKey = new SecretKeySpec((this.publicParams.getAccessKeySecret() + "&").getBytes(), mac.getAlgorithm());
			mac.init(signKey);
			return mac;
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			log.error("initMac error", e);
			throw new RuntimeException(e);
		}
	}

	private  ResponseHandler<String>  responseHandler = new BasicResponseHandler(){
		@Override
		public String handleResponse(
				final HttpResponse response) throws HttpResponseException, IOException {
			final StatusLine statusLine = response.getStatusLine();
			final HttpEntity entity = response.getEntity();
			if (statusLine.getStatusCode() >= 300) {
				String data = EntityUtils.toString(entity);
				log.error("{},{},{}", statusLine.getStatusCode(),
						statusLine.getReasonPhrase(),
						data);
				AssertExt.fail("%s,%s,%s", statusLine.getStatusCode(),
						statusLine.getReasonPhrase(),
						data
				);
			}
			return super.handleResponse(response);
		}
	};

	@Data
	@Accessors(chain = true)
	public static class PublicParams{
		/**
		 * host
		 */
		private String host = "https://vod.cn-shanghai.aliyuncs.com";
		/**
		 * version
		 */
		private String version = "2017-03-21";
		/**
		 * accessKeyId
		 */
		private String accessKeyId;
		/**
		 * accessKeySecret
		 */
		private String accessKeySecret;

	}
}

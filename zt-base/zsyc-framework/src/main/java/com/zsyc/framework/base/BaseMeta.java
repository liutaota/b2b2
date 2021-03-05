package com.zsyc.framework.base;

/**
 * Created by lcs on 2020/4/1.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class BaseMeta extends BaseBean {
	/**
	 * 扩展内容
	 */
	private Map<String, Object> metaData;

	private static final Gson gson = new GsonBuilder().create();
	public String getMetaData() {
		return metaData == null ? null : gson.toJson(this.metaData);
	}

	public void setMetaData(String metaData) {
		if (metaData == null) {
			this.metaData = null;
			return;
		}
		this.metaData = gson.fromJson(metaData, HashMap.class);
	}

	public void setMetaData(String key, Object value) {
		if (metaData == null) {
			metaData = new HashMap<>();
		}
		metaData.put(key, value);
	}

	public String getMetaData(String key) {
		if (this.metaData == null) return null;
		Object value = this.metaData.get(key);
		if (value == null) return null;
		return value.toString();
	}

	public <T> T getMetaData(String key, Type type) {
		if (this.metaData == null) return null;
		Object value = this.metaData.get(key);
		if (value == null) return null;
		return gson.fromJson(gson.toJson(value), type);
	}
}


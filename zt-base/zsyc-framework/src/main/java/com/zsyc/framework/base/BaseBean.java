package com.zsyc.framework.base;

import org.apache.commons.lang3.NotImplementedException;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by lcs on 2018-12-26.
 */

public class BaseBean implements Serializable {
	public Long getId(){
		throw new NotImplementedException("");
	}
	public LocalDateTime getCreateTime(){
		throw new NotImplementedException("");
	}
}
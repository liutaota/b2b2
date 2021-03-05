package com.zsyc.framework.base;

import ch.qos.logback.core.AppenderBase;

/**
 * Created by lcs on 2020/7/25.
 */
public class EmptyAppender<E> extends AppenderBase<E> {

	@Override
	protected void append(E eventObject) {
	}
}

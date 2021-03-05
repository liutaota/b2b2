package com.zsyc.framework.dubbo;


import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * Created by lcs on 2019-04-08.
 */

@Activate(group = CommonConstants.PROVIDER)
@Slf4j
public class DubboExceptionFilter implements Filter {

	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		try {
			Result result = invoker.invoke(invocation);
			if (result.hasException() && GenericService.class != invoker.getInterface()) {
				Throwable exception = result.getException();
				log.error("DubboExceptionFilter error", exception);
			}
			return result;
		} catch (RuntimeException e) {
			log.error("Got unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost()
					+ ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
					+ ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);

			throw e;
		}
	}

}

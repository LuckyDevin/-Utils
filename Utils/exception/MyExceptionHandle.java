package com.ccx.credit.util.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionHandle implements HandlerExceptionResolver {
    public static final Logger logger = LoggerFactory.getLogger(MyExceptionHandle.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception e) {
		Map<String, Object> model = new HashMap<String ,Object>();
		model.put("e", e);
		//异常信息记录日志中
		logger.debug("异常类型: "+e.getClass().getSimpleName(),e);
		logger.info("异常类型: "+e.getClass().getSimpleName(),e);
		request.setAttribute("msg", "抱歉，服务器内部错误！");
//	    return new ModelAndView("runtime_exception",model);
	    return new ModelAndView("error",model);
	}

}

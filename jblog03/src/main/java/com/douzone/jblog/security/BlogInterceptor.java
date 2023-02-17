package com.douzone.jblog.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.douzone.jblog.service.JblogService;

public class BlogInterceptor implements HandlerInterceptor {
	@Autowired
	private JblogService jblogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String userId = (String) pathVariables.get("userId");
		String category = (String) pathVariables.get("category");
		String post = (String) pathVariables.get("post");
	
		System.out.println(category+"ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡprehandler");
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		Blog blog = handlerMethod.getMethodAnnotation(Blog.class);

		if (blog == null) {
			blog = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Blog.class);
		}
		if (blog == null) {
			return true;
		}
		

		if(jblogService.getBlog(userId)==null) {
			response.sendError(404);
			return false;
		}
		
		request.getServletContext().setAttribute("vo", jblogService.getBlog(userId));
		
		return true;
	}
	

}

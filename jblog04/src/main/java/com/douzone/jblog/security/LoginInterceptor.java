package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.service.JblogService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;


public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;
	@Autowired
	private JblogService jblogService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setPassword(password);

		UserVo authUser = userService.getUser(vo);
		
		if(authUser ==null) {
			request.setAttribute("id", vo.getId());
			request
			.getRequestDispatcher("WEB-INF/views/user/login.jsp")
			.forward(request, response);
			return false;
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		request.getServletContext().setAttribute("vo", jblogService.getBlog(authUser.getId()));
		System.out.println("request.getContextPath"+request.getContextPath());
		response.sendRedirect(request.getContextPath());
		return false;
	}

}

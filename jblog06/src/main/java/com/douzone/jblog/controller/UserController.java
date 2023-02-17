package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.JblogService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.JblogVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private JblogService jblogService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo,BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(userVo);
		JblogVo jblog = new JblogVo();
		jblog.setId(userVo.getId());
		jblogService.create(jblog);
		CategoryVo cg = new CategoryVo();
		cg.setId(userVo.getId());
		categoryService.create(cg);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public void login(UserVo vo) {
		
	}

}

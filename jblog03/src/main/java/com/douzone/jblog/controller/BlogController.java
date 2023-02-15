package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.Blog;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.service.JblogService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.JblogVo;
import com.douzone.jblog.vo.PostVo;

@Blog
@Controller
@RequestMapping("/{userId:(?!assets).*}")
public class BlogController {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private JblogService jblogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private FileuploadService fileuploadService;

	@GetMapping(value = { "", "/{category}", "/{category}/{post}" })
	public String admin(@PathVariable("userId") String userId, @PathVariable("category") Optional<Integer> category,
			@PathVariable("post") Optional<Integer> post, Model model) {

		HashMap<String, Object> map = new HashMap<>();

		if (category.isPresent()) {
			List<PostVo> postList = postService.getPostList(category.get().intValue());
			map.put("postList", postList);

			if (post.isPresent()) {
				PostVo postVo = postService.getPost(post.get().intValue());
				map.put("postVo", postVo);
			}
		}

		List<CategoryVo> list = categoryService.findById(userId);
		map.put("list", list);

		JblogVo jblog = jblogService.getBlog(userId);
		map.put("jblog", jblog);

		model.addAllAttributes(map);

		return "/blog/main";
	}

	@Auth
	@RequestMapping("/admin/basic")
	public String basic(@PathVariable("userId") String userId, Model model) {
		JblogVo vo = jblogService.getBlog(userId);
		model.addAttribute("vo", vo);
		return "blog/admin-basic";
	}
	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String basic(@PathVariable("userId") String userId, JblogVo vo, @RequestParam("file") MultipartFile file) {
		vo.setId(userId);
		vo.setProfile(fileuploadService.restore(file));
		jblogService.update(vo);
		servletContext.setAttribute("vo", vo);
		return "redirect:/" + userId + "/admin/basic";
	}
	@Auth
	@RequestMapping("/admin/category")
	public String category(@PathVariable("userId") String userId, Model model) {
		List<CategoryVo> list = categoryService.findById(userId);
		model.addAttribute("list", list);
		return "blog/admin-category";
	}
	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String category(@PathVariable("userId") String userId, CategoryVo vo) {
		vo.setId(userId);
		categoryService.create(vo);
		return "redirect:/" + userId + "/admin/category";
	}
	@Auth
	@RequestMapping("/admin/category/delete/{no}")
	public String category(@PathVariable("userId") String userId, @PathVariable("no") int no) {
		// if userId== no.id
		categoryService.delete(no);
		return "redirect:/" + userId + "/admin/category";
	}
	@Auth
	@RequestMapping("/admin/write")
	public String write(@PathVariable("userId") String userId, Model model) {
		List<CategoryVo> List = categoryService.findById(userId);
		model.addAttribute("List", List);
		return "blog/admin-write";
	}
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(@PathVariable("userId") String userId, PostVo vo) {
		postService.insert(vo);
		return "redirect:/" + userId;
	}
}

package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	public void create(CategoryVo cg) {
		categoryRepository.insert(cg);
	}
	public List<CategoryVo> findById(String userId) {
		return categoryRepository.select(userId);
	}
	public void delete(int no) {
		categoryRepository.delete(no);
	}

}

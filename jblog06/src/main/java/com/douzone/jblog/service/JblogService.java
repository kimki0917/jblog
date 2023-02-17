package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.JblogRepository;
import com.douzone.jblog.vo.JblogVo;

@Service
public class JblogService {
	@Autowired
	private JblogRepository jblogRepository;

	public void join(JblogVo vo) {
		jblogRepository.insert(vo);
	}

	public JblogVo getBlog(String userId) {
		return jblogRepository.findById(userId);
	}

	public void create(JblogVo vo) {
		jblogRepository.insert(vo);
	}

	public void update(JblogVo vo) {
		jblogRepository.update(vo);
	}

}

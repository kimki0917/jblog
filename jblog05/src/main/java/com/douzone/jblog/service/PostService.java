package com.douzone.jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public void insert(PostVo vo) {
		postRepository.insert(vo);
	}

	public PostVo getPost(int intValue) {
		// TODO Auto-generated method stub
		return postRepository.getPost(intValue);
	}

	public List<PostVo> getPostList(int categoryNo) {
		// TODO Auto-generated method stub
		return postRepository.getPostList(categoryNo);
	}
}

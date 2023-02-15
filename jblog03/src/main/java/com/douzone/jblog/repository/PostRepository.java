package com.douzone.jblog.repository;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class PostRepository {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
	public void insert(PostVo vo) {
		sqlSession.insert("post.insert",vo);
	}
	public PostVo getPost(int intValue) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("post.select",intValue);
	}
	public List<PostVo> getPostList(int categoryNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("post.selectPostList",categoryNo);
	}

}

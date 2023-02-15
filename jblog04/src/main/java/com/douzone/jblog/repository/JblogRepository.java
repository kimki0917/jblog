package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.JblogVo;

@Repository
public class JblogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(JblogVo vo) {
		sqlSession.insert("jblog.insert",vo);
	}

	public void update(JblogVo vo) {
		sqlSession.update("jblog.update",vo);
	}

	public JblogVo findById(String userId) {
		return sqlSession.selectOne("jblog.findById",userId);
	}

}

package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;


@Repository
public class UserRepository {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert",vo);
	}

	public UserVo findByIdAndPassword(String id, String password){
		Map<String, Object> map = new HashMap<>();
		map.put("i", id);
		map.put("p", password);
		return sqlSession.selectOne("user.findByIdAndPassword",map );

	}	

	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.findByNo",no);
	}

	public void update(UserVo vo) {
		sqlSession.update("user.update",vo);
	
	}
}

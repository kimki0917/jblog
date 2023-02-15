package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	public void insert(CategoryVo cg) {
		sqlSession.insert("category.insert",cg);
	}
	public List<CategoryVo> select(String userId) {
		return sqlSession.selectList("category.findAllById",userId);
	}
	public void delete(int no) {
		sqlSession.delete("category.delete",no);
	}

}

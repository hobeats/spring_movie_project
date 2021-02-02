package com.project.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.user.vo.LoginDTO;
import com.project.user.vo.UserVO;


public interface UserDAO {
	
	@Insert("INSERT INTO movie_test(userid,userpw,nickname,email) VALUES(#{userid},#{userpw},#{nickname},#{email})")
	void join(UserVO vo)throws Exception;
	
	@Select("SELECT * FROM movie_test WHERE userid = #{userid} AND userpw = #{userpw}")
	UserVO login(LoginDTO dto)throws Exception;
	
	@Select("SELECT * FROM movie_test WHERE userid = #{userid}")
	UserVO getUserById(String uid)throws Exception;

}




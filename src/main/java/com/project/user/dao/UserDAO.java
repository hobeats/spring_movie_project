package com.project.user.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.user.vo.LoginDTO;
import com.project.user.vo.UserVO;


public interface UserDAO {
	
	@Insert("INSERT INTO movie_user(uid,upw,nickName) VALUES(#{uid},#{upw},#{nickName})")
	void join(UserVO vo)throws Exception;
	
	@Select("SELECT * FROM movie_user WHERE uid = #{uid} AND upw = #{upw}")
	UserVO login(LoginDTO dto)throws Exception;
	
	@Select("SELECT * FROM movie_user WHERE uid = #{uid}")
	UserVO getUserById(String uid)throws Exception;
	
	//cert = y로 변경
	@Update("UPDATE movie_user SET cert = 'Y' WHERE uid = #{uid}")
	void changeCert(String uid) throws Exception;
	
	//회원 수정(프사)
	@Update("UPDATE movie_user set profile_path = #{profile_path} WHERE uid = #{uid}")
	public void modify(UserVO vo)throws Exception;
		
	//회원 탈퇴 (비밀번호 일치시)
	@Delete("delete from movie_user where uid = #{uid} AND upw = #{upw}")
	public void delete(UserVO vo)throws Exception;
	
	
}




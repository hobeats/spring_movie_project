package com.project.user.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.user.vo.TempVO;

public interface TempDAO {
	
	//임시로 사용자 등록
	@Insert("INSERT INTO temp_user(uid,cert) VALUES(#{uid}, #{cert})")
	void insertTemp(TempVO vo) throws Exception;
	
	//사용자 확인
	@Select("SELECT * FROM temp_user WHERE uid = #{uid} AND cert = #{cert}")
	TempVO checkTemp(TempVO vo) throws Exception; 
	
	//사용자 삭제
	@Delete("DELETE FROM temp_user WHERE uid = #{uid}")
	void deleteTemp(String uid) throws Exception;
}

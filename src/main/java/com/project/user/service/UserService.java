package com.project.user.service;

import com.project.user.vo.LoginDTO;
import com.project.user.vo.UserVO;

public interface UserService {
	
	// 회원가입
	void join(UserVO vo)throws Exception;
	
	// 로그인
	UserVO login(LoginDTO dto) throws Exception;
	
	// 아이디 정보로 사용자 정보 확인
	UserVO getUserById(String userid)throws Exception;

}
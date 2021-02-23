package com.project.user.service;

import com.project.user.vo.LoginDTO;
import com.project.user.vo.TempVO;
import com.project.user.vo.UserVO;

public interface UserService {

	// 회원가입
	void join(UserVO vo) throws Exception;

	// 로그인
	UserVO login(LoginDTO dto) throws Exception;

	// 아이디 정보로 사용자 정보 확인
	UserVO getUserById(String uid) throws Exception;

	// 임시 저장 정보 확인
	TempVO checkTemp(TempVO vo) throws Exception;

	// cert = y로 바꾸고 temp_user 에서 삭제
	void deleteTemp(TempVO vo) throws Exception;
	
	//user수정
	public void modify(UserVO vo) throws Exception;
	
	//회원 탈퇴
	public void delete(UserVO vo) throws Exception;
}
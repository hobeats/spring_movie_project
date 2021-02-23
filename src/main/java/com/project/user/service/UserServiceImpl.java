package com.project.user.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.user.dao.TempDAO;
import com.project.user.dao.UserDAO;
import com.project.user.vo.LoginDTO;
import com.project.user.vo.TempVO;
import com.project.user.vo.UserVO;


@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	UserDAO dao;
	@Inject
	TempDAO tempDAO;

	@Override
	public void join(UserVO vo) throws Exception {
		dao.join(vo);
	}

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public UserVO getUserById(String uid) throws Exception {
		return dao.getUserById(uid);
	}

	@Override
	public TempVO checkTemp(TempVO vo) throws Exception {
		return tempDAO.checkTemp(vo);
	}

	@Override
	@Transactional
	public void deleteTemp(TempVO vo) throws Exception {
		String uid = vo.getUid();
		dao.changeCert(uid);
		tempDAO.deleteTemp(uid);
		
	}
	//회원 수정
	@Override
	public void modify(UserVO vo) throws Exception {
		
		dao.modify(vo);
	}
	//회원탈퇴
	@Override
	public void delete(UserVO vo) throws Exception {
		dao.delete(vo);
	}

}

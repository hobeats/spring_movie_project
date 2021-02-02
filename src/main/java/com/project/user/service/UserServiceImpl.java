package com.project.user.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.user.dao.UserDAO;
import com.project.user.vo.LoginDTO;
import com.project.user.vo.UserVO;


@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	UserDAO dao;

	@Override
	public void join(UserVO vo) throws Exception {
		dao.join(vo);
	}

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public UserVO getUserById(String userid) throws Exception {
		return dao.getUserById(userid);
	}

}

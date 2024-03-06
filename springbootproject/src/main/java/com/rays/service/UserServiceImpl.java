package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.ORSResponse;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

@Service
@Transactional
public class UserServiceImpl<T extends UserDTO, D extends UserDAOInt> implements UserServiceInt {

	@Autowired
	D userService;

	@Override
	public long add(UserDTO dto) {
		return userService.add(dto);
	}

	@Override
	public UserDTO update(UserDTO dto) {
		userService.update(dto);
		return dto;
	}

	@Override
	public UserDTO findById(long id) {
		UserDTO dto = userService.findByPk(id);
		return dto;
	}

	@Override
	public UserDTO findByLoginId(String loginId) {
		UserDTO dto = userService.findByUniqueKey("loginId", loginId);
		return dto;
	}

	@Override
	public UserDTO delete(long id) {

		ORSResponse res = new ORSResponse();
		UserDTO dto = userService.findByPk(id);

		if (dto != null) {
			userService.delete(dto);
		} else {
			res.addMessage("Record not found...!!!");
		}
		return dto;
	}

	@Override
	public UserDTO authenticate(String loginId, String password) {
		return findByLoginId(loginId);
	}

	@Override
	public List search(UserDTO dto) {
		return userService.serach(dto);
	}

}

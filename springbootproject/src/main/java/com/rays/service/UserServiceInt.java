package com.rays.service;

import java.util.List;

import com.rays.dto.UserDTO;

public interface UserServiceInt {

	public long add(UserDTO dto);

	public UserDTO update(UserDTO dto);

	public UserDTO findById(long id);

	public UserDTO findByLoginId(String loginId);

	public UserDTO delete(long id);

	public UserDTO authenticate(String loginId, String password);

	public List search(UserDTO dto);

}

package com.rays.dao;

import java.util.List;

import com.rays.dto.UserDTO;

public interface UserDAOInt {

	public long add(UserDTO dto);

	public void update(UserDTO dto);

	public UserDTO findByPk(long pk);

	public UserDTO findByUniqueKey(String attribute, Object value);

	public void delete(UserDTO dto);

	public List serach(UserDTO dto);

}

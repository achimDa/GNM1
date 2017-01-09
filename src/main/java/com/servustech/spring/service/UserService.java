package com.servustech.spring.service;

import java.util.List;

import com.servustech.spring.model.User;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.dto.UserModelDTO;

public interface UserService {

	void addUser(BaseEntity user);

	void save(UserModelDTO userModelDTO);

	void updateUser(BaseEntity user);

	List<User> listUser(String userName);

	List<UserModelDTO> listUser();

	Object getUserById(int id, Class<? extends BaseEntity> clazz);

	void removeUser(int id, Class<? extends BaseEntity> clazz);

	/**
	 * @param areaModelDTO
	 */
	void editUser(UserModelDTO userModelDTO);

	User getUser(String username);
}

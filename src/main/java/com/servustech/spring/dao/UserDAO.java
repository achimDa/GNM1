package com.servustech.spring.dao;

import java.util.List;

import com.servustech.spring.model.User;
import com.servustech.spring.model.base.BaseEntity;

public interface UserDAO {

	void addUser(BaseEntity user);

	void updateUser(BaseEntity user);

	List<User> listUser(String userName);

	List<User> listUser();

	User getUserById(int id, Class<? extends BaseEntity> userclass);

	void removeUser(int id, final Class<? extends BaseEntity> userclass);
	
	User getUser(String username);
}
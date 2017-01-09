package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.UserDAO;
import com.servustech.spring.model.User;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.UserModelConverter;
import com.servustech.spring.model.dto.UserModelDTO;

@Service
@Transactional

public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public void addUser(final BaseEntity user) {
		userDAO.addUser(user);
	}

	@Override
	public void updateUser(final BaseEntity user) {
		userDAO.updateUser(user);
	}

	@Override
	public void save(final UserModelDTO userModelDTO) {
		final User user = new User();
		UserModelConverter.toEntity(userModelDTO, user);
		if (user.getId() == 0) {
			addUser(user);
		} else {
			updateUser(user);
		}
	}

	@Override
	public List<User> listUser(final String userName) {
		return userDAO.listUser(userName);
	}

	@Override
	public List<UserModelDTO> listUser() {
		return UserModelConverter.toDTOList(userDAO.listUser());
	}

	@Override
	public User getUserById(final int id, final Class<? extends BaseEntity> userclass) {
		return userDAO.getUserById(id, userclass);
	}

	@Override
	public void removeUser(final int id, final Class<? extends BaseEntity> userclass) {
		userDAO.removeUser(id, userclass);
	}

	@Override
	public void editUser(final UserModelDTO userModelDTO) {
		final User user = getUserById(userModelDTO.getId(), User.class);

		user.setEmail((userModelDTO.getEmail()));
		user.setNume(userModelDTO.getNume());
		user.setPrenume(userModelDTO.getPrenume());
		user.setParola(userModelDTO.getParola());
		user.setId(userModelDTO.getId());
		user.setInactiv(userModelDTO.getInactiv());
		user.setUserId(userModelDTO.getUserId());
		

		userDAO.updateUser(user);

	}

	@Override
	public User getUser(String username) {
		return userDAO.getUser(username);
	}

}
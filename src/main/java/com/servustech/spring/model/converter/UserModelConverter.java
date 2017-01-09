package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.User;
import com.servustech.spring.model.UserRole;
import com.servustech.spring.model.dto.UserModelDTO;

public class UserModelConverter {
	public static UserModelDTO toDTO(final User model) {
		final UserModelDTO userdto = new UserModelDTO();
		userdto.setId(model.getId());
		userdto.setEmail(model.getEmail());
		userdto.setNume(model.getNume());
		userdto.setPrenume(model.getPrenume());
		userdto.setParola(model.getParola());
		userdto.setInactiv(model.getInactiv());
		userdto.setUserId(model.getUserId());
		userdto.setAccessMobil(model.getAccessMobil());
		userdto.setAccessWeb(model.getAccessWeb());
		userdto.setCodLicienta(model.getCodLicienta());
		userdto.setDataNasterii(model.getDataNasterii());

		return userdto;
	}

	public static List<UserModelDTO> toDTOList(final Collection<User> userList) {
		final List<UserModelDTO> user = new ArrayList<>();
		for (final User userModel : userList) {
			user.add(toDTO(userModel));
		}
		return user;
	}

	public static void toEntity(final UserModelDTO usermod, final User userentity) {
		final UserRole userrole = new UserRole();

		userentity.setId(usermod.getId());
		userentity.setEmail(usermod.getEmail());
		userentity.setNume(usermod.getNume());
		userentity.setPrenume(usermod.getPrenume());
		userentity.setParola(usermod.getParola());
		userentity.setUserId(usermod.getUserId());
		userentity.setInactiv(usermod.getInactiv());
		userentity.setAccessMobil(usermod.getAccessMobil());
		userentity.setAccessWeb(usermod.getAccessWeb());
		userentity.setCodLicienta(usermod.getCodLicienta());
		userentity.setDataNasterii(usermod.getDataNasterii());

	}

}
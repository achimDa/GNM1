package com.servustech.spring.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.servustech.spring.model.UserRole;
import com.servustech.spring.model.dto.UserRoleModelDTO;

public class UserRoleModelConverter {
	public static UserRoleModelDTO toDTO(final UserRole model) {
		final UserRoleModelDTO userroledto = new UserRoleModelDTO();
		userroledto.setNume(model.getNume());
		userroledto.setDescriere(model.getDescriere());

		return userroledto;
	}

	public static List<UserRoleModelDTO> toDTOList(final Collection<UserRole> userroleList) {
		final List<UserRoleModelDTO> userroles = new ArrayList<>();
		for (final UserRole userroleModel : userroleList) {
			userroles.add(toDTO(userroleModel));
		}
		return userroles;
	}

	public static void toEntity(final UserRoleModelDTO userrolemod, final UserRole userroleentity) {

		userroleentity.setDescriere(userrolemod.getDescriere());
		userroleentity.setNume(userrolemod.getNume());
	}

}

package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.ProtectedAreaTypeDAO;
import com.servustech.spring.model.ProtectedAreaType;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.ProtectedAreaTypeModelConverter;
import com.servustech.spring.model.dto.ProtectedAreaTypeModelDTO;

@Service
@Transactional

public class ProtectedAreaTypeServiceImpl implements ProtectedAreaTypeService {
	@Autowired
	private ProtectedAreaTypeDAO protectedDAO;

	@Override
	public void addProtectedAreaType(final BaseEntity protectedareatype) {
		protectedDAO.addProtectedAreaType(protectedareatype);
	}

	@Override
	public void updateProtectedAreaType(final BaseEntity protectedareatype) {
		protectedDAO.updateProtectedAreaType(protectedareatype);
	}

	@Override
	public void save(final ProtectedAreaTypeModelDTO protectedModelDTO) {
		final ProtectedAreaType protectedareatype = new ProtectedAreaType();
		ProtectedAreaTypeModelConverter.toEntity(protectedModelDTO, protectedareatype);
		if (protectedareatype.getId() == 0) {
			addProtectedAreaType(protectedareatype);
		} else {
			updateProtectedAreaType(protectedareatype);
		}
	}

	@Override
	public List<ProtectedAreaType> listProtectedAreaType(final String protectedName) {
		return protectedDAO.listProtectedAreaType(protectedName);
	}

	@Override
	public List<ProtectedAreaTypeModelDTO> listProtectedAreaType() {
		return ProtectedAreaTypeModelConverter.toDTOList(protectedDAO.listProtectedAreaType());
	}

	@Override
	public ProtectedAreaType getProtectedAreaTypeById(final int id, final Class<? extends BaseEntity> protectedclass) {
		return protectedDAO.getProtectedAreaTypeById(id, protectedclass);
	}

	@Override
	public void removeProtectedAreaType(final int id, final Class<? extends BaseEntity> protectedclass) {
		protectedDAO.removeProtectedAreaType(id, protectedclass);
	}

	@Override
	public void editProtectedAreaType(final ProtectedAreaTypeModelDTO protectedModelDTO) {
		final ProtectedAreaType protectedareatype = getProtectedAreaTypeById(protectedModelDTO.getId(), ProtectedAreaType.class);

		protectedareatype.setDenumire(protectedModelDTO.getDenumire());
		protectedareatype.setId(protectedModelDTO.getId());

		protectedDAO.updateProtectedAreaType(protectedareatype);

	}

}
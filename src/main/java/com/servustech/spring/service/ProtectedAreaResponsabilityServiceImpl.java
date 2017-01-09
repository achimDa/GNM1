package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.ProtectedAreaResponsabilityDAO;
import com.servustech.spring.model.ProtectedAreaResponsability;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.ProtectedAreaResponsabilityModelConverter;
import com.servustech.spring.model.dto.ProtectedAreaResponsabilityModelDTO;

@Service
@Transactional

public class ProtectedAreaResponsabilityServiceImpl implements ProtectedAreaResponsabilityService {
	@Autowired
	private ProtectedAreaResponsabilityDAO protectedDAO;

	@Override
	public void addProtectedAreaResponsability(final BaseEntity protectedarearesponsability) {
		protectedDAO.addProtectedAreaResponsability(protectedarearesponsability);
	}

	@Override
	public void updateProtectedAreaResponsability(final BaseEntity protectedarearesponsability) {
		protectedDAO.updateProtectedAreaResponsability(protectedarearesponsability);
	}

	@Override
	public void save(final ProtectedAreaResponsabilityModelDTO protectedModelDTO) {
		final ProtectedAreaResponsability protectedarearesponsability = new ProtectedAreaResponsability();
		ProtectedAreaResponsabilityModelConverter.toEntity(protectedModelDTO, protectedarearesponsability);
		if (protectedarearesponsability.getId() == 0) {
			addProtectedAreaResponsability(protectedarearesponsability);
		} else {
			updateProtectedAreaResponsability(protectedarearesponsability);
		}
	}

	@Override
	public List<ProtectedAreaResponsability> listProtectedAreaResponsability(final String protectedName) {
		return protectedDAO.listProtectedAreaResponsability(protectedName);
	}

	@Override
	public List<ProtectedAreaResponsabilityModelDTO> listProtectedAreaResponsability() {
		return ProtectedAreaResponsabilityModelConverter.toDTOList(protectedDAO.listProtectedAreaResponsability());
	}

	@Override
	public ProtectedAreaResponsability getProtectedAreaResponsabilityById(final int id, final Class<? extends BaseEntity> protectedclass) {
		return protectedDAO.getProtectedAreaResponsabilityById(id, protectedclass);
	}

	@Override
	public void removeProtectedAreaResponsability(final int id, final Class<? extends BaseEntity> protectedclass) {
		protectedDAO.removeProtectedAreaResponsability(id, protectedclass);
	}

	@Override
	public void editProtectedAreaResponsability(final ProtectedAreaResponsabilityModelDTO protectedModelDTO) {
		final ProtectedAreaResponsability protectedarearesponsability = getProtectedAreaResponsabilityById(protectedModelDTO.getId(), ProtectedAreaResponsability.class);

		protectedarearesponsability.setDenumire(protectedModelDTO.getDenumire());
		protectedarearesponsability.setDescriere(protectedModelDTO.getDescriere());
		protectedarearesponsability.setId(protectedModelDTO.getId());
		
		protectedDAO.updateProtectedAreaResponsability(protectedarearesponsability);

	}

}
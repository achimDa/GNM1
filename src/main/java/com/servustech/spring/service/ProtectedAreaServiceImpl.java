package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.ProtectedAreaDAO;
import com.servustech.spring.model.ProtectedArea;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.ProtectedAreaModelConverter;
import com.servustech.spring.model.dto.ProtectedAreaModelDTO;

@Service
@Transactional

public class ProtectedAreaServiceImpl implements ProtectedAreaService {
	@Autowired
	private ProtectedAreaDAO protectedareaDAO;

	@Override
	public void addProtectedArea(final BaseEntity protectedarea) {
		protectedareaDAO.addProtectedArea(protectedarea);
	}

	@Override
	public void updateProtectedArea(final BaseEntity protectedarea) {
		protectedareaDAO.updateProtectedArea(protectedarea);
	}

	@Override
	public void save(final ProtectedAreaModelDTO protectedareaModelDTO) {
		final ProtectedArea protectedarea = new ProtectedArea();
		ProtectedAreaModelConverter.toEntity(protectedareaModelDTO, protectedarea);
		if (protectedarea.getId() == 0) {
			addProtectedArea(protectedarea);
		} else {
			updateProtectedArea(protectedarea);
		}
	}

	@Override
	public List<ProtectedArea> listProtectedArea(final String protectedareaName) {
		return protectedareaDAO.listProtectedArea(protectedareaName);
	}

	@Override
	public List<ProtectedAreaModelDTO> listProtectedArea(final int objectID) {
		return ProtectedAreaModelConverter.toDTOList(protectedareaDAO.listProtectedArea(objectID));
	}

	@Override
	public ProtectedArea getProtectedAreaById(final int id, final Class<? extends BaseEntity> protectedareaclass) {
		return protectedareaDAO.getProtectedAreaById(id, protectedareaclass);
	}

	@Override
	public void removeProtectedArea(final int id, final Class<? extends BaseEntity> protectedareaclass) {
		protectedareaDAO.removeProtectedArea(id, protectedareaclass);
	}

	@Override
	public void editProtectedArea(final ProtectedAreaModelDTO protectedareaModelDTO) {
		final ProtectedArea protectedarea = getProtectedAreaById(protectedareaModelDTO.getId(), ProtectedArea.class);

		protectedarea.setDenumire(protectedareaModelDTO.getDenumire());
		protectedarea.setCustodeId(protectedareaModelDTO.getCustodeId());
		protectedarea.setDescriere(protectedareaModelDTO.getDescriere());
		protectedarea.setId(protectedareaModelDTO.getId());

		protectedareaDAO.updateProtectedArea(protectedarea);

	}

}
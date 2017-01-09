package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.ProtectedArea_Has_MapDAO;
import com.servustech.spring.model.ProtectedArea_Has_Map;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.ProtectedArea_Has_MapModelConverter;
import com.servustech.spring.model.dto.ProtectedArea_Has_MapModelDTO;

@Service
@Transactional

public class ProtectedArea_Has_MapServiceImpl implements ProtectedArea_Has_MapService {
	@Autowired
	private ProtectedArea_Has_MapDAO areahasmapDAO;

	@Override
	public void addProtectedArea_Has_Map(final BaseEntity areahasmap) {
		areahasmapDAO.addProtectedArea_Has_Map(areahasmap);
	}

	@Override
	public void updateProtectedArea_Has_Map(final BaseEntity areahasmap) {
		areahasmapDAO.updateProtectedArea_Has_Map(areahasmap);
	}

	@Override
	public void save(final ProtectedArea_Has_MapModelDTO areahasmapModelDTO) {
		final ProtectedArea_Has_Map areahasmap = new ProtectedArea_Has_Map();
		ProtectedArea_Has_MapModelConverter.toEntity(areahasmapModelDTO, areahasmap);
		if (areahasmap.getId() == 0) {
			addProtectedArea_Has_Map(areahasmap);
		} else {
			updateProtectedArea_Has_Map(areahasmap);
		}
	}

	@Override
	public List<ProtectedArea_Has_Map> listProtectedArea_Has_Map(final String areahasmapName) {
		return areahasmapDAO.listProtectedArea_Has_Map(areahasmapName);
	}

	@Override
	public List<ProtectedArea_Has_MapModelDTO> listProtectedArea_Has_Map(final int objectID) {
		return ProtectedArea_Has_MapModelConverter.toDTOList(areahasmapDAO.listProtectedArea_Has_Map(objectID));
	}

	@Override
	public ProtectedArea_Has_Map getProtectedArea_Has_MapById(final int id, final Class<? extends BaseEntity> areahasmapclass) {
		return areahasmapDAO.getProtectedArea_Has_MapById(id, areahasmapclass);
	}

	@Override
	public void removeProtectedArea_Has_Map(final int id, final Class<? extends BaseEntity> areahasmapclass) {
		areahasmapDAO.removeProtectedArea_Has_Map(id, areahasmapclass);
	}

	@Override
	public void editProtectedArea_Has_Map(final ProtectedArea_Has_MapModelDTO areahasmapModelDTO) {
		final ProtectedArea_Has_Map areahasmap = getProtectedArea_Has_MapById(areahasmapModelDTO.getMap(), ProtectedArea_Has_Map.class);

		
		areahasmapDAO.updateProtectedArea_Has_Map(areahasmap);

	}

}
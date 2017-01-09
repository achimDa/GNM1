package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.AreaDAO;
import com.servustech.spring.model.Area;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.AreaModelConverter;
import com.servustech.spring.model.dto.AreaModelDTO;

@Service
@Transactional

public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDAO areaDAO;

	@Override
	public void addArea(final BaseEntity area) {
		areaDAO.addArea(area);
	}

	@Override
	public void updateArea(final BaseEntity area) {
		areaDAO.updateArea(area);
	}

	@Override
	public void save(final AreaModelDTO areaModelDTO) {
		final Area area = new Area();
		AreaModelConverter.toEntity(areaModelDTO, area);
		if (area.getId() == 0) {
			addArea(area);
		} else {
			updateArea(area);
		}
	}

	@Override
	public List<Area> listArea(final String areaName) {
		return areaDAO.listArea(areaName);
	}

	@Override
	public List<AreaModelDTO> listArea(final int objectID) {
		return AreaModelConverter.toDTOList(areaDAO.listArea(objectID));
	}

	@Override
	public Area getAreaById(final int id, final Class<? extends BaseEntity> areaclass) {
		return areaDAO.getAreaById(id, areaclass);
	}

	@Override
	public void removeArea(final int id, final Class<? extends BaseEntity> areaclass) {
		areaDAO.removeArea(id, areaclass);
	}

	@Override
	public void editArea(final AreaModelDTO areaModelDTO) {
		final Area area = getAreaById(areaModelDTO.getId(), Area.class);

		area.setDenumire(areaModelDTO.getDenumire());
		area.setIndicativ(areaModelDTO.getIndicativ());
		area.setId(areaModelDTO.getId());

		areaDAO.updateArea(area);

	}

}
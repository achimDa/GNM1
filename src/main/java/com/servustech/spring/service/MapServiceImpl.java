package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.MapDAO;
import com.servustech.spring.model.Map;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.MapModelConverter;
import com.servustech.spring.model.dto.MapModelDTO;

@Service
@Transactional

public class MapServiceImpl implements MapService {
	@Autowired
	private MapDAO mapDAO;

	@Override
	public void addMap(final BaseEntity map) {
		mapDAO.addMap(map);
	}

	@Override
	public void updateMap(final BaseEntity map) {
		mapDAO.updateMap(map);
	}

	@Override
	public void save(final MapModelDTO mapModelDTO) {
		final Map map = new Map();
		MapModelConverter.toEntity(mapModelDTO, map);
		if (map.getId() == 0) {
			addMap(map);
		} else {
			updateMap(map);
		}
	}

	@Override
	public List<Map> listMap(final String mapName) {
		return mapDAO.listMap(mapName);
	}

	@Override
	public List<MapModelDTO> listMap(final int objectID) {
		return MapModelConverter.toDTOList(mapDAO.listMap(objectID));
	}

	@Override
	public Map getMapById(final int id, final Class<? extends BaseEntity> mapclass) {
		return mapDAO.getMapById(id, mapclass);
	}

	@Override
	public void removeMap(final int id, final Class<? extends BaseEntity> mapclass) {
		mapDAO.removeMap(id, mapclass);
	}

	@Override
	public void editMap(final MapModelDTO mapModelDTO) {
		final Map map = getMapById(mapModelDTO.getId(), Map.class);

		map.setDenumire(mapModelDTO.getDenumire());
		map.setDescriere(mapModelDTO.getDescriere());
		map.setFisier(mapModelDTO.getFisier());
		map.setId(mapModelDTO.getId());

		mapDAO.updateMap(map);

	}

}
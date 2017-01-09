package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.MetaDataMapDAO;
import com.servustech.spring.model.MetaDataMap;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.MetaDataMapModelConverter;
import com.servustech.spring.model.dto.MetaDataMapModelDTO;

@Service
@Transactional

public class MetaDataMapServiceImpl implements MetaDataMapService {
	@Autowired
	private MetaDataMapDAO metamapDAO;

	@Override
	public void addMetaDataMap(final BaseEntity metamap) {
		metamapDAO.addMetaDataMap(metamap);
	}

	@Override
	public void updateMetaDataMap(final BaseEntity metamap) {
		metamapDAO.updateMetaDataMap(metamap);
	}

	@Override
	public void save(final MetaDataMapModelDTO metamapModelDTO) {
		final MetaDataMap metamap = new MetaDataMap();
		MetaDataMapModelConverter.toEntity(metamapModelDTO, metamap);
		if (metamap.getId() == 0) {
			addMetaDataMap(metamap);
		} else {
			updateMetaDataMap(metamap);
		}
	}

	@Override
	public List<MetaDataMap> listMetaDataMap(final String metamapName) {
		return metamapDAO.listMetaDataMap(metamapName);
	}

	@Override
	public List<MetaDataMapModelDTO> listMetaDataMap(final int objectID) {
		return MetaDataMapModelConverter.toDTOList(metamapDAO.listMetaDataMap(objectID));
	}

	@Override
	public MetaDataMap getMetaDataMapById(final int id, final Class<? extends BaseEntity> metamapclass) {
		return metamapDAO.getMetaDataMapById(id, metamapclass);
	}

	@Override
	public void removeMetaDataMap(final int id, final Class<? extends BaseEntity> metamapclass) {
		metamapDAO.removeMetaDataMap(id, metamapclass);
	}

	@Override
	public void editMetaDataMap(final MetaDataMapModelDTO metamapModelDTO) {
		final MetaDataMap metamap = getMetaDataMapById(metamapModelDTO.getId(), MetaDataMap.class);

		metamap.setId(metamapModelDTO.getId());
		metamap.setEtc(metamapModelDTO.getEtc());
		metamap.setNumar(metamapModelDTO.getNumar());
		
		metamapDAO.updateMetaDataMap(metamap);

	}

}
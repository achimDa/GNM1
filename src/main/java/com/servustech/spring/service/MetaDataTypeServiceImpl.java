package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.MetaDataTypeDAO;
import com.servustech.spring.model.MetaDataType;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.MetaDataTypeModelConverter;
import com.servustech.spring.model.dto.MetaDataTypeModelDTO;

@Service
@Transactional

public class MetaDataTypeServiceImpl implements MetaDataTypeService {
	@Autowired
	private MetaDataTypeDAO metatypeDAO;

	@Override
	public void addMetaDataType(final BaseEntity metatype) {
		metatypeDAO.addMetaDataType(metatype);
	}

	@Override
	public void updateMetaDataType(final BaseEntity metatype) {
		metatypeDAO.updateMetaDataType(metatype);
	}

	@Override
	public void save(final MetaDataTypeModelDTO metatypeModelDTO) {
		final MetaDataType metatype = new MetaDataType();
		MetaDataTypeModelConverter.toEntity(metatypeModelDTO, metatype);
		if (metatype.getId() == 0) {
			addMetaDataType(metatype);
		} else {
			updateMetaDataType(metatype);
		}
	}

	@Override
	public List<MetaDataType> listMetaDataType(final String metatypeName) {
		return metatypeDAO.listMetaDataType(metatypeName);
	}

	@Override
	public List<MetaDataTypeModelDTO> listMetaDataType() {
		return MetaDataTypeModelConverter.toDTOList(metatypeDAO.listMetaDataType());
	}

	@Override
	public MetaDataType getMetaDataTypeById(final int id, final Class<? extends BaseEntity> metatypeclass) {
		return metatypeDAO.getMetaDataTypeById(id, metatypeclass);
	}

	@Override
	public void removeMetaDataType(final int id, final Class<? extends BaseEntity> metatypeclass) {
		metatypeDAO.removeMetaDataType(id, metatypeclass);
	}

	@Override
	public void editMetaDataType(final MetaDataTypeModelDTO metatypeModelDTO) {
		final MetaDataType metatype = getMetaDataTypeById(metatypeModelDTO.getId(), MetaDataType.class);

		metatype.setDenumire(metatypeModelDTO.getDenumire());
		metatype.setId(metatypeModelDTO.getId());
		metatypeDAO.updateMetaDataType(metatype);

	}

}
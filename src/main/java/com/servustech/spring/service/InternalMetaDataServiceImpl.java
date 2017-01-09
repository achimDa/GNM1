package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.InternalMetaDataDAO;
import com.servustech.spring.model.InternalMetaData;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.InternalMetaDataModelConverter;
import com.servustech.spring.model.dto.InternalMetaDataModelDTO;

@Service
@Transactional

public class InternalMetaDataServiceImpl implements InternalMetaDataService {
	@Autowired
	private InternalMetaDataDAO internaldataDAO;

	@Override
	public void addInternalMetaData(final BaseEntity internaldata) {
		internaldataDAO.addInternalMetaData(internaldata);
	}

	@Override
	public void updateInternalMetaData(final BaseEntity internaldata) {
		internaldataDAO.updateInternalMetaData(internaldata);
	}

	@Override
	public void save(final InternalMetaDataModelDTO internalModelDTO) {
		final InternalMetaData internaldata = new InternalMetaData();
		InternalMetaDataModelConverter.toEntity(internalModelDTO, internaldata);
		if (internaldata.getId() == 0) {
			addInternalMetaData(internaldata);
		} else {
			updateInternalMetaData(internaldata);
		}
	}

	@Override
	public List<InternalMetaData> listInternalMetaData(final String internaldataName) {
		return internaldataDAO.listInternalMetaData(internaldataName);
	}

	@Override
	public List<InternalMetaDataModelDTO> listInternalMetaData(final int objectID) {
		return InternalMetaDataModelConverter.toDTOList(internaldataDAO.listInternalMetaData(objectID));
	}

	@Override
	public InternalMetaData getInternalMetaDataById(final int id, final Class<? extends BaseEntity> internaldataclass) {
		return internaldataDAO.getInternalMetaDataById(id, internaldataclass);
	}

	@Override
	public void removeInternalMetaData(final int id, final Class<? extends BaseEntity> internaldataclass) {
		internaldataDAO.removeInternalMetaData(id, internaldataclass);
	}

	@Override
	public void editInternalMetaData(final InternalMetaDataModelDTO internalModelDTO) {
		final InternalMetaData internaldata = getInternalMetaDataById(internalModelDTO.getId(), InternalMetaData.class);

		internaldata.setDenumire(internalModelDTO.getDenumire());
		internaldata.setExternalId(internalModelDTO.getExternalId());
		internaldata.setId(internalModelDTO.getId());
		

		internaldataDAO.updateInternalMetaData(internaldata);

	}

}
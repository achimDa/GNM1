package com.servustech.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servustech.spring.dao.IssuingUnitDAO;
import com.servustech.spring.model.IssuingUnit;
import com.servustech.spring.model.base.BaseEntity;
import com.servustech.spring.model.converter.IssuingUnitModelConverter;
import com.servustech.spring.model.dto.IssuingUnitModelDTO;

@Service
@Transactional

public class IssuingUnitServiceImpl implements IssuingUnitService {
	@Autowired
	private IssuingUnitDAO issunitDAO;

	@Override
	public void addIssuingUnit(final BaseEntity issunit) {
		issunitDAO.addIssuingUnit(issunit);
	}

	@Override
	public void updateIssuingUnit(final BaseEntity issunit) {
		issunitDAO.updateIssuingUnit(issunit);
	}

	@Override
	public void save(final IssuingUnitModelDTO issunitModelDTO) {
		final IssuingUnit issunit = new IssuingUnit();
		IssuingUnitModelConverter.toEntity(issunitModelDTO, issunit);
		if (issunit.getId() == 0) {
			addIssuingUnit(issunit);
		} else {
			updateIssuingUnit(issunit);
		}
	}

	@Override
	public List<IssuingUnit> listIssuingUnit(final String issunitName) {
		return issunitDAO.listIssuingUnit(issunitName);
	}

	@Override
	public List<IssuingUnitModelDTO> listIssuingUnit() {
		return IssuingUnitModelConverter.toDTOList(issunitDAO.listIssuingUnit());
	}

	@Override
	public IssuingUnit getIssuingUnitById(final int id, final Class<? extends BaseEntity> issunitclass) {
		return issunitDAO.getIssuingUnitById(id, issunitclass);
	}

	@Override
	public void removeIssuingUnit(final int id, final Class<? extends BaseEntity> issunitclass) {
		issunitDAO.removeIssuingUnit(id, issunitclass);
	}

	@Override
	public void editIssuingUnit(final IssuingUnitModelDTO issunitModelDTO) {
		final IssuingUnit issunit = getIssuingUnitById(issunitModelDTO.getId(), IssuingUnit.class);

		issunit.setDenumire(issunitModelDTO.getDenumire());
		issunit.setId(issunitModelDTO.getId());
		issunit.setNrInregistrare(issunitModelDTO.getNrInregistrare());

		issunitDAO.updateIssuingUnit(issunit);

	}

}
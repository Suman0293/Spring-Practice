package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.AttachmentDAOInt;
import com.rays.dto.AttachmentDTO;

@Service
@Transactional
public class AttachmentServiceImpl<T extends AttachmentDTO, D extends AttachmentDAOInt>
		implements AttachmentServiceInt {

	@Autowired
	D att;

	@Override
	public long add(AttachmentDTO dto) {
		return att.add(dto);
	}

	@Override
	public long save(AttachmentDTO dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);
		} else {
			id = add(dto);
		}
		return id;
	}

	@Override
	public AttachmentDTO findById(long id) {
		AttachmentDTO dto = att.findByPk(id);
		return dto;
	}

	@Override
	public AttachmentDTO update(AttachmentDTO dto) {
		att.update(dto);
		return dto;
	}

}

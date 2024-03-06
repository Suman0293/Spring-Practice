package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rays.dto.AttachmentDTO;

@Repository
public class AttachmentDAOImpl implements AttachmentDAOInt {

	@PersistenceContext
	EntityManager entity;

	@Override
	public long add(AttachmentDTO dto) {
		entity.persist(dto);
		return dto.getId();
	}

	@Override
	public AttachmentDTO findByPk(long pk) {
		AttachmentDTO dto = entity.find(AttachmentDTO.class, pk);
		return dto;
	}

	@Override
	public void update(AttachmentDTO dto) {
		entity.remove(dto);

	}

}

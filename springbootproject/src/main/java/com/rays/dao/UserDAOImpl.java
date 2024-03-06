package com.rays.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAOInt {

	@PersistenceContext
	EntityManager entitymanager;

	@Override
	public long add(UserDTO dto) {
		entitymanager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(UserDTO dto) {
		entitymanager.merge(dto);

	}

	@Override
	public UserDTO findByPk(long pk) {
		UserDTO dto = entitymanager.find(UserDTO.class, pk);
		return dto;
	}

	@Override
	public UserDTO findByUniqueKey(String attribute, Object value) {

		CriteriaBuilder builder = entitymanager.getCriteriaBuilder();

		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		Root<UserDTO> qRoot = cq.from(UserDTO.class);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<UserDTO> querry = entitymanager.createQuery(cq);

		List<UserDTO> list = querry.getResultList();

		UserDTO dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
		}
		return dto;
	}

	@Override
	public void delete(UserDTO dto) {
		entitymanager.remove(dto);

	}

	@Override
	public List serach(UserDTO dto) {

		CriteriaBuilder builder = entitymanager.getCriteriaBuilder();

		CriteriaQuery<UserDTO> cq = builder.createQuery(UserDTO.class);

		Root<UserDTO> qRoot = cq.from(UserDTO.class);

		cq.select(qRoot);

		if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
			cq.where(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}

		if (dto.getDob() != null) {
			cq.where(builder.equal(qRoot.get("dob"), dto.getDob() + "%"));
		}

		TypedQuery<UserDTO> querry = entitymanager.createQuery(cq);

		List<UserDTO> list = querry.getResultList();

		return list;
	}

}

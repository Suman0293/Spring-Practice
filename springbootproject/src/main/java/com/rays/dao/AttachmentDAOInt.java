package com.rays.dao;

import com.rays.dto.AttachmentDTO;

public interface AttachmentDAOInt {

	public long add(AttachmentDTO dto);

	public AttachmentDTO findByPk(long pk);

	public void update(AttachmentDTO dto);

}

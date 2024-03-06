package com.rays.service;

import com.rays.dto.AttachmentDTO;

public interface AttachmentServiceInt {

	public long add(AttachmentDTO dto);

	public long save(AttachmentDTO dto);

	public AttachmentDTO findById(long id);

	public AttachmentDTO update(AttachmentDTO dto);

}

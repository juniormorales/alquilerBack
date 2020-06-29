package com.back.alquiler.dto;

import java.util.List;

import com.back.alquiler.models.ImagenPropiedad;

public class ImagenPropiedadDTO {
	
	private List<Integer> lsIds;

	public List<Integer> getLsIds() {
		return lsIds;
	}

	public void setLsIds(List<Integer> lsIds) {
		this.lsIds = lsIds;
	}

}

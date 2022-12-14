package com.kakao.school.service;

import java.util.List;

import com.kakao.school.domain.ItemEntity;
import com.kakao.school.dto.ItemDTO;

public interface ItemService {
	
	// 전체 데이터 가져오는 
	public List<ItemDTO> allItem();

	// 하나의 데이터 가져오는 
	public ItemDTO getItem(int code);

	// DTO를 Entity로 변환하는 메서드
	public default ItemEntity dtoToEntity(ItemDTO dto) {
		
		return ItemEntity.builder()
				.code(dto.getCode())
				.name(dto.getName())
				.manufacture(dto.getManufacture())
				.pictureurl(dto.getPictureurl())
				.price(dto.getPrice())
				.build();
	}
	
	// Entity를 DTO로 변환하는 메서드
	public default ItemDTO entityToDTO(ItemEntity entity) {
		
		return ItemDTO.builder()
				.code(entity.getCode())
				.name(entity.getName())
				.manufacture(entity.getManufacture())
				.pictureurl(entity.getPictureurl())
				.price(entity.getPrice())
				.build();
	}
	
}

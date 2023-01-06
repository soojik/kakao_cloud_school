package com.kakao.school.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ItemDTO {

	private int code;
	private String name;
	private int price;
	private String manufacture;
	private String pictureurl;
}

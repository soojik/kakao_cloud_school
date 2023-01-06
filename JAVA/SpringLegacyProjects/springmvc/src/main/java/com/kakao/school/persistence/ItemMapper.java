package com.kakao.school.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kakao.school.domain.ItemEntity;
import com.kakao.school.dto.ItemDTO;

@Repository
public interface ItemMapper {

	@Select("select * from goods")
	public List<ItemEntity> allItem();

	@Select("select * from goods where code = #{code}")
	public ItemEntity getItem(int code);

}

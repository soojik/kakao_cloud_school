package di.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import domain.Good;

@Repository
public interface GoodMapper {
	
	@Select("select * from goods")
	public List<Good> allGood();

}

package di.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // NoArgsConstructor 없으면 에러
public class MemberEntity {

	private String id;
	private String password;
	private String nickname;

}

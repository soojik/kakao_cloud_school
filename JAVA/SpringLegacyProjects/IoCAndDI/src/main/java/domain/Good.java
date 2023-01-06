package domain;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Good {
	
	private int code;
	private String name;
	private String manufacture;
	private int price;
	private Date shelflife;

}

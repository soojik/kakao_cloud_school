package main.java;

import java.io.File;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONCreate {

	public static void main(String[] args) {
		ToDoVO vo1 = 
			new ToDoVO(1, 1, "한글", true);
		ToDoVO vo2 = 
			new ToDoVO(2, 11, "영어", false);
		
		//저장할 JSON 파일 생성
		File file = new File("./todo.json");
		//JSON 기록을 위한 인스턴스 생성
		ObjectMapper mapper = new ObjectMapper();
		try {
			//기록
			mapper.writeValue(file, Arrays.asList(vo1, vo2));
		}catch(Exception e) {
			System.out.println("기록 실패");
		}
	}

}

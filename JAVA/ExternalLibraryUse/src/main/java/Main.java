package main.java;

public class Main {

	public static void main(String[] args) {
		//MySQL 드라이버 로딩
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
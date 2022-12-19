package kakao.soo.basic;

import java.util.Scanner;

public class IdPassword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Scanner sc = new Scanner(System.in)){
			System.out.print("아이디를 입력하세요");
			String id = sc.nextLine();
			System.out.println("비밀번호를 입력하세요");
			String password = sc.nextLine();
		//문자열 비교할 때는 equals로 비교
		if(id.equals("root") && password.equals("1234")) {
			System.out.println("로그인성공");
		}else {
			System.out.println("로그인실패");
		}
		
		}catch(Exception e) {
        System.out.println(e.getLocalizedMessage());
     }	


}
}
package kakao.soo.basic;

import java.util.Scanner;

public class PassIf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Scanner sc = new Scanner(System.in)){
			System.out.print("점수입력 : ");
			int score=sc.nextInt();
			if(score>=90 && score<=100) {
				System.out.println("A");
			}else if(score>=80 && score<90) {
				System.out.println("B");
			}
			else if(score>=70 && score<80) {
				System.out.println("C");
			}
			else if(score>=60 && score<70) {
				System.out.println("D");
			}
			else if(score<60) {
				System.out.println("F");
				//예외처리를 else로 넣음
			}else {
				System.out.println("잘못된 점수 입력입니다");
			}

		}catch(Exception e) {
	        System.out.println(e.getLocalizedMessage());
	     }	
	}

}

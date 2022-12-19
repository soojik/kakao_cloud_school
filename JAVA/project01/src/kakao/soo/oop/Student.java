package kakao.soo.oop;

public class Student {
	
	
	public static String schoolName;
	
	//인스턴스 속성 생성
	//접근 지정자가 public 이므로 외부에서 인스턴스를 통해서 접근
	
	
	//static 초기화
	static {
		System.out.println("로그 출력");
		//static 변수사용 가능
		schoolName="만재국민학교";
		
		//num=1; 불가
	}
	
	public final int x; //이것 만은 에러 ->초기화 하지 않았음
	
	public Student() {
		x=10;
	}//생성자에서 초기화 해도 되고
	
//	public final int x=10;//이렇게 해도 됨
	
	
	public int num;
	public String name;
	public int kor;
	public int eng;
	public int mat;
	
	
	

}

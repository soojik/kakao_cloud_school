package kakao.soo.Inheritance;

public class InheritanceMain {

	public static void main(String[] args) {
		Sub sub = new Sub();
		//Sub 클래스에 만들지 않았던 setNum 과 setName 사용 가능
		sub.setNum(1);
		sub.setName("adam");
		sub.setNickname("군계");
		
		System.out.println(sub);
		
		Super s1 = new Super();
		Sub s2 = new Sub();
		
		//상위 클래스 타입의 참조형 변수에는 하위 클래스 타입의
		//인스턴스 참조를 배로 대입할 수 있습니다.
		Super s3 = new Sub();
		
		//하위 클래스 타입의 참조형 변수에는 상위 클래스 타입의
		//인스턴스 참조를 대입할 수 없습니다.
		//강제 형 변환을 하면 가능
		//Sub s4 = new Super();
		
		//s3에 대입된 인스턴스는 원래 Sub 타입이어서 문제 없음
		Sub s4 = (Sub)s3;
		//원래 타입이 Super 이기 때문에 예외 발생
		Sub s5 = (Sub)(new Super());
	}
}

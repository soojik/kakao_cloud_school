package kakao.soo.operator;

public class ArithmeticOperator {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	short s1=20;
	short s2=30;
	
	//short 사이의 덧셈이고 결과도 50이라서 short저장이 가능하지만 이 문장은 에러(short result)
	//자바의 산술연산의 최소 단위는 Int
	//자바는 s1과s2로 int를 변환해서 연산을 수행하므로 결과는 int가 되어서 short에 저장할 수 없음
	
	int result = s1+ s2;
	
	//실수의 산술연산 결과
	double d=0.1;
	double tot=0.0;
	for(int i=1; i<100; i++) {
		tot=tot+d;
	}
	
	//0.1을 100번 더했는데 10이 아니고 0.9999999998
	System.out.println("tot:"+tot);//tot:9.89999999999998
	
	//나누기 연산에서의 나누는 수 확인
	// 0으로 나눈 것이 에러가 아닐 수 있음
	//연산이 안되는 경우 Infinity나NAN이 될 수 있음
	System.out.println(5/0.0);

	}
}

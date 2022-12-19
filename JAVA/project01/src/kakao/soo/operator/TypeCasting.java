package kakao.soo.operator;

public class TypeCasting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double d = 37.7;
		// 실수를 정수에 직접 데입하면 에러가 발생
		// 실수가 정수보다 크기 때문 double>int
		//int n = d;
		//강제형변환을이용하면 대입이 가능
		int n = (int)d;
		//실수를 정수로 강제 형변환하면 소수가 버려짐
		System.out.println("n:"+n);//n:37
		
		
		short s1 = 200;
		short s2 = 300;
		//산술연산은 int 로 변환돼서 수행되므로 결과가 최소 int
		//short result = s1+s2;//error
		short result = (short)(s1+s2);
		System.out.println("result:"+result);//result:500
		
		
		short s3 = 30000;
		short s4 = 30000;
		//산술연산은 int 로 변환돼서 수행되므로 결과가 최소 int
		//short result = s1+s2;//error
		short result2 = (short)(s3+s4);
		System.out.println("result:"+result2);//result:-5536 
		//-> short는 3만얼마..?까지 저장가능하기 때문에 이상한값이 들어옴  
		
		
		//정수2개의 평균을 실수로 구하고자 하는 경우
		int score1=91;
		int score2=90;
		//하나의 형을 double로 바꾸어서 int와 연산하게 되 실수로 나옴 
		double avg=((double)score1+score2)/2;
		System.out.println(avg);// 90.5
		
		
		
	}

}

package kakao.soo.operator;

public class ShiftOperator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int x = -29;
		System.out.println(x<<2); //1번 밀 때마다 2 곱하기
		System.out.println(x>>3); //1번 밀 때마다 2 나누기 29/2 -> -14.5=>-15->-7.25=>-8/4=-4>
		System.out.println(x>>>3); //부호변경 
		System.out.println(x<<333); //32로 나눈 나머지만큼 밀기 
	}

}

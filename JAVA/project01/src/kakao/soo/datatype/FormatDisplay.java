package kakao.soo.datatype;

public class FormatDisplay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		int n=37;
		double d = 23.768952123;
		String name1 = "adam";
		String name2 ="아담12";
		
		
		System.out.printf("n:%d\n", n);
		//10자리를 확보해서 출력 
		System.out.printf("n:%10d\n", n);
		//남는 자리는 0을 추가해서 출력
		System.out.printf("n:%010d\n", n);
		
		//소수 6째자리까지 출력
		System.out.printf("d:%f\n",d );//d:23.768952
		//소수 3째자리까지 출력 - 반올림
		System.out.printf("d:%.3f\n",d );//d:23.769
		
		
		//%s는 문자열을 출력하는 것이 아니고 원래 %s는 시작위치부터 byte단위로 데이터를 가져와서
		//문자로 변환한 후 출력하는 것
		//null을 만날 때 까지 출력 =
		System.out.printf("name1:%10s\n", name1);//name1:      adam
		System.out.printf("name2:%10s\n", name2);//name2:      아담12
		
		
	}

}

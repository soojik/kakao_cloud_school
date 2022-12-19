package kakao.soo.basic;

public class ConsoleDisplay {

	public static void main(String[] args) {
		//console에 메시지 출력
		System.out.println("Message");
		//메세지와 데이터를 결합해서 출력
		System.out.println("Data:"+99);
		Integer i=120;
		int[] ar= {10,20,30};
		System.out.println(i);//i.toString() 결과를 출력함
		System.out.println(i.toString());
		System.out.println(ar);//배열은toString()하지 못함
		//toString이 재정의 되지 않아서 해시코드가 출력됨

	}

}

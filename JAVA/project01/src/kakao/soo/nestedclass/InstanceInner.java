package kakao.soo.nestedclass;

public class InstanceInner {
	
	//InstanceInner 클래스 - 클래스 안에 만들어진 클래스
	public static class Inner{

		public int score; //아무런 문제가 되지 않음
		public static int autoIncrement;// public static class Inner 이렇게 안쓰면 오류
	}

}

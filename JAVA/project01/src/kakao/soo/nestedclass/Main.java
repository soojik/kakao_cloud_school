package kakao.soo.nestedclass;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//InstanceInner instanceInner = new InstanceInner();
		//inner class가 Static이 아닐 때
		//InstanceInner.Inner inner = instanceInner.new Inner();
		
		//static inner class 의 인스턴스 생성
		InstanceInner. Inner obj = new InstanceInner.Inner();

		// 인스턴스 생성
		// 인터페이스나 추상 클래스 등을 상속해서 클래스를 만들고
		// 인스턴스를 만드는 경우
		// 변수는 대부분 인터페이스나 추상 클래스 이름으로 만들고
		// 생성자는 사용하고자 하는 클래스의 생성자를 이용하는 경우가 많다.
		// 상위 클래스나 인터페이스로 만들어진 변수에
		// 하위 클래스의 인스턴스 참조를 대입할 수 있다.
		// 이렇게 만들어진 변수는 상위 클래스나 인터페이스에 존재하는 이름만 호출 가능
		// 실제 호출되는 것은 생성자를 따라 간다.
		SampleAble sampleAble = new SampleAbleImpl();

		// 메서드 호출
		sampleAble.method01();

		// Anonymous Class 사용
		SampleAble anonymous = new SampleAble() {
			@Override
			public void method01() {
				System.out.println("anonymous 이용");
			}
		};

		// 메서드 한번만 호출할거라면 변수 생성하지 않고 가능
		new SampleAble() {

			@Override
			public void method01() {
				System.out.println("변수 만들지 않고 Anonymous 사용");
			}
		}.method01();

		anonymous.method01();

		// public 클래스에 public static 속성은 어디서든 호출 가능
		GlobalData.global = 10;

		/* 생성자의 접근 지정자를 private으로 설정해 생성 시 에러
		Singleton singleton01 = new Singleton();
		Singleton singleton02 = new Singleton();
		*/

		Singleton singleton01 = Singleton.sharedInstance();
		Singleton singleton02 = Singleton.sharedInstance();

		// 해시코드 확인
		System.out.println("singleton01 == singleton02 : " + (System.identityHashCode(singleton01) == System.identityHashCode(singleton02)));

		Table row01 = new Table();
		System.out.println(row01.getNum());
		Table.setStep(10);
		Table row02 = new Table();
		System.out.println(row02.getNum());


	}

}

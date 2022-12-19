package kakao.soo.nestedclass;

// 포함되는 클래스
public class EmbeddedClass {
    public int score;
    // 포함하는 클래스의 인스턴스의 참조를 기억하기 위한 속성
    private Embed embed;

    // 생성자를 이용한 주입
    // 인스턴스 만들 때 호출
    // 속도에서 이득을 보지만 메모리 효율은 떨어질 수 있다.
    // 서버 측에서는 메모리는 하드웨어 쪽에서 늘리면 되고 속도가 중요하니까
    public EmbeddedClass(Embed embed) {
        this.embed = embed;
    }

    // setter를 이용한 주입
    // 필요할 때 호출
    // 생성자 주입과 반대로 메모리 효율에서 이득을 볼 수는 있다.
    // 클라이언트 측에서는 메모리가 부족하면 쉽게 기기를 바꿀 수 없고, 로드가 안될 경우가 있으니까
    public void setEmbed(Embed embed) {
        this.embed = embed;
    }

    public void set() {
        // 여기서 포함하는 클래스의 멤버를 수정

    }
}

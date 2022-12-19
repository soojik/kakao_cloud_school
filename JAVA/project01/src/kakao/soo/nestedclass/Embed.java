package kakao.soo.nestedclass;

public class Embed {

    public String name;

    public void createEmbedded() {
        // 다른 클래스의 인스턴스 생성
        // 생성자롤 이용한 주입 - 생성자를 호출할 때 현재 인스턴스의 참조 넘겨주는 것
        EmbeddedClass obj = new EmbeddedClass(this);
        obj.setEmbed(this);

        obj.score = 100;
        System.out.println(obj.score);
    }
}

package kakao.soo.java.lang;

import java.util.Arrays;
import java.util.Objects;

// 하나의 데이터 묶음을 표현하기 위한 클래스 - Value Object(VO)
// 복제 clone 메서드 이용하기 위해 Cloneable 인터페이스 implements
public class Data implements Cloneable {
    private int n;
    private String name;
    private String[] nicknames;

    public Data() {}

    // 속성 대입받아 생성하는 생성자
    // 인스턴스 빠르게 초기화하기 위해서 사용
    public Data(int n, String name, String[] nicknames) {
        super();
        this.n = n;
        this.name = name;
        this.nicknames = nicknames;
    }

    // 접근자 메서드
    public int getN() {
        return n;
    }

    public String getName() {
        return name;
    }

    public String[] getNicknames() {
        return nicknames;
    }

    // 생성자 메서드
    public void setN(int n) {
        this.n = n;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNicknames(String[] nicknames) {
        this.nicknames = nicknames;
    }

    // 인스턴스의 내용을 빠르게 만들기, 출력하기 위해 사용
    // 디버깅을 위한 메서드
    @Override
    public String toString() {
        return "Data{" +
                "n=" + n +
                ", name='" + name + '\'' +
                ", nicknames=" + Arrays.toString(nicknames) +
                '}';
    }

    public Data clone() {
        Data data = new Data();

        // 이 방식은 얕은 복사(weak copy)
        // n은 숫자 데이터이므로 바로 복제 가능
        // name과 nicknames는 문자열, 배열 데이터이기 때문에 바로 대입하면 또 참조가 대입되는 형식
        data.setN(this.n);
        data.setName(this.name);

        // 배열 복제한 후 대입 - 깊은 복사
        String[] nicknames = Arrays.copyOf(this.nicknames, this.nicknames.length);

        data.setNicknames(nicknames);

        return data;
    }

    @Override
    public boolean equals(Object o) {
        /*
        boolean result = false;

        // n과 name 속성 값이 같다면 true 리턴
        if (this.n == ((Data)o).getN() && this.name.equals(((Data)o).getName())) {
            return true;
        }
         */

        Data obj = (Data) o;

        // Objects.hash(데이터 나열)을 이용하면
        // 데이터를 가지고 정수로 만든 해시코드 생성
        return ((Objects.hash(n, name)) == (Objects.hash(obj.getN(), obj.getN())));
    }
}

package kakao.soo.java.lang;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*
        String[] nicknames = {"ning", "닝이줘"};

        // 인스턴스 생성
        Data original = new Data(1, "닝닝", nicknames);

        // 인스턴스 참조 복사
        // 참조 대상이나 원본이 내부 데이터를 변경하면 영향
        Data copy1 = original;
        System.out.println(original);

        copy1.setN(2);
        System.out.println(original);

        // 자바는 복제를 이용하고자 하는 경우 clone 메서드를 재정의
        // 재정의할 때 Cloneable 인터페이스를 implements 하기를 권장

        Data copy2 = original.clone();
        System.out.println(original);

        copy2.setN(3);
        System.out.println(original);

        String[] nicknames2 = copy2.getNicknames();
        nicknames2[0] = "ning ning";
        System.out.println(original);

        Data data1 = new Data(1, "구름", null);
        Data data2 = new Data(1, "구름", null);

        System.out.println(data1 == data2);
        System.out.println(data1.equals(data2));


        double d1 = 1.60000000000000;
        double d2 = 0.10000000000000;
        System.out.println("d1 + d2 : " + (d1+d2));

        BigDecimal b1 = new BigDecimal("1.60000000000000");
        BigDecimal b2 = new BigDecimal("0.10000000000000");
        System.out.println("b1 + b2 : " + (b1.add(b2)));
         */

        String str = "Hello";
        System.out.println("String hashcode : " + System.identityHashCode(str));
        str += " Java";
        System.out.println("String hashcode : " + System.identityHashCode(str));

        // StringBuilder에는 변경 가능한 문자열을 저장
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println("StringBuilder hashcode : " + System.identityHashCode(sb));
        // 문자열을 추가하면 현재 저장된 공간에 이어붙이기 수행
        sb.append(" Java");
        // 해시코드가 이전과 같음
        System.out.println("StringBuilder hashcode : " + System.identityHashCode(sb));
    }
}

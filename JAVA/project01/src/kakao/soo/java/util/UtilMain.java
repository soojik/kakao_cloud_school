package kakao.soo.java.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UtilMain {
    public static void main(String[] args) {
        /* 1부터 45 사이의 랜덤한 정수 가져오기
        // seed 설정하지 않으면 실행할 때마다 다른 수, 설정하면 매번 같은 수
        Random r = new Random();
        int lotto = r.nextInt(45) + 1;
        System.out.println(lotto);
        */

        /*
        관계형 데이터베이스에서는 하나의 테이블에 기본키를 설정하면
        이 기본키에 배열로 매핑되는 데이터는 삽입할 수 없다.
        제 1정규형 (NF) : 모든 속성의 값은 원자값이어야 한다.
        Atomic Value(원자 값) : 더 이상 분해할 수 없는 값
        하나의 게시글에 여러 개의 첨부파일 삽입하는 경우, 첨부 파일 테이블을 별도로 만든다.
        이렇게 별도의 테이블로 만들면, 게시글로 가져와 첨부 파일명을 출력하려면 join이 필요하다.
        관계형 데이터베이스에서 가장 많은 시간을 소요하는 것이 join이라고 할 수 있다.
        이럴 때 join 시간을 아끼고자 할 때, 첨부 파일 이름들을 하나로 묶어서 저장해도 된다.
        구분자 - 전통적으로 , 를 가장 많이 사용했는데 이 파일이 csv
         */

        // ArrayList : 데이터를 물리적으로 연속해서 저장
        // LinkedList 보다 조회 속도는 빠르다.
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("image1.png");
        list.add("image3.png, image5.png, image7.png");

        // list 순회
        for (String str : list) {
            // null 확인
            if (str == null) {
                System.out.println("첨부파일 없음");
            } else {
                String[] splited_str = str.split(",");
                System.out.println(Arrays.toString(splited_str));
            }
        }

        String[] image0 = null;
        String[] image1 = {"image100.png"};
        String[] image2 = {"image101.png", "image102.png"};

        if (image0 == null) {
            System.out.println("default.png");
        }

        if (image1 != null && image1.length == 1) {
            System.out.println(image1[0]);
        } else if (image1.length > 1) {
            StringBuilder x = new StringBuilder(image1[0]);
            int len = image1.length;
            for (int i = 1; i < len; i++) {
                x.append(", ").append(image1[i]);
            }
        }

        if (image2 != null && image2.length == 1) {
            System.out.println(image2[0]);
        } else if (image2.length > 1) {
            StringBuilder x = new StringBuilder(image2[0]);
            int len = image2.length;
            for (int i = 1; i < len; i++) {
                x.append(", ").append(image2[i]);
            }
            System.out.println(x);
        }

    }
}

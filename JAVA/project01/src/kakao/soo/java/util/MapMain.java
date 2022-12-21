package kakao.soo.java.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 데이터 클래스 - VO
// Model
class VO {
    private int number;
    private String name;

    public VO(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public VO() {
        super();
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "VO{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}

public class MapMain {
    public static void main(String[] args) {
        /* RDB
        VO vo = new VO(1, "hanni");

        // 데이터 출력 - ViewVO 클래스의 num -> number
        모델의 근원이 되는 VO 클래스 안의 속성 이름을 변경하면 View도 수정이 되어야 한다.
        관계형 데이터베이스는 VO 클래스를 활용
        System.out.println("vo(name) : " + vo.getName());
        System.out.println("vo(num) : " + vo.getNum());

         */

        /* Mongo DB

        Map<String, Object> map = new HashMap<>();

        // 데이터 저장
        map.put("num", 1);
        map.put("name", "hanni");

        // map의 모든 키를 가져와서 출력
        Set<String> keys = map.keySet();

        for (String key : keys) {
            System.out.println(key + " : " + map.get(key));
        }
         */

        // 배열의 배열 - 2차원 배열 : Matrix - 태그가 없다
        String[] doro = {"배유나", "박정아", "문정원"};
        String[] lg = {"안혜진", "유서연"};

        // 팀 추가
        String[] ginseng = {"염혜선", "이소영"};

        String[][] volley = {doro, lg, ginseng};

        // 문제점 - 데이터가 추가되면 바꿔줘야할 코드 늘어나
        int i = 0;
        for (String[] strings : volley) {
            if (i == 0) {
                System.out.print("도로공사:\t");
            } else {
                System.out.print("lg:\t");
            }
            for (String name : strings) {
                System.out.print(name + "\t");
            }
            System.out.println();
            i++;
        }

        // 2차원 배열 대신에 Map의 배열 - Table
        Map<String, Object> map1 = new HashMap<>();

        map1.put("name", "도로공사");
        map1.put("team", doro);


        Map<String, Object> map2 = new HashMap<>();

        map2.put("name", "LG");
        map2.put("team", lg);

        Map<String, Object> map3 = new HashMap<>();

        map3.put("name", "KGC 인삼공사");
        map3.put("team", ginseng);

        Map[] v = {map1, map2, map3};

        // 2차원 배열보다 Map 사용하는 것이 데이터 추가에 유연함을 볼 수 있다.
        for (Map map : v) {
            System.out.print(map.get("name") + " : \t");
            String[] team = (String[]) map.get("team");
            for (String name : team) {
                System.out.print(name + "\t");
            }
            System.out.println();
        }

    }
}

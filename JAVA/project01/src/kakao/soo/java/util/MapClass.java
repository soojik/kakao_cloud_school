package kakao.soo.java.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapClass {
    public static void main(String[] args) {
        // Map의 활용
        // 데이터의 종류가 1가지라면 Object 대신에 그 자료형을 기재해도 된다.
        // 어떤 데이터 저장하기 정하기 전까지는 Object 사용하면 된다.
        Map<String, Object> map = new HashMap<>();

        // 데이터 추가
        map.put("name", "hanni");
        map.put("group", "newjeans");
        map.put("age", 19);

        // 데이터 가져오기
        System.out.println(map.get("name"));

        // 중복된 키를 이용한 데이터 삽입
        map.put("name", "해린");
        // name이라는 키의 value를 수정
        System.out.println(map.get("name"));

        // 없는 키를 이용해서 가져왔을 때 - Java는 null
        System.out.println(map.get("job"));

        // Value를 Object로 설정했을 때 사용
        // Value를 삽입할 때 String이지만, Map 만들 때는 Value의 type을 Object로 설정했기 때문에
        // get을 해서 데이터를 원상 복구하고자 하면 강제 형 변환을 해야한다.

        /* error
        String value = map.get("name");
         */

        String value = (String) map.get("name");
        System.out.println(value);

        Set<String> keys = map.keySet();
        System.out.println("key list : " + keys);
    }
}

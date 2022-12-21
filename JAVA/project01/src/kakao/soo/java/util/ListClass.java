package kakao.soo.java.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListClass {
    public static void main(String[] args) {
        // 정수 저장하기 위한 ArrayList
        List<Integer> al = new ArrayList<>();
        // 정수 저장하기 위한 LinkedList
        List<Integer> li = new LinkedList<>();

        for (int i = 0; i < 1000000; i++) {
            al.add(i);
            li.add(i);
        }

        long start, end;

        // ArrayList와 LinkedList 조회 시간 비교
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Integer integer = al.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList 조회 시간 : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            Integer integer = li.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("LinkedList 조회 시간 : " + (end - start));

    }
}

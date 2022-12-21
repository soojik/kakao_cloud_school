package kakao.soo.java.util;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueClass {
    public static void main(String[] args) {
        // 데이터를 sort된 것처럼 저장
        Queue<String> queue = new PriorityQueue<>();

        queue.offer("hihi");
        queue.offer("iieeiiee");
        queue.offer("dddddggggggg");

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}

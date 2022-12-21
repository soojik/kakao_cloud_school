package kakao.soo.java.util;

import java.util.Stack;

public class StackClass {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.add("hihi");
        stack.add("iieeiiee");
        stack.add("dddddggggggg");

        System.out.println(stack);

        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(stack.pop());
        }
    }
}

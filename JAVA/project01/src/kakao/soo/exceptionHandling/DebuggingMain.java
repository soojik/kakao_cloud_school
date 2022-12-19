package kakao.soo.exceptionHandling;

import java.util.Arrays;

public class DebuggingMain {
    public static void test() {
        int x = 10;
        int[] arr = {20, 50, 30 ,70, 20};
        x = 20;
        System.out.println(x);
        arr[2] = 80;
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        test();
    }
}

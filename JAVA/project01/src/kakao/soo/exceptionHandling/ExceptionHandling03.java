package kakao.soo.exceptionHandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExceptionHandling03 {
    public static void main(String[] args) {

        String msg = null;

        // br의 처리가 끝나면 자동으로 close()를 호출
        // jdk 1.7에서 추가된 문법 - try ~ resource
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            msg = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(msg);

    }
}

package kakao.soo.java.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexMain {
    static String[] arr = {"abc", "asc", "aiic", "amanda"};


    public static void main(String[] args) {
        // 정규식 생성
        Pattern p = Pattern.compile("^a.c$");

        for (String str : arr) {
            // 정규식 패턴과 일치하는지 조사
            Matcher matcher = p.matcher(str);
            System.out.println(str + " : " + matcher.find());
        }
    }
}

package kakao.soo.io;

import java.io.File;
import java.util.Date;

public class FileInformationMain {
    public static void main(String[] args) {
        File f = new File("/Users/soo/Downloads/java.util.txt");

        if (f.exists()) {
            System.out.println("파일 크기(KB) : " + f.length() / 1024);
            System.out.println("마지막 수정 날짜 : " + new Date(f.lastModified()));
        } else {
            System.out.println("파일 존재하지 않음");
        }
    }
}

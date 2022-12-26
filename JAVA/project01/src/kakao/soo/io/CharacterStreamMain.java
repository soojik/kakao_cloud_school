package kakao.soo.io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CharacterStreamMain {
    public static void main(String[] args) {

        // 오늘 날짜로 파일 명 생성 - 2022-12-23

        // path - 기본 디렉토리 경로 생성
        String directory = "/Users/soo/Desktop/";
        // 오늘 날짜 문자열 만들기
        // SimpleDateFormat 클래스 이용해 날짜 데이터 포맷 먼저 생성
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 포맷에 맞춰 오늘 날짜 데이터(new Date()) 가져와 적용 후, 반환된 String 데이터를 filename이 참조하도록
        String filename = simpleDateFormat.format(new Date());

        String path = String.format("%s%s%s", directory, filename, ".log");

        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println("안녕~");
            pw.println("반가워~");
            pw.println("나는 24살~");
            pw.flush();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

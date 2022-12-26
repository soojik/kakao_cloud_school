package kakao.soo.io;

import java.io.*;
import java.util.Arrays;

public class ByteStreamMain {
    public static void main(String[] args) {
        /*
        // 파일에 바이트 단위로 기록
        // java 1.7 이후에 등장한 방식으로, try(FileOutputStream ... ) 으로 생성해주면 try ~ catch 구문이 끝나면 자동으로 close()
        try (FileOutputStream fos = new FileOutputStream("./sample.txt", true)) {
            String msg = "Hello Stream\n";
            fos.write((msg.getBytes()));
            fos.flush();
            // fos.close() -> 여기서 close하게 되면 도중에 예외 발생해서 catch로 넘어갈 때 close()가 안된다.
        } catch (IOException e) {
            // 근데 catch 구문에는 close()가 안된다.
            System.out.println(e.getLocalizedMessage());
        }

         */

        /*
        try (FileInputStream fis = new FileInputStream("./sample.txt")) {
            while (true) {
                // 읽을 수 있는 크기로 byte 배열 생성
                byte[] b = new byte[fis.available()];
                int len = fis.read(b);
                if (len <= 0) {
                    System.out.println("읽을 데이터 없음");
                    break;
                } else {
                    // System.out.println(Arrays.toString(b));
                    // 위에서는 ascii 값을 출력
                    // 텍스트가 아닌 경우 괜찮지만 텍스트인 경우는 문자열로 변환해서 출력
                    System.out.println(new String(b));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

         */

        // 버퍼 단위로 기록 - 데이터 뒤에 계속 더하도록
        try (PrintStream ps = new PrintStream(new FileOutputStream("./buffer.txt", true))) {
            String msg = "Hello Stream\n";
            // write - 바이트 단위 기록
            ps.write(msg.getBytes());
            // print - 문자열을 스스로 바이트로 변환해 기록
            ps.print(msg);
            ps.flush();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

        // 버퍼 단위로 읽기
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./buffer.txt"))) {
            while (true) {
                /* 한꺼번에 받아오기
                byte[] b = new byte[bis.available()];
                if (bis.read() > 0) {
                    System.out.println(new String(b));
                }
                 */

                /* 문제 1
                // 일반적으로 패킷의 크기가 16 byte == 128 bit
                byte[] b = new byte[16];

                int len = bis.read(b, 0, b.length);
                if (len <= 0) {
                    break;
                }

                // 받을 내용 가지고 작업
                // 다운로드라면 파일에 기록
                // 문자열이라면 하나로 모아서 읽어야 한다.

                System.out.println(new String(b));
                한글의 경우, 16바이트씩 나눈 b를 읽으면 깨질 수 있기 때문에

                해결 방법
                1. 한번에 받아오는 데이터 크기를 1024로 설정

                // 문제 2
                byte[] b = new byte[1024] 로 한 단위를 설정하게 되면
                실제로 데이터가 1024가 안되었을 땐 뒤에 공백이 생긴다.
                문자열 출력할 때는 좌우공백 제거가 필요하다.

                해결 방법
                1. new String(b).trim() - 불러온 데이터 b를 String으로 바꾼 후 trim() 사용
                2. new String(b, 0, len) - 0 ~ len 범위 설정으로, 실제 데이터 있는 만큼만 불러오도록
                 */

                /*
                Surrogate pairs라고, 한글이나 특수문자 만들 ㄸㅐ, 실제 java는 두개의 문자를 하나로 합쳐 만들기 때문에
                https://m.blog.naver.com/nakim02/221478419731
                입력 받는 데이터 고려 필요
                 */

                byte[] b = new byte[1024];



            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

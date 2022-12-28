package kakao.soo.network;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class WebTextDownload {
    public static void main(String[] args) {
        try {
            // kakao.com의 주소 정보 가져오기
            InetAddress inetAddress = InetAddress.getByName("www.naver.com");

            // kakao.com에 연결
            Socket socket = new Socket(inetAddress, 443);

            // 요청을 전송할 수 있는 스트림 생성
            PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            // 요청 전송
            ps.println("GET https://www.naver.com");
            ps.flush();

            // 문자 단위로 전송을 받기 위한 스트림 생성
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 여러 줄의 문자열을 하나로 만들기
            StringBuilder sb = new StringBuilder();

            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str).append("\n");
            }

            String html = sb.toString();
            System.out.println(html);

            // 사용한 자원 정리
            br.close();
            ps.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package kakao.soo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try {
            // 서버 소켓 생성
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                System.out.println("서버 대기 중");

                // 대기
                Socket socket = serverSocket.accept();

                // 접속한 클라이언트 정보 확인
                System.out.println(socket);

                // 클라이언트가 전송한 내용 읽기
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 한줄만 받아올 때는 String 으로 받아와도 무관
                String msg = br.readLine();

                System.out.println(msg);

                br.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package kakao.soo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.0.24");

            // 9999 번 포트로 연결
            Socket socket;
            PrintWriter printWriter;

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                socket = new Socket(inetAddress, 9999);
                // 문자열 전송 위한 스트림
                printWriter = new PrintWriter(socket.getOutputStream());
                System.out.print("보낼 메세지 : ");
                String msg = br.readLine();

                printWriter.println(msg);
                printWriter.flush();

                if (msg.equals("exit")) {
                    break;
                }
            }

            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package kakao.soo.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) {

        // UDP 소켓 생성
        try (DatagramSocket dsoc = new DatagramSocket(8888)) {
            byte[] data = new byte[65536];

            DatagramPacket dp = new DatagramPacket(data, data.length);

            while (true) {
                // 데이터 받기
                dsoc.receive(dp);

                // 클라이언트 정보 확인
                System.out.println(dp.getAddress());

                // 받은 메세지 출력
                System.out.println(new String(dp.getData()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

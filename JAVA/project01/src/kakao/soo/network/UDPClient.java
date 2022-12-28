package kakao.soo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msg = "";
            while (!msg.equals("exit")) {
                System.out.print("보낼 메세지 : ");
                msg = br.readLine();

                InetAddress addr = InetAddress.getByName("192.168.0.24");

                // 소켓 생성
                DatagramSocket dsoc = new DatagramSocket();

                // 보낼 패킷 생성
                DatagramPacket dpac = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, 8888);

                dsoc.send(dpac);
                dsoc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

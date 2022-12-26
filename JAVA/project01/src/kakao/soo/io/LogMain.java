package kakao.soo.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogMain {
    public static void main(String[] args) {
        File file = new File("log.txt");

        // 존재하는지 확인
        if (!file.exists()) {
            System.out.println("존재하지 않는 파일");
            return;
        }

        // 문자열 읽기위한 스트림 생성
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // 더이상 데이터가 없을 때 까지 줄 단위로 읽어서 출력
            String log;
            // traffic의 합계를 구할 변수
            long traffic_sum = 0;

            // IP 별 접속 횟수와 IP 별 트래픽 합계를 구해라.
            HashMap<String, long[]> IP_map = new HashMap<>();

            while ((log = br.readLine()) != null) {
                // 공백 기준으로 분할
                String[] arr = log.split(" ");

                String IP = arr[0];
                Long now_traffic;

                try {
                    // 정수로 변환해서 더하는데 예외가 발생하면 넘어가도록 하기 위해 try ~ catch 사용
                    now_traffic = Long.parseLong(arr[arr.length - 1]);
                    traffic_sum += now_traffic;
                } catch (NumberFormatException e) {
                    now_traffic = 0L;
                }

                if (!IP_map.containsKey(IP)) {
                    IP_map.put(arr[0], new long[]{1, now_traffic});
                } else {
                    long[] IP_info = IP_map.get(arr[0]);
                    IP_info[0]++;
                    IP_info[1] += now_traffic;
                    IP_map.put(arr[0], IP_info);
                }
            }

            for (String key : IP_map.keySet()) {
                System.out.println(key + " appears " + IP_map.get(key)[0] + " times, and traffic sum is " + IP_map.get(key)[1]);
            }

            System.out.println("sum of traffic : " + traffic_sum);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

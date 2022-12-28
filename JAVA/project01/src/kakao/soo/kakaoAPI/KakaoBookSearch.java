package kakao.soo.kakaoAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class KakaoBookSearch {
    public static void main(String[] args) {
        // 카카오 도서 검색 API
        new Thread(new Runnable() {
            @Override
            public void run() {
                // url 만들기
                // get 방식에서의 parameter는 반드시 encoding 되어야 한다.
                StringBuilder addr = new StringBuilder("https://dapi.kakao.com/v3/search/book");
                addr.append("?target=title&query=");
                try {
                    addr.append(URLEncoder.encode("미움받을용기", "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    URL url = new URL(addr.toString());

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    con.setConnectTimeout(30000);
                    con.setUseCaches(false);
                    con.setRequestMethod("GET");

                    con.setRequestProperty("Authorization", "KakaoAK 1540268f26cf9428dc8d5e06a18aa583");

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String str;

                    while ((str = br.readLine()) != null) {
                        sb.append(str).append("\n");
                    }

                    System.out.println(sb.toString());

                    br.close();
                    con.disconnect();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

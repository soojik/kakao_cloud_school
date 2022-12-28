package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MariaDB {
    public static void main(String[] args) {

        // 데이터베이스 접속에 필요한 정보 불러오기
        String driver = null;
        String url = null;
        String id = null;
        String passwd = null;

        // 읽어올 파일 생성
        File file = new File("./db.properties");
        try (FileInputStream fis = new FileInputStream(file)) {
            // 파일의 내용을 properties에 저장
            Properties properties = new Properties();
            properties.load(fis);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            id = properties.getProperty("id");
            passwd = properties.getProperty("passwd");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 데이터베이스 드라이버 로드
        // 드라이버 의존성을 설정하지 않거나 클래스 이름일 틀리면 예외 발생
        try {
            Class.forName(driver);
            System.out.println("드라이버 로드 성공");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 데이터베이스 접속
        try (Connection con = DriverManager.getConnection(url, id, passwd)) {
            System.out.println(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

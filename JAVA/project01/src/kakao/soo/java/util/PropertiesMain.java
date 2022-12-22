package kakao.soo.java.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesMain {
    public static void main(String[] args) {

        // 인스턴스를 1개만 만들 때 참조하는 이름은 클래스 이름을 그대로 작성하고 첫글자만 소문자로 변경
        // Spring이 bean을 만들 때 사용하는 방법
        Properties properties = new Properties();

        properties.setProperty("num", "1");
        properties.setProperty("name", "ningning");

        System.out.println(properties.getProperty("num"));
        System.out.println(properties.getProperty("name"));

        // key 가져오기 - 반복자 사용
        Enumeration<Object> keys = properties.keys();

        while (keys.hasMoreElements()) {
            System.out.println(keys.nextElement());
        }

        try {
            // 파일로 저장
            properties.store(new FileOutputStream("./pro.properties"), "텍스트로 저장");
            properties.storeToXML(new FileOutputStream("./pro.xml"), "XML로 저장");
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

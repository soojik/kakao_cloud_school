package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GoodsDAOImpl implements GoodsDAO {
    // 싱글톤 만들기 위한 코드
    // 외부에서 인스턴스 생성하지 못하도록 생성자를 private으로 설계
    private GoodsDAOImpl() {
    }

    // 참조를 저장할 변수를 static으로 생성
    private static GoodsDAO goodDAO;

    // 변수가 null이면 생성하고 아니면 바로 리턴하도록
    public static GoodsDAO getInstanece() {
        if (goodDAO == null) {
            goodDAO = new GoodsDAOImpl();
        }

        return goodDAO;
    }

    // 데이터베이스 연결
    private Connection connection;
    // SQL 실행
    private PreparedStatement preparedStatement;
    // select 구문의 결과
    private ResultSet resultSet;

    static File file = new File("./db.properties");

    // static 초기화 - 클래스가 로드 될 때 1번만 수행
    // static 속성만 사용 가능
    // e.g.) Class.forName
    static {
        try (FileInputStream fis = new FileInputStream(file)){
            Properties properties = new Properties();
            properties.load(fis);

            Class.forName(properties.getProperty("driver"));
            System.out.println("드라이버 로드 성공");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    // 초기화 - 생성자를 호출할 때마다 먼저 호출
    // 이 영역은 init이라는 메서드로 생성
    // 모든 속성 사용이 가능
    // e.g.) connection
    {
        try (FileInputStream fis = new FileInputStream(file)) {
            Properties properties = new Properties();
            properties.load(fis);

            String url = properties.getProperty("url");
            String id = properties.getProperty("id");
            String passwd = properties.getProperty("passwd");

            connection = DriverManager.getConnection(url, id, passwd);
            System.out.println("conneciton : " + connection);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Goods> getAll() {
        // list는 조회할 데이터가 없더라고 인스턴스는 생성되어야 한다.
        // 조회할 데이터가 없다는 사실은 size()가 0이다.
        // 데이터가 없다는 사실을 확인하려면 반환된 List 인스턴스의 size가 0이라는 것을 확인
        List<Goods> goodsList = new ArrayList<>();

        // 원래는 이 위치의 작업을 Hibernate 또는 MyBatis가 해준다.
        try {
            // SQL 실행 클래스의 인스턴스 생성
            preparedStatement = connection.prepareStatement("select * from goods");

            // (매개변수 없을 땐 바로) SQL 실행
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Goods goods = new Goods();
                goods.setCode(resultSet.getString("code"));
                goods.setName(resultSet.getString("name"));
                goods.setManufacture(resultSet.getString("manufacture"));
                goods.setPrice(resultSet.getInt("price"));

                goodsList.add(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goodsList;
    }

    public Goods findByCode(String code) {
        Goods goods = null;
        try {
            preparedStatement = connection.prepareStatement("select * from goods where code=?");

            // ?에 바인딩
            // index가 1부터 시작
            preparedStatement.setString(1, code);

            resultSet = preparedStatement.executeQuery();

            // id, nickname 중복 검사
            // 조회해서 null이면 중복되지 않은거, null이 아니라 데이터가 존재하면 이미 사용중인 데이터
            if (resultSet.next()) {
                goods = new Goods();

                goods.setCode(resultSet.getString("code"));
                goods.setName(resultSet.getString("name"));
                goods.setManufacture(resultSet.getString("manufacture"));
                goods.setPrice(resultSet.getInt("price"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return goods;
    }

    @Override
    public int insertGoods(Goods goods) {
        int result = 0;

        try {
            // auto commit 해제
            connection.setAutoCommit(false);

            // 삽입, 삭제, 갱신 시에는 transaction 고려 필요 - 작업 성공/실패에 따라 commit/rollback
            preparedStatement = connection.prepareStatement("insert into goods values (?, ?, ?, ?)");

            preparedStatement.setString(1, goods.getCode());
            preparedStatement.setString(2, goods.getName());
            preparedStatement.setString(3, goods.getManufacture());
            preparedStatement.setInt(4, goods.getPrice());

            // executeQuery - ResultSet 반환
            // executeUpdate - int 반환
            result = preparedStatement.executeUpdate();

            // 삽입 성공 시 commit으로 반영
            connection.commit();
        } catch (SQLException e) {
            try {
                // 삽입 실패 시 rollback으로 되돌리기
                connection.rollback();
            } catch (SQLException exception) {
                System.out.println(e.getLocalizedMessage());
            }
            System.out.println(e.getLocalizedMessage());
        }

        return result;
    }

    @Override
    public List<Goods> likeGoods(String content) {
        List<Goods> goodsList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("select * from goods where name like ? or manufacture like ?");

            preparedStatement.setString(1, "%" + content + "%");
            preparedStatement.setString(2, "%" + content + "%");

            resultSet = preparedStatement.executeQuery();

            Goods goods = null;
            while (resultSet.next()) {
                goods = new Goods();
                goods.setCode(resultSet.getString("code"));
                goods.setName(resultSet.getString("name"));
                goods.setManufacture(resultSet.getString("manufacture"));
                goods.setPrice(resultSet.getInt("price"));

                goodsList.add(goods);
            }

        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return goodsList;
    }
}

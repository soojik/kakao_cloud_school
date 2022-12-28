package main.java;

import java.util.List;

// goods 테이블에 수행할 데이터베이스 작업의 원형을 소유할 인터페이스
public interface GoodsDAO {
    // goods 테이블의 전체 데이터 가져오기
    // 리턴타입 List 타입 설정 - 보통 기본형, 또는 DTO 클래스
    public List<Goods> getAll();

    // goods 테이블에서 code를 가지고 데이터 조회
    public Goods findByCode(String code);

    // goods 테이블에 데이터 삽입
    // 수정은 모양 동일
    // 삭제는 동일하게 만들어도 되고 매개변수 기본키로 만들어도 된다.
    public int insertGoods(Goods goods);

    // goods 테이블에서 name이나 manufacture에 포함된 데이터 조회
    public List<Goods> likeGoods(String content);
}

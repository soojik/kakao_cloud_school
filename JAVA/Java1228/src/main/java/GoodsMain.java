package main.java;

public class GoodsMain {
    public static void main(String[] args) {
        GoodsDAO goodsDAO = GoodsDAOImpl.getInstanece();

        System.out.println(goodsDAO.getAll().toString());
        System.out.println(goodsDAO.findByCode("1").toString());
        try {
            System.out.println(goodsDAO.findByCode("20").toString());
        } catch (NullPointerException e) {
            System.out.println(e.getLocalizedMessage());
        }

        Goods goods = new Goods();
        goods.setCode("11");
        goods.setName("아이스 아메리카노");
        goods.setManufacture("한국");
        goods.setPrice(2500);

        System.out.println((goodsDAO.insertGoods(goods) == 1) ? "성공" : "이미 존재하는 데이터");
        System.out.println(goodsDAO.findByCode("11").toString());

        // 검색과 비슷
        System.out.println(goodsDAO.likeGoods("아").toString());
    }
}

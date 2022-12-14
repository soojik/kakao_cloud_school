package persistence;

import domain.Item;

public class ItemRepository {
	// 다른 패키지에서는 인스턴스 생성을 못하도록 생성자의 접근 지정자를 protected로 설정
	ItemRepository(){}
	
	public Item get() {
		Item item = new Item();

		item.setItemId(1);
		item.setItemName("망고");
		item.setDescription("달콤달콤");
		item.setPrice(3000);
		item.setPictureUrl("mango.png");

		return item;
	}

}

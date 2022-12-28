package main.java;

public class Goods {
    private String code;
    private String name;
    private String manufacture;
    private int price;

    @Override
    public String toString() {
        return "Goods{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", manufacture='" + manufacture + '\'' +
                ", price=" + price +
                '}';
    }

    public Goods(String code, String name, String manufacture, int price) {
        this.code = code;
        this.name = name;
        this.manufacture = manufacture;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Goods() {
        super();
    }
}

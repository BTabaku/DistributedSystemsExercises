package Exercise9_ChainManagementSystem;

/**
 * Project Name: DSproject
 * Created With: IntelliJ IDEA.
 * Author: Baftjar TABAKU & Erli REÇI
 * Date Created: 1/16/2020
 * Time Created: 3:19 PM
 **/
public class Product {
    private int quantity;
    private String name;
    private int id;

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    private double pricePerUnit;
    private double totalPrice;

    public Product() {
        quantity = 0;
        name = "";
    }

    public Product(int quantity, String name, int id, double pricePerUnit) {
        this.quantity = quantity;
        this.name = name;
        this.id = id;
        this.pricePerUnit = pricePerUnit;
        totalPrice = getTotalPrice();
    }

    public double getTotalPrice() {
        return pricePerUnit * quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "quantity=" + quantity +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", pricePerUnit=" + pricePerUnit +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

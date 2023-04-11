public class Item {
    String name;
    int quantity;
    int sumOfOne;

    public Item (String name, int quantity, int sumOfOne) {
        this.name = name;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public int getTotalSum() {
        return quantity*sumOfOne;
    }
}

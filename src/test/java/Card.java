public class Card {
    private int id;
    private String number;
    private Order order;

    public Card() {
    }

    public Card(int id_order) {
        setOrder(new Order(id_order));
    }

    public Card(String number) {
        setNumber(number);
    }

    public Card(String number,int id_order) {
        setNumber(number);
        setOrder(new Order(id_order));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String nunber) {
        this.number = nunber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", order=" + order +
                '}';
    }
}

public class Order {
    private int id;
    private User user;
    private int Balance;

    public Order(int balance,int id_user) {
        this.setBalance(balance);
        this.setUser(new User(id_user));
    }
    public Order(int id) {
        this.id = id;
    }
    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", Balance=" + Balance +
                '}';
    }
}

package view.tm;

import entity.Customer;

public class RoomTM {
    private String id;
    private boolean AC;
    private boolean available;
    private byte humanCapacity;
    private double price;
    private Customer customer;

    public RoomTM() {
    }

    public RoomTM(String id, String AC, boolean available, byte humanCapacity, double price, Customer customer) {
        this.id = id;
        this.AC = AC.equals("Yes")? true:false;
        this.available = available;
        this.humanCapacity = humanCapacity;
        this.price = price;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAC() {
        return AC;
    }

    public void setAC(boolean AC) {
        this.AC = AC;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public byte getHumanCapacity() {
        return humanCapacity;
    }

    public void setHumanCapacity(byte humanCapacity) {
        this.humanCapacity = humanCapacity;
    }

    @Override
    public String toString() {
        return "RoomTM{" +
                "id='" + id + '\'' +
                ", AC=" + AC +
                ", available=" + available +
                ", humanCapacity=" + humanCapacity +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

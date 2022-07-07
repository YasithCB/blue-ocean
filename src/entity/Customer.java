package entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Customer {

    private String name;
    private String nicNo;
    private String tpNo;
    private String email;
    private String address;
    private int noOfPersons;
    private int noOfDays;
    private String mealName;
    private String roomId;
    private String packageId;
    private Calendar date;
    private double price;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Calendar roomFreeDate;

    public Customer() {
    }

    public Customer(String name, String nicNo, String tpNo, String email, String address, int noOfPersons, int noOfDays,
                    String mealName, String roomId, String packageId, Calendar date, double price) {
        this.name = name;
        this.nicNo = nicNo;
        this.tpNo = tpNo;
        this.email = email;
        this.address = address;
        this.noOfPersons = noOfPersons;
        this.noOfDays = noOfDays;
        this.mealName = mealName;
        this.roomId = roomId;
        this.packageId = packageId;
        this.date = date;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicNo() {
        return nicNo;
    }

    public void setNicNo(String nicNo) {
        this.nicNo = nicNo;
    }

    public String getTpNo() {
        return tpNo;
    }

    public void setTpNo(String tpNo) {
        this.tpNo = tpNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getNoOfPersons() {
        return noOfPersons;
    }

    public void setNoOfPersons(int noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getMealName() {
        return mealName.toString();
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getRoomFreeDate() {
        roomFreeDate = date;
        roomFreeDate.add(Calendar.DATE, noOfDays);
        return sdf.format(roomFreeDate.getTime());
    }

    public void setRoomFreeDate(Calendar roomFreeDate) {
        this.roomFreeDate = roomFreeDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

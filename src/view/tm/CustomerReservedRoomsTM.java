package view.tm;

import java.util.Calendar;
import java.util.Date;

public class CustomerReservedRoomsTM {
    private String roomId;
    private String AC;
    private String name;
    private int noOfDays;
    private int noOfPersons;
    private String date;
    private Double price;

    private String roomFreeDate;

    public CustomerReservedRoomsTM() {
    }

    public CustomerReservedRoomsTM(String roomId, String AC, String cname, int noOfDays, int noOfPersons, String date, String roomFreeDate, Double price) {
        this.roomId = roomId;
        this.AC = AC;
        this.name = cname;
        this.noOfDays = noOfDays;
        this.noOfPersons = noOfPersons;
        this.date = date;
        this.roomFreeDate = roomFreeDate;
        this.price = price;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String isAC() {
        return AC;
    }

    public void setAC(String AC) {
        this.AC = AC;
    }

    public String getCname() {
        return name;
    }

    public void setCname(String cname) {
        this.name = cname;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public int getNoOfPersons() {
        return noOfPersons;
    }

    public void setNoOfPersons(int noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoomFreeDate() {
        return roomFreeDate;
    }

    public void setRoomFreeDate(String roomFreeDate) {
        this.roomFreeDate = roomFreeDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

package view.tm;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IncomesTM {
    private String name;
    private String roomId;
    private Calendar date;
    private double income;

    public IncomesTM() {
    }

    public IncomesTM(String name, String roomId, Calendar date, double income) {
        this.name = name;
        this.roomId = roomId;
        this.date = date;
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date.getTime());

    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "IncomesTM{" +
                "name='" + name + '\'' +
                ", roomId='" + roomId + '\'' +
                ", date=" + date +
                ", income=" + income +
                '}';
    }
}

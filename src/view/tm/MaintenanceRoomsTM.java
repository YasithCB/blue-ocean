package view.tm;

import java.util.Calendar;

public class MaintenanceRoomsTM {
    private String roomId;
    private String ac;
    private String name;
    private String reservedDate;
    private String roomFreeDate;

    public MaintenanceRoomsTM() {
    }

    public MaintenanceRoomsTM(String roomId, String ac, String name, String reservedDate, String roomFreeDate) {
        this.roomId = roomId;
        this.ac = ac;
        this.name = name;
        this.reservedDate = reservedDate;
        this.roomFreeDate = roomFreeDate;
    }

    @Override
    public String toString() {
        return "MaintenanceRoomsTM{" +
                "roomId='" + getRoomId() + '\'' +
                ", ac=" + getAc() +
                ", name='" + getName() + '\'' +
                ", reservedDate='" + getReservedDate() + '\'' +
                ", roomFreeDate='" + getRoomFreeDate() + '\'' +
                '}';
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(String reservedDate) {
        this.reservedDate = reservedDate;
    }

    public String getRoomFreeDate() {
        return roomFreeDate;
    }

    public void setRoomFreeDate(String roomFreeDate) {
        this.roomFreeDate = roomFreeDate;
    }
}

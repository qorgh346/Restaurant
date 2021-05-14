package Hobe.Restaurant.Domain;

public class Booking {
    String Date;
    String startTime;
    String endTime;
    int tableNumber;
    int howMany;

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void startTime(){
        this.startTime = startTime;
    }
    public void setTableNumber(int tableNumber){
        this.tableNumber = tableNumber;
    }
    public int getTableNumber(){
        return tableNumber;
    }
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getHour(String time){
        return Integer.valueOf(time.split(":")[0]);
    }
    public int getMin(String time){
        return Integer.valueOf(time.split(":")[1]);
    }

}

package Hobe.Restaurant.Domain;

public class Table {
     long tableNumber;
     int maxNumber; //테이블에 앉을 수 있는 수.
     boolean Available; //테이블 이용 가능한지 안한지.

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }

    public long getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }
}

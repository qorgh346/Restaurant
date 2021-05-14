package Hobe.Restaurant.Domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class Member {
    private long id;
    private String name;
    private String Phone_num;
    private boolean available_Booking = true; //기본값은 True다.
    public Member member(){
        return new Member();
    }

    public long getId() {
        return id;
    }

    public boolean isAvailable_Booking() {
        return available_Booking;
    }

    public void setAvailable_Booking(boolean available_Booking) {
        this.available_Booking = available_Booking;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_num() {
        return Phone_num;
    }

    public void setPhone_num(String phone_num) {
        Phone_num = phone_num;
    }
}

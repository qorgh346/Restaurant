package Hobe.Restaurant.Repository;

import Hobe.Restaurant.Domain.Booking;
import Hobe.Restaurant.Domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookingMemory implements BookingRepository{

    //bookingDB 딕셔너리 = { 회원의 기본키인 ID : Booking 클래스 }
    Map<Long,Booking> bookingDB = new HashMap<>();
    @Override
    public Booking save(Long Memberid,Booking booking) {
        bookingDB.put(Memberid,booking);
        return booking;
    }
    @Override
    public Optional<Booking> findByMemberID(Long id) {

        return Optional.ofNullable(bookingDB.get(id));
    }
    public List<Booking> findAll() {
        return new ArrayList<>(bookingDB.values());
    }

    @Override
    public List<Booking> findDate(String date) {
        List<Booking> DateBookingList = new ArrayList<>();
        for(Booking book : bookingDB.values()){
            if(book.getDate().equals(date))
                DateBookingList.add(book);
        }
        return DateBookingList;
    }
    public void UpdateBooking(Booking booking,long MemberID){
        bookingDB.replace(MemberID,booking); //변경해줘
    }
    public void DeleteBooking(long MemberID){
        bookingDB.remove(MemberID);
    }
}

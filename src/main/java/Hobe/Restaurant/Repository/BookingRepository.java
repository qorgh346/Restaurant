package Hobe.Restaurant.Repository;

import Hobe.Restaurant.Domain.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    Booking save(Long Memberid,Booking booking);
    Optional<Booking> findByMemberID(Long id);
    List<Booking> findAll();
    List<Booking> findDate(String date);
    public void UpdateBooking(Booking booking,long MemberID);
    public void DeleteBooking(long MemberID);
}

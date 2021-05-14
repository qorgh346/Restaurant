package Hobe.Restaurant.Service;

import Hobe.Restaurant.Domain.Booking;
import Hobe.Restaurant.Domain.Member;
import Hobe.Restaurant.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingDB;
    private final TableService tableService;
    private final MemberService memberService;
    @Autowired
    public BookingService(BookingRepository bookingRepository, TableService tableService, MemberService memberService) {
        this.bookingDB = bookingRepository;
        this.tableService = tableService;
        this.memberService = memberService;
    }
    public void reservation(Booking booking,long id){ //long id = 사용자의 ID값
        //BookingControl.class에서의 주석처리한 부분 예외처리 하기..
        validateDuplicateReservation(booking,id);
        long TableNumber = booking.getTableNumber();
        String date = booking.getDate();
        if(tableService.checkTable(booking.getTableNumber()) && !OverlapReservation(booking,bookingDB.findDate(date))
                && tableService.checkMaxNumber(booking,TableNumber)){
            //테이블DB에 예약하고자 하는 테이블 번호가 있고 테이블 시간이 안겹치면. if문 실행.
            //테이블 관련해서는 예약이 가능하니까 예약하기
            Booking newBook = bookingDB.save(id,booking); //테이블 예약.
            //tableService.changeAvailable(TableNumber); //현재 이용중이니까 True->False로 변경함.
        }
        else{
            memberService.Change_AvailableBooing(id,true); //위에 조건에 따라서 예약이 가능상태에서 갑자기 불가능 상태로 바꼈으니까
                                                                    //member의 예약 가능 상태를 true로 변경해주기.
            throw new IllegalStateException("예약이 불가능합니다.");
        }

    }
    public void validateDuplicateReservation(Booking booking,Long id){
        if(memberService.Check_Booing_Member(id)) //회원이 예약했으면 false, 예약을 안했으면 예약이 가능한거니까 true
            memberService.Change_AvailableBooing(id,false);
        else
            throw new IllegalStateException("기존에 예약을 했습니다.");
    }
    public boolean OverlapReservation(Booking booking,List<Booking>books){ //겹치는 시간대가 있는지 체크하기.
        //코드 더럽네요..또 다른 방법 있으면 수정!!!!
        String input_StartTime = booking.getStartTime();
        String input_EndTime = booking.getEndTime();
        int input_startHour = booking.getHour(input_StartTime);
        int input_endHour = booking.getHour(input_EndTime);

        for(Booking bookDB : books){  //이 부분 수정하기..시간 겹치는거
            if(bookDB.getDate().equals(booking.getDate())){ //예약한 날짜가 겹치고.
                int start_hour = bookDB.getHour(bookDB.getStartTime());
                int end_hour = bookDB.getHour(bookDB.getEndTime());
                boolean check1 = (start_hour < input_endHour && end_hour < input_startHour);
                boolean check2 = (start_hour > input_endHour && end_hour > input_startHour);
                if(booking.getTableNumber() == bookDB.getTableNumber()){//날짜 같고 테이블 번호가 같을 때. 시간비교
                    if(check1 || check2){
                        System.out.println(start_hour+"~"+end_hour+"시간 중복 X ");
                        break;
                    }else{
                        System.out.println(start_hour+"~"+end_hour+"중복임");
                        return true; //시간 중복임
                    }
                }

    //                if(start_hour<= input_startHour && end_hour >= input_endHour){
//                    boolean
//                }
            }
        }
        return false;
    }

    public Booking MyPage_BookingFind(long MemberId){
        return bookingDB.findByMemberID(MemberId).get();
    }

    public boolean ChangeBooking(Booking booking,long MemberId) {
        long TableNumber = booking.getTableNumber();
        String date = booking.getDate();
        if(tableService.checkTable(booking.getTableNumber()) && !OverlapReservation(booking,bookingDB.findDate(date))
                && tableService.checkMaxNumber(booking,TableNumber)){
            bookingDB.UpdateBooking(booking,MemberId);
            System.out.println("변경이 완료되었습니다.");
            return true;
              }
        else {
            throw new IllegalStateException("예약이 불가능합니다.");
        }
    }

    public boolean Delete_Booking(long MemberID){ //MemberID
      if(bookingDB.findByMemberID(MemberID).get() != null)
        bookingDB.DeleteBooking(MemberID);
      else return false;
      return true;
    }

}

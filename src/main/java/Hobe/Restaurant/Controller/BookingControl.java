package Hobe.Restaurant.Controller;

import Hobe.Restaurant.Domain.Booking;
import Hobe.Restaurant.Domain.Table;
import Hobe.Restaurant.Service.BookingService;
import Hobe.Restaurant.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BookingControl {
    private final BookingService bookingService;
    private final TableService tableService;
    @Autowired
    public BookingControl(BookingService bookingService, TableService tableService) {
        this.bookingService = bookingService;
        this.tableService = tableService;
    }
    @GetMapping("/booking")
    public String booking(Model model){
        if(LoginControl.currentMember == null){
             return "LoginPage";
        }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[] today_Date = format.format(date).split(" ");
       //예약 페이지 보여주기 전에 현재 테이블 정보를 넘겨야된다.
        List<Table> tableList = tableService.showTables();
        for(Table table : tableList){
            System.out.println(table.isAvailable()); //콘솔화면 출력용
        }
        System.out.println(today_Date[0]+today_Date[1]);
        model.addAttribute("Member",LoginControl.currentMember.getName());
        model.addAttribute("Date",today_Date[0]);
        model.addAttribute("Time",today_Date[1]);
        model.addAttribute("tableList",tableList);
        return "Reservation/reservation";
    }
    @PostMapping("/booking/bookingForm")
    public String bookingFormHandler(BookingForm bookingForm,Model model){
        Long id = LoginControl.currentMember.getId();
    //예외처리 넣어야됌
        Booking booking = new Booking();
        booking.setDate(bookingForm.getDate());
        booking.setStartTime(bookingForm.getStartTime());
        booking.setEndTime(bookingForm.getEndTime());
        booking.setTableNumber(bookingForm.getTableNumber());
        booking.setHowMany(bookingForm.getHowMany());
        //화면에 보일때 각 테이블의 private boolean Available; 변수로
        //이용이 가능한지 안한지 알 수 있게 화면에 뿌려주고 + 그래도 이용자가 번호 입력하면
        //현재 이용가능한지 안한지 체크할 수 있도록 코드 구현해야함.
        //사용자가 입력한 예약 날짜와 시간에 예약이 가능한지 체크도 해야됌.
        //+ 총 몇 명 예약할건지 입력받고나서 ( 폼태그 추가해야됌. ) ->  Table의 Maxnumber수랑 비교해서
        //가능한지 안가능한지 체크도 해야됌....
        //이런 예외처리는 BookingService 클래스에서 작성하면 좋을듯!
        //위의 과정이 다 끝난 후 이제
        if(tableService.checkTable(bookingForm.getTableNumber()))
            bookingService.reservation(booking,id); //예약서비스한테 예약해달라고 booking 객체와 , 사용자 id 를 넘긴다.

        model.addAttribute("data",booking);
        return "Reservation/showReservation";

    }
}

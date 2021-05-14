package Hobe.Restaurant.Controller;

import Hobe.Restaurant.Domain.Booking;
import Hobe.Restaurant.Domain.Table;
import Hobe.Restaurant.Service.BookingService;
import Hobe.Restaurant.Service.TableService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MyPageController {
    private final BookingService bookingService;
    private final TableService tableService;
    private String MemberName;
    private long MemberId;
    public MyPageController(BookingService bookingService, TableService tableService) {
        this.bookingService = bookingService;
        this.tableService = tableService;
    }

    @GetMapping("GoMyPage")
    public String MyPage(Model model){
        if(LoginControl.currentMember == null){
            System.out.println("로그인 하셔야 됩니다.");
            return "LoginPage";
        }else{
            this.MemberName = LoginControl.currentMember.getName();
            this.MemberId = LoginControl.currentMember.getId();
            model.addAttribute("MyMember",MemberName);
            return "MyPage/myPage";
        }

    }
    @GetMapping("BookingFind")
    public String BookingFind(Model model){ //예약조회
       Booking nowBooking = bookingService.MyPage_BookingFind(LoginControl.currentMember.getId());
       model.addAttribute("MemberBooking",nowBooking);
        model.addAttribute("MyMember",MemberName);
       return "MyPage/BookFind";
    }
   @GetMapping("BookingUpdate")
    public String BookingUpdate(Model model){ //예약변경
       // BookingControl에 있는 코드 재사용 .. -> 이 부분 고쳐야될듯
       Booking nowBooking = bookingService.MyPage_BookingFind(LoginControl.currentMember.getId());
       Date date = new Date();
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
       String[] today_Date = format.format(date).split(" ");
       List<Table> tableList = tableService.showTables();
       model.addAttribute("nowBooking",nowBooking);
       model.addAttribute("Member",MemberName);
       model.addAttribute("Date",today_Date[0]);
       model.addAttribute("Time",today_Date[1]);
       model.addAttribute("tableList",tableList);
       return "MyPage/BookUpdate";
    }
    @GetMapping("BookingDelete")
   public String BookingDelete(Model model){ //예약취소
        Booking nowBooking = bookingService.MyPage_BookingFind(LoginControl.currentMember.getId());
        model.addAttribute("MemberBooking",nowBooking);
        model.addAttribute("MyMember",MemberName);
        return "MyPage/BookDelete";
    }

    @PostMapping("GoMyPage")
    public String MyPageUpdateHandler(BookingUpdateForm form){
        Booking UpdateBooking = new Booking();
        UpdateBooking.setStartTime(form.getStartTime());
        UpdateBooking.setTableNumber(form.getTableNumber());
        UpdateBooking.setHowMany(form.getHowMany());
        UpdateBooking.setDate(form.getDate());
        UpdateBooking.setStartTime(form.getStartTime());
        UpdateBooking.setEndTime(form.getEndTime());
        if(bookingService.ChangeBooking(UpdateBooking,MemberId))
            return "MyPage/myPage";  //변경 완료.

        return "MyPage/myPage";
    }
    @PostMapping("My_delete")
    public String Booking_Delete(){
        System.out.println("취소 버튼 클릭");
//        정말 취소하실건가요?
//        ㅇㅇ
        if(bookingService.Delete_Booking(MemberId)) {
            System.out.println("삭제되었습니다.");
        }
        return "redirect:/";
    }

}

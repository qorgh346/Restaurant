package Hobe.Restaurant.Controller;

import Hobe.Restaurant.Service.BookingService;
import Hobe.Restaurant.Service.MemberService;
import Hobe.Restaurant.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final MemberService memberService;
    private final TableService tableService;
    private final BookingService bookingService;
    @Autowired
    public MainController(MemberService memberService, TableService tableService, BookingService bookingService) {
        this.memberService = memberService;
        this.tableService = tableService;
        this.bookingService = bookingService;
    }
    @GetMapping("Master")
    public String signTable(){
        tableService.testInputTable();
        return "redirect:/";
    }

}

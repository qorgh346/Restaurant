package Hobe.Restaurant.Controller;

import Hobe.Restaurant.Domain.Member;
import Hobe.Restaurant.Service.MemberService;
import Hobe.Restaurant.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginControl {
    private final MemberService memberService;
    public static Member currentMember;
    private final TableService tableService; //임시용 ...아마도 메인화면 보여질 때 테이블 넣어야될듯.
    @Autowired
    public LoginControl(MemberService memberService, TableService tableService) {
        this.memberService = memberService;
        this.tableService = tableService;
    }

    @GetMapping("/login")
    public String loadingLoginPage(){ //이미 로그인을 했으면 로그인 이미 했다고 알리고
                        //현재 currentMember가 null이면 로그인 페이지 들어가도 됌.
        if(currentMember == null){
            return "LoginPage";
        }
        return "landing";
    }

    @PostMapping("/login/loginTest")
    public String LoginSuccess(MemberForm memberform,Model model){
        memberService.printMember();
        System.out.println(memberform.getName()+"->"+memberform.getPhoneNumber());
        boolean result = memberService.CheckLogin(memberform.getName(), memberform.getPhoneNumber());
        if(!result) return "loginFail"; //로그인 실패시 -> loginFail.html.
        else {
            this.currentMember = memberService.getMember(memberform.getPhoneNumber());
            model.addAttribute("data",memberform.getName());
            //아직 데이터베이스 연동 전이라서 테이블 따로 만들어야됌. 이건 관리자 부분에서 다시 구현
            //tableService.testInputTable(); //임시용 테스트 코드
            //
            return "LoginAfter/LoginAfterMainPage";
        }
    }
    @GetMapping("/sign")
    public String SignMember(){ //회원가입.
        return "SignMember"; //SignMember.html 화면 보이기.
    }
    @PostMapping("/sign/signRegistration")
    public String MemberRegistraion(SignForm signform){
        String name = signform.getName();
        String phoneNumber = signform.getPhoneNumber();
        Member newMember = new Member();
        newMember.setPhone_num(phoneNumber);
        newMember.setName(name);
        if(memberService.Registraion(newMember)){
            //중복회원 검증도 통과하고 회원 가입도 완료.
            memberService.printMember();
            return "redirect:/";
        }
        return "redirect:/"; //회원가입 실패 화면이 보여야함.
    }
    @GetMapping("/Logout")
    public String LogOut(){
        currentMember = null; //로그아웃을 했으니 현재 이용중인 사용자(currentMember)는 null임.
        return "redirect:/";
    }


}

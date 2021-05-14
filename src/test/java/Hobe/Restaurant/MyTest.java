package Hobe.Restaurant;

import Hobe.Restaurant.Domain.Member;
import Hobe.Restaurant.Repository.MemberMemoryRepository;
import Hobe.Restaurant.Repository.MemberRepository;
import Hobe.Restaurant.Service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MyTest {
    MemberService memberService;
    MemberMemoryRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemberMemoryRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    public void 아이디있을때테스트(){
        Member member1 = new Member();
        member1.setName("Hobezxc");
        member1.setPhone_num("010-3390-7772");
        System.out.println(member1.getName());
        memberRepository.save(member1);
        Member result = memberRepository.findByName("Hobezxc").get();

        String name = "Hobezxc";
        String phone_number = "010-3390-7772";
        Member result2 = memberRepository.findByName_Phone(name,phone_number).get();
        System.out.println(result2.getPhone_num());
        assertThat(result).isEqualTo(member1);


    }
}

package Hobe.Restaurant.Repository;

import Hobe.Restaurant.Domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberMemoryRepository implements MemberRepository {

    Map<Long,Member> memberDB = new HashMap<>(); //id와 Member로 회원 저장해주는 자료구조.
    private static long sequence = 0L; //아이디 자동 증가해주는 역할.

    public Member save(Member member) {
        member.setId(sequence++);
        memberDB.put(member.getId(),member);
        return member;
    }

    @Override
        public Optional<Member> findById(Long id) {
            return Optional.ofNullable(memberDB.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return memberDB.values().stream()
                .filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public Optional<Member> findByPhone(String phoneNum) {
        return memberDB.values().stream()
                .filter(member -> member.getPhone_num().equals(phoneNum)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberDB.values());
    }
    public Optional<Member> findByName_Phone(String name,String phone){
        return memberDB.values().stream()
                .filter(member -> (member.getName().equals(name))&& member.getPhone_num().equals(phone) )
                .findAny();
    }
}

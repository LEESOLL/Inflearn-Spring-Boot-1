package hello.memberservice.repository;

import hello.memberservice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장하면 저장된 회원이 반환됨
    Optional<Member> findById(Long id); //id로 회원을 찾음
    Optional<Member> findByName(String name); //name 으로 회원을 찾음
    List<Member> findAll(); //저장된 회원의 목록을 list 로 반환해줌
}

package hello.memberservice.repository;

import hello.memberservice.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //jpa 는 EntityManager 라는 걸로 모든게 동작함. 따라서 jpa 를 쓰려면 entity manager 를 주입 받아야 함.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회할 타입(member)과 식별자(id) 넣어주면 em이 find 해줌
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //jpql 이라는 쿼리 언어인데, 테이블이 아닌 객체를 대상으로 쿼리를 날림 -> SQL 로 번역이 됨.
                .getResultList();
    }
}

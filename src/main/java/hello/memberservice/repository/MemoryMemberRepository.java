package hello.memberservice.repository;

import hello.memberservice.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //키(id) 값을 생성해줄거야

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = store.get(id);
        return Optional.ofNullable(member); //null 이 발생할 경우를 대비해 Optional 로 감싸서 반환 -> 클라이언트에서 처리 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //member 의 name 이 파라미터로 들어온 name 과 같은지 확인, 같은 경우에만 필터링이 됨
                .findAny(); //찾으면 바로 반환, 결과가 없으면 optional 에 null 이 포함되서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store 는 map 이니까 new ArrayList 해서 반환해줌, store 에 있는 values 들이 member 임
    }
}

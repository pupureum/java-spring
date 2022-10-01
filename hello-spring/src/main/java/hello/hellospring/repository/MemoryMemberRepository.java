package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    // 실무에서는 동시성 문제가 있을 수 있으므로 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // key값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //  null일 수 있으므로 Optional.ofNullable() 로 감쌈
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // value값들을 루프를 돌아서
                .filter(member->member.getName().equals(name)) // param로 넘어온 name과 같은 것을 filtering
                .findAny(); // 먼저 찾은 요소 1개 return
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

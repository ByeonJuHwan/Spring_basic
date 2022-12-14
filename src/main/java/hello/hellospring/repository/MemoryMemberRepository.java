package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class  MemoryMemberRepository implements MemberRepository{


    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 문제가 고려되어 있지않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    // NullPointerException을 해결하기위한 방책으로 Java8부터 Optional이 등장했다.

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public  void clearStore() {
        store.clear();
    }
}

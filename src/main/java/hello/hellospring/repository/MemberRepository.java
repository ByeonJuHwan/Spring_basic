package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // id가 없거나 name이 없으면 null을 반환할텐데 이때 null을 처리하는 방식으로 optioinal을 감싸서 반환하는 방식을 많이쓴다.
    List<Member> findAll();
}

package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    // Test 케이스 작성시 관례는 테스트 폴더에서 패키지를 만들때 똑같은 이름으로 패키지를 만든후에 클래스이름도 테스트 하고싶은 클래스이름에 Test를 붙이는게
    // 관례이다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 어떤 메서드가 동작이끝나면 이 메서드를 실행한다 call-back 메서드라고도 한다.
    public void afterEach(){
        repository.clearStore();
    }


    @Test // Test 애너테이션을 붙이면 실행을 할수 있게 된다.
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result= repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }



}

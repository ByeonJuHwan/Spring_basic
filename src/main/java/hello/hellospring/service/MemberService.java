package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    /**
     *  회원가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m->{  // Optional 로 한번 깜쌋기때문에 사용할수 있는 메서드이다.
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 위의 코드를 좀더 간결하게 작성하능
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                          .ifPresent(m->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    /**
     * 아이디를 이용해서 회원 찾기
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

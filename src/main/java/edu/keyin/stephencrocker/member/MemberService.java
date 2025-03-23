package edu.keyin.stephencrocker.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAllMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    public Member findMemberById(long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);

        return optionalMember.orElse(null);
    }

    public Member findByName(String name) {
        return memberRepository.findByMemberNameContainingIgnoreCase(name);
    }

    public Member createMember(Member newMember) {
        return memberRepository.save(newMember);
    }

    public Member updateMember(long id, Member updatedMember) {
        Member memberToUpdate = findMemberById(id);

        if (memberToUpdate != null) {
            memberToUpdate.setMemberName(updatedMember.getMemberName());
            memberToUpdate.setMemberAddress(updatedMember.getMemberAddress());
            memberToUpdate.setMemberEmail(updatedMember.getMemberEmail());
            memberToUpdate.setMemberPhone(updatedMember.getMemberPhone());
            memberToUpdate.setMembershipDuration(updatedMember.getMembershipDuration());
            return memberRepository.save(memberToUpdate);
        }

        return null;
    }
}
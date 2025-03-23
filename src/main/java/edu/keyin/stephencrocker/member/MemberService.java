package edu.keyin.stephencrocker.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

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

    public Member findByEmail(String email) {
        return memberRepository.findByMemberEmail(email);
    }

    public Member findByPhoneNumber(String phoneNumber) {
        return memberRepository.findByMemberPhoneContaining(phoneNumber);
    }

    public List<Member> searchMembers(String name, String email, String phoneNumber) {
        List<Member> results = new ArrayList<>();

        if (email != null && !email.trim().isEmpty()) {
            Member member = findByEmail(email);
            if (member != null) {
                results.add(member);
                return results;
            }
        }

        if (name != null && !name.trim().isEmpty()) {
            Member member = findByName(name);
            if (member != null) {
                results.add(member);
                return results;
            }
        }

        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            Member member = findByPhoneNumber(phoneNumber);
            if (member != null) {
                results.add(member);
                return results;
            }
        }

        return results;
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
package edu.keyin.stephencrocker.member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    public Member findByMemberNameContainingIgnoreCase(String name);

    public Member findByMemberEmail(String email);

    public Member findByMemberPhoneContaining(String phoneNumber);
}
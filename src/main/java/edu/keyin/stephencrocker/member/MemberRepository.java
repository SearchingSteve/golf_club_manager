package edu.keyin.stephencrocker.member;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    public Member findByMemberNameContainingIgnoreCase(String name);
    public Member findByEmail(String email);
    public Member findByPhoneNumberContaining(String phoneNumber);
}
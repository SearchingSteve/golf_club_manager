package edu.keyin.stephencrocker.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class MemberController {
    @Autowired
    private MemberService memberService;

    // Get all members
    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    // Get member by id
    @GetMapping("/members/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.findMemberById(id);
    }

    // Search members
    @GetMapping("/members/search")
    public List<Member> searchMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) LocalDate membershipStartDate) {
        return memberService.searchMembers(name, email, phoneNumber);
    }

    // Create new member
    @PostMapping("/member")
    public Member createMember(@RequestBody Member newMember) {
        return memberService.createMember(newMember);
    }

    // Update member
    @PutMapping("/member/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
        return memberService.updateMember(id, member);
    }
}

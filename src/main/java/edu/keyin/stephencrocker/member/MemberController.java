package edu.keyin.stephencrocker.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping("members{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.findMemberById(id);
    }

    // Get member/s by name
    @GetMapping("/member_search")
    public List<Member> getMemberByName(@RequestParam(value = "name", required = false) String name) {
        List<Member> results = new ArrayList<Member>();
        Member member = memberService.findByName(name);

        if (member != null) {
            results.add(member);
        }
        return results;
    }

    // Create new member
    @PostMapping("/member")
    public Member createMember(@RequestBody Member newMember) {
        return memberService.createMember(newMember);
    }

    // Update member
    @PutMapping("member{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member) {
        return memberService.updateMember(id, member);
    }
}

package hello.memberservice.controller;


import hello.memberservice.domain.Member;
import hello.memberservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) { //Post 요청으로 받은 name 이 MemberForm form 을 통해 들어옴
        Member member = new Member();
        member.setName(form.getName()); //form 에서 받아온 name 가져와서 memberName 설정해줌
        memberService.join(member);

        return "redirect:/"; //회원가입 완료 후 홈 화면으로 보냄
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }


}

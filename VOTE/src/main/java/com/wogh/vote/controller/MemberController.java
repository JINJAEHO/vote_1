package com.wogh.vote.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wogh.vote.dto.MemberDTO;
import com.wogh.vote.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/wogh")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
	
	private MemberService memberService;
	
	//회원가입 폼 요청
	@GetMapping("/signup")
	public void signup() {
		log.info("singup form 요청");
	}
	
	//회원가입 실제 처리
	@PostMapping("/signup")
	/*public String singupPost(MemberDTO memberDTO, Model model) {
		log.info("signup 처리");
		log.info("memberDTO: " + memberDTO);
		
		//삽입 서비스 메서드 호출
		int result = memberService.insertMember(memberDTO);
		// result == 1 이면 성공, 아니면 실패
		//model이 아니라 redirectattributes를 사용해야 하는지 검사
		model.addAttribute("result", result);
		
		//처리 후 메인으로 이동
		return "redirect:/main";
	}*/
	
	//로그인 화면 요청
	@GetMapping("/login")
	public void login() {
		log.info("login 화면 요청");
	}
	
	@PostMapping("/login")
	public String loginPro(HttpServletRequest req, MemberDTO memberDTO, Model model) {
		HttpSession session = req.getSession();
		String memID = (String)session.getAttribute("authInfo");
		if(memID == null) {
			int result = memberService.memberLogin(memberDTO);
			if(result == 1) {
				session.setAttribute("authInfo", memberDTO.getEmail());
				return "redirect:/main";
			}else {
				model.addAttribute("result", result);
				return "redirect:/wogh/login";
			}
		}
		return "";
	}
	
	//마이 페이지 요청
	@GetMapping("/mypage")
	public void mypage(Model model) {
		
	}
}

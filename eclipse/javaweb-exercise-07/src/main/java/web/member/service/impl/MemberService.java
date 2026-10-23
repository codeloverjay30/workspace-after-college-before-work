package web.member.service.impl;

import web.member.vo.Member;

public interface MemberService {
	String register(Member member);
	String modifyPassword(Member member);
	Member login(Member member);
}

package web.member.dao.impl;

import web.member.vo.Member;

public interface MemberDao {
	int insert(Member member);
	Member selectByUserName(String username);
	int updatePassword(String username,String password);
	Member selectByUserNameAndPassword(Member member);
}

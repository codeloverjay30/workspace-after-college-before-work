package web.member.service.impl;

import java.util.Objects;

import javax.naming.NamingException;

import web.member.dao.impl.MemberDao;
import web.member.dao.impl.MemberDaoImpl;
import web.member.vo.Member;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;

	public MemberServiceImpl(MemberDao memberDao) throws NamingException {
		this.memberDao = new MemberDaoImpl(null);
	}

	@Override
	public String register(Member member) {
		var username = member.getUsername();
		var password = member.getPassword();
		var nickname = member.getNickname();
		var confirmPassword = member.getConfirmPassword();

		if (username == null || username.length() < 5 || username.length() > 50) {
			return "username must have 5 to 50 characters.";
		}

		if (password == null || password.length() < 6 || password.length() > 12) {
			return "password must have 6 to 12 characters.";
		}

		if (nickname == null || nickname.length() < 1 || nickname.length() > 20) {
			return "nickname must have 1 to 20 characters.";
		}

		if (!Objects.equals(password, confirmPassword)) {
			return "password and confirmPassword must be equal and nonNull.";
		}

		if (memberDao.selectByUserName(username) != null) {
			return "account has been registered";
		}

		int affectedRow = memberDao.insert(member);
		if (affectedRow != 1) {
			return "Unknown error during registration.";
		}

		return null;
	}

	@Override
	public Member login(Member member) {
		String username = member.getUsername();
		String password = member.getPassword();
		
		if(username == null || username.isBlank()) {
			return null;
		}
		
		if(password == null || password.isBlank()) {
			return null;
		}
		
		return memberDao.selectByUserNameAndPassword(member);
	}
	
	@Override
	public String modifyPassword(Member member) {
		try {
			if (member == null) {
				throw new Exception("The member is null.");
			}

			var username = member.getUsername();
			var password = member.getPassword();
			var confirmPassword = member.getConfirmPassword();

			if (password == null || password.length() < 6 || password.length() > 12) {
				return "password must have 6 to 12 characters.";
			}

			if (!Objects.equals(password, confirmPassword)) {
				return "password and confirmPassword must be equal and nonNull.";
			}

			int affectedRows = this.memberDao.updatePassword(username, password);
			if(affectedRows!=1) {
				throw new Exception("Unknown error during new data.");
			}
			return null;
			
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}

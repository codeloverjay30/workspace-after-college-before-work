package web.member.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import web.member.service.impl.MemberService;
import web.member.service.impl.MemberServiceImpl;
import web.member.vo.Member;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		try {
			this.memberService = new MemberServiceImpl(null);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();

		Member member = gson.fromJson(req.getReader(), Member.class);
		Member loginMember = memberService.login(member);
		String errorMessage = memberService.register(member);

		if (loginMember != null) {
			jsonObject.addProperty("result", loginMember != null);
			jsonObject.addProperty("nickname", loginMember.getNickname());
			jsonObject.addProperty("errorMessage", "");
			
			if(req.getSession(false)!=null) {
				req.changeSessionId();
			}
			req.getSession().setAttribute("loginMember", loginMember);
		}else {
			jsonObject.addProperty("result", loginMember != null);
			jsonObject.addProperty("errorMessage", "username or password is wrong.");
		}
		
		res.getWriter().write(jsonObject.toString());
	}
}

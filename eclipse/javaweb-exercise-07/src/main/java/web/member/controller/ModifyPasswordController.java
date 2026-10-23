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

@WebServlet("/member/modifyPassword")
public class ModifyPasswordController extends HttpServlet {
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
		String errorMessage = memberService.modifyPassword(member);

		if (errorMessage == null) {
			jsonObject.addProperty("result", "successfully modify your password.");
			jsonObject.addProperty("errorMessage", errorMessage);
		}else {
			jsonObject.addProperty("result", "failed to modify your password.");
			jsonObject.addProperty("errorMessage", errorMessage);
		}

		res.getWriter().write(jsonObject.toString());
	}
}

package com.NDS.Front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.NDS.member.JoinService;
import com.NDS.member.LoginService;
import com.NDS.member.LogoutService;
import com.NDS.member.UpdateService;
import com.google.gson.Gson;
import com.inter.command;
import com.memberDAO.memberDAO;
import com.snsDAO.snsDAO;
import com.snsDTO.snsDTO;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		System.out.println(path);
		String command = uri.substring(path.length() + 1);
		System.out.println("��û��� : " + command);

		command com = null;
		String nextpage = null;

		if (command.equals("LoginCon.do")) {

			com = new LoginService();
			nextpage = com.execute(request, response);

		} else if (command.equals("JoinCon.do")) {

			com = new JoinService();
			nextpage = com.execute(request, response);

		} else if (command.equals("LogoutCon.do")) {

			com = new LogoutService();
			nextpage = com.execute(request, response);

		} else if (command.equals("UpdateCon.do")) {

			com = new UpdateService();
			nextpage = com.execute(request, response);

		} else if (command.equals("check.do")) {

			String id = request.getParameter("id");
			
			
			memberDAO dao = new memberDAO();
			boolean check = dao.IdCheck(id);
			
			PrintWriter out = response.getWriter();
			out.print(check);
			
		} else if ( command.equals("actor_comment.do")){
			
			String comment = request.getParameter("comment");
			String mbid = request.getParameter("mbid");

			snsDAO dao = new snsDAO();
			dao.feed(mbid, comment);
			
			PrintWriter out = response.getWriter();
			out.print(mbid);
			
		} else if (command.equals("search_post.do")) {
			
			String search_post = request.getParameter("search_post");
			
			snsDAO dao = new snsDAO();
			ArrayList<snsDTO> list = dao.search(search_post);
			Gson gson = new Gson();
			String list_json = gson.toJson(list);
			
			
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
//			out.print(search_post);
			out.print(list_json);
		}
		
		if(nextpage !=null) {
		response.sendRedirect(nextpage);
		}
	}
}

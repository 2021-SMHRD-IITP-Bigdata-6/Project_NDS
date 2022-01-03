package com.NDS.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snsDAO.snsDAO;
import com.snsDTO.snsDTO;

@WebServlet("/SnsService")
public class SnsService extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
		snsDAO dao = new snsDAO(); 
		ArrayList<snsDTO> list = dao.sns();
		
		
		request.setAttribute("post_info", list);
		// �����ִ� getMb_id���� �����Ǿ��־ 8�����δ� �ȳ������°Ű��׿�
		RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
		dis.forward(request, response);
	}

}

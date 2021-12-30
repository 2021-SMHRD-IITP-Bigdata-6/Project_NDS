package map_controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import map_model.map_DAO;
import map_model.map_DTO;

@WebServlet("/Hospital_info")
public class Hospital_info extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("map_controller : Hospital_info");
		
		//DAO�� ���ؼ� DB���� ������ ���� list ������ ����
		System.out.println("Hospital_info");
		map_DAO dao = new map_DAO();
		ArrayList<map_DTO> hospital_list = dao.showInfo();
		
		//GSON�� ����ؼ� ArrayList - > JSON���� ��ȯ
		//GSON : Java���� Json������ �Ľ��ϰ� �����ϱ� ���� ���ۿ� ���� ���¼ҽ�
		//WebContent - WEB-INF- lib �� �־��ֱ�
		
		//new Gson : null���� �������� �ʴ´�.
		//GsonBuilder().serializeNulls().create(); :  null�� ����
	Gson gson = new GsonBuilder().serializeNulls().create();
	String hospital_json = gson.toJson(hospital_list);
		
		//�����ϱ� ���� ���ڵ� ����!
	response.setCharacterEncoding("utf-8");
		
		
		// ��½�Ʈ���� �̿��ؼ� kakaoMap.jsp�� json ���� �����ֱ�!
	PrintWriter out = response.getWriter();
	out.print(hospital_json);
		
		}
		
		

}
	

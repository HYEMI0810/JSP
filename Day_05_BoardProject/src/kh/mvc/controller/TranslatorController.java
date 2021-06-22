package kh.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.NaverDAO;

@WebServlet("*.tr")
public class TranslatorController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String url = requestURI.substring(ctxPath.length());
		System.out.println("요청URL : " + url);
		
		if(url.contentEquals("/main.tr")) {
			response.sendRedirect("translator/main.jsp");
			
		}else if(url.contentEquals("/translator.tr")) {
			NaverDAO dao = new NaverDAO();
			String text = request.getParameter("text");
			response.getWriter().append(dao.translate(text));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

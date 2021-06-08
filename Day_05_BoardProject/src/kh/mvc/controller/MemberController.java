package kh.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import DTO.MemberDTO;

@WebServlet("*.mem")
public class MemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String url = requestURI.substring(ctxPath.length());
		System.out.println("요청URL : " + url);
		
		try {
			MemberDAO dao = MemberDAO.getInstance();
			
			if(url.contentEquals("/joinForm.mem")) {
				response.sendRedirect("member/joinForm.jsp");
				
			}else if(url.contentEquals("/idCheck.mem")) {
				String id = request.getParameter("id");
				boolean result = dao.check(id);
				request.setAttribute("result", result);
				request.getRequestDispatcher("member/idCheckView.jsp").forward(request, response);
			
			}else if(url.contentEquals("/member/joinMember.mem")){
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				
				int result = dao.join(new MemberDTO(id, pw, name, phone, email, zipcode, address1, address2,null));
				request.setAttribute("result", result);
				request.getRequestDispatcher("joinMemberView.jsp").forward(request, response);
				
			}else if(url.contentEquals("/login.mem")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				MemberDTO dto = dao.login(id, pw);
				if(dto != null) {
					request.getSession().setAttribute("login",dto);
				}			
				response.sendRedirect("Index.jsp");
				
			}else if(url.contentEquals("/logout.mem")) {
				 request.getSession().invalidate();
				 response.sendRedirect("Index.jsp");
				 
			}else if(url.contentEquals("/myPage.mem")) {
				MemberDTO dto =(MemberDTO)request.getSession().getAttribute("login");
				MemberDTO temp = dao.myPage(dto.getId());
				request.setAttribute("list", temp);
				request.getRequestDispatcher("/member/myPage.jsp").forward(request, response);
				
			}else if(url.contentEquals("/quit.mem")) {
				MemberDTO dto =(MemberDTO)request.getSession().getAttribute("login");
				int result = dao.delete(dto.getId());
				request.getSession().invalidate();				
				request.setAttribute("result", result);
				request.getRequestDispatcher("member/quitView.jsp").forward(request, response);
				
			}else if(url.contentEquals("/update.mem")) {
				request.getRequestDispatcher("/member/updateForm.jsp").forward(request, response);
				 
			}else if(url.contentEquals("/updateMember.mem")) {
				MemberDTO dto =(MemberDTO)request.getSession().getAttribute("login");
				dto.setPw(request.getParameter("pw"));
				dto.setPhone(request.getParameter("phone"));
				dto.setEmail(request.getParameter("email"));
				dto.setZipcode(request.getParameter("zipcode"));
				dto.setAddress1(request.getParameter("address1"));
				dto.setAddress2(request.getParameter("address2"));
				int result = dao.update(dto);
				request.setAttribute("modi_result", result);
				request.getRequestDispatcher("/member/updateView.jsp").forward(request, response);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

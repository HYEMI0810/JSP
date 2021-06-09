package kh.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DAO.MemberDAO;
import DTO.BoardDTO;
import DTO.MemberDTO;

@WebServlet("*.board")
public class BoardController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String url = requestURI.substring(ctxPath.length());
		System.out.println("요청URL : " + url);
		
		try {
		BoardDAO dao = BoardDAO.getInstance();
		
		if(url.contentEquals("/boardList.board")){
			MemberDTO mdto =(MemberDTO)request.getSession().getAttribute("login");
			List<BoardDTO> dto = dao.list();
			request.setAttribute("list", dto);
			request.getRequestDispatcher("board/boardList.jsp").forward(request, response);
			
		}else if(url.contentEquals("/board/write.board")) {
			MemberDTO dto =(MemberDTO)request.getSession().getAttribute("login");
			String writer = dto.getId(); 
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			int result = dao.write(title, contents, writer);
			
			response.sendRedirect(ctxPath+"/boardList.board");
			
		}else if(url.contentEquals("/post.board")) {
			int id = Integer.parseInt(request.getParameter("post"));
			BoardDTO bd =dao.view(id);
			request.setAttribute("post", bd);
			request.getRequestDispatcher("board/boardView.jsp").forward(request, response);
			
		}else if(url.contentEquals("/delete.board")) {
			int seq = Integer.parseInt(request.getParameter("board"));
			int result = dao.delete(seq);
			
			
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

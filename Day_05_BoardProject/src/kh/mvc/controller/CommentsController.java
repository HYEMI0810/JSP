package kh.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DAO.CommentsDAO;
import DTO.BoardDTO;
import DTO.CommentsDTO;
import DTO.MemberDTO;

@WebServlet("*.comm")
public class CommentsController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String url = requestURI.substring(ctxPath.length());
		CommentsDAO dao = CommentsDAO.getInstance();
		System.out.println(url);
		try {
			
			if(url.contentEquals("/comment.comm")) {
				MemberDTO dto = (MemberDTO)request.getSession().getAttribute("login");
				String comments = request.getParameter("comments");
				int parent = Integer.parseInt(request.getParameter("parent"));
				int result = dao.comm(dto.getId(), comments,parent);
				response.sendRedirect("post.board?post="+parent);
				
			}else if(url.contentEquals("/delete.comm")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				int parent = dao.parentid(seq);
				int result = dao.delComment(seq);
				response.sendRedirect("post.board?post="+parent);
				
			}else if(url.contentEquals("/modify.comm")) {
				int seq2 = Integer.parseInt(request.getParameter("seq"));
				System.out.println(seq2);
				request.setAttribute("seq3", seq2);
				String comments = request.getParameter("comments2");
				BoardDAO bb = BoardDAO.getInstance();
				bb.view(seq2);
				request.setAttribute("post", bb.view(dao.parentid(seq2)));
				request.setAttribute("comments", comments);
				
				request.getRequestDispatcher("comments/commModify.jsp").forward(request, response);

			}else if(url.contentEquals("/modifyProc.comm")) {
				int seq4 = Integer.parseInt(request.getParameter("seq3"));
				String comments1 = request.getParameter("comments1");
				int result = dao.modifyComment(comments1, seq4);
				int parent =dao.parentid(seq4);
				response.sendRedirect("post.board?post="+parent);
				
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

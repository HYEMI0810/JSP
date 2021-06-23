package kh.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.BoardDAO;
import DAO.MemberDAO;
import DAO.CommentsDAO;
import DAO.FilesDAO;
import DTO.BoardDTO;
import DTO.CommentsDTO;
import DTO.FilesDTO;
import DTO.MemberDTO;
import kh.mvc.config.BoardConfig;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	
	private String XSSFilter(String target) {
		if(target != null) {
			target = target.replaceAll("<", "&lt");
			target = target.replaceAll(">", "&gt");
			target = target.replaceAll("&", "&amp");
		}
		return target;
	}

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
		FilesDAO fdao = FilesDAO.getInstance();
		
		if(url.contentEquals("/boardList.board")){			
			int cpage = Integer.parseInt(request.getParameter("cpage"));
			
			int endNum = cpage * BoardConfig.RECORD_COUNT_PER_PAGE;
			int startNum = endNum - (BoardConfig.RECORD_COUNT_PER_PAGE - 1);
			
			List<BoardDTO> dto = dao.getPageList(startNum , endNum);
			List<String> pageNavi = dao.getPageNavi(cpage);
			request.setAttribute("list", dto);
			request.setAttribute("navi", pageNavi);
			request.getRequestDispatcher("board/boardList.jsp").forward(request, response);
			
		}else if(url.contentEquals("/board/write.board")) {
			MemberDTO dto =(MemberDTO)request.getSession().getAttribute("login");
			
			
			String filesPath = request.getServletContext().getRealPath("files");
			File filesFolder = new File(filesPath);
			int maxSize = 1024 * 1024 * 10;
			if(!filesFolder.exists()) filesFolder.mkdir();
			MultipartRequest multi = new MultipartRequest(request,filesPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
			
			String writer = dto.getId(); 
			String title = multi.getParameter("title");
			String contents = multi.getParameter("contents");
			
			title = XSSFilter(title);
			contents = XSSFilter(contents);
			
			int result = dao.write(title, contents, writer);
			int seq = dao.seq(title, contents, writer);			
		
			String oriName = multi.getOriginalFileName("file");
			String sysName = multi.getFilesystemName("file");
		
			FilesDTO fdto = new FilesDTO(0,oriName,sysName,null,seq);
			int fresult = fdao.insert(fdto);
			
			response.sendRedirect(ctxPath+"/boardList.board?cpage=1");
			
		}else if(url.contentEquals("/post.board")) {
			int seq = Integer.parseInt(request.getParameter("post"));			
			BoardDTO bd =dao.view(seq);
			
			CommentsDAO cd = CommentsDAO.getInstance();
			cd.commentList(seq);
			int result = dao.viewCount(seq, bd.getViewCount());
			
			request.setAttribute("post", bd);
			request.setAttribute("comment", cd.commentList(seq));
			request.setAttribute("file", fdao.selectAll(seq));
			request.getRequestDispatcher("board/boardView.jsp").forward(request, response);
			
		}else if(url.contentEquals("/delete.board")) {
			int seq = Integer.parseInt(request.getParameter("board"));
			int result = dao.delete(seq);
			
			response.sendRedirect(ctxPath+"/boardList.board?cpage=1");
					
		}else if(url.contentEquals("/modify.board")) {
			int seq = Integer.parseInt(request.getParameter("post"));
			BoardDTO dto = dao.modify(seq);
			request.setAttribute("post", dto);
			request.getRequestDispatcher("board/modify.jsp").forward(request, response);
			
		}else if(url.contentEquals("/modifyProc.board")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			int result = dao.modifyBoard(new BoardDTO(seq,title,contents));
			response.sendRedirect(ctxPath+"/boardList.board?cpage=1");
			
		}else if(url.contentEquals("/search.board")) {
			
			if(!request.getParameter("text").equalsIgnoreCase("")) {
			String search = request.getParameter("search");
			String text = request.getParameter("text");
			List<BoardDTO>dto = null;
			
			if(search.contentEquals("제목")) {
				int cpage = Integer.parseInt(request.getParameter("cpage"));	
				int endNum = cpage * BoardConfig.RECORD_COUNT_PER_PAGE;
				int startNum = endNum - (BoardConfig.RECORD_COUNT_PER_PAGE - 1);
				dto = dao.searchTitle(startNum,endNum,text);
				List<String> pageNavi = dao.getPageNavi(cpage);
				request.setAttribute("navi", pageNavi);
				
			}else if(search.contentEquals("내용")) {
				int cpage = Integer.parseInt(request.getParameter("cpage"));	
				int endNum = cpage * BoardConfig.RECORD_COUNT_PER_PAGE;
				int startNum = endNum - (BoardConfig.RECORD_COUNT_PER_PAGE - 1);
				dto = dao.searchContent(startNum,endNum,text);
				List<String> pageNavi = dao.getPageNavi(cpage);
				request.setAttribute("navi", pageNavi);
				
			}else if(search.contentEquals("작성자")) {
				int cpage = Integer.parseInt(request.getParameter("cpage"));	
				int endNum = cpage * BoardConfig.RECORD_COUNT_PER_PAGE;
				int startNum = endNum - (BoardConfig.RECORD_COUNT_PER_PAGE - 1);
				dto = dao.searchWriter(startNum,endNum,text);
				List<String> pageNavi = dao.getPageNavi(cpage);
				request.setAttribute("navi", pageNavi);
				
			}else if(search.contentEquals("제목 + 내용")){
				int cpage = Integer.parseInt(request.getParameter("cpage"));	
				int endNum = cpage * BoardConfig.RECORD_COUNT_PER_PAGE;
				int startNum = endNum - (BoardConfig.RECORD_COUNT_PER_PAGE - 1);
				dto = dao.searchDuple(startNum,endNum,text);
				List<String> pageNavi = dao.getPageNavi(cpage);
				request.setAttribute("navi", pageNavi);
								
			}
			request.setAttribute("list", dto);
			request.setAttribute("search", search);
			request.setAttribute("text", text);
			request.getRequestDispatcher("board/searchView.jsp").forward(request, response);
			}
			else {
				response.sendRedirect(ctxPath+"/boardList.board?cpage=1");
			}
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

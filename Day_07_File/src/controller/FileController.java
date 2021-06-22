package controller;

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


import DAO.filesDAO;
import DTO.filesDTO;

@WebServlet("*.file")
public class FileController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String url = requestURI.substring(ctxPath.length());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		filesDAO dao = filesDAO.getInstance();
		try {
		
			if(url.contentEquals("/upload.file")) {
				String filesPath = request.getServletContext().getRealPath("files");
				File filesFolder = new File(filesPath);
				int maxSize = 1024 * 1024 * 10;
				System.out.println("프로젝트가 저장된 진짜 경로 : " + filesPath);
			
				if(!filesFolder.exists()) filesFolder.mkdir();
			
				MultipartRequest multi = new MultipartRequest(request,filesPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
				System.out.println(multi.getParameter("message"));	
			
				String oriName = multi.getOriginalFileName("file");
				String sysName = multi.getFilesystemName("file");
			
				filesDTO dto = new filesDTO(0,oriName,sysName,null,0);
				int result = dao.insert(dto);
				
				response.sendRedirect("index.jsp");
				
			}else if(url.contentEquals("/list.file")) {
				List<filesDTO>list = dao.selectAll();
				
				String result = new Gson().toJson(list);
				response.getWriter().append(result.toString());
				
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

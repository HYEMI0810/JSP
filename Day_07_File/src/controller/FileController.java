package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("*.file")
public class FileController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String url = requestURI.substring(ctxPath.length());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		if(url.contentEquals("/upload.file")) {
			String filesPath = request.getServletContext().getRealPath("files");
			File filesFolder = new File(filesPath);
			int maxSize = 1024 * 1024 * 10;
			System.out.println("프로젝트가 저장된 진짜 경로 : " + filesPath);
			
			if(!filesFolder.exists()) filesFolder.mkdir();
			
			MultipartRequest multi = new MultipartRequest(request,filesPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
			System.out.println(multi.getParameter("message"));			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.BoardDTO;
import DTO.MemberDTO;
import kh.mvc.config.BoardConfig;

public class BoardDAO {
	private static BoardDAO instance;
	
	public synchronized static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		} 
		return instance;
	}
	private BoardDAO() {}
	
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	public int write(String title, String contents, String writer) throws Exception{
		String sql = "insert into board values(seq_seq.nextval,?,?,?,sysdate,0)";
		BoardDTO dto = null;
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setString(1, title);
			pstat.setString(2, contents);
			pstat.setString(3, writer);
			int result = pstat.executeUpdate();
			return result;
			}
		}
	
	public List<BoardDTO> getPageList(int startNum, int endNum) throws Exception{
		String sql = "select * from (select row_number() over(order by seq desc) rnum,seq,title,writer,writeDate,viewCount from board) where rnum between ? and ?";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);	
			try(ResultSet rs = pstat.executeQuery();){
			List<BoardDTO>list = new ArrayList<>();
			while(rs.next()) {
				int seq = rs.getInt(2);
				String title = rs.getString(3);
				String writer = rs.getString(4);
				Date writeDate = rs.getDate(5);
				int viewCount = rs.getInt(6);
				BoardDTO dto = new BoardDTO(seq,title,writer,writeDate,viewCount);
				list.add(dto);					 
			}
				return list;
		}
		}
	}
	
	public BoardDTO view(int seq) throws Exception{
		String sql = "select * from board where seq = ?";
		BoardDTO dto = null;
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					dto = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6));
				}
				return dto;
			}
		}
	}
	
	public int viewCount(int seq,int viewCount) throws Exception{
		String sql = "update board set viewCount=? where seq = ?";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, viewCount+1);
			pstat.setInt(2, seq);
			int result = pstat.executeUpdate();
			connection.commit();
			return result;
			
		}
	}
	
	public int delete(int seq) throws Exception{
		String sql = "delete from board where seq = ?";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			connection.commit();
			return result;
		}
	}
	public BoardDTO modify(int seq) throws Exception{
		String sql = "select * from board where seq = ?";
		BoardDTO dto = null;
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					dto = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6));
				}
				return dto;
			}
		}
	}
	public int modifyBoard(BoardDTO dto) throws Exception{
		String sql = "update board set title = ? , contents = ? where seq = ?";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContents());
			pstat.setInt(3, dto.getSeq());
			int result = pstat.executeUpdate();
			connection.commit();
			return result;
		}
	}
	
	private int getRecordCount() throws Exception{
		String sql = "select count(*) from board";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			ResultSet rs = pstat.executeQuery();{
				rs.next();
				return rs.getInt(1);
			}
		}
	}
	
	public List<String> getPageNavi(int currentPage) throws Exception{
		int recordTotalCount = this.getRecordCount(); //전체 레코드의 개수
		int recordCountPerPage = BoardConfig.RECORD_COUNT_PER_PAGE; //한 페이지당 보여줄 게시글의 개수
		int naviCountPerPage = BoardConfig.NAVI_COUNT_PER_PAGE; //내 위치를 기준으로 시작부터 끝까지의 페이지가 총 몇개인지
		
		int pageTotalCount = 0;		
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}else if(currentPage < 1) {
			currentPage = 1;
		}
				
		int startNavi = (currentPage-1) / naviCountPerPage * naviCountPerPage + 1;
		int endNavi = startNavi + (naviCountPerPage - 1);
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {needPrev = false;}
		if(endNavi == pageTotalCount) {needNext = false;}
		
		List<String> pageNavi = new ArrayList<>();
		
		if(needPrev) {pageNavi.add("<");}
		
		for(int i = startNavi; i <= endNavi; i++) {
			pageNavi.add(String.valueOf(i));
		}
		
		if(needNext) {pageNavi.add(">");}

		return pageNavi;
	}
	
	public List<BoardDTO> searchTitle(int startNum, int endNum,String text) throws Exception{
		String sql = "select * from (select row_number() over(order by seq desc) rnum,seq,title,writer,writeDate,viewCount from board where title like '%" + text +"%') where rnum between ? and ?";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			try(ResultSet rs = pstat.executeQuery();){
				List<BoardDTO> list = new ArrayList<>();
				while(rs.next()) {
					int seq = rs.getInt("seq");
					String title = rs.getString("title");
					String writer = rs.getString("writer");
					Date writeDate = rs.getDate("writeDate");
					int viewCount = rs.getInt("viewCount");
					BoardDTO dto = new BoardDTO(seq,title,writer,writeDate,viewCount);
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public List<BoardDTO>searchContent(int startNum, int endNum,String text) throws Exception{
		String sql = "select * from (select row_number() over(order by seq desc) rnum,seq,title,writer,writeDate,viewCount from board where contents like '%" + text +"%') where rnum between ? and ?";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			try(ResultSet rs = pstat.executeQuery();){
				List<BoardDTO> list = new ArrayList<>();
				while(rs.next()) {
					int seq = rs.getInt("seq");
					String title = rs.getString("title");
					String writer = rs.getString("writer");
					Date writeDate = rs.getDate("writeDate");
					int viewCount = rs.getInt("viewCount");
					BoardDTO dto = new BoardDTO(seq,title,writer,writeDate,viewCount);
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public List<BoardDTO>searchWriter(int startNum, int endNum,String text) throws Exception{
		String sql = "select * from (select row_number() over(order by seq desc) rnum,seq,title,writer,writeDate,viewCount from board where writer like '%" + text +"%') where rnum between ? and ?";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			try(ResultSet rs = pstat.executeQuery();){
				List<BoardDTO> list = new ArrayList<>();
				while(rs.next()) {
					int seq = rs.getInt("seq");
					String title = rs.getString("title");
					String writer = rs.getString("writer");
					Date writeDate = rs.getDate("writeDate");
					int viewCount = rs.getInt("viewCount");
					BoardDTO dto = new BoardDTO(seq,title,writer,writeDate,viewCount);
					list.add(dto);
				}
				return list;
			}
		}
	}
	
	public List<BoardDTO>searchDuple(int startNum, int endNum,String text) throws Exception{
		String sql = "select * from (select row_number() over(order by seq desc) rnum,seq,title,writer,writeDate,viewCount "
				+ "from board where title like '%" + text + "%' or contents like '%" + text + "%') where rnum between ? and ?";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, startNum);
			pstat.setInt(2, endNum);
			try(ResultSet rs = pstat.executeQuery();){
				List<BoardDTO> list = new ArrayList<>();
				while(rs.next()) {
					int seq = rs.getInt("seq");
					String title = rs.getString("title");
					String writer = rs.getString("writer");
					Date writeDate = rs.getDate("writeDate");
					int viewCount = rs.getInt("viewCount");
					BoardDTO dto = new BoardDTO(seq,title,writer,writeDate,viewCount);
					list.add(dto);
				}
				return list;
			}
		}
	}
	
}










//Class.forName("oracle.jdbc.driver.OracleDriver");		
//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
//Statement stat = con.createStatement();
//
//for(int i = 0; i < 143; i++) {
//	stat.executeUpdate("insert into board values(seq_seq.nextval,'title"+i+"','contents"+i+"','writer"+i+"',default,0)");
//	stat.addBatch("insert into board values(seq_seq.nextval,'title"+i +"','contents"+i+"','writer"+i+"',default,0)");
//}
//stat.executeBatch();
//con.commit();
//con.close();

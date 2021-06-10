package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.BoardDTO;
import DTO.MemberDTO;

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
	
	public List<BoardDTO> list() throws Exception{
		String sql = "select * from board order by 1";
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();){
			List<BoardDTO>list = new ArrayList<>();
			while(rs.next()) {
				int seq = rs.getInt(1);
				String title = rs.getString(2);
				String writer = rs.getString(4);
				Date writeDate = rs.getDate(5);
				int viewCount = rs.getInt(6);
				BoardDTO dto = new BoardDTO(seq,title,writer,writeDate,viewCount);
				list.add(dto);					 
			}
				return list;
		}
	}
	public BoardDTO view(int id) throws Exception{
		String sql = "select * from board where seq = ?";
		BoardDTO dto = null;
		try(Connection connection = this.getConnection();
			PreparedStatement pstat = connection.prepareStatement(sql);){
			pstat.setInt(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					dto = new BoardDTO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getInt(6));
				}
				return dto;
			}
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
}

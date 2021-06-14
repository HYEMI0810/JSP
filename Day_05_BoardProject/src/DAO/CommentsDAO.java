package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.CommentsDTO;

public class CommentsDAO {
	private static CommentsDAO instance;
	public synchronized static CommentsDAO getInstance() {
		if(instance == null) {
			instance = new CommentsDAO();
		}
		return instance;
	}
	private CommentsDAO() {}
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	public int comm(String id, String comments, int parent) throws Exception{
		String sql = "insert into comments values(seq.nextval,?,?,sysdate,?)";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, comments);
			pstat.setInt(3, parent);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public List<CommentsDTO> commentList(int seq) throws Exception{
		String sql = "select * from comments where parent_seq = ? order by 1";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery();){
				List<CommentsDTO> commentList = new ArrayList<>();	
				while(rs.next()) {
					int seq2 = rs.getInt("seq");
					String writer = rs.getString("writer");
					String comments = rs.getString("comments");
					Date writeDate = rs.getDate("writeDate");
					int parent_seq = rs.getInt("parent_seq");
					
					CommentsDTO dto = new CommentsDTO(seq2,writer,comments,writeDate,parent_seq);
					commentList.add(dto);
				}
				return commentList;
			}
		}
	}
	
	public int delComment(int seq) throws Exception{
		String sql = "delete from comments where seq = ?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int modifyComment(String comments, int seq4) throws Exception{
		String sql = "update comments set comments = ? where seq = ?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, comments);
			pstat.setInt(2, seq4);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}

}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DTO.MemberDTO;
import Utill.utill;

public class MemberDAO {
	private static MemberDAO instance;
	
	public synchronized static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	private MemberDAO() {}
	
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	public boolean check(String id) throws Exception{
		String sql = "select * from member where id = ?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
		}
	}
	
	public int join(MemberDTO dto) throws Exception{
		String sql = "insert into member values(?,?,?,?,?,?,?,?,sysdate)";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1,dto.getId());
			pstat.setString(2, utill.getSHA512(dto.getPw()));
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getPhone());
			pstat.setString(5, dto.getEmail());
			pstat.setString(6, dto.getZipcode());
			pstat.setString(7, dto.getAddress1());
			pstat.setString(8, dto.getAddress2());
			
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public MemberDTO login(String id, String pw) throws Exception{
		String sql = "select * from member where id = ? and pw = ?";
		MemberDTO md = null;
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, utill.getSHA512(pw));
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					String tmpId = rs.getString("id");
					String tmpName = rs.getString("name");
					md = new MemberDTO(tmpId,tmpName);
				}
			}
			return md;
		}
	}
	
	public MemberDTO myPage(String id) throws Exception{
		String sql = "select * from member where id = ?";
		MemberDTO temp =null; 
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					temp = new MemberDTO(rs.getString(1),rs.getString(2),
							rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
							rs.getString(8),rs.getDate(9));
				}
				return temp;
			}
		}
	}
	
	public int delete(String id) throws Exception{
		String sql = "delete from member where id = ?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int update(MemberDTO dto) throws Exception{
		String sql = "update member set pw = ?,phone = ?, email = ?,"
				+ "zipcode = ?, address1 = ?, address2 = ? where id = ?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, utill.getSHA512(dto.getPw()));
			pstat.setString(2, dto.getPhone());
			pstat.setString(3, dto.getEmail());
			pstat.setString(4, dto.getZipcode());
			pstat.setString(5, dto.getAddress1());
			pstat.setString(6, dto.getAddress2());
			pstat.setString(7, dto.getId());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
}

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

import DTO.filesDTO;

public class filesDAO {
private static filesDAO instance;
	
	public synchronized static filesDAO getInstance() {
		if(instance == null) {
			instance = new filesDAO();
		} 
		return instance;
	}
	private filesDAO() {}
	
	private Connection getConnection() throws Exception{
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	public int insert(filesDTO dto) throws Exception{
		String sql = "insert into files values(files_seq.nextval,?,?,sysdate,?)";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getOriName());
			pstat.setString(2, dto.getSysName());
			pstat.setInt(3, dto.getParent());
			int result = pstat.executeUpdate();
			//con.commit();
			return result;
		}
	}
	
	public List<filesDTO> selectAll() throws Exception{
		String sql = "select * from files";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery()){
			List<filesDTO>list = new ArrayList<>();
			while(rs.next()) {
				int seq = rs.getInt("seq");
				String oriName = rs.getString("oriName");
				String sysName = rs.getString("sysName");
				Date regDate = rs.getDate("regDate");
				int parent = rs.getInt("parent");
				list.add(new filesDTO(seq,oriName,sysName,regDate,parent));
			}
			return list;
		}
	}

}

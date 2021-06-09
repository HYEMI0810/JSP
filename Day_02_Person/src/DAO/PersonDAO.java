package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.PersonDTO;

public class PersonDAO {
	public PersonDAO() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}
	private Connection getConnection() throws Exception{
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","kh");
	}
	
	public int insert(String name, String contact) throws Exception{
		String sql = "insert into person values(person_seq.nextval,?,?,sysdate)";
		try(
			Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, name);
			pstat.setString(2, contact);			
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public List<PersonDTO> selectAll() throws Exception{
		String sql = "select * from person";
		try(
			Connection con = this.getConnection();	
			PreparedStatement pstat = con.prepareStatement(sql);	
			ResultSet rs = pstat.executeQuery();){
			List<PersonDTO>list = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String contact = rs.getString("contact");
				Date reg_date = rs.getDate("reg_date");
				
				PersonDTO dto = new PersonDTO(id,name,contact,reg_date);
				list.add(dto);
			}
			return list;		
		}
	}
	
	public int delete(String str) throws Exception{
		String sql = "delete from person where id = ?";
		try(
			Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, str);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
			
		}
	}
	
	public int modify(PersonDTO dto) throws Exception{
		String sql = "update person set name =?,contact=? where id=?";
		try(
			Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getContact());
			pstat.setInt(3, dto.getId());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
			}
		}
	}

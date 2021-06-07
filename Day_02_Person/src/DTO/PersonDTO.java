package DTO;

import java.util.Date;

public class PersonDTO {
	private int id;
	private String name;
	private String contact;
	private Date reg_date;
	
	public PersonDTO(int id, String name, String contact, Date reg_date) {
		this.id= id;
		this.name = name;
		this.contact = contact;
		this.reg_date = reg_date;
	}
	
	public PersonDTO(String name, String contact) {
		this.name=name;
		this.contact=contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	

}

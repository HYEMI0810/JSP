package DTO;

import java.sql.Date;

public class FilesDTO {
	private int seq;
	private String oriName;
	private String sysName;
	private Date regDate;
	private int parent;
	
	public FilesDTO() {
		super();
	}
	public FilesDTO(int seq, String oriName, String sysName,Date regDate, int parent) {
		this.seq = seq;
		this.oriName = oriName;
		this.sysName = sysName;
		this.regDate = regDate;
		this.parent = parent;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	

}

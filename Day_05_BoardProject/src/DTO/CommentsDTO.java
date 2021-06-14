package DTO;

import java.sql.Date;

public class CommentsDTO {
	private int seq;
	private String writer;
	private String comments;
	private Date writeDate;
	private int parent_seq;
	
	public CommentsDTO(int seq,String writer,String comments,Date writeDate,int parent_seq) {
		this.seq = seq;
		this.writer = writer;
		this.comments = comments;
		this.writeDate = writeDate;
		this.parent_seq = parent_seq;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getParent_seq() {
		return parent_seq;
	}

	public void setParent_seq(int parent_seq) {
		this.parent_seq = parent_seq;
	}
	

}

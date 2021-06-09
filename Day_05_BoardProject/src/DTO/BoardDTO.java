package DTO;

import java.util.Date;

public class BoardDTO {
	private int seq;
	private String title;
	private String contents;
	private String writer;
	private Date writeDate;
	private int viewCount;
	
	public BoardDTO(int seq, String title, String contents, String writer, Date writeDate, int viewCount) {
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.writeDate = writeDate;
		this.viewCount = viewCount;
	}
	
	public BoardDTO(String writer) {
		this.writer = writer;
	}
	
	public BoardDTO(int seq, String title,String writer, Date WriteDate, int viewCount) {
		this.seq = seq;
		this.title = title;
		this.writer = writer;
		this.writeDate = writeDate;
		this.viewCount = viewCount;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	

}

package bitedu.bipa.tiles.vo;

import java.sql.Timestamp;

public class BoardVO {
	private int textNum;
	private String title;
	private String content;
	private String writer;
	private int viewNum;
	private Timestamp createdAt;
	public int getTextNum() {
		return textNum;
	}
	public void setTextNum(int textNum) {
		this.textNum = textNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


}

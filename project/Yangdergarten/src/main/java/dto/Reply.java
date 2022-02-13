package dto;

public class Reply {
	private int no;
	private String author;
	private String createDate;
	private int boardindex;
	private String content;

	public void setBoardindex(int boardindex) {
		this.boardindex = boardindex;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getBoardindex() {
		return boardindex;
	}
	public void setIndex(int index) {
		this.boardindex = index;
	}
	
	@Override
	public String toString() {
		return "Reply [no=" + no + ", author=" + author + ", createDate=" + createDate + ", index=" + boardindex + "]";
	}
	
	public Reply() {
		super();
	}
	
	public Reply(int no, String author, String createDate, int index) {
		super();
		this.no = no;
		this.author = author;
		this.createDate = createDate;
		this.boardindex = index;
	}
}

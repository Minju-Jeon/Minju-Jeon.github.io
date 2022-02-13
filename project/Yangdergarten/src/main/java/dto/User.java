package dto;

public class User {
	private String id;
	private String pwd;
	private String phone;
	private int studentNum;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", phone=" + phone + ", studentNum=" + studentNum + "]";
	}
	
	public User() {
		super();
	}
	
	public User(String id, String pwd, String phone, int studentNum) {
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.studentNum = studentNum;
	}
}

package dto;

public class Student extends BClassKind{
    private int studentNum;
    private String bName;
    private int bClassNum;
    private String phone;

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public int getbClassNum() {
        return bClassNum;
    }

    public void setbClassNum(int bClassNum) {
        this.bClassNum = bClassNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student [studentNum=" + studentNum + ", bName=" + bName + ", bClassNum=" + bClassNum + ", phone=" + phone + "]";
    }

    public Student() {
        super();
    }

    public Student(int studentNum, String bName, int bClassNum, String phone) {
        this.studentNum = studentNum;
        this.bName = bName;
        this.bClassNum = bClassNum;
        this.phone = phone;
    }
}

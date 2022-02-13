package dto;

public class BClassKind {

    private int bClassNum;
    private String bClass;

    public BClassKind() {}

    public BClassKind(int bClassNum, String bClass) {
        this.bClassNum = bClassNum;
        this.bClass = bClass;
    }

    public int getbClassNum() {
        return bClassNum;
    }

    public void setbClassNum(int bClassNum) {
        this.bClassNum = bClassNum;
    }

    public String getbClass() {
        return bClass;
    }

    public void setbClass(String bClass) {
        this.bClass = bClass;
    }

    @Override
    public String toString() {
        return "BClassKind{" +
                "bClassNum=" + bClassNum +
                ", bClass='" + bClass + '\'' +
                '}';
    }
}

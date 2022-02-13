package dto;

public class UserKind {
    private int cnum;
    private String cname;

    public UserKind(){}

//    public void UserKind(String cname) {
//        this.cname = cname;
//    }


    public UserKind(int cnum, String cname) {
        this.cnum = cnum;
        this.cname = cname;
    }

    public UserKind(String cname) {
        this.cname = cname;
    }

    public int getCnum() {
        return cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }

    public String getCname() {
        return cname;
    }

    public String setCname(String cname) {
        this.cname = cname;
        return null;
    }

    @Override
    public String toString() {
        return "UserKind{" +
                "cnum=" + cnum +
                ", cname='" + cname + '\'' +
                '}';
    }


}

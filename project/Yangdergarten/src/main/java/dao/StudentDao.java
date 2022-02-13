package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Student;
import utils.ConnectionHelper;

public class StudentDao {

    public int addStudent(Student st) { //원아등록ok
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultrow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");

            String sql = "insert into STUDENT(studentnum,bname,bclassnum,phone) values(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, st.getStudentNum());
            pstmt.setString(2, st.getbName());
            pstmt.setInt(3, st.getbClassNum());
            pstmt.setString(4, st.getPhone());

            resultrow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("addStudent : " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return resultrow;
    }

    public List<Student> showStudentList() throws SQLException { //원아목록show
        Connection conn = ConnectionHelper.getConnection("oracle");
        PreparedStatement pstmt = null;
//        String sql = "select studentnum,bname,bclassnum,phone from STUDENT";
        String sql = "SELECT STUDENT.STUDENTNUM, STUDENT.BNAME, STUDENT.phone ,BCLASSKIND.BCLASS " +
        "FROM STUDENT, BCLASSKIND " +
        "WHERE STUDENT.BCLASSNUM = BCLASSKIND.BCLASSNUM";

        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Student> studentList = new ArrayList<Student>();

        while (rs.next()) {
            Student st = new Student();
            st.setStudentNum(rs.getInt("studentnum"));
            st.setbName(rs.getString("bname"));
            st.setPhone(rs.getString("phone"));
            st.setbClass(rs.getString("bClass"));

            studentList.add(st);
        }
        ConnectionHelper.close(rs);
        ConnectionHelper.close(pstmt);
        ConnectionHelper.close(conn);

        return studentList;
    }

    public Student showStudentInfo(int studentNum) throws SQLException { //원아상세정보, 원아정보수정show
        Connection conn = ConnectionHelper.getConnection("oracle");
        PreparedStatement pstmt = null;
//        String sql = "select studentnum,bname,bclassnum,phone from STUDENT where studentnum=?";
        String sql = "SELECT STUDENT.STUDENTNUM, STUDENT.BNAME, STUDENT.phone, BCLASSKIND.BCLASS " +
                "FROM STUDENT, BCLASSKIND " +
                "WHERE STUDENT.BCLASSNUM = BCLASSKIND.BCLASSNUM AND STUDENTNUM=?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, studentNum);
        ResultSet rs = pstmt.executeQuery();

        Student st = new Student();

        while (rs.next()) {
            st.setStudentNum(rs.getInt("studentnum"));
            st.setbName(rs.getString("bname"));
            st.setPhone(rs.getString("phone"));
            st.setbClass(rs.getString("bClass"));
        }
        ConnectionHelper.close(rs);
        ConnectionHelper.close(pstmt);
        ConnectionHelper.close(conn);

        return st;
    }

    public int editStudentInfo(Student st) { //원아정보수정ok
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultrow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update STUDENT set bname=?, bclassnum=?, phone=? where studentnum=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, st.getbName());
            pstmt.setInt(2, st.getbClassNum());
            pstmt.setString(3, st.getPhone());
            pstmt.setInt(4, st.getStudentNum());

            resultrow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("editStudent : " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultrow;
    }

    public int deleteStudent(int studentNum) { //원아삭제ok
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultrow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "delete from STUDENT where studentnum=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentNum);

            resultrow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("deleteStudent : " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultrow;
    }

    public List<Student> searchStudentByName(String bName) throws SQLException { //원아이름검색
        Connection conn = ConnectionHelper.getConnection("oracle");
        PreparedStatement pstmt = null;
//        String sql = "select studentnum,bname,bclass,phone from STUDENT where bname like ?";
        String sql = "SELECT STUDENT.STUDENTNUM, STUDENT.BNAME, STUDENT.phone ,BCLASSKIND.BCLASS " +
                "FROM STUDENT, BCLASSKIND " +
                "WHERE STUDENT.BCLASSNUM = BCLASSKIND.BCLASSNUM and BNAME like ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "%" + bName + "%");
        System.out.println(bName);

        ResultSet rs = pstmt.executeQuery();
        List<Student> studentList = new ArrayList<Student>();

        while (rs.next()) {
            Student st = new Student();
            st.setStudentNum(rs.getInt("studentnum"));
            st.setbName(rs.getString("bname"));
            st.setPhone(rs.getString("phone"));
            st.setbClass(rs.getString("bClass"));

            studentList.add(st);
        }
        ConnectionHelper.close(rs);
        ConnectionHelper.close(pstmt);
        ConnectionHelper.close(conn);

        return studentList;
    }
}

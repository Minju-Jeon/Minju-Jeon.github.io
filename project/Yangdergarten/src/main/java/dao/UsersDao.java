package dao;

import dto.UsersDto;
import utils.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
   # users 구현해야하는 기능
     d @@@@@@@@@@@@@@@@@@@@@@@@ showSignUp => 회원가입 화면
     d * signUp => 회원가입 ok
     d @@@@@@@@@@@@@@@@@@@@@@@@ showLogin => 로그인 화면
     d * login => 로그인 ok
     d @@@@@@@@@@@@@@@@@@@@@@@@ logout => 로그아웃
     d * showUserList => 회원정보목록 화면
     d * showUserInfo => 회원 상세페이지 화면
     d * showEditUserInfo => 회원 정보 수정 화면
     d editUserInfo => 회원 정보수정 ok
     d * deleteUser => 회원 삭제 ok
     d * 회원 검색 ok

 */

public class UsersDao {

    //  회원가입 ok
    public int signUp(UsersDto usersDto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultrow = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
//                                                                     처음 가입은 우선 비회원 0
            String sql = "insert into users(id, pwd, phone, studentNum, cnum ) values(?,?,?,?,0)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usersDto.getId());
            pstmt.setString(2, usersDto.getPwd());
            pstmt.setString(3, usersDto.getPhone());
            pstmt.setInt(4, usersDto.getStudentNum());

            resultrow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("signUp Insert : " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultrow;
    }


    //  로그인 ok
    public UsersDto login(String id, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        UsersDto usersDto = new UsersDto();

        try {
            conn = ConnectionHelper.getConnection("oracle");

            String sql = "select id, pwd, cnum from users where id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                usersDto.setId(resultSet.getString("id"));
                usersDto.setPwd(resultSet.getString("pwd"));
                usersDto.setCnum(Integer.parseInt(resultSet.getString("cnum")));
            }

        } catch (Exception e) {
            System.out.println("logIn select : " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }

        return usersDto;
    }

    //  회원정보목록 화면
    public ArrayList<UsersDto> showUsersList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        ArrayList<UsersDto> usersList = null;

        try {
            conn = ConnectionHelper.getConnection("oracle");

//            String sql = "select id, pwd, phone, studentNum, cnum from users"; //조인 전 쿼리!
            String sql = "SELECT users.id, users.PWD, USERS.PHONE, USERS.STUDENTNUM, Userkind.cname " +
                "FROM users, USERKIND " +
                "WHERE users.CNUM = Userkind.CNUM";

            pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            usersList = new ArrayList<UsersDto>();


            while (resultSet.next()) {
                UsersDto usersDto = new UsersDto();
                usersDto.setId(resultSet.getString("id"));
                usersDto.setPwd(resultSet.getString("pwd"));
                usersDto.setPhone(resultSet.getString("phone"));
                usersDto.setStudentNum(Integer.parseInt(resultSet.getString("studentNum")));
                usersDto.setCname(resultSet.getString("cname"));

                usersList.add(usersDto);
            }

        } catch (Exception e) {
            System.out.println("UserList Select : " + e.getMessage());
        } finally {
            ConnectionHelper.close(resultSet);
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return usersList;
    }

    // 회원 상세 정보, 회원 정보 수정 show
    public UsersDto showUserInfo(String id) throws SQLException {

        Connection conn = ConnectionHelper.getConnection("oracle");

        String sql = "SELECT users.id, users.PWD, USERS.PHONE, USERS.STUDENTNUM, Userkind.cname " +
                "FROM users, USERKIND " +
                "WHERE users.CNUM = Userkind.CNUM AND id=?";

//        String sql = "select id, pwd, phone, studentnum from users where id=?"; // 조인 이전 쿼리문

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, id);

        ResultSet resultSet = pstmt.executeQuery();

        UsersDto usersDto = new UsersDto();


        while (resultSet.next()) {

            usersDto.setId(resultSet.getString("id"));
            usersDto.setPwd(resultSet.getString("pwd"));
            usersDto.setPhone(resultSet.getString("phone"));
            usersDto.setStudentNum(Integer.parseInt(resultSet.getString("studentNum")));
            usersDto.setCname(resultSet.getString("cname"));

        }

        ConnectionHelper.close(resultSet);
        ConnectionHelper.close(pstmt);
        ConnectionHelper.close(conn);
        System.out.println(usersDto);

        return usersDto;
    }

    //  회원 삭제
    public int deleteUser(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int resultrow = 0;
        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "delete from users where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            resultrow = pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("User delete Select : " + e.getMessage());

        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return resultrow;
    }


    //  회원 수정 화면
    public UsersDto showEditUserInfo(String id) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        UsersDto usersDto = new UsersDto();

        try {
            conn = ConnectionHelper.getConnection("oracle");// 추가

            String sql = "select id, pwd, phone, studentNum, cnum from users where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                usersDto.setId(resultSet.getString("id"));
                usersDto.setPwd(resultSet.getString("pwd"));
                usersDto.setPhone(resultSet.getString("phone"));
                usersDto.setStudentNum(resultSet.getInt("studentNum"));
                usersDto.setCnum(resultSet.getInt("cnum"));
            }

        } catch (Exception e) {
            System.out.println("User Edit Select : " + e.getMessage());

        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
        }
        return usersDto;
    }


    //  회원 수정 ok
    public int editUserInfo(UsersDto usersDto) {

        Connection conn = null;
        Connection conn2 = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        int resultrow = 0;
        int resultrow2 = 0;

        try {
            conn = ConnectionHelper.getConnection("oracle");
            String sql = "update users set pwd=?, phone=?, studentNum=?, cnum=? where id=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, usersDto.getPwd());
            pstmt.setString(2, usersDto.getPhone());
            pstmt.setInt(3, usersDto.getStudentNum());
            pstmt.setInt(4, usersDto.getCnum());
            pstmt.setString(5, usersDto.getId());

            resultrow = pstmt.executeUpdate();
            
            conn2 = ConnectionHelper.getConnection("oracle");
            String sql2 = "update STUDENT set phone=? where studentnum=?";
            pstmt2 = conn2.prepareStatement(sql2);
            
            pstmt2.setString(1, usersDto.getPhone());
            pstmt2.setInt(2, usersDto.getStudentNum());
            System.out.println("핸드폰번호 : "+usersDto.getPhone()+" /원아번호 : "+usersDto.getStudentNum());
            
            resultrow2 = pstmt2.executeUpdate();
            System.out.println("원아 핸드폰번호 수정건 : "+resultrow2);

        } catch (Exception e) {
            System.out.println("User Edit update : " + e.getMessage());
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(pstmt2);
            ConnectionHelper.close(conn);
            ConnectionHelper.close(conn2);
        }
        return resultrow;
    }

    //  회원 id 검색
    public ArrayList<UsersDto> searchUsersById(String id) throws SQLException {
        Connection conn = ConnectionHelper.getConnection("oracle");
        PreparedStatement pstmt = null;

//        String sql = "select id, pwd, phone, STUDENTNUM, cnum from users where id like ?"; // 조인 전 쿼리
        String sql = "SELECT users.id, users.PWD, USERS.PHONE, USERS.STUDENTNUM, Userkind.cname " +
                "FROM users, USERKIND " +
                "WHERE users.CNUM = Userkind.CNUM AND id like ?";

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "%" + id + "%");
        System.out.println(id);

        ResultSet resultSet = pstmt.executeQuery();
        ArrayList<UsersDto> usersDtoList = new ArrayList<UsersDto>();

        while (resultSet.next()) {
            UsersDto usersDto = new UsersDto();
            usersDto.setId(resultSet.getString("id"));
            usersDto.setPwd(resultSet.getString("pwd"));
            usersDto.setPhone(resultSet.getString("phone"));
            usersDto.setStudentNum(resultSet.getInt("studentnum"));
            usersDto.setCname(resultSet.getString("cname"));

            usersDtoList.add(usersDto);
        }
        ConnectionHelper.close(resultSet);
        ConnectionHelper.close(pstmt);
        ConnectionHelper.close(conn);

        return usersDtoList;
    }

}

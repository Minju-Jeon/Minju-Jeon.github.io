package dao;

import dto.Reply;
import utils.ConnectionHelper;
import utils.DateFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
      # 있어야 하는 메소드 and 설명

 */
public class ReplyDao {

    public void insertReply(String boardindex, String author , String content) throws SQLException {

        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;

        try {
            //커넥션 획득
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            //sql 입력
            String sql = "INSERT INTO REPLY(NO,AUTHOR,CONTENT,CREATEDATE,BOARDINDEX) VALUES(REPLY_SEQUENCE.nextval,?,?,SYSDATE,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,author);
            pstmt.setString(2,content);
            pstmt.setInt(3,Integer.parseInt(boardindex));
            resultrow = pstmt.executeUpdate();
            System.out.println(resultrow);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Reply> getReplies(String boardindex){
        DateFormat dateFormat = new DateFormat();
        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;
        ArrayList<Reply> replyList = new ArrayList<Reply>();

        try {
            //커넥션 획득
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            //sql 입력
            String sql = "SELECT NO,AUTHOR,CONTENT,CREATEDATE,BOARDINDEX FROM REPLY WHERE boardindex = ? ORDER by createdate desc";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(boardindex));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Reply reply = new Reply();
                reply.setNo(rs.getInt("no"));
                reply.setAuthor(rs.getString("author"));
                reply.setContent(rs.getString("content"));
                reply.setCreateDate(dateFormat.transform(rs.getDate("createdate")));
                reply.setIndex(rs.getInt("boardindex"));
                replyList.add(reply);
            }
            System.out.println(resultrow);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return replyList;
    }

    public void deleteReply(String boardindex, String no){
        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;

        try {

            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            String delete_sql = "DELETE FROM reply WHERE boardindex = ? and no = ? ";
            pstmt =  conn.prepareStatement(delete_sql);
            pstmt.setInt(1,Integer.parseInt(boardindex));
            System.out.println(no);
            pstmt.setInt(2,Integer.parseInt(no));
            pstmt.executeUpdate();


            //sql 입력

            System.out.println(resultrow);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.close(pstmt);
            ConnectionHelper.close(conn);
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

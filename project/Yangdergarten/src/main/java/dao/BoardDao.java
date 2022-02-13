package dao;


import dto.Board;
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
public class BoardDao {



    /*
    private int index;
	private int boardId;
	private String createDate;
	private String updateDate;
	private String author;
	private String title;
	private String content;
	private int reference;
	private int depth;
	private int step;
     */

    public void insertBoard(int boardid, String author, String title, String content) throws SQLException {

        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;

        try {
            //커넥션 획득
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            //sql 입력
            String sql = "INSERT INTO BOARD(boardindex,REFERENCE,Boardid,author,title,content) VALUES(BOARD_SEQUENCE.nextval,BOARD_SEQUENCE.nextval,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, boardid);
            pstmt.setString(2, author);
            pstmt.setString(3, title);
            pstmt.setString(4, content);

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

    public void insertReBoard(String author, String content, Board pboard){

        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;

        try {
            //커넥션 획득
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            String get_ccount = "select ccount FROM BOARD WHERE depth = ? and REFERENCE = ?";
            pstmt =  conn.prepareStatement(get_ccount);

            pstmt.setInt(1,pboard.getDepth()+1);
            pstmt.setInt(2,pboard.getReference());
            ResultSet rs = pstmt.executeQuery();
            int ccount = 0;
            while (rs.next()){
                ccount += rs.getInt("ccount");
            }
            System.out.println("ccount : " + ccount);

            String update_sql = "UPDATE BOARD SET STEP = STEP + 1 WHERE STEP >= ? and REFERENCE = ?";
            pstmt =  conn.prepareStatement(update_sql);

            pstmt.setInt(1,pboard.getStep()-pboard.getCcount()-ccount);
            pstmt.setInt(2, pboard.getReference());

            resultrow = pstmt.executeUpdate();
            System.out.println(resultrow);

            //sql 입력
            String sql = "INSERT INTO BOARD(boardindex,Boardid,author,title,content,REFERENCE,depth,pnum,step) VALUES(BOARD_SEQUENCE.nextval,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);


            pstmt.setInt(1, pboard.getBoardId());
            pstmt.setString(2,author);
            pstmt.setString(3, " re : " + pboard.getTitle());
            pstmt.setString(4,content);
            pstmt.setInt(5, pboard.getReference());
            pstmt.setInt(6,pboard.getDepth()+1);
            pstmt.setInt(7,pboard.getBoardindex());
            pstmt.setInt(8, pboard.getStep()-pboard.getCcount()-ccount);

            resultrow = pstmt.executeUpdate();
            System.out.println(resultrow);

            String increaseccount = "UPDATE BOARD SET CCOUNT = CCOUNT + 1 WHERE BOARDINDEX = ? and REFERENCE = ?";
            pstmt =  conn.prepareStatement(increaseccount);

            pstmt.setInt(1,pboard.getBoardindex());
            pstmt.setInt(2, pboard.getReference());

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

    public int totalBoardCount(String boardid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalcount = 0;
        try {
            conn = ConnectionHelper.getConnection("oracle"); //dbcp 연결객체 얻기
            String sql="select count(*) cnt from board WHERE boardid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(boardid));
            rs = pstmt.executeQuery();
            if(rs.next()) {
                totalcount = rs.getInt("cnt");
            }
        }catch (Exception e) {

        }finally {
            try {
                pstmt.close();
                rs.close();
                conn.close();//반환  connection pool 에 반환하기
            }catch (Exception e) {

            }
        }
        return totalcount;
    }

    public int totalBoardCountName(String author,String boardid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalcount = 0;
        try {
            conn = ConnectionHelper.getConnection("oracle"); //dbcp 연결객체 얻기
            String sql="select count(*) cnt from board WHERE author = ? AND boardid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,author);
            pstmt.setInt(2, Integer.parseInt(boardid));
            rs = pstmt.executeQuery();
            if(rs.next()) {
                totalcount = rs.getInt("cnt");
            }
        }catch (Exception e) {

        }finally {
            try {
                pstmt.close();
                rs.close();
                conn.close();//반환  connection pool 에 반환하기
            }catch (Exception e) {

            }
        }
        return totalcount;
    }

    public int totalBoardCountTitle(String title,String boardid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalcount = 0;
        try {
            conn = ConnectionHelper.getConnection("oracle"); //dbcp 연결객체 얻기
            String sql="select count(*) cnt from board WHERE title = ? and boardid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,title);
            pstmt.setInt(2, Integer.parseInt(boardid));
            rs = pstmt.executeQuery();
            if(rs.next()) {
                totalcount = rs.getInt("cnt");
            }
        }catch (Exception e) {

        }finally {
            try {
                pstmt.close();
                rs.close();
                conn.close();//반환  connection pool 에 반환하기
            }catch (Exception e) {

            }
        }
        return totalcount;
    }

    public ArrayList<Board> getBoard(String boardid, int cpage, int pagesize) {
        DateFormat dateFormat = new DateFormat();
        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;
        ArrayList<Board> boardList = new ArrayList<Board>();

        try {
            //커넥션 획득
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            //sql 입력
//            String sql = "SELECT BOARDINDEX, BOARDID, AUTHOR, CREATEDATE, UPDATEDATE, TITLE, CONTENT, REFERENCE, DEPTH , STEP, PNUM, CCOUNT FROM board WHERE boardid = ? Order by REFERENCE desc , step desc";
            String sql = "select * from " +
                    "(select rownum rn ,BOARDINDEX, BOARDID, AUTHOR, CREATEDATE, UPDATEDATE, TITLE, CONTENT, REFERENCE, DEPTH , STEP, PNUM, CCOUNT " +
                    " from ( SELECT * FROM board WHERE boardid = ? ORDER BY REFERENCE DESC , step DESC ) "+
                    " where rownum <= ?" +  //endrow
                    ") where rn >= ?"; //startrow
            int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
            int end = cpage * pagesize; // 1 * 5 >> 5

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(boardid));
            pstmt.setInt(2,end);
            pstmt.setInt(3,start);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Board board = new Board();
                board.setBoardindex(rs.getInt("boardindex"));
                board.setBoardId(rs.getInt("boardid"));
                board.setAuthor(rs.getString("author"));
                board.setCreateDate(dateFormat.transform(rs.getDate("createdate")));
                board.setUpdateDate(dateFormat.transform(rs.getDate("updatedate")));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setReference(rs.getInt("reference"));
                board.setDepth(rs.getInt("depth"));
                board.setStep(rs.getInt("step"));
                board.setPnum(rs.getInt("pnum"));
                board.setCcount(rs.getInt("ccount"));
                boardList.add(board);
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
        return boardList;

    }

    public ArrayList<Board> getBoardByName(String boardid, int cpage, int pagesize, String seachvar) {
        DateFormat dateFormat = new DateFormat();
        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;
        ArrayList<Board> boardList = new ArrayList<Board>();

        try {
            //커넥션 획득
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            //sql 입력
//            String sql = "SELECT BOARDINDEX, BOARDID, AUTHOR, CREATEDATE, UPDATEDATE, TITLE, CONTENT, REFERENCE, DEPTH , STEP, PNUM, CCOUNT FROM board WHERE boardid = ? Order by REFERENCE desc , step desc";
            String sql = "select * from " +
                    "(select rownum rn ,BOARDINDEX, BOARDID, AUTHOR, CREATEDATE, UPDATEDATE, TITLE, CONTENT, REFERENCE, DEPTH , STEP, PNUM, CCOUNT " +
                    " from ( SELECT * FROM board WHERE boardid = ? AND author like ? ORDER BY REFERENCE DESC , step DESC ) "+
                    " where rownum <= ?" +  //endrow
                    ") where rn >= ?"; //startrow
            int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
            int end = cpage * pagesize; // 1 * 5 >> 5

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(boardid));
            pstmt.setString(2,"%" + seachvar + "%");
            pstmt.setInt(3,end);
            pstmt.setInt(4,start);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Board board = new Board();
                board.setBoardindex(rs.getInt("boardindex"));
                board.setBoardId(rs.getInt("boardid"));
                board.setAuthor(rs.getString("author"));
                board.setCreateDate(dateFormat.transform(rs.getDate("createdate")));
                board.setUpdateDate(dateFormat.transform(rs.getDate("updatedate")));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setReference(rs.getInt("reference"));
                board.setDepth(rs.getInt("depth"));
                board.setStep(rs.getInt("step"));
                board.setPnum(rs.getInt("pnum"));
                board.setCcount(rs.getInt("ccount"));
                boardList.add(board);
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
        return boardList;

    }

    public ArrayList<Board> getBoardByTitle(String boardid, int cpage, int pagesize, String seachvar) {
        DateFormat dateFormat = new DateFormat();
        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;
        ArrayList<Board> boardList = new ArrayList<Board>();

        try {
            //커넥션 획득
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            //sql 입력
//            String sql = "SELECT BOARDINDEX, BOARDID, AUTHOR, CREATEDATE, UPDATEDATE, TITLE, CONTENT, REFERENCE, DEPTH , STEP, PNUM, CCOUNT FROM board WHERE boardid = ? Order by REFERENCE desc , step desc";
            String sql = "select * from " +
                    "(select rownum rn ,BOARDINDEX, BOARDID, AUTHOR, CREATEDATE, UPDATEDATE, TITLE, CONTENT, REFERENCE, DEPTH , STEP, PNUM, CCOUNT " +
                    " from ( SELECT * FROM board WHERE boardid = ? AND title LIKE ? ORDER BY REFERENCE DESC , step DESC ) "+
                    " where rownum <= ?" +  //endrow
                    ") where rn >= ?"; //startrow
            int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
            int end = cpage * pagesize; // 1 * 5 >> 5

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(boardid));
            pstmt.setString(2,"%" + seachvar + "%");
            pstmt.setInt(3,end);
            pstmt.setInt(4,start);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Board board = new Board();
                board.setBoardindex(rs.getInt("boardindex"));
                board.setBoardId(rs.getInt("boardid"));
                board.setAuthor(rs.getString("author"));
                board.setCreateDate(dateFormat.transform(rs.getDate("createdate")));
                board.setUpdateDate(dateFormat.transform(rs.getDate("updatedate")));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setReference(rs.getInt("reference"));
                board.setDepth(rs.getInt("depth"));
                board.setStep(rs.getInt("step"));
                board.setPnum(rs.getInt("pnum"));
                board.setCcount(rs.getInt("ccount"));
                boardList.add(board);
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
        return boardList;

    }

    public Board getBoardContent(String boardindex) {
        DateFormat dateFormat = new DateFormat();
        Connection conn = null;
        PreparedStatement pstmt = null;
        Board board = new Board();

        try {
            //커넥션 획득
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            //sql 입력
            String sql = "SELECT BOARDINDEX, BOARDID, AUTHOR, CREATEDATE, UPDATEDATE, TITLE, CONTENT, REFERENCE, DEPTH , STEP, PNUM, CCOUNT FROM board WHERE boardindex = ?";
            pstmt = conn.prepareStatement(sql);
            System.out.println("dao"+boardindex);
            pstmt.setInt(1, Integer.parseInt(boardindex));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){

                board.setBoardindex(rs.getInt("boardindex"));
                board.setBoardId(rs.getInt("boardid"));
                board.setAuthor(rs.getString("author"));
                board.setCreateDate(dateFormat.transform(rs.getDate("createdate")));
                board.setUpdateDate(dateFormat.transform(rs.getDate("updatedate")));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setReference(rs.getInt("reference"));
                board.setDepth(rs.getInt("depth"));
                board.setStep(rs.getInt("step"));
                board.setPnum(rs.getInt("pnum"));
                board.setCcount(rs.getInt("ccount"));

            }

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
        return board;

    }

    public void editBoard(String boardindex, String title, String content) {

        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;

        try {
            //커넥션 획득
            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;

            //sql 입력
            String sql = "UPDATE board SET updatedate = SYSDATE, title = ?,content = ?  WHERE boardindex= ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, title);
            pstmt.setString(2, content);
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


    public void deleteBoard(String boardindex, Board board) {

        Connection conn = null;
        int resultrow = 0;
        PreparedStatement pstmt = null;

        try {

            conn = ConnectionHelper.getConnection("oracle");
            pstmt = null;


            int[] ints = new int[2];
            int breaker = 0;
            int comstep = 0;

            //1. depth가 같은 게시물이 나올때까지의 거리를 계산하는 sql문입니다.
            String select_sql = "select step from board where DEPTH = ? and REFERENCE = ? ORDER by step desc";
            pstmt = conn.prepareStatement(select_sql);
            pstmt.setInt(1, board.getDepth());
            pstmt.setInt(2,board.getReference());
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()&&breaker<2){

                ints[breaker] = rs.getInt("step");
                breaker++;

            } //breaker의 수치를 더해서 상황별로 아래의 if문에서 다른 로직을 실행시킴.

            comstep = ints[0] - ints[1]; //현재 글을 기준으로 depth가 같은 게시물 사이의 거리


            ArrayList<Integer> arrayList = new ArrayList<Integer>();

            if(breaker==1){ //다음에 같은 depth를 가진 글이 없을 때

                //2. 스텝의 거리를 기준으로 삭제할 boardindex값 받아오기 (댓글 삭제때문에 필요하게 되었습니다.)

                String select_sql1 = "select boardindex FROM board WHERE step <= ? and REFERENCE = ?";
                pstmt =  conn.prepareStatement(select_sql1);
                pstmt.setInt(1,ints[0]);
                pstmt.setInt(2,board.getReference());
                ResultSet rs1 = pstmt.executeQuery();

                while (rs1.next()){
                    String delete_reply = "DELETE FROM REPLY WHERE BOARDINDEX = ?";
                    pstmt = conn.prepareStatement(delete_reply);
                    pstmt.setInt(1,rs1.getInt("boardindex"));
                    pstmt.executeUpdate();

                }


                String delete_sql = "DELETE FROM board WHERE step <= ? and REFERENCE = ?";
                pstmt =  conn.prepareStatement(delete_sql);
                pstmt.setInt(1,ints[0]);
                pstmt.setInt(2,board.getReference());
                pstmt.executeUpdate();
            }
            if(board.getBoardindex()==board.getReference()){ //원글일 때

                String select_sql1 = "SELECT boardindex FROM board WHERE REFERENCE = ?";
                pstmt =  conn.prepareStatement(select_sql1);
                pstmt.setInt(1,board.getReference());
                ResultSet rs1 = pstmt.executeQuery();

                while (rs1.next()){
                    String delete_reply = "DELETE FROM REPLY WHERE BOARDINDEX = ?";
                    pstmt = conn.prepareStatement(delete_reply);
                    pstmt.setInt(1,rs1.getInt("boardindex"));
                    pstmt.executeUpdate();

                }

                String delete_sql = "DELETE FROM board WHERE REFERENCE = ?";
                pstmt =  conn.prepareStatement(delete_sql);

                pstmt.setInt(1,board.getReference());
                pstmt.executeUpdate();
            }else { //다음에 같은 depth를 가진 글이 있을 때

                String select_sql1 = "SELECT boardindex FROM board WHEREstep <= ? and step > ? and REFERENCE = ?";
                pstmt =  conn.prepareStatement(select_sql1);
                pstmt.setInt(1,ints[0]);
                pstmt.setInt(2,ints[1]);
                pstmt.setInt(3,board.getReference());
                ResultSet rs1 = pstmt.executeQuery();

                while (rs1.next()){
                    String delete_reply = "DELETE FROM REPLY WHERE BOARDINDEX = ?";
                    pstmt = conn.prepareStatement(delete_reply);
                    pstmt.setInt(1,rs1.getInt("boardindex"));
                    pstmt.executeUpdate();

                }
                String delete_sql = "DELETE FROM board WHERE step <= ? and step > ? and REFERENCE = ?";
                pstmt =  conn.prepareStatement(delete_sql);
                pstmt.setInt(1,ints[0]);
                pstmt.setInt(2,ints[1]);
                pstmt.setInt(3,board.getReference());
                pstmt.executeUpdate();
            }


            String ccount_sql = "UPDATE board SET CCOUNT = CCOUNT - 1  WHERE BOARDINDEX = ?";
            pstmt = conn.prepareStatement(ccount_sql);
            pstmt.setInt(1,board.getPnum());
            pstmt.executeUpdate();

            String update_sql2 = "UPDATE board SET step = step - ?  WHERE REFERENCE = ? AND step > ?";
            pstmt = conn.prepareStatement(update_sql2);
            pstmt.setInt(1,comstep);
            pstmt.setInt(2,board.getReference());
            pstmt.setInt(3,board.getStep());

            pstmt.executeUpdate();











            //커넥션 획득

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

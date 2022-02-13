package service.board;

import action.Action;
import action.ActionForward;
import dao.BoardDao;
import dto.Board;
import utils.TheBoardPager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Leo.
 * User: KBS
 * Date: 9/25/2021
 * Time: 오후 10:33
 */
public class ShowBoard implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        BoardDao boardDao = new BoardDao();
        String boardid = request.getParameter("boardid");
        if(boardid == null){
            boardid =  "0";
        }
        ///////////////////////////////////////////////////////
        String ps = request.getParameter("ps");
        String cp = request.getParameter("cp");

        if(ps == null || ps.trim().equals("")) {
            //default값 설정
            ps = "5"; //5개의 묶음
        }

        if(cp == null || cp.trim().equals("")) {
            //default값 설정
            cp = "1"; //전체 묶음 중에서 처음 페이지를 보겠다
        }


        int pagesize = Integer.parseInt(ps);
        int cpage = Integer.parseInt(cp);
        int totalboardcount = boardDao.totalBoardCount(boardid);
        int pagersize = 3; //[1][2][3] ...
        String url = "";
        url = "BoardList.samb?boardid="+ boardid;
        TheBoardPager pager = new TheBoardPager(totalboardcount, cpage, pagesize, pagersize, url);


        ///////////////////////////////////////////////////////
        ArrayList<Board> boardArrayList = null;

        boardArrayList = boardDao.getBoard(boardid,cpage,pagesize);

        ActionForward forward  = new ActionForward();
        request.setAttribute("pager", pager);
        request.setAttribute("boardid",boardid);
        request.setAttribute("boardlist",boardArrayList);
        forward.setPath("/WEB-INF/views/boardMain.jsp");

        return forward;
    }
}

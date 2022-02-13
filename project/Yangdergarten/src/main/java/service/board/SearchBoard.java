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
 * Date: 9/28/2021
 * Time: 오후 2:59
 */
public class SearchBoard implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        BoardDao boardDao = new BoardDao();
        String boardid = request.getParameter("boardid");
        String seachmode = request.getParameter("searchmode");
        String searchvar = request.getParameter("searchvar");

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
        int totalboardcount = 0;
        int pagersize = 3; //[1][2][3] ...
        String url = "";
        url = "BoardList.samb?boardid="+ boardid;


        ///////////////////////////////////////////////////////
        ArrayList<Board> boardArrayList = null;

        if(seachmode.equals("0")){
            boardArrayList = boardDao.getBoardByName(boardid,cpage,pagesize,searchvar);
            totalboardcount =  boardDao.totalBoardCountName(searchvar,boardid);
        }else {
            boardArrayList = boardDao.getBoardByTitle(boardid,cpage,pagesize,searchvar);
            totalboardcount =  boardDao.totalBoardCountTitle(searchvar,boardid);
        }

        TheBoardPager pager = new TheBoardPager(totalboardcount, cpage, pagesize, pagersize, url);
        ActionForward forward  = new ActionForward();
        request.setAttribute("pager", pager);
        request.setAttribute("boardid",boardid);
        request.setAttribute("boardlist",boardArrayList);
        forward.setPath("/WEB-INF/views/boardMain.jsp");

        return forward;
    }
}

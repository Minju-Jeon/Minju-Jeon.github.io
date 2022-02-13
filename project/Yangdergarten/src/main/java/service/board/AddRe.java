package service.board;

import action.Action;
import action.ActionForward;
import dao.BoardDao;
import dto.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by Leo.
 * User: KBS
 * Date: 9/26/2021
 * Time: 오후 4:41
 */
public class AddRe implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        BoardDao boardDao = new BoardDao();
        HttpSession session = request.getSession(true);
        String boardindex = request.getParameter("boardindex");
        String boardid = request.getParameter("boardid");
        String author = (String)session.getAttribute("userId");
        String content = request.getParameter("content");
        Board board = null;
        board = boardDao.getBoardContent(boardindex);
        System.out.println(board.toString());

        boardDao.insertReBoard(author,content,board);

        ActionForward forward  = new ActionForward();

        request.setAttribute("boardid",boardid);

        forward.setPath("/BoardList.samb");
        return forward;
    }
}

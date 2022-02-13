package service.board;

import action.Action;
import action.ActionForward;
import dao.BoardDao;
import dto.Board;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Leo.
 * User: KBS
 * Date: 9/26/2021
 * Time: 오전 3:56
 */
public class DeleteBoard implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        BoardDao boardDao = new BoardDao();

        String boardindex = request.getParameter("boardindex");
        String boardid = request.getParameter("boardid");

        Board board = null;
        board = boardDao.getBoardContent(boardindex);



        boardDao.deleteBoard(boardindex,board);

        ActionForward forward  = new ActionForward();

        request.setAttribute("boardid",boardid);

        forward.setPath("/BoardList.samb");
        return forward;


    }
}

package service.board;

import action.Action;
import action.ActionForward;
import dao.BoardDao;
import dto.Board;
import service.reply.ShowReply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by Leo.
 * User: KBS
 * Date: 9/26/2021
 * Time: 오전 1:29
 */
public class ShowBoardContent implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        ActionForward forward  = new ActionForward();
        HttpSession session = request.getSession(true);
        if(session.getAttribute("userKind")==null){
            String msg="";
            String url="";
            msg = "로그인 후 이용해주세요";
            url = "BoardList.samb?boardid=" + request.getParameter("boardid");
            request.setAttribute("sam_msg", msg);
            request.setAttribute("sam_url", url);
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/redirect.jsp");
        }else {
            String boardindex = request.getParameter("boardindex");
            System.out.println(boardindex);
            Board board = null;
            //DAO
            BoardDao boardDao = new BoardDao();

            board = boardDao.getBoardContent(boardindex);
            ShowReply showReply =  new ShowReply();
            showReply.execute(request,response);
            request.setAttribute("board",board);
            forward.setPath("/WEB-INF/views/boardDetail.jsp");
        }

        return forward;
    }
}

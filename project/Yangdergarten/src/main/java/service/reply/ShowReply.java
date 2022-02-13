package service.reply;

import action.Action;
import action.ActionForward;
import dao.ReplyDao;
import dto.Reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Leo.
 * User: KBS
 * Date: 9/28/2021
 * Time: 오전 9:48
 */
public class ShowReply implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {


        String boardindex = request.getParameter("boardindex");

        ReplyDao replyDao = new ReplyDao();
        ArrayList<Reply> replyArrayList = null;
        replyArrayList = replyDao.getReplies(boardindex);

        ActionForward forward  = new ActionForward();
        request.setAttribute("replylist",replyArrayList);
        return forward;

    }
}

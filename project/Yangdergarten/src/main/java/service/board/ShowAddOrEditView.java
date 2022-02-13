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
 * Date: 9/25/2021
 * Time: 오후 11:06
 */
public class ShowAddOrEditView implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        ActionForward forward  = new ActionForward();
        HttpSession session = request.getSession(true);
        String boardindex = request.getParameter("boardindex");
        String boardid = request.getParameter("boardid");
        String mode = request.getParameter("mode");

        Object userkindobj =session.getAttribute("userKind");

        if(userkindobj == null){
            userkindobj = 0;
        }

        int userkind = (Integer)userkindobj;
        System.out.println("userkind : " + userkind);

        if (boardid.equals("3")&&userkind == 0) {
        	
        	 String msg="";
             String url="";
             msg = "정회원만 사용할 수 있는 기능입니다.";
             url = "BoardList.samb?boardid=" + request.getParameter("boardid");
             request.setAttribute("sam_msg", msg);
             request.setAttribute("sam_url", url);
             forward.setRedirect(false);
             forward.setPath("/WEB-INF/views/redirect.jsp");
        	
      

        }else if(boardid.equals("0")&&userkind!=2) {

            String msg="";
            String url="";
            msg = "권한이 없습니다.";
            url = "BoardList.samb?boardid=" + request.getParameter("boardid");
            request.setAttribute("sam_msg", msg);
            request.setAttribute("sam_url", url);
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/redirect.jsp");

        }else if(boardid.equals("1")&&userkind!=2){

            String msg="";
            String url="";
            msg = "권한이 없습니다.";
            url = "BoardList.samb?boardid=" + request.getParameter("boardid");
            request.setAttribute("sam_msg", msg);
            request.setAttribute("sam_url", url);
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/redirect.jsp");

        }else if (userkind==0) {
        	
        	
            String msg="";
            String url="";
            msg = "권한이 없습니다.";
            url = "BoardList.samb?boardid=" + request.getParameter("boardid");
            request.setAttribute("sam_msg", msg);
            request.setAttribute("sam_url", url);
            forward.setRedirect(false);
            forward.setPath("/WEB-INF/views/redirect.jsp");
            
            
        }else {

        		



            Board board = null;

            if(!mode.equals("0")){
                BoardDao boardDao = new BoardDao();
                board = boardDao.getBoardContent(boardindex);

            }

            forward = new ActionForward();

            request.setAttribute("boardindex",boardindex);
            request.setAttribute("boardid", boardid);
            request.setAttribute("mode", mode);
            request.setAttribute("board",board);


            if(request.getParameter("depth")!=null){
                int depth = Integer.parseInt(request.getParameter("depth"));
                request.setAttribute("depth",depth);
            }

            if(request.getParameter("reference")!=null){
                int reference = Integer.parseInt(request.getParameter("reference"));
                request.setAttribute("reference",reference);
            }

            forward.setPath("/WEB-INF/views/addboard.jsp");
        }



        return forward;
    }
}

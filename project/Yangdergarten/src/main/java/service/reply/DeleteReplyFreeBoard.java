package service.reply;

import action.Action;
import action.ActionForward;
import dao.BoardDao;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
강사님 파일 복붙하여 채워져 있지만 지우고 다시해도 됩니다!
 */
public class DeleteReplyFreeBoard implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx_fk = request.getParameter("idx");// 댓글의 원본 게시글 번호
		String no = request.getParameter("no");// 댓글의 순번(PK)
		String pwd = request.getParameter("delPwd");// 댓글의 암호

		String msg = "";
		String url = "";

		if (idx_fk == null || no == null || pwd == null || no.trim().equals("")) {
			msg = "이상한 링크 입니다.";
			url = "BoardContent.do?idx=" + idx_fk;
		} else {

			try {
				BoardDao dao = new BoardDao();

				int result = dao.replyDelete(no, pwd);

				if (result > 0) {
					msg = "댓글 삭제 성공";
					url = "BoardContent.do?idx=" + idx_fk;
				} else {
					msg = "댓글 삭제 실패";
					url = "BoardContent.do?idx=" + idx_fk;
				}

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/redirect.jsp");

		return forward;
	}

}

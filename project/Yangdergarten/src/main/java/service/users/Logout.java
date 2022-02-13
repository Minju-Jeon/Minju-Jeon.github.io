package service.users;

import action.Action;
import action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession();

    session.invalidate();

    ActionForward forward = new ActionForward();
    forward.setRedirect(false);
    forward.setPath("GoToMain.samb");

    return forward;
  }
}

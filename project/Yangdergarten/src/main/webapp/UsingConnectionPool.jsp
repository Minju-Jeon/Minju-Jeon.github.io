<%@ page import="java.sql.Connection" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %><%--
  Created by IntelliJ IDEA.
  User: yangsujin
  Date: 2021/09/10
  Time: 9:56 오전
  To change this template use File | Settings | File Templates.
  풀링 테스트 하는 파일
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Tomcat Connection Pool TEST</title>
</head>
<body>
<%--커넥션 인터페이스 타입 --%>
  <%
    Connection conn = null;

    //JNDI (현재 프로젝트에서 특정한 이름으로 파일을 검색)
    Context context = new InitialContext(); //javax.naming // 컨텍스트 객체를 쓰면 검색기능 가능
//    DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
    DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
    //java:comp/env 까지는 정해진 약속된 표현 + 이름을 준다 context파일안에 name name="jdbc/oracle" 이름
    //위에 치면 상점을 찾은거 상점은 type="javax.sql.DataSource" 데이터 소스타입.// 상점 여러갠데 데이터 소스타입을 판매하는 상점
    //DataSource 판매하는 상점 (튜브를 판매)

    //POOL 안에서 Connection 빌려주세요
    conn = ds.getConnection();

    out.print("db연결여부 :" + conn.isClosed() + "<br>");

    //반드시 사용 후에는 반환 (메서드 안에서 빌리고 반환)
    conn.close(); //반환(POOL)

    out.print("db연결여부 :" + conn.isClosed() + "<br>");
    //반환 끝났으면 연결이고 뭐고 없겟지?

  %>
</body>
</html>

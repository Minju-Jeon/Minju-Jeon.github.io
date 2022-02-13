<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String msg = (String)request.getAttribute("sam_msg");
	String url = (String)request.getAttribute("sam_url");
  
	if(msg != null && url != null){
%>
	<script>
		alert('<%= msg %>');
		<%-- swal("<%= msg %>","warning"); --%>
	    location.href='<%=url%>';
	</script>
	
<%	  		
  }
%>
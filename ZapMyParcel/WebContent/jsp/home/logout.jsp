<%
	if(session.getAttribute("username")!=null){	
		session.removeAttribute("username"); // remove username from session 
		session.removeAttribute("access_zones"); // for handling menu.
		session.removeAttribute("userid");  //user id
	}
%>
<script type"text/javascript"> window.location.href = "http://localhost:8084/IFS"; </script>
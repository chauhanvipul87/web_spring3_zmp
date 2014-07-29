<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:choose>
	<c:when test="${option =='Failure' and ErrorMsg !=null}">
			<div id="emsg" class="${errorClass}">${ErrorMsg}</div>
	</c:when>
	<c:otherwise>
		
		<c:choose>
			<c:when test="${ErrorMsg == 'Something went wrong while processing your request.'}">
					${ErrorMsg}
			</c:when>
			<c:otherwise>
					<div id="emsg" class="${errorClass}">${ErrorMsg}</div>	
			</c:otherwise>
		</c:choose>
	
			
	</c:otherwise>	
</c:choose>


<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table>


             <form:form method="post" action="go" modelAttribute="test">

 	     <c:forEach var="property" items="${list}">
 	
    <tr>    
        <td>${property.key}</td>
<%--<td><input TYPE="text" NAME="${property.key}" VALUE="${property.value}" SIZE="45"></td> --%>


        <c:forEach items="${property.value}" var="listItem">    
        		<c:if test="${listItem.name == 'text'}">
        		           		
        		 <td><form:input type="text" required="${listItem.author}" path="${property.key}"/></td>
				</c:if>
		</c:forEach>
    </tr>
		</c:forEach>
		
		<input type="submit" value="submit"/>
</form:form>
</table>
 
</body>
</html>
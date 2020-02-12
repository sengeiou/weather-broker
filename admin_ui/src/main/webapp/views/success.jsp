<%--
  Created by IntelliJ IDEA.
  User: nacht
  Date: 10.02.2020
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather Broker</title>
</head>
<body>
    <h3>Account Info</h3>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<td>Username</td>
			<td>${city}</td>
		</tr>
	</table>

	<p>
		<a href="${pageContext.request.contextPath}">Back to home page</a>
	</p>

</body>
</html>

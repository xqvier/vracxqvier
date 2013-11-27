<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/theme.css" />" />
<title>Login</title>
</head>
<body>
	<%@ include file="/fragment/header.jspf"%>
	<section>
		<form method="post">
			<label>Username : </label><input type="text" name="username" /><br />
			<label>Password : </label><input type="password" name="password" /><br />
			<input type="submit" value="Login !" />
		</form>
	</section>

	<%@ include file="/fragment/footer.jspf"%>
</body>
</html>
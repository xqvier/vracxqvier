<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/theme.css" />" />
<title>Validez votre commande</title>
</head>
<body>
	<%@ include file="/fragment/header.jspf"%>
	<section>
		<h1>Votre commande est validée !</h1>
		Voici la commande que vous allez recevoir chez vous incessement sous
		peu.<br />
		<table>
			<c:forEach var="product" items="${sessionScope.cart.products}">
				<tr>
					<td>${product.name}</td>
					<td>${product.price}€</td>
				</tr>
			</c:forEach>
		</table>
		Nombre produits : ${sessionScope.cart.nombreArticles} <br /> <b>Total
			: ${sessionScope.cart.total}€ </b> <br />
	</section>
	<%@ include file="/fragment/footer.jspf"%>
</body>
</html>
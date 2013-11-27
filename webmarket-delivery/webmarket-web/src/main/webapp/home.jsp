<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/theme.css" />" />
<title>Home</title>
</head>
<body>
	<%@ include file="/fragment/header.jspf"%>
	<section>
		<h1>Produits</h1>
		<table>
			<thead>
				<tr>
					<th>Produit</th>
					<th>Prix</th>
					<th>Ajouter au panier</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product"
					items="${applicationScope.productBean.products}">
					<tr>

						<td>${product.name}</td>
						<td>${product.name}</td>
						<td>${product.price}€</td>
						<td><c:url var="url" value="/Home">
								<c:param name="addId" value="${product.id}" />
							</c:url> <a href="${url}"><img alt="Ajouter au panier" width="31px"
								height="25px"
								src="<c:url value="/image/site/icon/add-to-cart-icon.jpg"/>" />
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<aside>
		<h1>Panier</h1>
		<c:choose>
			<c:when test="${not empty sessionScope.cart.products}">
				<table>
					<c:forEach var="product" items="${sessionScope.cart.products}">
						<tr>
							<td>${product.name}</td>
							<td><c:url var="url" value="/Home">
									<c:param name="removeId" value="${product.id}" />
								</c:url><a href="${url}"><img alt="Supprimer du panier" width="31px"
									height="25px"
									src="<c:url value="/image/site/icon/delete-icon.jpg" />" /></a></td>
						</tr>
					</c:forEach>
				</table>
		Nombre produits : ${sessionScope.cart.nombreArticles} <br />
				<b>Total : ${sessionScope.cart.total}€ </b>
				<br />
				<a href='<c:url value="/Commande"/>'>Validez ma commande</a>
			</c:when>
			<c:otherwise>
				Le panier est vide
			</c:otherwise>
		</c:choose>
	</aside>
	<%@ include file="/fragment/footer.jspf"%>
</body>
</html>
<%@page import="com.xqvier.webmarket.web.pojo.Cart"%>
<%@page import="com.xqvier.webmarket.web.bean.HomeBean"%>
<%@page import="com.xqvier.webmarket.business.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=getServletContext().getContextPath()%>/theme.css">
<title>Home</title>
</head>
<%
    HomeBean bean = new HomeBean();
    List<Product> products = bean.getProducts();

    Cart panier = (Cart) session.getAttribute("panier");
%>
<body>
	<header>
		<h1>WebMarket</h1>
	</header>
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
				<%
				    for (Product product : products) {
				%>
				<tr>
					<td><%=product.getName()%></td>
					<td><%=product.getPrice()%>€</td>
					<td><a
						href='<%=response.encodeURL(getServletContext()
                        .getContextPath() + "/Home?addId=" + product.getId())%>'>
							<img alt="Ajouter au panier" width="31px" height="25px"
							src="<%out.print(application.getContextPath()
                        + "/image/site/icon/add-to-cart-icon.jpg");%>">
					</a></td>
				</tr>
				<%
				    }
				%>
			</tbody>
		</table>
	</section>
	<aside>
		<h1>Panier</h1>

		<table>
			<%
			    int count = 0;
			    for (Product product : panier.getProducts()) {
			%>
			<tr>
				<td><%=product.getName()%></td>
				<td><a
					href="<%=response.encodeURL(getServletContext()
                        .getContextPath() + "/Home?removeId=" + count)%>"><img
						alt="Supprimer du panier" width="31px" height="25px"
						src="<%out.print(application.getContextPath()
                        + "/image/site/icon/delete-icon.jpg");%>"></a></td>
			</tr>
			<%
			    count++;
			    }
			%>
		</table>
		Nombre produits :
		<%=panier.getNombreArticles()%><br />
		<b>Total : <%=panier.getTotal()%>€</b>

	</aside>
	
</body>
</html>
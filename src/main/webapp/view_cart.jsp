<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="java.util.List, com.wrapper.spotify.models.Album, javamusic.models.*" %>
<c:import url="includes/header.html" />
<c:import url="includes/menu_bar.jsp" />
<c:import url="includes/search_options.jsp" />

	<% 	Cart cart = (Cart) session.getAttribute("cart");
		if ((cart == null) || (cart.isEmpty())) {
	%>
	<h2>Cart is empty</h2>
	<% 	} else { %>

	<form action="music" method="post">  
		<table>
		<% List<Album> albums = cart.getAlbums(); 
		for (Album album : albums) {
		%>
			<tr>
				<td><input type="checkbox" name="checkedAlbum" value="<%= album.getId() %>" />&nbsp;</td>
				<td><%= album.getName() %></td>
				<td><img src=<%=album.getImages().get(0).getUrl() %> width=100 height=100></td>
				<!-- <td><button type="submit" name="action" value="add_to_cart">Add to Cart</button></td> -->
			</tr>
		<%} %>
		</table>
		<button type="submit" name="action" value="remove_from_cart">Remove from Cart</button>
		<button type="submit" name="action" value="checkout">Checkout</button>		
	</form>
	<%} %>

<c:import url="includes/footer.html" />

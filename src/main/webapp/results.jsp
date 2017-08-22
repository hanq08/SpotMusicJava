<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 <%@ page import="java.util.List, com.wrapper.spotify.models.Album" %>
<c:import url="includes/header.html" />
<c:import url="includes/menu_bar.jsp" />

<c:import url="includes/search_options.jsp" />
	
	<form action="music" method="post">  
		<table>
		<tr>
		<td><div><button type="submit" name="action" value="add_to_cart">Add Selected Albums to Cart</button></div></td>
		<td></td>
		</tr>
		<% List<Album> albums = (List<Album>) session.getAttribute("albums"); 
		for (Album album : albums) {
		%><tr>
				<td><input type="checkbox" name="checkedAlbum" value="<%= album.getId() %>" /><strong><%= album.getName() %></strong>
				- <%= album.getArtists().get(0).getName()%>
				</td>
				<td><img src=<%=album.getImages().get(0).getUrl() %> width=200 height=200><p></p></td>
				<!-- <td><button type="submit" name="action" value="add_to_cart">Add to Cart</button></td> -->
		</tr>
		<%} %>
		</table>
		
	</form>


<c:import url="includes/footer.html" />

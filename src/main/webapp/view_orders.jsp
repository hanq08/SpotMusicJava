<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 <%@ page import="java.util.List, com.wrapper.spotify.models.Album, javamusic.models.*" %>
<c:import url="includes/header.html" />
<c:import url="includes/menu_bar.jsp" />
<c:import url="includes/search_options.jsp" />
		<% 	 List<Album> albums = (List<Album>) session.getAttribute("orders");
		if ((albums == null) || albums.size()==0) {
	%>
	<h2>No purchased albums</h2>
	
	<% 	} else { %> 
		<table>
	<%	for (Album album:albums) {%>
			<tr>
				<td><strong><%= album.getName() %></strong> - <%= album.getArtists().get(0).getName()%></td>
				<td><img src=<%=album.getImages().get(0).getUrl() %> width=100 height=100></td>
			</tr>		
	
	 <% } %> </table> <% } %> 	
	 


<c:import url="includes/footer.html" />

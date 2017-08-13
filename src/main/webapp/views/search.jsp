<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="search">
	<input type="text" name="query">
	<input type="submit" value="Search" >
</form>
<h1>New Release</h1>
	<%@ page import="java.util.List, com.wrapper.spotify.models.SimpleAlbum" %>
	<% List<SimpleAlbum> albums = (List<SimpleAlbum>) request.getAttribute("albums"); 
	for (SimpleAlbum album : albums) {%>
	<p><%= album.getName() %></p>
	<a href ="detail?albumId=<%= album.getId() %>"><img src=<%= album.getImages().get(0).getUrl() %> width=200 height=200></a>
	<%} %>
</body>
</html>
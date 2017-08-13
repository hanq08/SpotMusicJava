<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Artists</h1>
	<%@ page import="java.util.List, com.wrapper.spotify.models.Artist" %>
	<% List<Artist> artists = (List<Artist>) request.getAttribute("artists"); 
	for (Artist artist : artists) {%>
	<p><%= artist.getName() %></p>
	<%} %>
<br>
<h1>Albums</h1>
	<%@ page import="java.util.List, com.wrapper.spotify.models.SimpleAlbum" %>
	<% List<SimpleAlbum> albums = (List<SimpleAlbum>) request.getAttribute("albums"); 
	for (SimpleAlbum album : albums) {%>
	<p><%= album.getName() %></p>
	<%} %>
</body>
</html>
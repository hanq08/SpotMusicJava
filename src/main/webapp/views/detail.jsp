<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.util.List, com.wrapper.spotify.models.Album, 
	 com.wrapper.spotify.models.SimpleTrack" %>
	<% Album album = (Album) request.getAttribute("album"); %>
	<h1><%= album.getName() %></h1>
	<img src=<%= album.getImages().get(0).getUrl()%> width=200 height=200>
	<% List<SimpleTrack> tracks = album.getTracks().getItems(); %>
	<%for (SimpleTrack track : tracks){ %>
		<p><%= track.getTrackNumber() %>. <%= track.getName() %>
		<%if (!track.getPreviewUrl().equals("null")){ %>
		<a href=<%=track.getPreviewUrl() %>>preview</a>
		<%} %></p>
	<%} %>
</body>
</html>
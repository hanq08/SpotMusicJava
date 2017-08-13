package javamusic.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.SimpleAlbum;

import javamusic.services.SpotifyService;

@WebServlet("/search")
public class SearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		SpotifyService spotify = SpotifyService.getInstance();
		List<Artist> artists = spotify.searchArtist(query);
		List<SimpleAlbum> albums = spotify.searchAlbums(query);
		request.setAttribute("artists", artists);
		request.setAttribute("albums", albums);
	    request.getRequestDispatcher("/views/results.jsp").forward(request, response);
	}
}

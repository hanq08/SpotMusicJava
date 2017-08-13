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

@WebServlet("/newreleases")
public class MainController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpotifyService spotify = SpotifyService.getInstance();
		List<SimpleAlbum> albums = spotify.getNewReleases();
		request.setAttribute("albums", albums);
	    request.getRequestDispatcher("/views/search.jsp").forward(request, response);
	}
}

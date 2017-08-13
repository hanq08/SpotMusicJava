package javamusic.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.Artist;
import com.wrapper.spotify.models.SimpleAlbum;

import javamusic.services.SpotifyService;

@WebServlet("/detail")
public class DetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpotifyService spotify = SpotifyService.getInstance();
		String albumId = request.getParameter("albumId");
		Album album = spotify.getAlbumById(albumId);
		request.setAttribute("album", album);
	    request.getRequestDispatcher("/views/detail.jsp").forward(request, response);
	}
}
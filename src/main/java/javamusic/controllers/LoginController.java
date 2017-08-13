package javamusic.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wrapper.spotify.models.SimpleAlbum;

import javamusic.services.DBService;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	
	DBService dbService;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		dbService = DBService.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String psw = request.getParameter("psw");
		if (username != null && psw != null){
		    dbService.createUser(username, psw);
			System.out.println(dbService.getUsers().get(0).getUserame());
		}
		
	    request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}
}

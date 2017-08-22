package javamusic.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.SimpleAlbum;

import javamusic.services.SpotifyService;
import javamusic.services.DBService;
import javamusic.models.Cart;
import javamusic.models.UserAlbum;
import javamusic.models.User;

/**
 * Controller for app
 */
@WebServlet("/music")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handle GET requests
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handle POST requests
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * Validate input  
	 * Display error message(s) or results to user.
	 * 
	 */	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// default url for forwarding
		String url = "/index.jsp";
	
        // get current action
        String action = request.getParameter("action");
        
		System.out.println("action: " + action);
        
        // get the current session
		HttpSession session = request.getSession();		
        
        if (action == null) {
        	session.invalidate();
            action = "login";  // default action
        }				
	
        // perform action and set URL to appropriate page
        
        if (action.equals("login")) {  
        	url = login(session, request, response);		
        } else if (action.equals("register")) {
            url = "/account.jsp";	      	      	
        } else if (action.equals("save_account")) {
        	url = saveAccount(session, request, response);	        	      	
        } else if (action.equals("cancel_create_account")) {
        	url = cancelAccountEdit(session, request, response);
        } else if (action.equals("show_current")) {
        	url = showCurrentReleases(session, request, response);	        	      	
        } else if (action.equals("edit_account")) {
            url = "/account.jsp";	     	      	
        } else if (action.equals("view_cart")) {
        	url = viewCart(session, request, response);	     	      	
        } else if (action.equals("order_history")) {
            url = viewOrder(session, request, response);     	      	
        } else if (action.equals("search")) {
        	url = search(session, request, response);	     	      	
        } else if (action.equals("add_to_cart")) {
        	url = addToCart(session, request, response);	   	      	
        } else if (action.equals("remove_from_cart")) {
        	url = removeFromCart(session, request, response);	   	      	
        } else if (action.equals("checkout")) {
        	url = checkout(session, request, response);	   	      	
        } else if (action.equals("logout")) {
        	session.invalidate();
        	url = "/index.jsp";	   	      	
        } else if (action.equals("process_order")) {
        	url = processOrder(session, request, response);	   	      	
        }
		
		getServletContext().getRequestDispatcher(url).forward(request, response);	
	}
	
	private String viewOrder(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		DBService dbservice = DBService.getInstance();
		SpotifyService spotifyService = SpotifyService.getInstance();
		User user = (User) session.getAttribute("user");
		User userdb = dbservice.getUser(user.getUsername());
		Set<UserAlbum> orders = userdb.getOrders();
		List<Album> albums = new ArrayList<Album>();
		for (UserAlbum order: orders){
			String album_id = order.getAlbum_id();
			Album album = spotifyService.getAlbumById(album_id);
			albums.add(album);
		}
		session.setAttribute("orders", albums);
		return "/view_orders.jsp";	
	}

	private String login(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String url;
		
		// get parameters from the request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// create User object from the parameters
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);	
		
		// validate name, password
		List<String> messages = new ArrayList<String>();
		if (username.length() == 0)
			messages.add("* User name is required.");
		if (password.length() == 0)
			messages.add("* Password is required.");
		
		if (messages.isEmpty()) {
			
			//validate user
			DBService dbservice = DBService.getInstance();
			boolean validUser = false;
			if (dbservice.getUser(user.getUsername()) != null){
				validUser = dbservice.getUser(user.getUsername()).getPassword().equals(user.getPassword());
			}
			// validUser = checkValidUser(user);
			if (validUser) {	
				session.setAttribute("loggedIn", "true");
				url = "/search.jsp";	
			} else {
				messages.add("** Username and/or password are incorrect. **");
				url = "/index.jsp";				
			}
		// if errors, go back to login page
		} else {		
			url = "/index.jsp";
		}
		
		// store the User object and messages in the session and forward to the new url	
		session.setAttribute("messages", messages.toArray(new String[0]));		
		session.setAttribute("user", user);				
		
		return url;
	}	
	
	// Used to either register a new user or update an existing user.
	private String saveAccount(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String url = null;
        
		// get parameters from the request
		
		// if the user is already logged in, get the username from the session
    	String s = (String)session.getAttribute("loggedIn");
    	String username;
    	if ((s == null) || !Boolean.valueOf(s)) {
    		username = request.getParameter("username");
    	} else {
    		username = ((User)session.getAttribute("user")).getUsername();
    	}		
		
		String password = request.getParameter("password");
		
		// create User object from the parameters
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setOrders(new HashSet<UserAlbum>());
		
		// validate name, email, ...
		List<String> messages = new ArrayList<String>();
		if (username.length() == 0)
			messages.add("* User name is required.");
		if (password.length() == 0)
			messages.add("* Password is required.");        
        
		// if no errors, update/create account and show search page
		if (messages.isEmpty()) {
			
			//update/create the user account
			DBService dbservice = DBService.getInstance();
			dbservice.saveUser(user);
			//updateUserAccount(user);
			session.setAttribute("loggedIn", "true");						
			
			url = "/search.jsp";	
		// if errors, go back to account page
		} else {		
			url = "/account.jsp";
		}        
		
		// store the User object and messages in the session and forward to the new url	
		session.setAttribute("messages", messages.toArray(new String[0]));		
		session.setAttribute("user", user);			
		
		return url;
	}	
		
	private String cancelAccountEdit(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String url = null;						
			
		// if they're already logged in, go to search page.  If not, got to login page.
    	String s = (String)session.getAttribute("loggedIn");
    	if (Boolean.valueOf(s)) {
    		url = "/search.jsp";
    	} else {
    		url = "/index.jsp";	  
    	}	      
		
		return url;
	}	
	
	private String showCurrentReleases(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String url = null;						
			
		SpotifyService spotify = SpotifyService.getInstance();
		List<SimpleAlbum> simpleAlbums = spotify.getNewReleases();
		List<Album> albums = new ArrayList<Album>();
		for (SimpleAlbum album: simpleAlbums){
			Album a = spotify.getAlbumById(album.getId());
			albums.add(a);
		}
		session.setAttribute("albums", albums);
		url = "/results.jsp";	      
		
		return url;
	}	
	
	private String search(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String url = null;						
			
		//TODO: Get the input parameters, do search
		String keyword = request.getParameter("keyword");
		SpotifyService spotify = SpotifyService.getInstance();
		List<SimpleAlbum> simpleAlbums = spotify.searchAlbums(keyword);
		List<Album> albums = new ArrayList<Album>();
		for (SimpleAlbum album: simpleAlbums){
			Album a = spotify.getAlbumById(album.getId());
			albums.add(a);
		}
		session.setAttribute("albums", albums);
		url = "/results.jsp";	      
		
		return url;
	}	
	
	private String addToCart(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String url = null;						

		Cart cart = (Cart)session.getAttribute("cart");
		if (cart == null) 		
			cart = new Cart();
		
		// get the album id(s) selected
		String[] checkedAlbums = request.getParameterValues("checkedAlbum");
		
		// get the albums stored in the session
		List<Album> albums = (List<Album>) session.getAttribute("albums"); 		
		
		// iterate over the selected album ids
		for (String albumId : checkedAlbums) {			
			// iterate over the albums stored in the session to get the album object
			for (Album album : albums) {
				
				// if the selected album id matches the album object id, add the album object to the cart
				if (albumId.equals(album.getId()))
					cart.addToCart(album);
			}
		}

		session.setAttribute("cart", cart);					
		url = "/results.jsp";	      		
		return url;
	}		
	
	private String removeFromCart(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String url = null;						
		
		// get the album id(s) selected for removal
		String[] checkedAlbums = request.getParameterValues("checkedAlbum");
		
		Cart cart = (Cart) session.getAttribute("cart"); 				
		
		// iterate over the selected album ids
		for (String albumId : checkedAlbums) {			
			cart.removeFromCartById(albumId);
		}

		session.setAttribute("cart", cart);	
		
		if (cart.isEmpty()) {
			url = "/results.jsp";				
		} else {
			url = "/view_cart.jsp";	 
		}
		return url;
	}	
	
	private String viewCart(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String url = null;										
		
		url = "/view_cart.jsp";	      
		
		return url;
	}		
	
	private String checkout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		String url = null;										
		
		url = "/checkout.jsp";	      
		
		return url;
	}	
	
	private String processOrder(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		String url = null;
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		if (cart == null || user == null){
			return "/index.jsp";
		}
		for (Album album:cart.getAlbums()){
			DBService dbservice = DBService.getInstance();
			dbservice.addOrder(user, album.getId());
		}
		cart.getAlbums().clear();
		url = viewOrder(session, request, response);
		return url;
	}
}

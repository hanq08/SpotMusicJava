package javamusic.services;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javamusic.models.Order;
import javamusic.models.User;

public class DBService {
	
	private SessionFactory factory;
	private static DBService dbservice;
	
	private DBService(){
		// A SessionFactory is set up once for an application!
		factory = new Configuration()
				.configure()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Order.class)
				.buildSessionFactory();

	}
	public static DBService getInstance(){
		if (dbservice == null) {
			dbservice = new DBService();
		}
		return dbservice;
	}
	
	public void createUser(String username, String password){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save( new User(username, password));
		session.getTransaction().commit();
	}
	
	public void updateUser(){
		
	}
	
	public User getUser(int id){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("SELECT User U Where U.id=" + id);
		List results = query.list();
		return (User)results.get(0);
	}
	
	public List<User> getUsers(){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Query<User> query = session.createQuery("FROM User");
		List<User> results = query.list();
		return results;
	}
	
	public void deleteUser(int id){
		Session session = factory.getCurrentSession();
		User user = (User) session.load(User.class,id);
	    session.delete(user);

	    //This makes the pending delete to be done
	    session.flush() ;
	}
	
	public Set<Order> getUserOrders(int user_id){
		return this.getUser(user_id).getOrders();
	}
	
	public void createOrder(User user, int album_id){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(new Order(user, album_id));
		session.getTransaction().commit();
	}
	
	public Order getOrder(int id){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("SELECT Order O Where O.id=" + id);
		List results = query.list();
		return (Order)results.get(0);
	}
	
}

package javamusic.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javamusic.models.UserAlbum;
import javamusic.models.User;

public class DBService {
	
	private SessionFactory factory;
	private static DBService dbservice;
	
	private DBService(){
		// A SessionFactory is set up once for an application!
		factory = new Configuration()
				.configure()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(UserAlbum.class)
				.buildSessionFactory();

	}
	public static DBService getInstance(){
		if (dbservice == null) {
			dbservice = new DBService();
		}
		return dbservice;
	}
	
	public void saveUser(User user){
		Session session = factory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateUser(){
		
	}
	
	public User getUser(int id){
		Session session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("SELECT User U Where U.id=" + id);
		List results = query.list();
		return (User)results.get(0);
	}
	
	public User getUser(String username){
		Session session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM User U Where U.username = :name");
		query.setParameter("name", username);
		User user = (User)query.uniqueResult();
		session.close();
		return user;
	}
	
	public List<User> getUsers(){
		Session session = factory.openSession();
		session.beginTransaction();
		Query<User> query = session.createQuery("FROM User");
		List<User> results = query.list();
		return results;
	}
	
	public void deleteUser(int id){
		Session session = factory.openSession();
		User user = (User) session.load(User.class,id);
	    session.delete(user);

	    //This makes the pending delete to be done
	    session.flush() ;
	}
	
	public Set<UserAlbum> getUserOrders(int user_id){
		return this.getUser(user_id).getOrders();
	}
	
	public void addOrder(User user, String album_id){
		Session session = factory.openSession();
		session.beginTransaction();
		UserAlbum order = new UserAlbum(user, album_id);
		if (user.getOrders() == null){
			user.setOrders(new HashSet<UserAlbum>());
		}
		user.getOrders().add(order);
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}
	
}

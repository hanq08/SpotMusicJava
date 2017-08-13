package javamusic.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Order")
public class Order {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;
	
	@Column(name = "album_id")
	private int album_id;
	
	

	public Order() {
	}



	public Order(User user, int album_id) {
		this.user = user;
		this.album_id = album_id;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getAlbum_id() {
		return album_id;
	}



	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	
	
}

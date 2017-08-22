package javamusic.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserAlbum")
public class UserAlbum implements Serializable{
	@Id
	@Column(name="id", nullable= false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	
	@Column(name = "album_id")
	private String album_id;
	
	
  
	public UserAlbum() {
	}



	public UserAlbum(User user, String album_id) {
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



	public String getAlbum_id() {
		return album_id;
	}



	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}
	
	
}

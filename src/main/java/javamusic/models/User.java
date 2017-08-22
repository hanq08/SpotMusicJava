package javamusic.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javamusic.models.UserAlbum;

@Entity
@Table(name = "User")
public class User implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
	private Set<UserAlbum> orders;

	public User(){
		
	}

	public User(String username, String password, Set<UserAlbum> orders) {
		this.username = username;
		this.password = password;
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserAlbum> getOrders() {
		return orders;
	}

	public void setOrders(Set<UserAlbum> orders) {
		this.orders = orders;
	}
	
}


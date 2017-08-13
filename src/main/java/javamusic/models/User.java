package javamusic.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username", unique=true)
	private String userame;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ORDER_ID")
	private Set<Order> orders;

	public User(){
		
	}

	public User(String userame, String password) {
		this.userame = userame;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserame() {
		return userame;
	}

	public void setUserame(String userame) {
		this.userame = userame;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
}

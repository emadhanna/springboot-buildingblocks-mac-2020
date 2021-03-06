package com.stacksimplify.restservices.mac2020.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

//@Entity(name="user") You can assign different name for the entity, default is the class name
//@Table(name="user", schema="ni00") You can also define the table in specific schema for distinguishing tables
@Entity
@Table(name="user")
public class User extends ResourceSupport{

	@Id
	@GeneratedValue
	private long userId;
	@NotEmpty(message="Username is mandatory field. Pleasse provide the username value")
	@Size(min=2, message="Firstname should have at least 2 characters")
	@Column(name="USER_NAME", length=50, nullable=false, unique=true)
	private String username;
	@Column(name="FIRST_NAME", length=50, nullable=false)
	private String firstname;
	@Column(name="LAST_NAME", length=50, nullable=false)
	private String lastname;
	@Column(name="EMAIL_ADDRESS", length=50, nullable=false)
	private String email;
	@Column(name="ROLE", length=50, nullable=false)
	private String role;
	@Column(name="SSN", length=50, nullable=false, unique=true)
	private String ssn;
	@OneToMany(mappedBy="user")
	private List<Order> order;
	
	public List<Order> getOrders() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public User() {

	}

	public User(long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		this.userId = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	
	}

	
	
	public long getUserId() { return userId; }

	public void setUserId(long id) { this.userId = id; }

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }

	public String getFirstname() { return firstname; }

	public void setFirstname(String firstname) { this.firstname = firstname; }

	public String getLastname() { return lastname; }

	public void setLastname(String lastname) { this.lastname = lastname; }

	public String getEmail() { return email; }
 
	public void setEmail(String email) { this.email = email; }

	public String getRole() { return role; }

	public void setRole(String role) { this.role = role; }

	public String getSsn() { return ssn; }

	public void setSsn(String ssn) { this.ssn = ssn; }

	@Override
	public String toString() {
		
		return "User [id=" + userId + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	
	}	
}
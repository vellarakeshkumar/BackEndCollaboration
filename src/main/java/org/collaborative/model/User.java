package org.collaborative.model;

import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.*;





@Entity
@Table(name = "user_account")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_VERIFIED = "VERIFIED";
    public void getUserToken() {
    	 this.token = UUID.randomUUID().toString();
         this.issuedDateTime = LocalDateTime.now();
         this.expiredDateTime = this.issuedDateTime.plusDays(1);
         this.status = STATUS_PENDING;
	}


    private String firstName;

    private String lastName;

    private String email;

    @Column(length = 60)
    private String password;

    private boolean enabled;
    
    private String token;
    private String status;
    private LocalDateTime expiredDateTime;
    private LocalDateTime issuedDateTime;
    private LocalDateTime confirmedDateTime;
    private String securityKey;
    
    public String getSecurityKey() {
		return securityKey;
	}
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
	@Transient
    private String url;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getExpiredDateTime() {
		return expiredDateTime;
	}
	public void setExpiredDateTime(LocalDateTime expiredDateTime) {
		this.expiredDateTime = expiredDateTime;
	}
	public LocalDateTime getIssuedDateTime() {
		return issuedDateTime;
	}
	public void setIssuedDateTime(LocalDateTime issuedDateTime) {
		this.issuedDateTime = issuedDateTime;
	}
	public LocalDateTime getConfirmedDateTime() {
		return confirmedDateTime;
	}
	public void setConfirmedDateTime(LocalDateTime confirmedDateTime) {
		this.confirmedDateTime = confirmedDateTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

  }
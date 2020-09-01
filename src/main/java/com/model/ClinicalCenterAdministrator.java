package com.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.*;

//Tabele sa nazivom klase
//UserDetails - ima minimum korisnicko ime, lozinku i ulogu
@Entity
public class ClinicalCenterAdministrator implements UserDetails {

	// Inkremetalno dodeljivanje vrednosti kljuca
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Kolone tabele ClinicalCenterAdministrator
	@Column(name = "email")
	private String email;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String country;
	@Column(nullable = false)
	private int mobileNumber;
	@Column(nullable = false)
	private int jmbg;
	@Column(nullable = false)
	private int firstLog;

	@Column(name = "enabled")
	private boolean enabled;

	// EAGER - ucitavanje svih veza sa objektom odmah
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ClinicalCenter clinicalCenter;

	// Kolone
	@Column(name = "username", nullable = false, unique = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public ClinicalCenterAdministrator() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClinicalCenter getClinicalCenter() {
		return clinicalCenter;
	}

	public void setClinicalCenter(ClinicalCenter clinicalCenter) {
		this.clinicalCenter = clinicalCenter;
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

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public int getJmbg() {
		return jmbg;
	}

	public int getFirstLog() {
		return firstLog;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}

	public void setFirstLog(int firstLog) {
		this.firstLog = firstLog;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	private List<Authority> authorities;

	@Override
	public String toString() {
		return "ClinicalCenterAdministrator{" + "id=" + id + ", email='" + email + '\'' + ", firstName='" + firstName
				+ '\'' + ", lastName='" + lastName + '\'' + ", address='" + address + '\'' + ", city='" + city + '\''
				+ ", country='" + country + '\'' + ", mobileNumber=" + mobileNumber + ", jmbg=" + jmbg + ", firstLog="
				+ firstLog + ", clinicalCenter=" + clinicalCenter + ", username='" + username + '\'' + ", password='"
				+ password + '\'' + ", authorities=" + authorities + '}';
	}
}

package com.teamgreen.pollconapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OWNER")
public class Owner implements Serializable {

	private static final long serialVersionUID = 1L;

	public Owner() {
		super();
	}

	private int id;
	
	private String aadharNumber;
	
	private String name;
	private String address;
	private String contactNumber;
	private String city;
	private String email;
	private Date dateOfBirth;
	private String drivingLicenseNo;
	private String licenseStatus;
	
	@Column(name="DRIVING_LICENSE_STATUS", nullable = true)
	public String getLicenseStatus() {
		return licenseStatus;
	}


	public void setLicenseStatus(String licenseStatus) {
		this.licenseStatus = licenseStatus;
	}


	@Column(name="DRIVING_LICENSE_NO", nullable = true, unique = true)
	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}


	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}


	@OneToMany(targetEntity = Registration.class , cascade = { CascadeType.ALL },fetch = FetchType.EAGER, mappedBy = "owner")
	public Set<Registration> getRegistrations() {
		return registrations;
	}

	
	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}


	@Override
	public String toString() {
		return "Owner [id=" + id + ", aadharNumber=" + aadharNumber + ", name=" + name + ", address=" + address
				+ ", contactNumber=" + contactNumber + ", city=" + city + ", email=" + email + ", dateOfBirth="
				+ dateOfBirth + ", registrations=" + registrations + "]";
	}

	private Set<Registration> registrations = new HashSet<Registration>(0);
	

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="AADHAR_NUMBER", nullable = false)
	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	@Column(name="ADDRESS", nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="CONTACT_NUMBER", nullable = false)
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name="CITY", nullable = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="EMAIL", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="DOB", nullable = false)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}

package com.teamgreen.pollconapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST")
public class Test implements Serializable {

	private static final long serialVersionUID = 1L;

	public Test() {
		super();
	}

	private int id;
	private String regNumber;
	private String testNumber;
	private Date testDate;
	private double testParameter1;
	private double testParameter2;
	private double testParameter3;
	private String testResult;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "REG_NUMBER", nullable = false)
	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	@Column(name = "TEST_NUMBER", nullable = false)
	public String getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(String testNumber) {
		this.testNumber = testNumber;
	}

	@Column(name = "TEST_DATE", nullable = false)
	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	
	
	@Column(name = "TEST_RESULT", nullable = false)
	public String getTestResult() {
		return testResult;
	}

	@Column(name = "TEST_PARAMETER_1", nullable = false)
	public double getTestParameter1() {
		return testParameter1;
	}

	
	public void setTestParameter1(double testParameter1) {
		this.testParameter1 = testParameter1;
	}

	@Column(name = "TEST_PARAMETER_2", nullable = false)
	public double getTestParameter2() {
		return testParameter2;
	}

	public void setTestParameter2(double testParameter2) {
		this.testParameter2 = testParameter2;
	}

	@Column(name = "TEST_PARAMETER_3", nullable = false)
	public double getTestParameter3() {
		return testParameter3;
	}

	public void setTestParameter3(double testParameter3) {
		this.testParameter3 = testParameter3;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

}

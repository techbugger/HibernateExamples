package com.example.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne; 
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ACCOUNT_TEST", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID")})
public class AccountEntityTest implements Serializable 
{
 
	private static final long serialVersionUID = -6790693372846798580L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Integer accountId;

	@Column(name = "ACC_NUMBER", unique = true, nullable = false, length = 100)
	private String accountNumber;
	
	@OneToOne(mappedBy="account", cascade=CascadeType.REMOVE)
	@MapsId
	@JoinColumn(name="id")
	private EmployeeEntityTest employee; 

	public EmployeeEntityTest getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntityTest employee) {
		this.employee = employee;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
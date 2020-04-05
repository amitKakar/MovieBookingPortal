package com.example.springexample.operator;
//import=com.example.springexample.operator.OperatorController

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Operators")
@EntityListeners(AuditingEntityListener.class)

public class Operator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int operatorId;
	private String operatorName;
	private String password;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Operator() {
		super();
	}
	
	public Operator(int operatorId, String operatorName, String password) {
		super();
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.password = password;
	}
	
	public int getOperatorId() {
		return operatorId;
	}
	
	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	
	public String getOperatorName() {
		return operatorName;
	}
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	

}

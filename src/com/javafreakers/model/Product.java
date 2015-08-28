package com.javafreakers.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long pIdLong;

private String pNameStr;

private Date pManufactueDate;

private Date pExpirDate;

public Long getpIdLong() {
	return pIdLong;
}

public void setpIdLong(Long pIdLong) {
	this.pIdLong = pIdLong;
}

public String getpNameStr() {
	return pNameStr;
}

public void setpNameStr(String pNameStr) {
	this.pNameStr = pNameStr;
}

public Date getpManufactueDate() {
	return pManufactueDate;
}

public void setpManufactueDate(Date pManufactueDate) {
	this.pManufactueDate = pManufactueDate;
}

public Date getpExpirDate() {
	return pExpirDate;
}

public void setpExpirDate(Date pExpirDate) {
	this.pExpirDate = pExpirDate;
}

}

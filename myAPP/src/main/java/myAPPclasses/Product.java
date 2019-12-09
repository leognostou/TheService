package myAPPclasses;

import java.time.LocalDate;

public class Product {
	String name;
	double price;
	String code;
	LocalDate ExpDate;
	LocalDate AvDate;
	boolean isAvailable;
	
	// Constructors
	public Product() {
		this.name="";
		this.price=-1;
		this.code="";
		this.ExpDate= LocalDate.parse("2001-10-02");
		this.AvDate= LocalDate.parse("2001-10-02");
		this.isAvailable=false;
	}
	
	public Product(String name, double price, String code, LocalDate ExpDate,LocalDate AvDate) {
		this.name=name;
		this.price=price;
		this.code=code;
		this.ExpDate=ExpDate;
		this.AvDate=AvDate;
		this.isAvailable=false;
	}
	
	//Setters-Getters
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price=price;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code=code;
	}
	
	public LocalDate getExpDate() {
		return this.ExpDate;
	}
	
	public void setExpDate(LocalDate ExpDate) {
		this.ExpDate=ExpDate;
	}
	
	public LocalDate getAvDate() {
		return this.AvDate;
	}
	
	public void setAvDate(LocalDate AvDate) {
		this.AvDate=AvDate;
	}
	
	public boolean getIsAvailable() {
		return this.isAvailable;
	}
	
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable=isAvailable;
	}
	
	public String toString() {
		String strng= String.format("{\n"
				+ "name:%s\n"
				+ "price:%f\n"
				+ "code:%s\n"
				+ "ExpDate:%s\n"
				+ "AvDate:%s\n"
				+ "isAvailable:%b\n"
				+ "}\n", this.name, this.price, this.code, this.ExpDate, this.AvDate, this.isAvailable);
		
		return strng;
	}
}

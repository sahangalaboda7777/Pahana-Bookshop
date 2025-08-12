package com.pahanabookshop.model;
import java.time.LocalDateTime;

public class Customer {
	 private String accountNo;     
	    private String name;
	    private String address;
	    private String telephone;
	    private int unitsConsumed;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;

	    public Customer() { }

	    public Customer(String accountNo, String name, String address,
	                    String telephone, int unitsConsumed, LocalDateTime createdAt, LocalDateTime updatedAt) {
	        this.accountNo    = accountNo;
	        this.name         = name;
	        this.address      = address;
	        this.telephone    = telephone;
	        this.unitsConsumed = unitsConsumed;
	        this.createdAt = createdAt;
	        this.updatedAt = updatedAt;
	    }

	   

	    public String getAccountNo()                { return accountNo; }
	    public void   setAccountNo(String accountNo){ this.accountNo = accountNo; }

	    public String getName()                     { return name; }
	    public void   setName(String name)          { this.name = name; }

	    public String getAddress()                  { return address; }
	    public void   setAddress(String address)    { this.address = address; }

	    public String getTelephone()                { return telephone; }
	    public void   setTelephone(String telephone){ this.telephone = telephone; }

	    public int    getUnitsConsumed()            { return unitsConsumed; }
	    public void   setUnitsConsumed(int units)   { this.unitsConsumed = units; }
	    
	    public LocalDateTime getCreatedAt() { return createdAt; }
	    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

	    public LocalDateTime getUpdatedAt() { return updatedAt; }
	    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
	

}

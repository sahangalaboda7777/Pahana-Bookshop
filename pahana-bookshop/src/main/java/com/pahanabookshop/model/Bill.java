package com.pahanabookshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bill {
	private String billNo;           
    private String accountNo;        
    private int units; 
    private int itemId;
    private BigDecimal amount;       
    private String generatedBy;      
    private LocalDateTime issuedAt; 


    public Bill() { }

    public Bill(String billNo, String accountNo, int units,int itemId,
                BigDecimal amount, String generatedBy,
                LocalDateTime issuedAt) {
        this.billNo      = billNo;
        this.accountNo   = accountNo;
        this.units       = units;
        this.itemId = itemId;
        this.amount      = amount;
        this.generatedBy = generatedBy;
        this.issuedAt    = issuedAt;
    }

   

    public String getBillNo()                     { return billNo; }
    public void   setBillNo(String billNo)        { this.billNo = billNo; }

    public String getAccountNo()                  { return accountNo; }
    public void   setAccountNo(String accountNo)  { this.accountNo = accountNo; }

    public int    getUnits()                      { return units; }
    public void   setUnits(int units)             { this.units = units; }

    public int getItemId() 						  { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    
    public BigDecimal getAmount()                 { return amount; }
    public void      setAmount(BigDecimal amount) { this.amount = amount; }

    public String getGeneratedBy()                { return generatedBy; }
    public void   setGeneratedBy(String user)     { this.generatedBy = user; }

    public LocalDateTime getIssuedAt()            { return issuedAt; }
    public void         setIssuedAt(LocalDateTime t){ this.issuedAt = t; }

}


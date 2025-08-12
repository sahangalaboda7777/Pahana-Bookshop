package com.pahanabookshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Item {
	private int id;                  
    private String title;
    private BigDecimal unitPrice;
    private int stockQty;
    private int categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

   

    public Item() { }

    public Item(int id, String title,
                BigDecimal unitPrice, int stockQty, int categoryId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id        = id;
        this.title     = title;
        this.unitPrice = unitPrice;
        this.stockQty  = stockQty;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

  

    public int getId()                         { return id; }
    public void setId(int id)                  { this.id = id; }

    public String getTitle()                   { return title; }
    public void setTitle(String title)         { this.title = title; }

    public BigDecimal getUnitPrice()           { return unitPrice; }
    public void setUnitPrice(BigDecimal price) { this.unitPrice = price; }

    public int getStockQty()                   { return stockQty; }
    public void setStockQty(int qty)           { this.stockQty = qty; }
    
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}
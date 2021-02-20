package com.example.items.catalogitems.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Specific details about the item")
@Entity
public class Item {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, max = 25, message = "Name should have at least 2 characters and max of 25")
	@ApiModelProperty(notes = "Name should have at least 2 characters and max of 25")
	private String name;

	@DecimalMin(value = "0.1", message = "Amount should be at least $0.1")
	@ApiModelProperty(notes = "Amount should be at least $0.1")
	private Double amount;

	@DecimalMin(value = "0", message = "Quantity should have at least 0 items")
	@ApiModelProperty(notes = "Quantity should have at least 0 items")
	private Integer qty;

	@ApiModelProperty(notes = "Unique inventory code for the item")
	private String inventoryCode;

	public Item() {
	}

	public Item(Integer id, String name, Double amount, Integer qty, String inventoryCode) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.qty = qty;
		this.inventoryCode = inventoryCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", amount=" + amount + ", qty=" + qty + ", inventoryCode="
				+ inventoryCode + "]";
	}

}

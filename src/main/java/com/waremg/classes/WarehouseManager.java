package com.waremg.classes;

public class WarehouseManager extends Employee{


	private int staffId;
	private String name;
	private String type;
	
	
	public WarehouseManager(int staffId, String username, String password, String name) {
		
		super(username, password);
		this.staffId = staffId;
		this.name = name;

	}


	public int getStaffId() {
		return staffId;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}
	
}

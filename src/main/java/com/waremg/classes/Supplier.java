package com.waremg.classes;

public class Supplier {
    private int supplierId;
    private String name;
    private int staffId;
    private String itemType;
    private int deliveryTime;

    public Supplier() {
    }

    public Supplier(int supplierId, String name, int staffId, String itemType, int deliveryTime) {
        this.supplierId = supplierId;
        this.name = name;
        this.staffId = staffId;
        this.itemType = itemType;
        this.deliveryTime = deliveryTime;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", name='" + name + '\'' +
                ", staffId=" + staffId +
                ", itemType='" + itemType + '\'' +
                ", deliveryTime=" + deliveryTime +
                '}';
    }
}

package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class Equipment extends BasicBean {

    private int equipmentId;
    private int equipmentTypeId;
    private int equipmentCount;
    private double equipmentWeight;
    private int userOwnerId;

    public Equipment() {
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getEquipmentTypeId() {
        return equipmentTypeId;
    }

    public void setEquipmentTypeId(int equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    public int getEquipmentCount() {
        return equipmentCount;
    }

    public void setEquipmentCount(int equipmentCount) {
        this.equipmentCount = equipmentCount;
    }

    public double getEquipmentWeight() {
        return equipmentWeight;
    }

    public void setEquipmentWeight(double equipmentWeight) {
        this.equipmentWeight = equipmentWeight;
    }

    public int getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(int userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

}

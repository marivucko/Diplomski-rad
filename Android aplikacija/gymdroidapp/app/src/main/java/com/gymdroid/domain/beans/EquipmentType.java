package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class EquipmentType extends BasicBean {

    public static final int DUMBELL_TYPE_ID = 4;

    private int equipmentTypeId;
    private String equipmentTypeName;
    private int equipmentIsUpgradable;
    private int equipmentIsWeight;

    public EquipmentType() {
    }

    public int getEquipmentTypeId() {
        return equipmentTypeId;
    }

    public void setEquipmentTypeId(int equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public int getEquipmentIsUpgradable() {
        return equipmentIsUpgradable;
    }

    public void setEquipmentIsUpgradable(int equipmentIsUpgradable) {
        this.equipmentIsUpgradable = equipmentIsUpgradable;
    }

    public int getEquipmentIsWeight() {
        return equipmentIsWeight;
    }

    public void setEquipmentIsWeight(int equipmentIsWeight) {
        this.equipmentIsWeight = equipmentIsWeight;
    }

}

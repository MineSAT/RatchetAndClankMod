package com.gugu42.rcmod.common.items.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public class ClankBackpackItem extends ArmorItem {
    protected double jumpBoostFactor;
    protected double sprintBoostFactor;

    public ClankBackpackItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    public double getJumpBoostFactor() {
        return jumpBoostFactor;
    }

    public double getSprintBoostFactor() {
        return sprintBoostFactor;
    }
}

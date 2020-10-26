package com.gugu42.rcmod.common.items.armor;

import com.gugu42.rcmod.client.model.HelipackModel;
import com.gugu42.rcmod.common.items.armor.ClankArmorMaterial;
import com.gugu42.rcmod.common.items.armor.ClankBackpackItem;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

public class HelipackItem extends ClankBackpackItem {
    public static final IArmorMaterial HELIPACK_ARMOR_MATERIAL = new ClankArmorMaterial();

    public HelipackItem(Properties builderIn) {
        super(HELIPACK_ARMOR_MATERIAL, EquipmentSlotType.CHEST, builderIn);
        this.jumpBoostFactor = 0.3d;
        this.sprintBoostFactor = 0.15d;
    }

    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        return (A) new HelipackModel(1.0f, entityLiving);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return null;
    }
}

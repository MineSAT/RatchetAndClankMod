package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemRatchetEars extends ItemArmor {
	
	public ItemRatchetEars(ArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
		super(par2EnumArmorMaterial, par3, EntityEquipmentSlot.HEAD);
		
		this.setCreativeTab(RcMod.rcTab);
	}

	//TODO - Fix ratchet ears
	
	/*@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		return "rcmod:models/Ratchet_ears.png";
	}*/
	
	
	/*
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving,
			ItemStack itemStack, int armorSlot) {
		initModel();
		return model;
	}*/

}
package com.gugu42.rcmod.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityMineGloveAmmo;

public class ItemMineGlove extends ItemRcGun {

	public int ammo;
	public int maxAmmo;
	public int cooldown;

	public ItemMineGlove() {
		super();
		this.weaponName = "mineGlove";
		this.hasAmmoImage = true;
		this.ammo = 50;
		this.maxAmmo = 50;
		this.ammoPrice = 5;
		this.cooldown = 20;
		this.setMaxDamage(maxAmmo);
		this.maxStackSize = 1;
		this.setCreativeTab(RcMod.rcTab);

	}

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!world.isRemote) {
				if (cooldown <= 0) {
					EntityMineGloveAmmo mine = new EntityMineGloveAmmo(
							world, player);
					world.spawnEntity(mine);
					par1ItemStack.damageItem(1, player);
					cooldown = 60;
					player.swingArm(hand);
				}
			}
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
	}

	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		if (cooldown > 0) {
			cooldown--;
		}

	}

}

package com.gugu42.rcmod.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;

public class ItemBombGlove extends ItemRcGun {

	public int ammo;
	public int maxAmmo;
	public int cooldown;
	
	public ItemBombGlove() {
		super();
		this.ammo = 40;
		this.maxAmmo = 40;
		this.weaponName = "bombGlove";
		this.hasAmmoImage = true;
		this.ammoPrice = 5;
		this.cooldown = 10;
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
					EntityBombGloveAmmo bomb = new EntityBombGloveAmmo(
							world, player);
					world.spawnEntity(bomb);
					par1ItemStack.damageItem(1, player);
					cooldown = 60;
					player.swingArm(hand);
				}
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
	}

	public boolean canHarvestBlock(Block par1Block) {
		return false;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 0.0f;
	}

	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		if (cooldown > 0) {
			cooldown--;
		}

	}

	public void onPlayerStoppedUsing(ItemStack stack, World w,
			EntityPlayer player, int i) {
	}
}

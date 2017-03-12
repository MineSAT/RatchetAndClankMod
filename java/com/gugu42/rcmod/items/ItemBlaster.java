package com.gugu42.rcmod.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import com.gugu42.rcmod.entity.projectiles.EntityBlasterAmmo;

public class ItemBlaster extends ItemRcGun {

	public int ammo;
	public int maxAmmo;
	public int cooldown;

	public boolean isInGold;

	public ItemBlaster() {
		super();
		this.ammo = 250;
		this.maxAmmo = 250;
		this.ammoPrice = 1;
		this.weaponName = "blaster";
		this.cooldown = 0;
		this.heldType = 1;
		this.setMaxDamage(maxAmmo);
		this.maxStackSize = 1;
		this.isInGold = false;
		this.hasCrosshair = true;
		this.hasAmmoImage = true;
		this.hasEquipSound = true;
		//		this.crosshairPath = "textures/gui/blasterCrosshair.png";
	}

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!world.isRemote) {
				EntityBlasterAmmo bullet = new EntityBlasterAmmo(world,
						player);
				bullet.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 0.2F);
				world.spawnEntity(bullet);
				par1ItemStack.damageItem(1, player);
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		super.onUpdate(stack, w, ent, i, flag);
	}

	public boolean canHarvestBlock(Block par1Block) {
		return false;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 0.0f;
	}

	public void onPlayerStoppedUsing(ItemStack stack, World w,
			EntityPlayer player, int i) {
	}
}

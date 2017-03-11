package com.gugu42.rcmod.items;

import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemVisibombGun extends ItemRcGun
{
	private int cooldown;

	public ItemVisibombGun() {
		super();
		this.ammoPrice = 100;
		this.maxAmmo = 20;
		this.weaponName = "visibomb";
		this.hasAmmoImage = true;
		this.hideCrosshair = true;
		this.heldType = 1;
		this.setMaxDamage(maxAmmo);
		this.hasEquipSound = true;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!world.isRemote) {
				if (cooldown <= 0) {
					world.spawnEntity(new EntityVisibombAmmo(world, player));
					par1ItemStack.damageItem(1, player);
					cooldown = 200;
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
	
	public void onUpdate(ItemStack par1ItemStack, World par2World,Entity par3Entity, int par4, boolean par5) 
	{
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		if (cooldown >= 1) {
			cooldown--;
		}
	}
}

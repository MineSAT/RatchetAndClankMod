package com.gugu42.rcmod.items;

import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRYNO extends ItemRcGun {

	public ItemRYNO() {
		super();
		this.ammoPrice = 20;
		this.maxAmmo = 50;
		this.weaponName = "ryno";
		this.hasAmmoImage = true;
		this.heldType = 1;
		this.setMaxDamage(maxAmmo);
	}

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		//TODO - Fix sounds
		//world.playSoundAtEntity(player, "random.bow", 0.5F,
		//		0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));

		if (!world.isRemote) {
			if (player.getEntityData().getInteger("rynoCooldown") <= 0) {
				player.getEntityData()
						.setInteger("rynoFireCount", 27);
				player.getEntityData().setBoolean("rynoFired", true);
				player.getEntityData()
						.setInteger("rynoCooldown", 120);
				if (!player.capabilities.isCreativeMode) {
					if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
						par1ItemStack.damageItem(1, player);
					}
				}
			}
		}

		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
	}

	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
		if (par3Entity.getEntityData().getInteger("rynoCooldown") >= 1) {
			par3Entity.getEntityData().setInteger("rynoCooldown",
					par3Entity.getEntityData().getInteger("rynoCooldown") - 1);
		}

		if (par3Entity.getEntityData().getBoolean("rynoFired")) {
			EntityPlayer par3EntityPlayer = (EntityPlayer) par3Entity;
			if (par3EntityPlayer.getEntityData().getInteger("rynoFireCount") >= 1) {
				int playerFireCount = par3EntityPlayer.getEntityData()
						.getInteger("rynoFireCount");
				par3EntityPlayer.getEntityData().setInteger("rynoFireCount",
						playerFireCount - 1);
				if (playerFireCount == 27) {
					fireRocket(par2World, par3EntityPlayer); // 1
				} else if (playerFireCount == 24) {
					fireRocket(par2World, par3EntityPlayer); // 2
				} else if (playerFireCount == 21) {
					fireRocket(par2World, par3EntityPlayer); // 3
				} else if (playerFireCount == 18) {
					fireRocket(par2World, par3EntityPlayer); // 4
				} else if (playerFireCount == 15) {
					fireRocket(par2World, par3EntityPlayer); // 5
				} else if (playerFireCount == 12) {
					fireRocket(par2World, par3EntityPlayer); // 6
				} else if (playerFireCount == 9) {
					fireRocket(par2World, par3EntityPlayer); // 7
				} else if (playerFireCount == 6) {
					fireRocket(par2World, par3EntityPlayer); // 8
				} else if (playerFireCount == 3) {
					fireRocket(par2World, par3EntityPlayer); // 9
				}

				if (playerFireCount == 0) {
					par3EntityPlayer.getEntityData().setBoolean("rynoFired",
							false);
				}
				
			}

		}
	}

	public void fireRocket(World world, EntityPlayer player) {
		EntityRYNOAmmo rocket = new EntityRYNOAmmo(world, player);
		world.spawnEntity(rocket);
	}

}

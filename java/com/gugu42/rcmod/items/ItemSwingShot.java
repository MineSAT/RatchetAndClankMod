package com.gugu42.rcmod.items;

import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntitySwingShotHook;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemSwingShot extends ItemRcWeap {
	
	public int cooldown = 90;
	
	public ItemSwingShot(){
		super();
		this.useAmmo = false;
		this.hasCrosshair = true;
		this.weaponName = "swingShot";
		this.setMaxStackSize(1);
		this.hasEquipSound = true;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
			if (!world.isRemote) {
				if (cooldown <= 0) {
					EntitySwingShotHook hook = new EntitySwingShotHook(world, player);
					world.spawnEntity(hook);
					cooldown = 90;
					player.swingArm(hand);
					//TODO - Fix sounds
					//player.world.playSoundAtEntity(player, "rcmod:SwingShotShoot", 1.0f, 1.0f);
				}
			}
			return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
	}
	
	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		super.onUpdate(stack, w, ent, i, flag);
		if (cooldown > 0) {
			cooldown--;
		}
	}

	public boolean canHarvestBlock(Block par1Block) {
		return false;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 0.0f;
	}
	
}

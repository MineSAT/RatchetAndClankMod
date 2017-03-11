package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityWrenchThrown;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemOmniWrench3000 extends ItemRcWeap {

	public ItemOmniWrench3000() {
		super();
		this.maxStackSize = 1;
		this.weaponName = "wrench";
		this.useAmmo = false;
		this.hasCrosshair = true;
		this.setCreativeTab(RcMod.rcWeapTab);
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase entityTarget,
			EntityLivingBase entitySource) {
		entityTarget
				.attackEntityFrom(DamageSource
						.causePlayerDamage((EntityPlayer) entitySource), RcMod.config.get("weapon_damage", "wrench_direct", 6).getInt());

		return true;
	}

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
		if (player.isSneaking()) {
			if (!world.isRemote) {
				EntityWrenchThrown wrench = new EntityWrenchThrown(world,
						player, par1ItemStack);
				world.spawnEntity(wrench);
				player.swingArm(hand);
				removeItem(player, par1ItemStack);
			}
			removeItem(player, par1ItemStack);
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public void removeItem(EntityPlayer ep, ItemStack removeitem) {
		IInventory inv = ep.inventory;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i) != ItemStack.EMPTY) {
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null && j.getItem() == removeitem.getItem()) {
					inv.setInventorySlotContents(i, ItemStack.EMPTY);
				}
			}
		}
	}

	public boolean canHarvestBlock(Block par1Block) {
		return true;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 1.0f;
	}
}

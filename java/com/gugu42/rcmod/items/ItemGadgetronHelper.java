package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGadgetronHelper extends Item {

	public ItemGadgetronHelper() {
		super();
		this.setCreativeTab(RcMod.rcGadgTab);
		this.setMaxStackSize(1);
		this.setFull3D();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack stack = player.getHeldItem(hand);
		
		if(!world.isRemote)
			player.openGui(RcMod.instance, 5, player.world, 0, 0, 0);
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}

}

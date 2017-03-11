package com.gugu42.rcmod.handler;

import com.gugu42.rcmod.RcMod;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class RcAchievementEventHandler {

	//TODO - Redo the achievements
	
	/*
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event) {
		if (event.player instanceof EntityPlayerMP) {
			EntityPlayerMP playerMP = (EntityPlayerMP) event.player;

			if (event.crafting.getItem() == Item.getItemFromBlock(RcMod.vendor)
					&& !playerMP.func_147099_x().hasAchievementUnlocked(
							RcMod.achievement_VendorCraft)) {
				event.player.triggerAchievement(RcMod.achievement_VendorCraft);
				event.player.world.playSoundAtEntity(event.player,
						"rcmod:achievement", 1.0f, 1.0f);
			}
			if (event.crafting.getItem() == RcMod.clankBackpack
					&& !playerMP.func_147099_x().hasAchievementUnlocked(
							RcMod.achievement_HelipackCraft)) {
				event.player.
						.triggerAchievement(RcMod.achievement_HelipackCraft);
				event.player.world.playSoundAtEntity(event.player,
						"rcmod:achievement", 1.0f, 1.0f);
			}
		}
	}
	*/
}

package com.gugu42.rcmod.handler;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.blocks.RcBlocks;
import com.gugu42.rcmod.items.RcItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class RcAchievementEventHandler {

	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event) {
		if (event.player instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.player;
			if (event.crafting.getItem() == Item.getItemFromBlock(RcBlocks.VENDOR)) {
				if(!player.hasAchievement(RcMod.achievement_VendorCraft)) {
					player.addStat(RcMod.achievement_VendorCraft);
					/*event.player.world.playSoundAtEntity(event.player,
							"rcmod:achievement", 1.0f, 1.0f);*/
					playAchievementSound(player);
				}
			}
			if (event.crafting.getItem() == RcItems.clankBackpack) {
				if(!player.hasAchievement(RcMod.achievement_HelipackCraft)) {
					player.addStat(RcMod.achievement_HelipackCraft);
					/*event.player.world.playSoundAtEntity(event.player,
							"rcmod:achievement", 1.0f, 1.0f);*/
					playAchievementSound(player);
				}
			}
		}
	}
	
	public static void playAchievementSound(EntityPlayer player)
	{
		player.world.playSound(null, player.posX, player.posY, player.posZ, new SoundEvent(new ResourceLocation("rcmod:achievement")), SoundCategory.MASTER, 1.0f, 1.0f);
	}
}

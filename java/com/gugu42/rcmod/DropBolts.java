package com.gugu42.rcmod;

import java.util.Random;

import com.gugu42.rcmod.items.RcItems;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//event handler class, it intercepts when events such as breaking ore blocks,
//breeding animals, killing mobs etc to give you bolts as well as xp and their respective drops

public class DropBolts
{
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onBlockBreakEvent(HarvestDropsEvent event)
	{
		if(event.isSilkTouching())return;
		Random random = new Random();
		if(event.getState().getBlock() == Blocks.COAL_ORE)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.BOLT, random.nextInt(3*(event.getFortuneLevel()+1))+3);
		     event.getDrops().add(itemStackToDrop);
		}
		if(event.getState().getBlock() == Blocks.LAPIS_ORE)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.BOLT, random.nextInt(5*(event.getFortuneLevel()+1))+3);
		     event.getDrops().add(itemStackToDrop);
		}
		if(event.getState().getBlock() == Blocks.REDSTONE_ORE)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.BOLT, random.nextInt(1*(event.getFortuneLevel()+1))+3);
		     event.getDrops().add(itemStackToDrop);
		}
		if(event.getState().getBlock() == Blocks.DIAMOND_ORE)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.BOLT, random.nextInt(8*(event.getFortuneLevel()+1))+3);
		     event.getDrops().add(itemStackToDrop);
		}
		if(event.getState().getBlock() == Blocks.QUARTZ_ORE)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.BOLT, random.nextInt(5*(event.getFortuneLevel()+1))+3);
		     event.getDrops().add(itemStackToDrop);
		}
		if(event.getState().getBlock() == Blocks.EMERALD_ORE)
		{
		     ItemStack itemStackToDrop = new ItemStack(RcItems.BOLT, random.nextInt(5*(event.getFortuneLevel()+1))+3);
		     event.getDrops().add(itemStackToDrop);
		}
	}
	//@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	//public void	
}

package com.gugu42.rcmod;

import com.gugu42.rcmod.items.RcItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RcRecipes 
{
	public static void addRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(RcMod.vendor, 1), new Object[]{
			"XZX",
			"ZYZ",
			"XZX",
			'X', Blocks.IRON_BLOCK, 'Y', RcItems.vendorCore, 'Z', Blocks.OBSIDIAN
		});
		GameRegistry.addRecipe(new ItemStack(RcItems.OMNIWRENCH_3000, 1), new Object[]{
			"X X",
			" Y ",
			" X ",
			'X', Items.IRON_INGOT, 'Y', Blocks.IRON_BLOCK
		});
		GameRegistry.addRecipe(new ItemStack(RcMod.crate, 1), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', Blocks.PLANKS, 'Z', Items.IRON_INGOT
		});
		GameRegistry.addRecipe(new ItemStack(RcMod.ammoCrate, 1), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', Blocks.COBBLESTONE, 'Z', Items.IRON_INGOT
		});
		GameRegistry.addRecipe(new ItemStack(RcItems.vendorCore, 1), new Object[]{
			"XXX",
			"XZX",
			"XXX",
			'X', new ItemStack(Items.DYE, 1, 14), 'Z', Items.DIAMOND
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.clankCore, 1), new Object[]{
			"GGG",
			"GDG",
			"GGG",
			'G', new ItemStack(Items.DYE, 1, 10), 'D', Items.DIAMOND
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.clank, 1), new Object[]{
			"IRI",
			"ICI",
			"IBI",
			'I', Items.IRON_INGOT, 'R', Items.REDSTONE, 'B', Blocks.REDSTONE_BLOCK, 'C', RcItems.clankCore
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.helipackHelice, 1), new Object[]{
			" I ",
			"IYI",
			" I ",
			'I', Items.IRON_INGOT, 'Y', new ItemStack(Items.DYE, 1, 11)
		});
		
		GameRegistry.addRecipe(new ItemStack(RcMod.clankBackpack, 1), new Object[]{
			" H ",
			" I ",
			"HCH",
			'I', Blocks.IRON_BARS, 'C', RcItems.clank, 'H', RcItems.helipackHelice
		});
		GameRegistry.addRecipe(new ItemStack(RcMod.tntCrate, 4), new Object[]{
			" T ",
			"TYT",
			" T ",
			'T', Blocks.TNT, 'Y', new ItemStack(Items.DYE, 1, 11)
		});
		
		GameRegistry.addRecipe(new ItemStack(RcMod.ratchetEars, 1), new Object[]{
			"Y Y",
			"YTY",
			'T', Items.STRING, 'Y', new ItemStack(Blocks.WOOL, 1, 4)
		});
		
		GameRegistry.addRecipe(new ItemStack(RcMod.thrusterPack, 1), new Object[]{
			"ICI",
			"H H",
			'I', Blocks.IRON_BARS, 'C', RcItems.clank, 'H', Items.FLINT_AND_STEEL
		});
		
		GameRegistry.addRecipe(new ItemStack(RcItems.swingShot, 1), new Object[]{
			"ISI",
			"  I",
			'I', Items.IRON_INGOT, 'S', Items.STRING
		});
		
		GameRegistry.addRecipe(new ItemStack(RcMod.versaTargetGreen, 1), new Object[]{
			"III",
			"IGI",
			"III",
			'I', Items.IRON_INGOT, 'G', new ItemStack(Items.DYE, 1, 10)
		});
		
		GameRegistry.addRecipe(new ItemStack(RcMod.shipPlatform, 1), new Object[]{
			"YPY",
			"III",
			"YIY",
			'I', Items.IRON_INGOT, 'Y', new ItemStack(Items.DYE, 1, 11), 'P', new ItemStack(Blocks.STONE_PRESSURE_PLATE)
		});
	}
}

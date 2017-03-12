package com.gugu42.rcmod.blocks;

import com.gugu42.rcmod.RcMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RcBlocks {

	public static Block             TNT_CRATE;
	public static ItemBlock			IB_TNT_CRATE;
	public static Block             CRATE;
	public static ItemBlock			IB_CRATE;
	public static Block             AMMO_CRATE;
	public static ItemBlock			IB_AMMO_CRATE;
	public static Block             VENDOR;
	public static ItemBlock			IB_VENDOR;
	public static Block             SHIP;
	public static ItemBlock			IB_SHIP;
	public static Block             SHIP_FILLER;
	public static ItemBlock			IB_SHIP_FILLER;
	public static Block             VERSA_TARGET_GREEN;
	public static ItemBlock			IB_VERSA_TARGET_GREEN;
	public static Block             SHIP_PLATFORM;
	public static ItemBlock			IB_SHIP_PLATFORM;
	
	//Called in init
	public static void registerBlocks()
	{
		TNT_CRATE = new BlockTNTCrate(Material.TNT).setUnlocalizedName("tntCrate");
		GameRegistry.register(TNT_CRATE, new ResourceLocation(RcMod.MODID, "tntCrate"));
		IB_TNT_CRATE = new ItemBlock(TNT_CRATE);
		GameRegistry.register(IB_TNT_CRATE, TNT_CRATE.getRegistryName());
		
		CRATE = new BlockCrate(Material.WOOD).setUnlocalizedName("crate").setHardness(0.0f)/*.setStepSound(crateStepSound)*/;
		GameRegistry.register(CRATE, new ResourceLocation(RcMod.MODID, "crate"));
		IB_CRATE = new ItemBlock(CRATE);
		GameRegistry.register(IB_CRATE, CRATE.getRegistryName());
		
		AMMO_CRATE = new BlockGadgetronAmmo(Material.WOOD).setUnlocalizedName("ammoCrate").setHardness(0.0f)/*.setStepSound(crateStepSound)*/;
		GameRegistry.register(AMMO_CRATE, new ResourceLocation(RcMod.MODID, "ammoCrate"));
		IB_AMMO_CRATE = new ItemBlock(AMMO_CRATE);
		GameRegistry.register(IB_AMMO_CRATE, AMMO_CRATE.getRegistryName());

		VENDOR = new BlockVendor(Material.IRON).setUnlocalizedName("vendor").setHardness(10.0f);
		GameRegistry.register(VENDOR, new ResourceLocation(RcMod.MODID, "vendor"));
		IB_VENDOR = new ItemBlock(VENDOR);
		GameRegistry.register(IB_VENDOR, VENDOR.getRegistryName());

		SHIP = new BlockShip(Material.IRON).setUnlocalizedName("ship").setHardness(5.0f);
		GameRegistry.register(SHIP, new ResourceLocation(RcMod.MODID, "ship"));
		IB_SHIP = new ItemBlock(SHIP);
		GameRegistry.register(IB_SHIP, SHIP.getRegistryName());

		SHIP_FILLER = new BlockShipFiller(Material.IRON).setUnlocalizedName("shipFiller");
		GameRegistry.register(SHIP_FILLER, new ResourceLocation(RcMod.MODID, "shipFiller"));
		IB_SHIP_FILLER = new ItemBlock(SHIP_FILLER);
		GameRegistry.register(IB_SHIP_FILLER, SHIP_FILLER.getRegistryName());

		VERSA_TARGET_GREEN = new BlockVersaTargetGreen(Material.IRON).setUnlocalizedName("versaTargetGreen");
		GameRegistry.register(VERSA_TARGET_GREEN, new ResourceLocation(RcMod.MODID, "versaTargetGreen"));
		IB_VERSA_TARGET_GREEN = new ItemBlock(VERSA_TARGET_GREEN);
		GameRegistry.register(IB_VERSA_TARGET_GREEN, VERSA_TARGET_GREEN.getRegistryName());

		SHIP_PLATFORM = new BlockShipPlatform().setUnlocalizedName("shipPlatform").setCreativeTab(RcMod.rcTab);
		GameRegistry.register(SHIP_PLATFORM, new ResourceLocation(RcMod.MODID, "shipPlatform"));
		IB_SHIP_PLATFORM = new ItemBlock(SHIP_PLATFORM);
		GameRegistry.register(IB_SHIP_PLATFORM, SHIP_PLATFORM.getRegistryName());
	}
	
	//Called in ClientProxy's PreInit
	public static void registerBlockRenders()
	{
		registerItemBlockRender(AMMO_CRATE);
		registerItemBlockRender(CRATE);
		registerItemBlockRender(TNT_CRATE);
		registerItemBlockRender(SHIP);
		registerItemBlockRender(VENDOR);
		registerItemBlockRender(VERSA_TARGET_GREEN);
		registerItemBlockRender(SHIP_PLATFORM);
		registerItemBlockRender(SHIP_FILLER);
	}
	
	public static void registerItemBlockRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}

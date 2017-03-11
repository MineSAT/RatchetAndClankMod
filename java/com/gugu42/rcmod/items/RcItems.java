package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RcItems {
	//All global mod items goes there
	public static Item omniwrench3000;
	public static Item bolt;
	public static Item vendorCore;
	public static Item helipackHelice;
	public static Item clankCore;
	public static Item clank;
	public static Item DUMMY_pyrocitorFlame;
	public static Item metropolisRecord;
	
	// All items from RC1 goes there
	public static Item blaster;
	public static Item bombGlove;
	public static Item ryno;
	public static Item pyrocitor;
	public static Item walloper;
	public static Item visibombGun;
	public static Item decoyGlove;
	public static Item devastator;
	public static Item droneDevice;
	public static Item gloveOfDoom;
	public static Item mineGlove;
	public static Item morphORay;
	public static Item suckCannon;
	public static Item taunter;
	public static Item teslaClaw;
	
	// All items from RC1 goes there
	public static Item ammoBlaster;
	public static Item ammoBombGlove;
	public static Item ammoRyno;
	public static Item ammoPyrocitor;
	public static Item ammoVisibombGun;
	public static Item ammoDecoyGlove;
	public static Item ammoDevastator;
	public static Item ammoDroneDevice;
	public static Item ammoGloveOfDoom;
	public static Item ammoMineGlove;
	public static Item ammoTeslaClaw;
	
	// All gadgets from RC1 goes there
	
	public static Item swingShot;
	//Not really a gadget from the game, but still something, so yeah
	public static Item gadgetronHelper;
	

	public RcItems(){
		
	}
	
	public static void initRc1Items() {

		blaster = new ItemBlaster().setUnlocalizedName("blaster").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.register(blaster, new ResourceLocation(RcMod.MODID, "blaster"));

		bombGlove = new ItemBombGlove().setUnlocalizedName("bombglove").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.register(bombGlove, new ResourceLocation(RcMod.MODID, "bombglove"));

		ryno = new ItemRYNO().setUnlocalizedName("ryno").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.register(ryno, new ResourceLocation(RcMod.MODID, "ryno"));

		pyrocitor = new ItemPyrocitor().setUnlocalizedName("pyrocitor")
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.register(pyrocitor, new ResourceLocation(RcMod.MODID, "pyrocitor"));

		walloper = new ItemWalloper().setUnlocalizedName("walloper")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(walloper, new ResourceLocation(RcMod.MODID, "walloper"));
		
		visibombGun = new ItemVisibombGun().setUnlocalizedName("visibombGun")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(visibombGun, new ResourceLocation(RcMod.MODID, "visibombGun"));
		
		decoyGlove = new ItemDecoyGlove().setUnlocalizedName("decoyGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(decoyGlove, new ResourceLocation(RcMod.MODID, "decoyGlove"));
		
		devastator = new ItemDevastator().setUnlocalizedName("devastator")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(devastator, new ResourceLocation(RcMod.MODID, "devastator"));
		
		droneDevice = new ItemDroneDevice().setUnlocalizedName("droneDevice")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(droneDevice, new ResourceLocation(RcMod.MODID, "droneDevice"));
		
		gloveOfDoom = new ItemGloveOfDoom().setUnlocalizedName("gloveOfDoom")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(gloveOfDoom, new ResourceLocation(RcMod.MODID, "gloveOfDoom"));
		
		mineGlove = new ItemMineGlove().setUnlocalizedName("mineGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(mineGlove, new ResourceLocation(RcMod.MODID, "mineGlove"));
		
		morphORay = new ItemMorphORay().setUnlocalizedName("morphORay")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(morphORay, new ResourceLocation(RcMod.MODID, "morphORay"));
		
		suckCannon = new ItemSuckCannon().setUnlocalizedName("suckCannon")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(suckCannon, new ResourceLocation(RcMod.MODID, "suckCannon"));
		
		taunter = new ItemTaunter().setUnlocalizedName("taunter")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(taunter, new ResourceLocation(RcMod.MODID, "taunter"));
		
		teslaClaw = new ItemTeslaClaw().setUnlocalizedName("teslaClaw")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(teslaClaw, new ResourceLocation(RcMod.MODID, "teslaClaw"));
		
		/*  GADGETS  */
		
		swingShot = new ItemSwingShot().setUnlocalizedName("swingShot").setCreativeTab(RcMod.rcGadgTab).setFull3D();
		GameRegistry.register(swingShot, new ResourceLocation(RcMod.MODID, "swingShot"));
		
		gadgetronHelper = new ItemGadgetronHelper().setUnlocalizedName("gadgetronHelper").setCreativeTab(RcMod.rcGadgTab).setFull3D();
		GameRegistry.register(gadgetronHelper, new ResourceLocation(RcMod.MODID, "gadgetronHelper"));
		
	}
	
	public static void initModItems(){
		omniwrench3000 = new ItemOmniWrench3000().setUnlocalizedName(
				"omwr3000");
		GameRegistry.register(omniwrench3000, new ResourceLocation(RcMod.MODID, "omwr3000"));

		bolt = new ItemBolt().setUnlocalizedName("bolt");
		GameRegistry.register(bolt, new ResourceLocation(RcMod.MODID, "bolt"));
		
		vendorCore = new ItemVendorCore().setUnlocalizedName(
				"vendorCore");
		GameRegistry.register(vendorCore, new ResourceLocation(RcMod.MODID, "vendorCore"));
		
		clankCore = new ItemRcSimple().setUnlocalizedName("clankCore").setCreativeTab(RcMod.rcTab);
		GameRegistry.register(clankCore, new ResourceLocation(RcMod.MODID, "clankCore"));
		
		clank = new ItemRcSimple().setUnlocalizedName("clank").setCreativeTab(RcMod.rcTab);
		GameRegistry.register(clank, new ResourceLocation(RcMod.MODID, "clank"));
		
		helipackHelice = new ItemRcSimple().setUnlocalizedName("helipackHelice").setCreativeTab(RcMod.rcTab);
		GameRegistry.register(helipackHelice, new ResourceLocation(RcMod.MODID, "helipackHelice"));
		
		DUMMY_pyrocitorFlame = new ItemRcSimple().setUnlocalizedName("dummyItem");
		GameRegistry.register(DUMMY_pyrocitorFlame, new ResourceLocation(RcMod.MODID, "DUMMY_pyrocitorFlame"));
		
		metropolisRecord = new ItemRcRecord("rcmod:recordmetropolis", "Metropolis - Kerwan", "metropolisRecord").setUnlocalizedName("metropolisRecord").setCreativeTab(RcMod.rcTab);
		GameRegistry.register(metropolisRecord, new ResourceLocation(RcMod.MODID, "metropolisRecord"));
	}
	
	public static void initAmmoItems()
	{
		ammoBlaster = new ItemAmmo(blaster).setUnlocalizedName(
				"ammoblaster")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoBlaster, new ResourceLocation(RcMod.MODID, "ammoblaster"));
		
		ammoBombGlove = new ItemAmmo(bombGlove).setUnlocalizedName(
				"ammobombGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoBombGlove, new ResourceLocation(RcMod.MODID, "ammobombGlove"));
		
		ammoRyno = new ItemAmmo(ryno).setUnlocalizedName(
				"ammoryno")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoRyno, new ResourceLocation(RcMod.MODID, "ammoryno"));
		
		ammoPyrocitor = new ItemAmmo(pyrocitor).setUnlocalizedName(
				"ammopyrocitor")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoPyrocitor, new ResourceLocation(RcMod.MODID, "ammopyrocitor"));
		
		ammoVisibombGun = new ItemAmmo(visibombGun).setUnlocalizedName(
				"ammovisibombGun")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoVisibombGun, new ResourceLocation(RcMod.MODID, "ammovisibombGun"));
		
		ammoDecoyGlove = new ItemAmmo(decoyGlove).setUnlocalizedName(
				"ammodecoyGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoDecoyGlove, new ResourceLocation(RcMod.MODID, "ammodecoyGlove"));
		
		ammoDevastator = new ItemAmmo(devastator).setUnlocalizedName(
				"ammodevastator")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoDevastator, new ResourceLocation(RcMod.MODID, "ammodevastator"));
		
		ammoDroneDevice = new ItemAmmo(droneDevice).setUnlocalizedName(
				"ammodroneDevice")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoDroneDevice, new ResourceLocation(RcMod.MODID, "ammodroneDevice"));
		
		ammoGloveOfDoom = new ItemAmmo(gloveOfDoom).setUnlocalizedName(
				"ammogloveOfDoom")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoGloveOfDoom, new ResourceLocation(RcMod.MODID, "ammogloveOfDoom"));
		
		ammoMineGlove = new ItemAmmo(mineGlove).setUnlocalizedName(
				"ammomineGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoMineGlove, new ResourceLocation(RcMod.MODID, "ammomineGlove"));
		
		ammoTeslaClaw = new ItemAmmo(teslaClaw).setUnlocalizedName(
				"ammoteslaClaw")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(ammoTeslaClaw, new ResourceLocation(RcMod.MODID, "ammoteslaClaw"));
	}

	public static boolean isAmmo(Item item)
	{
		return item instanceof ItemAmmo;
	}

}

package com.gugu42.rcmod.items;

import java.util.ArrayList;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.client.ModelLocation;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RcItems {
	
	public static ArrayList<ModelLocation> modelLocations = new ArrayList<ModelLocation>();
	
	//All global mod items goes there
	public static Item OMNIWRENCH_3000;
	public static Item BOLT;
	public static Item VENDOR_CORE;
	public static Item HELIPACK_HELICE;
	public static Item CLANK_CORE;
	public static Item CLANK;
	public static Item DUMMY_PYROCITOR_FLAME;
	public static Item RECORD_METROPOLIS;
	
	// All items from RC1 goes there
	public static Item BLASTER;
	public static Item BOMB_GLOVE;
	public static Item RYNO;
	public static Item PYROCITOR;
	public static Item WALLOPER;
	public static Item VISIBOMB_GUN;
	public static Item DECOY_GLOVE;
	public static Item DEVASTATOR;
	public static Item DRONE_DEVICE;
	public static Item GLOVE_OF_DOOM;
	public static Item MINE_GLOVE;
	public static Item MORPH_O_RAY;
	public static Item SUCK_CANNON;
	public static Item TAUNTER;
	public static Item TESLA_CLAW;
	
	// All items from RC1 goes there
	public static Item AMMO_BLASTER;
	public static Item AMMO_BOMB_GLOVE;
	public static Item AMMO_RYNO;
	public static Item AMMO_PYROCITOR;
	public static Item AMMO_VISIBOMB_GUN;
	public static Item AMMO_DECOY_GLOVE;
	public static Item AMMO_DEVASTATOR;
	public static Item AMMO_DRONE_DEVICE;
	public static Item AMMO_GLOVE_OF_DOOM;
	public static Item AMMO_MINE_GLOVE;
	public static Item AMMO_TESLA_CLAW;
	
	// All gadgets from RC1 goes there
	
	public static Item SWINGSHOT;
	//Not really a gadget from the game, but still something, so yeah
	public static Item GADGETRON_PDA;
	
	
	//previously in RcMod.java, I moved them here because I need more logic in my code
	public static Item              HELIPACK;
	public static Item              RATCHET_EARS;
	public static Item              THRUSTER_PACK;
	public static Item              SHIP_ITEM;

	public RcItems(){
		
	}
	
	public static void initRc1Items() {

		BLASTER = new ItemBlaster().setUnlocalizedName("blaster").setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.register(BLASTER, new ResourceLocation(RcMod.MODID, "blaster"));

		BOMB_GLOVE = new ItemBombGlove().setUnlocalizedName("bombglove").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.register(BOMB_GLOVE, new ResourceLocation(RcMod.MODID, "bombglove"));

		RYNO = new ItemRYNO().setUnlocalizedName("ryno").setFull3D()
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.register(RYNO, new ResourceLocation(RcMod.MODID, "ryno"));

		PYROCITOR = new ItemPyrocitor().setUnlocalizedName("pyrocitor")
				.setCreativeTab(RcMod.rcWeapTab);
		GameRegistry.register(PYROCITOR, new ResourceLocation(RcMod.MODID, "pyrocitor"));

		WALLOPER = new ItemWalloper().setUnlocalizedName("walloper")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(WALLOPER, new ResourceLocation(RcMod.MODID, "walloper"));
		
		VISIBOMB_GUN = new ItemVisibombGun().setUnlocalizedName("visibombGun")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(VISIBOMB_GUN, new ResourceLocation(RcMod.MODID, "visibombGun"));
		
		DECOY_GLOVE = new ItemDecoyGlove().setUnlocalizedName("decoyGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(DECOY_GLOVE, new ResourceLocation(RcMod.MODID, "decoyGlove"));
		
		DEVASTATOR = new ItemDevastator().setUnlocalizedName("devastator")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(DEVASTATOR, new ResourceLocation(RcMod.MODID, "devastator"));
		
		DRONE_DEVICE = new ItemDroneDevice().setUnlocalizedName("droneDevice")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(DRONE_DEVICE, new ResourceLocation(RcMod.MODID, "droneDevice"));
		
		GLOVE_OF_DOOM = new ItemGloveOfDoom().setUnlocalizedName("gloveOfDoom")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(GLOVE_OF_DOOM, new ResourceLocation(RcMod.MODID, "gloveOfDoom"));
		
		MINE_GLOVE = new ItemMineGlove().setUnlocalizedName("mineGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(MINE_GLOVE, new ResourceLocation(RcMod.MODID, "mineGlove"));
		
		MORPH_O_RAY = new ItemMorphORay().setUnlocalizedName("morphORay")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(MORPH_O_RAY, new ResourceLocation(RcMod.MODID, "morphORay"));
		
		SUCK_CANNON = new ItemSuckCannon().setUnlocalizedName("suckCannon")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(SUCK_CANNON, new ResourceLocation(RcMod.MODID, "suckCannon"));
		
		TAUNTER = new ItemTaunter().setUnlocalizedName("taunter")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(TAUNTER, new ResourceLocation(RcMod.MODID, "taunter"));
		
		TESLA_CLAW = new ItemTeslaClaw().setUnlocalizedName("teslaClaw")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(TESLA_CLAW, new ResourceLocation(RcMod.MODID, "teslaClaw"));
		
		/*  GADGETS  */
		
		SWINGSHOT = new ItemSwingShot().setUnlocalizedName("swingShot").setCreativeTab(RcMod.rcGadgTab).setFull3D();
		GameRegistry.register(SWINGSHOT, new ResourceLocation(RcMod.MODID, "swingshot"));
		
		GADGETRON_PDA = new ItemGadgetronHelper().setUnlocalizedName("gadgetronHelper").setCreativeTab(RcMod.rcGadgTab).setFull3D();
		GameRegistry.register(GADGETRON_PDA, new ResourceLocation(RcMod.MODID, "gadgetronhelper"));
		
	}
	
	public static void initModItems(){
		OMNIWRENCH_3000 = new ItemOmniWrench3000().setUnlocalizedName(
				"omwr3000");
		GameRegistry.register(OMNIWRENCH_3000, new ResourceLocation(RcMod.MODID, "omwr3000"));

		BOLT = new ItemBolt().setUnlocalizedName("bolt");
		GameRegistry.register(BOLT, new ResourceLocation(RcMod.MODID, "bolt"));
		
		VENDOR_CORE = new ItemVendorCore().setUnlocalizedName(
				"vendorCore");
		GameRegistry.register(VENDOR_CORE, new ResourceLocation(RcMod.MODID, "vendorcore"));
		
		CLANK_CORE = new ItemRcSimple().setUnlocalizedName("clankCore").setCreativeTab(RcMod.rcTab);
		GameRegistry.register(CLANK_CORE, new ResourceLocation(RcMod.MODID, "clankCore"));
		
		CLANK = new ItemRcSimple().setUnlocalizedName("clank").setCreativeTab(RcMod.rcTab);
		GameRegistry.register(CLANK, new ResourceLocation(RcMod.MODID, "clank"));
		
		HELIPACK_HELICE = new ItemRcSimple().setUnlocalizedName("helipackHelice").setCreativeTab(RcMod.rcTab);
		GameRegistry.register(HELIPACK_HELICE, new ResourceLocation(RcMod.MODID, "helipackHelice"));
		
		DUMMY_PYROCITOR_FLAME = new ItemRcSimple().setUnlocalizedName("dummyItem");
		GameRegistry.register(DUMMY_PYROCITOR_FLAME, new ResourceLocation(RcMod.MODID, "DUMMY_pyrocitorFlame"));
		
		RECORD_METROPOLIS = new ItemRcRecord("rcmod:recordmetropolis", "Metropolis - Kerwan", "metropolisRecord").setUnlocalizedName("metropolisRecord").setCreativeTab(RcMod.rcTab);
		GameRegistry.register(RECORD_METROPOLIS, new ResourceLocation(RcMod.MODID, "metropolisRecord"));
		
		HELIPACK = new ItemClankBackpack(RcMod.enumArmorMaterialClank, 1, 1).setUnlocalizedName("clankHeli");
		GameRegistry.register(HELIPACK, new ResourceLocation(RcMod.MODID, "clankheli"));
		RATCHET_EARS = new ItemRatchetEars(RcMod.enumArmorMaterialClank, 1, 0).setUnlocalizedName("ratchetEars");
		GameRegistry.register(RATCHET_EARS, new ResourceLocation(RcMod.MODID, "ratchetears"));
		THRUSTER_PACK = new ItemThrusterPack(RcMod.enumArmorMaterialClank, 1, 1).setUnlocalizedName("thrusterpack");
		GameRegistry.register(THRUSTER_PACK, new ResourceLocation(RcMod.MODID, "thrusterpack"));
		SHIP_ITEM = new ItemShip().setMaxStackSize(1).setCreativeTab(RcMod.rcTab);
		GameRegistry.register(SHIP_ITEM, new ResourceLocation(RcMod.MODID, "shipitem"));
	}
	
	public static void initAmmoItems()
	{
		AMMO_BLASTER = new ItemAmmo(BLASTER).setUnlocalizedName(
				"ammoblaster")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_BLASTER, new ResourceLocation(RcMod.MODID, "ammoblaster"));
		
		AMMO_BOMB_GLOVE = new ItemAmmo(BOMB_GLOVE).setUnlocalizedName(
				"ammobombGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_BOMB_GLOVE, new ResourceLocation(RcMod.MODID, "ammobombglove"));
		
		AMMO_RYNO = new ItemAmmo(RYNO).setUnlocalizedName(
				"ammoryno")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_RYNO, new ResourceLocation(RcMod.MODID, "ammoryno"));
		
		AMMO_PYROCITOR = new ItemAmmo(PYROCITOR).setUnlocalizedName(
				"ammopyrocitor")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_PYROCITOR, new ResourceLocation(RcMod.MODID, "ammopyrocitor"));
		
		AMMO_VISIBOMB_GUN = new ItemAmmo(VISIBOMB_GUN).setUnlocalizedName(
				"ammovisibombGun")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_VISIBOMB_GUN, new ResourceLocation(RcMod.MODID, "ammovisibombgun"));
		
		AMMO_DECOY_GLOVE = new ItemAmmo(DECOY_GLOVE).setUnlocalizedName(
				"ammodecoyGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_DECOY_GLOVE, new ResourceLocation(RcMod.MODID, "ammodecoyglove"));
		
		AMMO_DEVASTATOR = new ItemAmmo(DEVASTATOR).setUnlocalizedName(
				"ammodevastator")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_DEVASTATOR, new ResourceLocation(RcMod.MODID, "ammodevastator"));
		
		AMMO_DRONE_DEVICE = new ItemAmmo(DRONE_DEVICE).setUnlocalizedName(
				"ammodroneDevice")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_DRONE_DEVICE, new ResourceLocation(RcMod.MODID, "ammodronedevice"));
		
		AMMO_GLOVE_OF_DOOM = new ItemAmmo(GLOVE_OF_DOOM).setUnlocalizedName(
				"ammogloveOfDoom")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_GLOVE_OF_DOOM, new ResourceLocation(RcMod.MODID, "ammogloveofdoom"));
		
		AMMO_MINE_GLOVE = new ItemAmmo(MINE_GLOVE).setUnlocalizedName(
				"ammomineGlove")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_MINE_GLOVE, new ResourceLocation(RcMod.MODID, "ammomineglove"));
		
		AMMO_TESLA_CLAW = new ItemAmmo(TESLA_CLAW).setUnlocalizedName(
				"ammoteslaClaw")
				.setCreativeTab(RcMod.rcWeapTab).setFull3D();
		GameRegistry.register(AMMO_TESLA_CLAW, new ResourceLocation(RcMod.MODID, "ammoteslaclaw"));
		
		
	}

	public static boolean isAmmo(Item item)
	{
		return item instanceof ItemAmmo;
	}
	
	public static void registerItemRenders()
	{
		/* WEAPONS */
	
		registerItemRender(BLASTER, "blaster"); 		//done
		registerItemRender(BOMB_GLOVE, "bombglove"); 	//done
		registerItemRender(DEVASTATOR, "devastator");	//done
		registerItemRender(PYROCITOR, "pyrocitor");		//done
		registerItemRender(RYNO, "ryno");				//done
		registerItemRender(TAUNTER, "taunter");			//done
		
		
		
		ModelLoader.setCustomModelResourceLocation(DECOY_GLOVE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "decoyglove"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(DRONE_DEVICE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "dronedevice"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(GLOVE_OF_DOOM, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "gloveofdoom"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(MINE_GLOVE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "mineglove"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(MORPH_O_RAY, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "morphoray"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(SUCK_CANNON, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "suckcannon"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(TESLA_CLAW, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "teslaclaw"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(VISIBOMB_GUN, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "visibombgun"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(WALLOPER, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "walloper"), "inventory"));
		
		/* MISC */
		ModelLoader.setCustomModelResourceLocation(VENDOR_CORE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "vendorcore"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(BOLT, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "bolt"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(OMNIWRENCH_3000, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "omwr3000"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(CLANK, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "clank"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(CLANK_CORE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "clankcore"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(HELIPACK_HELICE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "helipackhelice"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(DUMMY_PYROCITOR_FLAME, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "dummy_pyrocitorflame"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(RECORD_METROPOLIS, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "metropolisrecord"), "inventory"));
		
		/* AMMO */
		ModelLoader.setCustomModelResourceLocation(AMMO_BLASTER, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammoblaster"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_BOMB_GLOVE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammobombglove"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_DECOY_GLOVE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammodecoyglove"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_DEVASTATOR, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammodevastator"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_DRONE_DEVICE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammodronedevice"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_GLOVE_OF_DOOM, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammogloveofdoom"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_MINE_GLOVE, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammomineglove"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_PYROCITOR, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammopyrocitor"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_RYNO, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammoryno"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_TESLA_CLAW, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammoteslaclaw"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(AMMO_VISIBOMB_GUN, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ammovisibombgun"), "inventory"));
		
		/* GADGETS */
		ModelLoader.setCustomModelResourceLocation(SWINGSHOT, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "swingshot"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(GADGETRON_PDA, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "gadgetronhelper"), "inventory"));
		/* ITEMS THAT WERE IN RCMOD.JAVA */
		ModelLoader.setCustomModelResourceLocation(HELIPACK, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "clankbackpack"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(RATCHET_EARS, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "ratchetears"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(THRUSTER_PACK, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "thrusterpack"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(SHIP_ITEM, 0, new ModelResourceLocation(new ResourceLocation(RcMod.MODID, "shipitem"), "inventory"));
	}
	
	/**
	 * Register an items that will use modelName for 3D rendering in hand and world, and will use modelName_inventory to render a 2D icon in hand
	 * 
	 * @param item The item to render
	 * @param modelName The model name to use (Used for .json files)
	 */
	private static void registerItemRender(Item item, String modelName)
	{
		ModelLocation modelLoc = new ModelLocation(new ModelResourceLocation(new ResourceLocation(RcMod.MODID, modelName + "_inventory"), "inventory"), new ModelResourceLocation(new ResourceLocation(RcMod.MODID, modelName), "normal"));
		ModelLoader.setCustomModelResourceLocation(item, 0, modelLoc.location2D); 				//done
		ModelBakery.registerItemVariants(item, modelLoc.location3D);
		modelLocations.add(modelLoc);
	}
}

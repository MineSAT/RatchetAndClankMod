package com.gugu42.rcmod;

import com.gugu42.rcmod.handler.RcSoundHandler;
import com.gugu42.rcmod.items.RcItems;
import com.gugu42.rcmod.utils.RcSimpleResourceManager;

import net.minecraft.client.resources.IResourceManager;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	// THIS NEEDS TO BE CLIENT SIDE ONLY !
	public static IResourceManager rcResourceManager = new RcSimpleResourceManager();
	//	public static RcModelManager rcModelManager = new RcModelManager();
	public static int              renderInventoryTESRId;

	@Override
	public void preInit()
	{
		super.preInit();
		OBJLoader.INSTANCE.addDomain(RcMod.MODID);
		RcItems.registerItemRenders();
	}
	
	@Override
	public void registerRenderInformation() {
		
		//TODO - Redo all rendering
		
		/*RenderingRegistry.registerEntityRenderingHandler(EntityTNTCrate.class, new RenderTNTCrate());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterAmmo.class, new RenderBlasterAmmo(0.1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityRYNOAmmo.class, new RenderRYNOAmmo(0.1f));
		RenderingRegistry.registerEntityRenderingHandler(EntityBombGloveAmmo.class, new RenderBombGloveAmmo(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPyrocitorAmmo.class, new RenderPyrocitorAmmo(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityVisibombAmmo.class, new RenderVisibombAmmo(0.1F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWrenchThrown.class, new RenderThrownWrench(0.1F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMineGloveAmmo.class, new RenderMineGloveAmmo(0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityDecoyGloveAmmo.class, new RenderDecoyGloveAmmo(0.5f));

		RenderingRegistry.registerEntityRenderingHandler(EntitySwingShotHook.class, new RenderSwingShotHook());

		RenderingRegistry.registerEntityRenderingHandler(EntitySuckCannonProj.class, new RenderSuckCannonProj());*/

		/* WEAPONS */
		//TODO - Fix item renders
/*
		MinecraftForgeClient.registerItemRenderer(RcItems.blaster, new BlasterRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.bombGlove, new BombGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.ryno, new RYNORender());
		MinecraftForgeClient.registerItemRenderer(RcItems.pyrocitor, new PyrocitorRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.walloper, new WalloperRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.omniwrench3000, new OmniWrench3000Render());
		MinecraftForgeClient.registerItemRenderer(RcItems.visibombGun, new VisibombRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.decoyGlove, new DecoyGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.devastator, new DevastatorRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.droneDevice, new DroneDeviceRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.gloveOfDoom, new GloveOfDoomRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.mineGlove, new MineGloveRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.morphORay, new MorphORayRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.suckCannon, new SuckCannonRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.taunter, new TaunterRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.teslaClaw, new TeslaClawRender());

		/* GADGETS

		MinecraftForgeClient.registerItemRenderer(RcItems.swingShot, new SwingShotRender());
		MinecraftForgeClient.registerItemRenderer(RcItems.gadgetronHelper, new GadgetronPDARender());

		/* AMMO ITEMS 

		MinecraftForgeClient.registerItemRenderer(RcItems.ammoBlaster, new AmmoBlasterItem());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoBombGlove, new AmmoBombGlove());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoDecoyGlove, new AmmoDecoyGlove());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoDevastator, new AmmoDevastator());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoDroneDevice, new AmmoDroneDevice());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoGloveOfDoom, new AmmoGloveofDoom());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoMineGlove, new AmmoMineGlove());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoPyrocitor, new AmmoPyrocitor());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoRyno, new AmmoRYNO());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoTeslaClaw, new AmmoTeslaClaw());
		MinecraftForgeClient.registerItemRenderer(RcItems.ammoVisibombGun, new AmmoVisibombGun()); */

		//renderInventoryTESRId = RenderingRegistry.getNextAvailableRenderId();
		//RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
		
		/*TESRInventoryRenderer.blockByTESR.put(new TESRIndex(RcMod.vendor, 0), new TileEntityVendorSpecialRenderer());
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(RcMod.ship, 0), new TileEntityShipSpecialRenderer());
		TESRInventoryRenderer.blockByTESR.put(new TESRIndex(RcMod.versaTargetGreen, 0), new TileEntityVersaTargetGSpecialRenderer());
		*/
		
		MinecraftForge.EVENT_BUS.register(new RcSoundHandler());
		
	}

	@Override
	public void registerTileEntityRender() {
		/*ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVendor.class, new TileEntityVendorSpecialRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityShip.class, new TileEntityShipSpecialRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVersaTargetG.class, new TileEntityVersaTargetGSpecialRenderer());
		*/
	}

}
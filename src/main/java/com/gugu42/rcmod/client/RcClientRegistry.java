package com.gugu42.rcmod.client;

import com.gugu42.rcmod.client.gui.BoltGui;
import com.gugu42.rcmod.client.gui.VendorScreen;
import com.gugu42.rcmod.client.model.RcModels;
import com.gugu42.rcmod.client.render.TntCrateRenderer;
import com.gugu42.rcmod.client.render.VendorTileEntityRenderer;
import com.gugu42.rcmod.client.render.projectiles.BlasterProjectileRenderer;
import com.gugu42.rcmod.client.render.projectiles.HarbingerLaserRenderer;
import com.gugu42.rcmod.client.utils.glutils.mc.TessellatorModel;
import com.gugu42.rcmod.common.RcBlocks;
import com.gugu42.rcmod.common.RcContainers;
import com.gugu42.rcmod.common.RcEntities;
import com.gugu42.rcmod.common.entities.projectiles.BlasterProjectileEntity;
import com.gugu42.rcmod.common.entities.projectiles.HarbingerLaserEntity;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RcClientRegistry {

    public static void registerClientStuff(final FMLClientSetupEvent event) {
        registerScreensAndGUIs();
        registerEntityRenderers();
        registerTileEntityRenderers();
        RcModels.loadModels();
    }

    private static void registerScreensAndGUIs() {
        MinecraftForge.EVENT_BUS.register(new BoltGui());
        ScreenManager.registerFactory(RcContainers.VENDOR_CONTAINER.get(), VendorScreen::new);
    }

    private static void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(RcEntities.TNT_CRATE_ENTITY.get(), TntCrateRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RcEntities.BLASTER_PROJECTILE.get(), BlasterProjectileRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RcEntities.HARBINGER_LASER_ENTITY.get(), HarbingerLaserRenderer::new);
    }

    private static void registerTileEntityRenderers() {
        ClientRegistry.bindTileEntityRenderer(RcBlocks.VENDOR_TILE_ENTITY.get(), VendorTileEntityRenderer::new);
        RenderTypeLookup.setRenderLayer(RcBlocks.VENDOR_BLOCK.get(), RenderType.getTranslucent());
    }
}

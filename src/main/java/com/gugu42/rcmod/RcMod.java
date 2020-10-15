package com.gugu42.rcmod;

import com.gugu42.rcmod.client.RcClientRegistry;
import com.gugu42.rcmod.client.gui.BoltGui;
import com.gugu42.rcmod.client.gui.VendorScreen;
import com.gugu42.rcmod.client.render.TntCrateRenderer;
import com.gugu42.rcmod.client.render.VendorTileEntityRenderer;
import com.gugu42.rcmod.common.RcBlocks;
import com.gugu42.rcmod.common.RcContainers;
import com.gugu42.rcmod.common.RcEntities;
import com.gugu42.rcmod.common.RcItems;
import com.gugu42.rcmod.common.capabilities.IRcModCapability;
import com.gugu42.rcmod.common.capabilities.RcModCapability;
import com.gugu42.rcmod.common.entities.TntCrateEntity;
import com.gugu42.rcmod.common.handler.RcEventHandler;
import com.gugu42.rcmod.common.handler.RcTickHandler;
import com.gugu42.rcmod.common.network.RcNetwork;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.loot.LootTableManager;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("rcmod")
public class RcMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "rcmod";

    public RcMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(RcTickHandler.class);
        MinecraftForge.EVENT_BUS.register(RcEventHandler.class);

        RcBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RcBlocks.TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        RcItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RcEntities.ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        RcContainers.CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(IRcModCapability.class, new RcModCapability.RcModCapabilityStorage(), new RcModCapability.RcModCapabilityFactory());
        RcNetwork.registerPackets();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
//        MinecraftForge.EVENT_BUS.register(new BoltGui());
//        RenderingRegistry.registerEntityRenderingHandler(RcEntities.TNT_CRATE_ENTITY.get(), TntCrateRenderer::new);
//        ClientRegistry.bindTileEntityRenderer(RcBlocks.VENDOR_TILE_ENTITY.get(), VendorTileEntityRenderer::new);
//        RenderTypeLookup.setRenderLayer(RcBlocks.VENDOR_BLOCK.get(), RenderType.getTranslucent());
//        ScreenManager.registerFactory(RcContainers.VENDOR_CONTAINER.get(), VendorScreen::new);
        RcClientRegistry.registerClientStuff(event);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }
}

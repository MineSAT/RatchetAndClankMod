package com.gugu42.rcmod.client.gui;

import com.gugu42.rcmod.common.capabilities.IRcModCapability;
import com.gugu42.rcmod.common.capabilities.RcModCapability;
import com.gugu42.rcmod.common.capabilities.RcModCapabilityProvider;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class BoltGui extends AbstractGui {

    private Minecraft mc;
    private static final ResourceLocation BOLT_TEXTURE = new ResourceLocation("rcmod", "textures/gui/bolt.png");

    private int lastBolts;
    private long showBoltTimer;
    private int maxBoltShowTime;

    public BoltGui() {
        this.mc = Minecraft.getInstance();
        this.lastBolts = 0;
        this.showBoltTimer = 0;
        this.maxBoltShowTime = 5000;
    }

    @SubscribeEvent
    public void onOverlayRenderEvent(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }

        Optional<IRcModCapability> capabilityOptional = this.mc.player.getCapability(RcModCapability.RCMOD_CAP).resolve();
        if (this.mc.player != null && capabilityOptional.isPresent()) {
            IRcModCapability capa = capabilityOptional.get();

            if (capa.getBolts() != lastBolts) {
                showBoltTimer = System.currentTimeMillis();
            }

            lastBolts = capa.getBolts();

            if (System.currentTimeMillis() < showBoltTimer + this.maxBoltShowTime
                || this.mc.currentScreen instanceof InventoryScreen
                || this.mc.currentScreen instanceof CreativeScreen) {
                this.mc.getTextureManager().bindTexture(BOLT_TEXTURE);
                blit(event.getMatrixStack(), 360, 5, 0, 0, 16, 16, 16, 16);
                this.mc.fontRenderer.drawString(event.getMatrixStack(), "" + capa.getBolts(), 380, 8, 87 * 233 * 255);
            }
        }
    }
}

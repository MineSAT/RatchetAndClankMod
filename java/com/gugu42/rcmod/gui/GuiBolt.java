package com.gugu42.rcmod.gui;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.capabilities.bolt.BoltProvider;
import com.gugu42.rcmod.capabilities.bolt.IBolt;
import com.gugu42.rcmod.items.ItemRcWeap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBolt extends Gui {
	private Minecraft mc;
	private static final ResourceLocation texturepath = new ResourceLocation(
			"rcmod", "textures/gui/bolt.png");

	private int lastBolts;
	private long showBoltTimer;
	private int maxBoltShowTime;

	public GuiBolt(Minecraft mc) {
		super();
		this.lastBolts = 0;
		this.showBoltTimer = 0;
		this.maxBoltShowTime = 10000;
		this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
			return;
		}

		//ExtendedPlayerBolt props = ExtendedPlayerBolt.get(this.mc.player);
		IBolt props = this.mc.player.getCapability(BoltProvider.BOLT_CAP, null);
		if (props == null || props.getMaxBolts() == 0) {
			return;
		}

		if (props.getCurrentBolt() != lastBolts
				|| mc.currentScreen instanceof GuiIngameMenu
				|| mc.currentScreen instanceof GuiVendor
				|| mc.currentScreen instanceof GuiContainer
				|| mc.currentScreen instanceof GuiInventory) {
			showBoltTimer = System.currentTimeMillis();
		}
		lastBolts = props.getCurrentBolt();
		if (System.currentTimeMillis() - showBoltTimer < maxBoltShowTime) {
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			this.mc.getTextureManager().bindTexture(texturepath);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			//drawTexturedQuadFit(360, 5, 16, 16, 0);
			drawTexturedModalRect(360, 5, 0, 0, 16, 16);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);
			GL11.glPopMatrix();

			mc.fontRendererObj.drawString("" + props.getCurrentBolt(), 380, 8,
					87 * 233 * 255);
		}

		ItemStack itemInHand = this.mc.player.inventory.getCurrentItem();
		if (itemInHand != null && itemInHand.getItem() instanceof ItemRcWeap) {
			ItemRcWeap weap = (ItemRcWeap) itemInHand.getItem();
			if (weap.isUsingAmmo()) {
				if (weap.hasAmmoImage) {
					GL11.glPushMatrix();
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glDisable(GL11.GL_LIGHTING);
					this.mc.getTextureManager().bindTexture(
							new ResourceLocation("rcmod", weap
									.getAmmoImageTexturePath()));
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glDisable(GL11.GL_DEPTH_TEST);
					GL11.glDepthMask(false);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA,
							GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
					GL11.glDisable(GL11.GL_ALPHA_TEST);
					drawTexturedModalRect(10, 5, 0, 0, 16, 16);
					GL11.glEnable(GL11.GL_DEPTH_TEST);
					GL11.glDepthMask(true);
					GL11.glPopMatrix();
				}
				mc.fontRendererObj
						.drawString(
								(itemInHand.getMaxDamage() - itemInHand
										.getItemDamage())
										+ "/"
										+ itemInHand.getMaxDamage(), 30, 8,
								87 * 233 * 255);

			}

			if (weap.hasCrosshair) {
				GL11.glPushMatrix();
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				this.mc.getTextureManager().bindTexture(
						new ResourceLocation("rcmod", weap
								.getCrosshairImagePath()));
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(false);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				ScaledResolution sr = new ScaledResolution(
						this.mc);
				drawTexturedModalRect((sr.getScaledWidth() / 2) - 16,
				(sr.getScaledHeight() / 2) - 16, 0, 0, 32, 32);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(true);
				GL11.glPopMatrix();

			}
		}
		
		this.mc.getTextureManager().bindTexture(ICONS);
	}
}
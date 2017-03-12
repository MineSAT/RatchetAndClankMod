package com.gugu42.rcmod.client.gui;

import java.util.ArrayList;

import com.gugu42.rcmod.shipsys.ShipSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;

public class GuiShipDestList extends GuiSlot {

	protected Minecraft       mc;

	protected int             slotHeight;

	private ArrayList<String> strings = new ArrayList<String>();

	private GuiShip           gui;

	public GuiShipDestList(GuiShip gui, EntityPlayer player) {
		super(gui.mc, gui.width, gui.height, 32, gui.height, 24);
		this.slotHeight = 24;
		this.mc = gui.mc;
		this.gui = gui;

		strings = ShipSystem.getWaypointsName(player);

	}

	/**
	 * Gets the size of the current slot list.
	 */
	protected int getSize() {
		return this.strings.size();
	}

	/**
	 * the element in the slot that was clicked, boolean for wether it was
	 * double clicked or not
	 */
	protected void elementClicked(int index, boolean twice, int var3, int var4) {
		this.gui.setString((String) strings.get(index));
		this.mc.displayGuiScreen(gui);
		this.gui.setTimer(30);
	}

	/**
	 * returns true if the element passed in is currently selected
	 */
	protected boolean isSelected(int par1) {
		return false;
	}

	/**
	 * return the height of the content being scrolled
	 */
	protected int getContentHeight() {
		return this.getSize() * 24;
	}

	@Override
	protected void drawSlot(int entryID, int insideLeft, int yPos, int insideSlotHeight, int mouseXIn, int mouseYIn) {
		this.gui.mc.fontRendererObj.setBidiFlag(true);
		this.gui.drawCenteredString(this.gui.mc.fontRendererObj, (String) strings.get(entryID), this.gui.width / 2, yPos + 1, 16777215);
	}

	@Override
	protected void drawBackground() {
		this.gui.drawDefaultBackground();
	}
}

package com.gugu42.rcmod.client.render.tesr;

import org.lwjgl.opengl.GL11;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.tileentity.TileEntityVendor;
import com.gugu42.rcmod.utils.glutils.mc.TessellatorModel;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRVendor extends TileEntitySpecialRenderer<TileEntityVendor> {

	/*
	 * MODEL INFO :
	 * Groups : 
	 * 		- Object0 = The blue body
	 * 		- Object1 = Antennas, blue dome
	 * 		- Object2 = Interior metal cube
	 * 		- Object3 = Gadgetron text
	 * 		- Object4 = Gadgetron "G"
	 */
	private TessellatorModel model;
	private long timeAlive;
	
	public TESRVendor()
	{
		this.timeAlive = 0;
		model = new TessellatorModel("/assets/" + RcMod.MODID + "/models/block/vendor.obj");
	}
	
	@Override
    public void renderTileEntityAt(TileEntityVendor te, double x, double y, double z, float partialTicks, int destroyStage) {
		this.timeAlive++;
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.disableRescaleNormal();
        
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.translate(0.5, 0, 0.5);
        GlStateManager.scale(0.0456, 0.0456, 0.0456);

        //The main body
        model.renderGroupsImpl("Object2");
        model.renderGroupsImpl("Object1");
        model.renderGroupsImpl("Object0");
        
        GlStateManager.rotate(timeAlive % 360, 0, 1, 0);
        model.renderGroupsImpl("Object4");
        GlStateManager.rotate(-2*timeAlive % 360, 0, 1, 0);
        model.renderGroupsImpl("Object3");
        

        
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();

    }
	
}

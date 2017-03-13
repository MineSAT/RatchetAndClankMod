package com.gugu42.rcmod.client.render.projectiles;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.utils.glutils.mc.TessellatorModel;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBomb extends Render<EntityBombGloveAmmo>{

	private TessellatorModel model;
	
	public RenderBomb(RenderManager renderManager) {
		super(renderManager);
		model = new TessellatorModel(new ResourceLocation(RcMod.MODID, "models/entities/bomb/bomb.obj"));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBombGloveAmmo entity) {
		return null;
	}

    public void doRender(EntityBombGloveAmmo entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	GlStateManager.pushAttrib();
    	GlStateManager.pushMatrix();
    	GlStateManager.translate(x, y + 0.1, z);
    	GlStateManager.scale(0.05, 0.05, 0.05);
    	model.render();
    	
    	GlStateManager.popMatrix();
    	GlStateManager.popAttrib();
    }
	
}

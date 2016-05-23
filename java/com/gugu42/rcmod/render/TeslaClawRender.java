package com.gugu42.rcmod.render;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import com.gugu42.rcmod.utils.CasteljauAlgorithm;
import com.gugu42.rcmod.utils.Vector3;
import com.gugu42.rcmod.utils.glutils.TessellatorModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class TeslaClawRender implements IItemRenderer {

	private TessellatorModel             model1;

	public static final ResourceLocation texture = new ResourceLocation("rcmod", "textures/items/bolt.png");

	public TeslaClawRender() {
		model1 = new TessellatorModel("/assets/rcmod/models/TeslaClaw.obj");
		model1.regenerateNormals();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
		case EQUIPPED:
			return true;
		case EQUIPPED_FIRST_PERSON:
			return true;
		case ENTITY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Tessellator tess = Tessellator.instance;

		switch (type) {
		case EQUIPPED: {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.8f, 0.2f, 0.8f);
			GL11.glRotatef(135, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(-65, 0.0f, 0.0f, 1.0f);
			// GL11.glRotatef(-45, 1.0f, 0.0f, 0.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			model1.render();

			GL11.glTranslatef(12, 4, 0);
			glRotated(180, 1, 0, 0);
			//drawLightningBolt((Entity)data[1]);
			GL11.glPopMatrix();

			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			tess.startDrawingQuads();
			if (data[1] instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)data[1];
				List<Entity> entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(player.posX, player.posY, player.posZ, player.posX + 1, player.posY + 1, player.posZ + 1).expand(16, 16, 16));
				for(Entity e : entities){
					if(e instanceof EntityLiving){
						EntityLiving ent = (EntityLiving)e;
						drawLightningBolt((Entity) data[1], ent);
					}
				}
			}
			tess.draw();

			break;
		}
		case EQUIPPED_FIRST_PERSON: {
			GL11.glPushMatrix();
			GL11.glTranslatef(0.9f, 0.5f, 0.9f);
			GL11.glRotatef(40, 0.0f, 1.0f, 0.0f);
			// GL11.glRotatef(-10, 0.0f, 0.0f, 1.0f);
			GL11.glScalef(0.09f, 0.09f, 0.09f);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			model1.render();
			GL11.glPopMatrix();

			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			tess.startDrawingQuads();
			if (data[1] instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)data[1];
				List<Entity> entities = player.worldObj.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(player.posX, player.posY, player.posZ, player.posX + 1, player.posY + 1, player.posZ + 1).expand(16, 16, 16));
				for(Entity e : entities){
					if(e instanceof EntityLiving){
						EntityLiving ent = (EntityLiving)e;
						drawLightningBolt((Entity) data[1], ent);
					}
				}
			}
			tess.draw();

			break;
		}
		case ENTITY: {
			GL11.glPushMatrix();
			GL11.glScalef(0.04f, 0.04f, 0.04f);
			GL11.glTranslatef(0f, 0f, 10.0f);
			GL11.glScalef(0.6f, 0.6f, 0.6f);
			GL11.glRotatef(90, 0.0f, 1.0f, 0.0f);
			GL11.glShadeModel(GL11.GL_SMOOTH);
			model1.render();
			GL11.glPopMatrix();
			break;
		}
		default:
			break;
		}

	}

	/*private void drawLightningBolt(Entity entity)
	{
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_LIGHTING);
		
		glColor3f(0, 0.6f, 1);
		glBegin(GL_QUADS);
		int lastX = 0;
		int lastY = 0;
		for(int i = 0;i<60;i++)
		{
			int x = i*2;
			int y = (int)(Math.sin(i*(Math.PI/4) - entity.ticksExisted*2.05f)*2.5f);
			
			glColor3f(1, 1f, 1);
			glVertex3d(x+0, y+0, 0);
			glVertex3d(x+0, y+1, 0);
			glVertex3d(lastX, lastY+0.75, 0);
			glVertex3d(lastX, lastY, 0);
			
			glColor3f(0, 0.3f, 1);
			glVertex3d(x+0, y+1, 0);
			glVertex3d(x+0, y+1.25, 0);			
			glVertex3d(lastX, lastY+1.25, 0);
			glVertex3d(lastX, lastY+1, 0);
			
			glVertex3d(x+0, y-0.25, 0);
			glVertex3d(x+0, y, 0);			
			glVertex3d(lastX, lastY, 0);
			glVertex3d(lastX, lastY-0.25, 0);
			lastX = x;
			lastY = y;
		}
		glEnd();
		glEnable(GL_LIGHTING);
		glEnable(GL_TEXTURE_2D);
	}*/

	private void drawLightningBolt(Entity ent, Entity target) {
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_LIGHTING);

		glColor3f(0, 0.6f, 1);
		glBegin(GL_QUADS);

		ArrayList<Vector3> curvePoints = new ArrayList<Vector3>();
		generateCurvePoints(curvePoints, ent, target);

		double xV[] = new double[curvePoints.size()];
		double yV[] = new double[curvePoints.size()];
		double zV[] = new double[curvePoints.size()];

		int i = 0;

		for (Vector3 vec : curvePoints) {
			xV[i] = vec.x;
			yV[i] = vec.y;
			zV[i] = vec.z;

			i++;
		}

		CasteljauAlgorithm algo = new CasteljauAlgorithm(xV, yV, zV, xV.length);
		double last[] = new double[] { xV[0], yV[0], zV[0] };
		double segments = 1d / 15;
		for (double t = 0; t <= 1; t += segments) {
			double[] values = algo.getXYZvalues(t);

			//tess.addVertexWithUV(values[0], values[1], values[2], minU, minV);
			//tess.addVertexWithUV(values[0], values[1]+0.1, values[2], maxU, minV);
			//tess.addVertexWithUV(last[0], last[1], last[2]+0.1, maxU, maxV);
			//tess.addVertexWithUV(last[0], last[1], last[2], minU, maxV);

			glColor3f(1, 1f, 1);
			glVertex3d(values[0], values[1], values[2]);
			glVertex3d(values[0], values[1] + 1, values[2]);
			glVertex3d(last[0], last[1] + 0.75, last[2]);
			glVertex3d(last[0], last[1], last[2]);

			last = values;
		}

		glEnd();
		glEnable(GL_LIGHTING);
		glEnable(GL_TEXTURE_2D);
	}

	private void generateCurvePoints(ArrayList<Vector3> points, Entity ent, Entity target) {
		points.add(new Vector3(0 + 0.0f, 0 + ent.getEyeHeight(), 0));
		points.add(new Vector3(0, 0 + 1f + ent.getEyeHeight(), 0));
		points.add(new Vector3(0 + 0.5f, 0 + 1f + ent.getEyeHeight(), 0));
		points.add(new Vector3(target.posX - ent.posX, target.posY - ent.posY, target.posZ - ent.posZ));
		
		
		
	}

}

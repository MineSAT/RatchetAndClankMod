package com.gugu42.rcmod.client.gui;

import static org.lwjgl.opengl.GL11.glColor4f;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.gugu42.rcmod.capabilities.suckcannon.ISuckCannon;
import com.gugu42.rcmod.capabilities.suckcannon.SuckCannonProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSuckCannon extends Gui
{

    private Minecraft mc;
    private HashMap<Integer, Entity> loadedEntities = new HashMap<Integer, Entity>();
    private HashMap<Integer, Float> rotations = new HashMap<Integer, Float>();

    public GuiSuckCannon(Minecraft mc)
    {
        super();
        this.mc = mc;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onTick(TickEvent event)
    {
        if(event.type == Type.WORLD && event.phase == Phase.END)
        {
            Collection<Entity> entities = loadedEntities.values();
            Object[] array = entities.toArray();
            for(int i = 0; i < entities.size(); i++)
            {
                if(i >= array.length)
                    break;
                try
                {
                    // ((Entity)array[i]).onUpdate();
                }
                catch(Exception e1)
                {
                    entities.remove(((Entity)array[i]));
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent event)
    {
        if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE)
        {
            return;
        }

        glColor4f(1, 1, 1, 1);
        EntityPlayer player = Minecraft.getMinecraft().player;
        if(player != null)
        {
            //ExtendedPropsSuckCannon props = ExtendedPropsSuckCannon.get(player);
            ISuckCannon props = player.getCapability(SuckCannonProvider.SUCK_CANNON_CAP, null);
            
            ArrayList<NBTTagCompound> list = props.getStackAsList();
            /*
            for(int i = 0; i <= 8 - list.size(); i++)
            {
                rotations.put(8 - i, 0f);
                loadedEntities.put(8 - i, null);
            }
            for(int index = 0; index < list.size(); index++)
            {
                float v = 0f;
                if(rotations.containsKey(index))
                    v = rotations.get(index);

                if(!loadedEntities.containsKey(index) || loadedEntities.get(index) == null)
                {
                    Entity e = EntityList.createEntityFromNBT(list.get(index), player.world);
                    loadedEntities.put(index, e);
                }
                rotations.put(index, v + 2.5f);

                if(loadedEntities.get(index) != null)
                {
                    Entity e = loadedEntities.get(index);
                    ScaledResolution res = new ScaledResolution(mc);
                    drawEntity(res.getScaledWidth() - 25 - (index % 2 == 0 ? 20 : 0), res.getScaledHeight() - 5 - (index / 2 * 35), 15, rotations.get(index), -10, (EntityLivingBase)e);
                }
            }
            */
        }
        this.mc.getTextureManager().bindTexture(ICONS);
    }

    @SideOnly(Side.CLIENT)
    /**
     * From GuiInventory
     */
    public static void drawEntity(int x, int y, int scale, float yaw, float pitch, EntityLivingBase entity)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, 50.0F);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = entity.renderYawOffset;
        float f3 = entity.rotationYaw;
        float f4 = entity.rotationPitch;
        float f5 = entity.prevRotationYawHead;
        float f6 = entity.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan((double)(pitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        entity.renderYawOffset = yaw;
        entity.rotationYaw = yaw;
        entity.rotationPitch = -((float)Math.atan((double)(pitch / 40.0F))) * 20.0F;
        entity.rotationYawHead = entity.rotationYaw;
        entity.prevRotationYawHead = entity.rotationYaw;
        GL11.glTranslated(0.0F, entity.getYOffset(), 0.0F);
        Minecraft.getMinecraft().getRenderManager().playerViewY = 180.0F;
        Minecraft.getMinecraft().getRenderManager().doRenderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, true);
        entity.renderYawOffset = f2;
        entity.rotationYaw = f3;
        entity.rotationPitch = f4;
        entity.prevRotationYawHead = f5;
        entity.rotationYawHead = f6;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

        glColor4f(1, 1, 1, 1);
    }
}

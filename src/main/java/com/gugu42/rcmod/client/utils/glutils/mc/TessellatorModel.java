package com.gugu42.rcmod.client.utils.glutils.mc;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;

import java.util.*;

import com.google.common.collect.ImmutableList;
import com.gugu42.rcmod.client.utils.glutils.*;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.BusBuilder;
import net.minecraftforge.eventbus.api.Event;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

/**
 * @author jglrxavpok
 */
public class TessellatorModel extends ObjModel
{

    public static final EventBus MODEL_RENDERING_BUS = new EventBus(BusBuilder.builder());
    public static final VertexFormat POSITION_TEX_NORMAL = new VertexFormat(ImmutableList.<VertexFormatElement>builder().add(POSITION_3F).add(TEX_2F).add(NORMAL_3B).add(PADDING_1B).build());
    private Map<ObjObject, RenderType> materialMap;

    public TessellatorModel(String string)
    {
        super(string);
        try
        {
            String content = new String(read(Model.class.getResourceAsStream(string)), "UTF-8");
            String startPath = string.substring(0, string.lastIndexOf('/') + 1);
            HashMap<ObjObject, IndexedModel> map = new OBJLoader().loadModel(startPath, content);
            objObjects.clear();
            Set<ObjObject> keys = map.keySet();
            Iterator<ObjObject> it = keys.iterator();
            while(it.hasNext())
            {
                ObjObject object = it.next();
                Mesh mesh = new Mesh();
                object.mesh = mesh;
                objObjects.add(object);
                map.get(object).toMesh(mesh);
            }

            materialMap = new HashMap<>();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public TessellatorModel(ResourceLocation loc)
    {
    	this("/assets/" + loc.getNamespace() + "/" + loc.getPath());
    	System.out.println("/assets/" + loc.getNamespace() + "/" + loc.getPath());
    }

    // Hackfix needs cleaning up
    public void render(MatrixStack.Entry msEntry, IVertexBuilder builder, int combinedLight) {
        Collections.sort(objObjects, new Comparator<ObjObject>()
        {

            @Override
            public int compare(ObjObject a, ObjObject b)
            {
                Vector3d v = Minecraft.getInstance().getRenderViewEntity().getPositionVec();
                double aDist = v.distanceTo(new Vector3d(a.center.getX(), a.center.getY(), a.center.getZ()));
                double bDist = v.distanceTo(new Vector3d(b.center.getX(), b.center.getY(), b.center.getZ()));
                return Double.compare(aDist, bDist);
            }
        });
        for(ObjObject object : objObjects)
        {
            renderGroupImpl(object, msEntry, builder, combinedLight);
        }
    }

    @Override
    public void renderImpl(MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight)
    {
        Collections.sort(objObjects, new Comparator<ObjObject>()
        {

            @Override
            public int compare(ObjObject a, ObjObject b)
            {
                Vector3d v = Minecraft.getInstance().getRenderViewEntity().getPositionVec();
                double aDist = v.distanceTo(new Vector3d(a.center.getX(), a.center.getY(), a.center.getZ()));
                double bDist = v.distanceTo(new Vector3d(b.center.getX(), b.center.getY(), b.center.getZ()));
                return Double.compare(aDist, bDist);
            }
        });
        for(ObjObject object : objObjects)
        {
            renderGroup(object, msEntry, buffer, combinedLight);
        }
    }

    @Override
    public void renderGroupsImpl(String group, MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight)
    {
        for(ObjObject object : objObjects)
        {
            if(object.getName().equals(group))
            {
                renderGroup(object, msEntry, buffer, combinedLight);
            }
        }
    }

    @Override
    public void renderGroupImpl(ObjObject obj, MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight)
    {
        if(obj.mesh == null)
            return;

        IVertexBuilder vertexBuilder = buffer.getBuffer(getRenderTypeForObject(obj));

        int[] indices = obj.mesh.indices;
        Vertex[] vertices = obj.mesh.vertices;

        for(int i = 0; i < indices.length; i += 3)
        {
            int i0 = indices[i];
            int i1 = indices[i + 1];
            int i2 = indices[i + 2];
            Vertex v0 = vertices[i0];
            Vertex v1 = vertices[i1];
            Vertex v2 = vertices[i2];

            vertexBuilder
                    .pos(msEntry.getMatrix(), v0.getPos().getX(), v0.getPos().getY(), v0.getPos().getZ())
                    .color(255, 255, 255, 255)
                    .tex(v0.getTexCoords().x, 1f - v0.getTexCoords().y)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .lightmap(combinedLight)
                    .normal(msEntry.getNormal(), v0.getNormal().getX(), v0.getNormal().getY(), v0.getNormal().getZ())
                    .endVertex();
            vertexBuilder
                    .pos(msEntry.getMatrix(), v1.getPos().getX(), v1.getPos().getY(), v1.getPos().getZ())
                    .color(255, 255, 255, 255)
                    .tex(v1.getTexCoords().x, 1f - v1.getTexCoords().y)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .lightmap(combinedLight)
                    .normal(msEntry.getNormal(), v1.getNormal().getX(), v1.getNormal().getY(), v1.getNormal().getZ())
                    .endVertex();
            vertexBuilder
                    .pos(msEntry.getMatrix(), v2.getPos().getX(), v2.getPos().getY(), v2.getPos().getZ())
                    .color(255, 255, 255, 255)
                    .tex(v2.getTexCoords().x, 1f - v2.getTexCoords().y)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .lightmap(combinedLight)
                    .normal(msEntry.getNormal(), v2.getNormal().getX(), v2.getNormal().getY(), v2.getNormal().getZ())
                    .endVertex();
            vertexBuilder
                    .pos(msEntry.getMatrix(), v2.getPos().getX(), v2.getPos().getY(), v2.getPos().getZ())
                    .color(255, 255, 255, 255)
                    .tex(v2.getTexCoords().x, 1f - v2.getTexCoords().y)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .lightmap(combinedLight)
                    .normal(msEntry.getNormal(), v2.getNormal().getX(), v2.getNormal().getY(), v2.getNormal().getZ())
                    .endVertex();
        }
    }

    // Hackfix needs cleaning up
    public void renderGroupImpl(ObjObject obj, MatrixStack.Entry msEntry, IVertexBuilder vertexBuilder, int combinedLight)
    {
        if(obj.mesh == null)
            return;

        int[] indices = obj.mesh.indices;
        Vertex[] vertices = obj.mesh.vertices;

        for(int i = 0; i < indices.length; i += 3)
        {
            int i0 = indices[i];
            int i1 = indices[i + 1];
            int i2 = indices[i + 2];
            Vertex v0 = vertices[i0];
            Vertex v1 = vertices[i1];
            Vertex v2 = vertices[i2];

            vertexBuilder
                    .pos(msEntry.getMatrix(), v0.getPos().getX(), v0.getPos().getY(), v0.getPos().getZ())
                    .color(255, 255, 255, 255)
                    .tex(v0.getTexCoords().x, 1f - v0.getTexCoords().y)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .lightmap(combinedLight)
                    .normal(msEntry.getNormal(), v0.getNormal().getX(), v0.getNormal().getY(), v0.getNormal().getZ())
                    .endVertex();
            vertexBuilder
                    .pos(msEntry.getMatrix(), v1.getPos().getX(), v1.getPos().getY(), v1.getPos().getZ())
                    .color(255, 255, 255, 255)
                    .tex(v1.getTexCoords().x, 1f - v1.getTexCoords().y)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .lightmap(combinedLight)
                    .normal(msEntry.getNormal(), v1.getNormal().getX(), v1.getNormal().getY(), v1.getNormal().getZ())
                    .endVertex();
            vertexBuilder
                    .pos(msEntry.getMatrix(), v2.getPos().getX(), v2.getPos().getY(), v2.getPos().getZ())
                    .color(255, 255, 255, 255)
                    .tex(v2.getTexCoords().x, 1f - v2.getTexCoords().y)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .lightmap(combinedLight)
                    .normal(msEntry.getNormal(), v2.getNormal().getX(), v2.getNormal().getY(), v2.getNormal().getZ())
                    .endVertex();
            vertexBuilder
                    .pos(msEntry.getMatrix(), v2.getPos().getX(), v2.getPos().getY(), v2.getPos().getZ())
                    .color(255, 255, 255, 255)
                    .tex(v2.getTexCoords().x, 1f - v2.getTexCoords().y)
                    .overlay(OverlayTexture.NO_OVERLAY)
                    .lightmap(combinedLight)
                    .normal(msEntry.getNormal(), v2.getNormal().getX(), v2.getNormal().getY(), v2.getNormal().getZ())
                    .endVertex();
        }
    }

    @Override
    public boolean fireEvent(ObjEvent event)
    {
        Event evt = null;
        if(event.type == ObjEvent.EventType.PRE_RENDER_GROUP)
        {
            evt = new TessellatorModelEvent.RenderGroupEvent.Pre(((ObjObject) event.data[1]).getName(), this);
        }
        else if(event.type == ObjEvent.EventType.POST_RENDER_GROUP)
        {
            evt = new TessellatorModelEvent.RenderGroupEvent.Post(((ObjObject) event.data[1]).getName(), this);
        }
        else if(event.type == ObjEvent.EventType.PRE_RENDER_ALL)
        {
            evt = new TessellatorModelEvent.RenderPre(this);
        }
        else if(event.type == ObjEvent.EventType.POST_RENDER_ALL)
        {
            evt = new TessellatorModelEvent.RenderPost(this);
        }
        if(evt != null)
            return !MODEL_RENDERING_BUS.post(evt);
        return true;
    }

    public RenderType getRenderTypeForObject(ObjObject objObject) {
        if (!materialMap.containsKey(objObject)) {
            materialMap.put(objObject, RenderType.getEntityTranslucent(objObject.material.diffuseTextureResloc));
        }

        return materialMap.get(objObject);
    }

    @Deprecated
    public void regenerateNormals()
    {

    }
}

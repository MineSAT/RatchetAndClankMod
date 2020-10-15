package com.gugu42.rcmod.client.utils.glutils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.math.vector.Matrix4f;
import org.apache.commons.io.output.ByteArrayOutputStream;

import com.gugu42.rcmod.client.utils.glutils.ObjEvent.EventType;

public abstract class ObjModel extends Model
{

    public List<ObjObject> objObjects;

    protected String filename;
    
    ObjModel()
    {
        objObjects = new ArrayList<ObjObject>();
    }
    
    public ObjModel(String classpathElem)
    {
        this();
        this.filename = classpathElem;
        if(filename.contains("/"))
            setID(filename.substring(filename.lastIndexOf("/")+1));
        else
            setID(filename);
    }
    
    protected byte[] read(InputStream resource) throws IOException
    {
        int i;
        byte[] buffer = new byte[65565];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while((i = resource.read(buffer, 0, buffer.length)) != -1)
        {
            out.write(buffer,0,i);
        }
        out.flush();
        out.close();
        return out.toByteArray();
    }

    public void renderGroup(ObjObject group, MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight)
    {
        if(fireEvent(new ObjEvent(this, EventType.PRE_RENDER_GROUP).setData(group, group)))
            this.renderGroupImpl(group, msEntry, buffer, combinedLight);
        fireEvent(new ObjEvent(this, EventType.POST_RENDER_GROUP).setData(group, group));
    }
    
    public void renderGroups(String groupsName, MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight)
    {
        if(fireEvent(new ObjEvent(this, EventType.PRE_RENDER_GROUPS).setData(groupsName)))
            this.renderGroupsImpl(groupsName, msEntry, buffer, combinedLight);
        fireEvent(new ObjEvent(this, EventType.POST_RENDER_GROUPS).setData(groupsName));
    }
    
    public void render(MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight)
    {
        if(fireEvent(new ObjEvent(this, EventType.PRE_RENDER_ALL)))
            this.renderImpl(msEntry, buffer, combinedLight);
        fireEvent(new ObjEvent(this, EventType.POST_RENDER_ALL));
    }
    
    protected abstract void renderGroupsImpl(String groupsName, MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight);
    
    protected abstract void renderGroupImpl(ObjObject objGroup, MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight);

    protected abstract void renderImpl(MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight);
    
    public abstract boolean fireEvent(ObjEvent event);
}

package com.gugu42.rcmod.client.utils.glutils;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.math.vector.Matrix4f;

public abstract class Model
{

    private String id;

    public abstract void render(MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight);
    public abstract void render(MatrixStack.Entry msEntry, IVertexBuilder builder, int combinedLight);
    
    public abstract void renderGroups(String s, MatrixStack.Entry msEntry, IRenderTypeBuffer buffer, int combinedLight);
    
    public void setID(String id)
    {
        this.id = id;
    }
    
    public String getID()
    {
        return id;
    }
}

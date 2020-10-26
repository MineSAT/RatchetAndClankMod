package com.gugu42.rcmod.client.render.projectiles;

import com.gugu42.rcmod.common.entities.projectiles.HarbingerLaserEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;

import java.util.Random;

public class HarbingerLaserRenderer extends EntityRenderer<HarbingerLaserEntity> {
    public HarbingerLaserRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    public void render(HarbingerLaserEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        Random random = new Random(0);

        for (int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float) (random.nextInt(11) - 5);
            f1 += (float) (random.nextInt(11) - 5);
        }

        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getLightning());
        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
        renderRay(matrix4f, ivertexbuilder, 0, 1, 0,10, 12, 0.45F, 0.45F, 0.5F, 0.17f, 0.16f, false, false, true, false);
        renderRay(matrix4f, ivertexbuilder, -0.1f, 1.1f, 0,10, 12, 0.45F, 0.45F, 0.5F, 0.17f, 0.16f, false, false, true, false);
//          func_229116_a_(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, 0.45F, 0.45F, 0.5F, f10, f11, false, false, true, false);


    }

    private static void renderRay(Matrix4f matrix4f, IVertexBuilder vertexBuilder, float xMin, float zMin, int yMin, float xMax, float zMax, float colorR, float colorG, float colorB, float p_229116_10_, float p_229116_11_, boolean p_229116_12_, boolean p_229116_13_, boolean p_229116_14_, boolean p_229116_15_) {
        int a = 100;
        vertexBuilder.pos(matrix4f, xMin + (p_229116_12_ ? p_229116_11_ : -p_229116_11_), (float) (yMin * a), zMin + (p_229116_13_ ? p_229116_11_ : -p_229116_11_)).color(colorR, colorG, colorB, 0.3F).endVertex();
        vertexBuilder.pos(matrix4f, xMax + (p_229116_12_ ? p_229116_10_ : -p_229116_10_), (float) ((yMin + 1) * a), zMax + (p_229116_13_ ? p_229116_10_ : -p_229116_10_)).color(colorR, colorG, colorB, 0.3F).endVertex();
        vertexBuilder.pos(matrix4f, xMax + (p_229116_14_ ? p_229116_10_ : -p_229116_10_), (float) ((yMin + 1) * a), zMax + (p_229116_15_ ? p_229116_10_ : -p_229116_10_)).color(colorR, colorG, colorB, 0.3F).endVertex();
        vertexBuilder.pos(matrix4f, xMin + (p_229116_14_ ? p_229116_11_ : -p_229116_11_), (float) (yMin * a), zMin + (p_229116_15_ ? p_229116_11_ : -p_229116_11_)).color(colorR, colorG, colorB, 0.3F).endVertex();
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getEntityTexture(HarbingerLaserEntity entity) {
        return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
    }
}

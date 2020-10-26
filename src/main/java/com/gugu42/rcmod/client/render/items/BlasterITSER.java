package com.gugu42.rcmod.client.render.items;

import com.gugu42.rcmod.client.render.IRcITSER;
import com.gugu42.rcmod.client.utils.glutils.mc.TessellatorModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class BlasterITSER extends ItemStackTileEntityRenderer implements IRcITSER {
    private TessellatorModel model = new TessellatorModel(new ResourceLocation("rcmod:models/item/blaster/blaster.obj"));
    private static final ResourceLocation ICON = new ResourceLocation("rcmod:textures/item/blaster.png");

    public BlasterITSER() {
    }

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
//        if (p_239207_2_ != ItemCameraTransforms.TransformType.GUI) {
            matrixStack.push();
            matrixStack.translate(getTranslation(p_239207_2_).getX(), getTranslation(p_239207_2_).getY(), getTranslation(p_239207_2_).getZ());
            matrixStack.rotate(Vector3f.YP.rotationDegrees(getRotation(p_239207_2_).getY()));
            matrixStack.scale(0.03f, 0.03f, 0.03f);
            model.renderGroups("Mesh0", matrixStack.getLast(), buffer, combinedLight);
            matrixStack.pop();
//        } else {
//            Minecraft.getInstance().getItemRenderer().renderModel(
//                    Minecraft.getInstance().getModelManager().getModel(new ResourceLocation("rcmod:item/blaster_icon")),
//                    stack,
//                    combinedLight,
//                    combinedOverlay,
//                    matrixStack,
//                    buffer.getBuffer(RenderType.getGlintDirect())
//            );
//        }
    }

    private void renderItemAsIcon(ItemStack stack, MatrixStack matrixStack)
    {
//        matrixStack.push();
//        Minecraft.getInstance().getTextureManager().bindTexture(ICON);
//        blit(matrixStack, 0, 0, 16, 16, 0, 0, 16, 16, 16, 16);
//        matrixStack.pop();


//        Minecraft.getInstance().getTextureManager().bindTexture(ICON);
//        GuiUtils.drawTexturedModalRect(matrixStack, 0, 0, 256, 256, 16, 16, 10.0f);
//        RenderType.State renderState = RenderType.State.getBuilder().texture(new RenderState.TextureState(ICON, false, false)).build(true);
//        RenderType renderType = RenderType.makeType("flat", DefaultVertexFormats.POSITION_TEX, 7, 256, renderState);
//        IVertexBuilder builder =
    }

    public static void blit(MatrixStack matrixStack, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        innerBlit(matrixStack.getLast().getMatrix(), x, x + width, y, y + height, 0, (uOffset + 0.0F) / (float)textureWidth, (uOffset + (float)uWidth) / (float)textureWidth, (vOffset + 0.0F) / (float)textureHeight, (vOffset + (float)vHeight) / (float)textureHeight);
    }

    private static void innerBlit(Matrix4f matrix, int x1, int x2, int y1, int y2, int blitOffset, float minU, float maxU, float minV, float maxV) {
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(matrix, (float)x1, (float)y2, (float)blitOffset).tex(minU, maxV).endVertex();
        bufferbuilder.pos(matrix, (float)x2, (float)y2, (float)blitOffset).tex(maxU, maxV).endVertex();
        bufferbuilder.pos(matrix, (float)x2, (float)y1, (float)blitOffset).tex(maxU, minV).endVertex();
        bufferbuilder.pos(matrix, (float)x1, (float)y1, (float)blitOffset).tex(minU, minV).endVertex();
//        bufferbuilder.finishDrawing();
////        WorldVertexBufferUploader.draw(bufferbuilder);
        Tessellator.getInstance().draw();
    }

    @Override
    public Vector3f getScale(ItemCameraTransforms.TransformType transformType) {
        return new Vector3f(1.0f, 1.0f, 1.0f);
    }

    @Override
    public Vector3f getRotation(ItemCameraTransforms.TransformType transformType) {
        return new Vector3f(0.0f, 180.0f, 0.0f);
    }

    @Override
    public Vector3f getTranslation(ItemCameraTransforms.TransformType transformType) {
        return new Vector3f(0.5f, 0.4f, 0.8f);
    }
}

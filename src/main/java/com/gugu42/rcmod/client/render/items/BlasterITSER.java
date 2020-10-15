package com.gugu42.rcmod.client.render.items;

import com.gugu42.rcmod.client.render.IRcITSER;
import com.gugu42.rcmod.client.utils.glutils.mc.TessellatorModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class BlasterITSER extends ItemStackTileEntityRenderer implements IRcITSER {
    private TessellatorModel model = new TessellatorModel(new ResourceLocation("rcmod:models/item/blaster/blaster.obj"));

    public BlasterITSER() {
    }

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
//        matrixStack.translate(0.5f, 0.0f, 0.5f);
        matrixStack.translate(getTranslation(p_239207_2_).getX(), getTranslation(p_239207_2_).getY(), getTranslation(p_239207_2_).getZ());
        matrixStack.rotate(Vector3f.YP.rotationDegrees(getRotation(p_239207_2_).getY()));
        matrixStack.scale(0.03f, 0.03f, 0.03f);
        model.renderGroups("Mesh0", matrixStack.getLast(), buffer, combinedLight);
        matrixStack.pop();
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

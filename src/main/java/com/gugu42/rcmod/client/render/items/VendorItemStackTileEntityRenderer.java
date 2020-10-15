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

public class VendorItemStackTileEntityRenderer extends ItemStackTileEntityRenderer implements IRcITSER {
    private TessellatorModel model = new TessellatorModel(new ResourceLocation("rcmod:models/tileentity/vendortest/vendor.obj"));

    public VendorItemStackTileEntityRenderer() {
    }

    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        matrixStack.push();
        matrixStack.translate(getTranslation(p_239207_2_).getX(), getTranslation(p_239207_2_).getY(), getTranslation(p_239207_2_).getZ());
        matrixStack.scale(getScale(p_239207_2_).getX(), getScale(p_239207_2_).getY(), getScale(p_239207_2_).getZ());
        model.renderGroups("Object2", matrixStack.getLast(), buffer, combinedLight);
        model.renderGroups("Object1", matrixStack.getLast(), buffer, combinedLight);
        model.renderGroups("Object0", matrixStack.getLast(), buffer, combinedLight);
        matrixStack.pop();
    }

    @Override
    public Vector3f getScale(ItemCameraTransforms.TransformType transformType) {
        switch (transformType) {
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
            case NONE:
            case HEAD:
                return new Vector3f(0.015f, 0.015f, 0.015f);
            case GROUND:
                return new Vector3f(0.013f, 0.013f, 0.013f);
            case FIXED:
                return new Vector3f(0.022f, 0.022f, 0.022f);
            case GUI:
            case FIRST_PERSON_RIGHT_HAND:
            case FIRST_PERSON_LEFT_HAND:
            default:
                return new Vector3f(0.03f, 0.03f, 0.03f);
        }
    }

    @Override
    public Vector3f getRotation(ItemCameraTransforms.TransformType transformType) {
        switch (transformType) {
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
            case NONE:
            case HEAD:
                return new Vector3f(0.015f, 0.015f, 0.015f);
            case GROUND:
                return new Vector3f(0.013f, 0.013f, 0.013f);
            case FIXED:
                return new Vector3f(0.022f, 0.022f, 0.022f);
            case GUI:
            case FIRST_PERSON_RIGHT_HAND:
            case FIRST_PERSON_LEFT_HAND:
            default:
                return new Vector3f(0.0f, 0.0f, 0.0f);
        }
    }

    @Override
    public Vector3f getTranslation(ItemCameraTransforms.TransformType transformType) {
        switch (transformType) {
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
                return new Vector3f(0.5f, 0.4f, 0.5f);
            case FIXED:
            case NONE:
            case HEAD:
            case GUI:
            case FIRST_PERSON_RIGHT_HAND:
            case FIRST_PERSON_LEFT_HAND:
            case GROUND:
            default:
                return new Vector3f(0.5f, 0.25f, 0.5f);
        }
    }
}

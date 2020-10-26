package com.gugu42.rcmod.client.model;

import com.gugu42.rcmod.common.capabilities.IRcModCapability;
import com.gugu42.rcmod.common.capabilities.RcModCapability;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Optional;

public class HelipackModel extends BipedModel {

    private LivingEntity wearingEntity;

    public HelipackModel(float modelSize, LivingEntity entity) {
        super(modelSize);
        this.wearingEntity = entity;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        Optional<IRcModCapability> capa = wearingEntity.getCapability(RcModCapability.RCMOD_CAP).resolve();
        if (capa.isPresent()) {
            IRcModCapability capability = capa.get();
            if (!wearingEntity.isOnGround() && wearingEntity.getMotion().getY() < 0.0f && capability.isUsingHelipack())
                renderHelipackMode(matrixStackIn, packedLightIn);
            else
                renderBackpackMode(matrixStackIn, packedLightIn);
        } else {
            renderBackpackMode(matrixStackIn, packedLightIn);
        }
    }

    public void renderHelipackMode(MatrixStack matrixStack, int packedLight) {
        float animationDegrees = wearingEntity.ticksExisted*18 % 360;

        // Main body
        matrixStack.push();
        matrixStack.translate(0.0f, 0.7f, 0.11f);
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));
        matrixStack.scale(0.045f, 0.045f, 0.045f);
        RcModels.HELIPACK_BODY.renderGroupsImpl("Mesh0", matrixStack.getLast(), Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(), packedLight);
        // Big "wing
        matrixStack.translate(0.0f, 35f, 3.5f);
        matrixStack.rotate(Vector3f.YP.rotationDegrees(animationDegrees));
        RcModels.HELIPACK_WING_BIG.renderGroupsImpl("Mesh1", matrixStack.getLast(), Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(), packedLight);
        matrixStack.pop();

        // Small "wing" 1
        matrixStack.push();
        matrixStack.translate(0.0f, 0.7f, 0.11f);
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));
        matrixStack.scale(0.045f, 0.045f, 0.045f);
        matrixStack.translate(9.0f, 8.5f, 8.0f);
        matrixStack.rotate(Vector3f.XP.rotationDegrees(80));
        matrixStack.rotate(Vector3f.ZN.rotationDegrees(35));
        matrixStack.rotate(Vector3f.YP.rotationDegrees(animationDegrees));
        RcModels.HELIPACK_WINGS_SMALL.renderGroupsImpl("BodyHeli", matrixStack.getLast(), Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(), packedLight);
        matrixStack.pop();

        // Small "wing" 2
        matrixStack.push();
        matrixStack.translate(0.0f, 0.7f, 0.11f);
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));
        matrixStack.scale(0.045f, 0.045f, 0.045f);
        matrixStack.translate(-9.0f, 8.5f, 8.0f);
        matrixStack.scale(-1.0f, -1.0f, -1.0f);
        matrixStack.rotate(Vector3f.XN.rotationDegrees(90));
        matrixStack.rotate(Vector3f.ZN.rotationDegrees(35));
        matrixStack.rotate(Vector3f.YP.rotationDegrees(animationDegrees));
        RcModels.HELIPACK_WINGS_SMALL.renderGroupsImpl("BodyHeli", matrixStack.getLast(), Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(), packedLight);
        matrixStack.pop();
    }

    public void renderBackpackMode(MatrixStack matrixStackIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0.0f, 0.7f, 0.11f);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(180));
        matrixStackIn.scale(0.045f, 0.045f, 0.045f);
        RcModels.CLANK_BACKPACK.renderGroupsImpl("Mesh0", matrixStackIn.getLast(), Minecraft.getInstance().getRenderTypeBuffers().getBufferSource(), packedLightIn);
        matrixStackIn.pop();
    }
}

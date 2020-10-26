package com.gugu42.rcmod.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class RcPlayerModel extends BipedModel {
    public RcPlayerModel(float modelSize) {
        super(modelSize);
    }

    @Override
    public void setRotationAngles(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.bipedLeftArm.rotateAngleX = 90;
    }
}

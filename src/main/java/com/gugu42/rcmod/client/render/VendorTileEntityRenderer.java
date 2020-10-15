package com.gugu42.rcmod.client.render;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.client.utils.glutils.mc.TessellatorModel;
import com.gugu42.rcmod.common.tileentities.VendorTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class VendorTileEntityRenderer extends TileEntityRenderer<VendorTileEntity> {
    private TessellatorModel model = new TessellatorModel(new ResourceLocation("rcmod:models/tileentity/vendortest/vendor.obj"));

    public VendorTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(VendorTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0.5f, 0.0f, 0.5f);
        matrixStackIn.scale(0.046f, 0.046f, 0.046f);
        model.renderGroups("Object2", matrixStackIn.getLast(), bufferIn, combinedLightIn);
        model.renderGroups("Object1", matrixStackIn.getLast(), bufferIn, combinedLightIn);
        model.renderGroups("Object0", matrixStackIn.getLast(), bufferIn, combinedLightIn);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(tileEntityIn.getTimeAlive() % 360));
        model.renderGroups("Object4", matrixStackIn.getLast(), bufferIn, combinedLightIn);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(tileEntityIn.getTimeAlive() % 360));
        model.renderGroups("Object3", matrixStackIn.getLast(), bufferIn, combinedLightIn);
        matrixStackIn.pop();
    }
}

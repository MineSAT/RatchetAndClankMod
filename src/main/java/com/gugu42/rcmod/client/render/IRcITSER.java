package com.gugu42.rcmod.client.render;

import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.math.vector.Vector3f;

public interface IRcITSER {
    Vector3f getScale(ItemCameraTransforms.TransformType transformType);
    Vector3f getRotation(ItemCameraTransforms.TransformType transformType);
    Vector3f getTranslation(ItemCameraTransforms.TransformType transformType);

}

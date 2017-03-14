package com.gugu42.rcmod.client.render;

import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;

public class Inventory2DItemModel implements IPerspectiveAwareModel {

	public final IBakedModel model2D, model3D;
	
	public Inventory2DItemModel(IBakedModel model2d, IBakedModel model3d) {
		super();
		this.model2D = model2d;
		this.model3D = model3d;
	}
	
	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType cameraTransformType) {
		
		Matrix4f transform = null;
		
		switch(cameraTransformType)
		{

		case GUI: 
			if(model2D instanceof IPerspectiveAwareModel){
				Pair<? extends IBakedModel, Matrix4f> result =((IPerspectiveAwareModel) model2D).handlePerspective(cameraTransformType);
				return result;
			}
			
			return Pair.of(this.model2D,transform);
		default: 
			if(model3D instanceof IPerspectiveAwareModel){
				Pair<? extends IBakedModel, Matrix4f> result =((IPerspectiveAwareModel) model3D).handlePerspective(cameraTransformType);
				return result;
			}
			
			return Pair.of(this.model3D,transform);
		}
	}

	@Override
	public boolean isAmbientOcclusion() {return false;}

	@Override
	public boolean isGui3d() {return false;}

	@Override
	public boolean isBuiltInRenderer() {return false;}

	@Override
	public TextureAtlasSprite getParticleTexture() {return null;}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {return ItemCameraTransforms.DEFAULT;}

	@Override
	public ItemOverrideList getOverrides() {return ItemOverrideList.NONE;}
	
	@Override
	public List<BakedQuad> getQuads(IBlockState state, EnumFacing side,long rand) {return null;}

}

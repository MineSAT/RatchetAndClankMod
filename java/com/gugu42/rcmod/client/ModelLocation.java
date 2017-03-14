package com.gugu42.rcmod.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

/**
 * 
 * This just is 2 model locations because I need to make it easier to register shit.
 *
 */
public class ModelLocation {

	public ModelResourceLocation location2D;
	public ModelResourceLocation location3D;
	
	public ModelLocation(ModelResourceLocation loc2D, ModelResourceLocation loc3D)
	{
		this.location2D = loc2D;
		this.location3D = loc3D;
	}
	
}

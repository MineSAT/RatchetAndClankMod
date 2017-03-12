package com.gugu42.rcmod.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityRenderers {

	//SOURCE OF THIS CODE : https://github.com/FireBall1725/Graves/blob/master/src/main/java/com/fireball1725/graves/client/render/entity/EntityRenderers.java
	//All credits to him for this class, which avoids having 15 billion factory classes
	
	public static <E extends Entity> void registerEntityRenderer(Class<E> entity, Class<? extends Render<E>> render)
	{
		RenderingRegistry.registerEntityRenderingHandler(entity, new EntityRenderFactory<E>(render));
	}
	
	private static class EntityRenderFactory<E extends Entity> implements IRenderFactory<E>
	{
		private Class<? extends Render<E>> renderClass;

		private EntityRenderFactory(Class<? extends Render<E>> renderClass)
		{
			this.renderClass = renderClass;
		}

		@Override
		public Render<E> createRenderFor(RenderManager manager)
		{
			Render<E> renderer = null;

			try
			{
				renderer = renderClass.getConstructor(RenderManager.class).newInstance(manager);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			return renderer;
		}
	}
}

package com.gugu42.rcmod.entity;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityBlasterAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityBombGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityDecoyGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityMineGloveAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityPyrocitorAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;
import com.gugu42.rcmod.entity.projectiles.EntitySuckCannonProj;
import com.gugu42.rcmod.entity.projectiles.EntitySwingShotHook;
import com.gugu42.rcmod.entity.projectiles.EntityVisibombAmmo;
import com.gugu42.rcmod.entity.projectiles.EntityWrenchThrown;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class RcEntities {

	public RcEntities() {

	}

	public static void initModEntities() {
		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "tntcrate"), EntityTNTCrate.class, "tntcrate", 50,
				RcMod.instance, 256, 1, false);
	}

	public static void initRc1Entities() {

		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "blasterammo"), EntityBlasterAmmo.class,
				"blasterammo", 52, RcMod.instance, 256, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "bombgloveammo"), EntityBombGloveAmmo.class,
				"bombgloveammo", 53, RcMod.instance, 256, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "rynoammo"), EntityRYNOAmmo.class, "rynoammo", 54,
				RcMod.instance, 256, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "pyrocitorammo"), EntityPyrocitorAmmo.class,
				"pyrocitorammo", 55, RcMod.instance, 256, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "visibombrocket"), EntityVisibombAmmo.class,
				"visibombrocket", 56, RcMod.instance, 256, 1, false);


		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "wrenchThrown"), EntityWrenchThrown.class,
				"wrenchThrown", 57, RcMod.instance, 256, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "minegloveammo"), EntityMineGloveAmmo.class,
				"minegloveammo", 58, RcMod.instance, 256, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "decoygloveammo"), EntityDecoyGloveAmmo.class,
				"decoygloveammo", 59, RcMod.instance, 256, 1, false);


		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "swingshothook"), EntitySwingShotHook.class,
				"swingshothook", 60, RcMod.instance, 256, 1, false);
	
		EntityRegistry.registerModEntity(new ResourceLocation(RcMod.MODID, "suckcannonproj"), EntitySuckCannonProj.class,
				"suckcannonproj", 61, RcMod.instance, 256, 1, false);
	}

}
package com.gugu42.rcmod.common;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.common.entities.TntCrateEntity;
import com.gugu42.rcmod.common.entities.projectiles.BlasterProjectileEntity;
import com.gugu42.rcmod.common.entities.projectiles.HarbingerLaserEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RcEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RcMod.MOD_ID);

    public static final RegistryObject<EntityType<TntCrateEntity>> TNT_CRATE_ENTITY =
            ENTITIES.register("tnt_crate_entity",
                    () -> EntityType.Builder.<TntCrateEntity>create(TntCrateEntity::new, EntityClassification.MISC)
                            .setTrackingRange(64)
                            .setUpdateInterval(10)
                            .build("tntcrateentity"));

    // Projectiles
    public static final RegistryObject<EntityType<BlasterProjectileEntity>> BLASTER_PROJECTILE =
            ENTITIES.register("blaster_projectile_entity",
                    () -> EntityType.Builder.<BlasterProjectileEntity>create(BlasterProjectileEntity::new, EntityClassification.MISC)
                            .setTrackingRange(64)
                            .setUpdateInterval(10)
                            .build("blaster_projectile"));

    public static final RegistryObject<EntityType<HarbingerLaserEntity>> HARBINGER_LASER_ENTITY =
            ENTITIES.register("harbinger_laser_entity",
                    () -> EntityType.Builder.<HarbingerLaserEntity>create(HarbingerLaserEntity::new, EntityClassification.MISC)
                            .setTrackingRange(64)
                            .setUpdateInterval(10)
                            .build("harbinger_laser"));
}

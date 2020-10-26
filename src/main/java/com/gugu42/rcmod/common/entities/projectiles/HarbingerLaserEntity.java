package com.gugu42.rcmod.common.entities.projectiles;

import com.gugu42.rcmod.common.RcEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.network.NetworkHooks;

public class HarbingerLaserEntity extends Entity {
    public HarbingerLaserEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public HarbingerLaserEntity(World world, PlayerEntity player) {
        super(RcEntities.HARBINGER_LASER_ENTITY.get(), world);
    }

    @Override
    public void tick() {
        super.tick();

//        if (this.ticksExisted > 60)
//            this.remove();
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

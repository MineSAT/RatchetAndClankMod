package com.gugu42.rcmod.common.entities.projectiles;

import com.gugu42.rcmod.common.RcBlocks;
import com.gugu42.rcmod.common.RcEntities;
import com.gugu42.rcmod.common.entities.RcProjectileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BlasterProjectileEntity extends RcProjectileEntity {

    private static final int LIFETIME = 70;

    public BlasterProjectileEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BlasterProjectileEntity(World worldIn) {
        super(RcEntities.BLASTER_PROJECTILE.get(), worldIn);
    }

    public BlasterProjectileEntity(World world, PlayerEntity player) {
        super(RcEntities.BLASTER_PROJECTILE.get(), player, world);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.world.isRemote && this.ticksExisted > LIFETIME) {
            this.setDead();
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result instanceof EntityRayTraceResult) {
            EntityRayTraceResult entityResult = (EntityRayTraceResult)result;
            entityResult.getEntity().attackEntityFrom(DamageSource.causeThornsDamage(this), 2);
//            int dmg = RcMod.config.get("weapon_damage", "blaster", 4).getInt();
//            result.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)this.getThrower()), dmg);
        } else if (result instanceof BlockRayTraceResult) {
            BlockRayTraceResult blockResult = (BlockRayTraceResult)result;
            // Break crates, ignite TNT crates
            BlockState blockHit = this.world.getBlockState(blockResult.getPos());
            if (blockHit.getBlock() == RcBlocks.CRATE_BLOCK.get()
                || blockHit.getBlock() == RcBlocks.AMMO_CRATE_BLOCK.get()
                || blockHit.getBlock() == RcBlocks.TNT_CRATE_BLOCK.get()) {
                this.world.destroyBlock(blockResult.getPos(), true, this);
            }
        }

        if (!this.world.isRemote)
            this.setDead();
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.00F;
    }
}
